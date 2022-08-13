package com.agendaeletro.project.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Scheduling implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date initialDate;
	private Date finalDate;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name = "classroom_id")
	private Classroom classroom;
	
	@ManyToMany
	@JoinTable(name = "equipments_schedulings", 
	joinColumns = @JoinColumn(name = "scheduling_id"),
	inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private List<Equipment> equipments = new ArrayList<>();

	
	public Scheduling() {
	}

	public Scheduling(Long id, Date initialDate, Date finalDate, Teacher teacher, Classroom classroom) {
		this.id = id;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.teacher = teacher;
		this.classroom = classroom;
	}

	@Override
	public String toString() {
		return String.format(
				"Scheduling: id=%s | initialDate=%s | finalDate=%s | teacher=%s | classroom=%s | equipment=%s", id,
				initialDate, finalDate, teacher, classroom, Arrays.asList(equipments));
	}

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

}
