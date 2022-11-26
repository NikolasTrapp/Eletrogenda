package com.nikolastrapp.agendaeletro.entities;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

@Entity // Anotação para definir que esta classe é uma entidade
public class Scheduling implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String TIME_ZONE = "Brazil/East";

	/*
	 * A classe Scheduling representa um agendamento, ela implementa a interface
	 * Serializable para poder ser convertida em bytes e trafegar na rede, esta
	 * classe cria uma tabela scheduling no banco de dados, com as colunas
	 * correspondendo aos seus atributos
	 */

	@Id // Anotações para definir que este atributo é o id e tem auto increment
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = TIME_ZONE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, length = 254)
	private Date initialDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = TIME_ZONE)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, length = 254)
	private Date finalDate;

	@ManyToOne // Defininfo a relação de muitos para um com a tabela teacher
	@JoinColumn(name = "teacher_id") // criando uma coluna teacher_id
	private Teacher teacher;

	@ManyToOne // Defininfo a relação de muitos para um com a tabela classroom
	@JoinColumn(name = "classroom_id") // criando uma coluna classroom_id
	private Classroom classroom;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class group;

	@ManyToMany // Defininfo a relação de muitos para muits com a tabela equipment
	@JoinTable(name = "equipments_schedulings", // criando tabela equipments_schedulings
			joinColumns = @JoinColumn(name = "scheduling_id"), // criando coluna scheduling_id
			inverseJoinColumns = @JoinColumn(name = "equipment_id")) // criando coluna equipment_id
	private List<Equipment> equipments = new ArrayList<>();

	// Construtor vazio
	public Scheduling() {
	}

	// Sobrecarga de construtor com parâmetros
	public Scheduling(Long id, Date initialDate, Date finalDate, Teacher teacher, Classroom classroom, Class group,
			List<Equipment> equipments) {
		this.id = id;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.teacher = teacher;
		this.classroom = classroom;
		this.group = group;
		addEquipments(equipments);
	}

	private void addEquipments(List<Equipment> equipments) {
		this.equipments.addAll(equipments);
	}

	// Método toString para retornar o agendamento em formato string
	@Override
	public String toString() {
		return String.format(
				"Scheduling: id=%s | initialDate=%s | finalDate=%s | teacher=%s | classroom=%s | class=%s | equipment=%s",
				id, initialDate, finalDate, teacher, classroom, group, equipments.toString());
	}

	// Getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public List<Equipment> getEquipment() {
		return equipments;
	}

	public void addEquipment(Equipment equipment) {
		this.equipments.add(equipment);
	}

	public void clearEquipments() {
		this.equipments.clear();
	}

	public Class getGroup() {
		return group;
	}

	public void setGroup(Class group) {
		this.group = group;
	}

	public int getMinutes(Date date) {
		// Retorna quantos minutos tem um horario de uma data
		Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
		calendar.setTime(date);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		return hours * 60 + minutes;
	}

	public boolean checkPeriod() {
		// Verifica se a hora está num periodo só True se sim, False se não
		boolean morning = (getMinutes(initialDate) >= 480 && getMinutes(finalDate) <= 720);
		boolean afternoon = (getMinutes(initialDate) >= 810 && getMinutes(finalDate) <= 1050);
		return morning || afternoon;
	}

	public boolean checkIfTimeHas45Min() {
		// Verifica se a diferença entre as horas tem mais de 45 minutos True se sim,
		// False se não
		int diference = (int) TimeUnit.MILLISECONDS.toMinutes(finalDate.getTime() - initialDate.getTime());
		return diference >= 45;
	}

	public boolean isSunday() {
		// Verifica se a data é um domingo True se não, False se sim
		Calendar gc = new GregorianCalendar();
		gc.setTime(initialDate);
		return gc.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;
	}

	public boolean isGreater() {
		// Verifica se data inicial é depois do dia do cadastro True se sim, False se
		// não
		Date today = new Date();
		return initialDate.after(today);
	}

	public boolean checkCompatibility() {
		boolean a = checkPeriod();
		boolean b = checkIfTimeHas45Min();
		boolean c = isSunday();
		boolean d = isGreater();
		return a && b && c && d;
	}

	// Métodos hashCode e equals para comprar objetos caso necessário
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scheduling other = (Scheduling) obj;
		return Objects.equals(id, other.id);
	}

	public boolean isBetween(Date firstDate, Date secondDate) {
		// Esta função verifica se a data inicial ou a data final estão entre uma data
		// inicial/final
		// de um agendamento que já consta no banco de dados returna True se sim, False
		// se não.
		boolean initialCheck = initialDate.compareTo(firstDate) >= 0 && initialDate.compareTo(secondDate) <= 0;
		boolean finalCheck = finalDate.compareTo(firstDate) >= 0 && finalDate.compareTo(secondDate) <= 0;
		System.out.println("initialCheck: " + (initialDate.compareTo(firstDate) >= 0 && initialDate.compareTo(secondDate) <= 0));
		System.out.println("finalCheck: " + (finalDate.compareTo(firstDate) >= 0 && finalDate.compareTo(secondDate) <= 0));
		
		System.out.println("Is between: " + (initialCheck || finalCheck));
		return initialCheck || finalCheck;
	}

}
