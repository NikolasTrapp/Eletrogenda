package com.nikolastrapp.agendaeletro.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nikolastrapp.agendaeletro.entities.enums.Color;

@Entity // Esta anotação define que esta classe é uma entidade
public class Classroom implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * A classe classroom representa uma sala de aula, ela implementa a interface
	 * Serializable para poder ser convertida em bytes e trafegar na rede, esta
	 * classe cria uma tabela classroom no banco de dados, com as colunas
	 * correspondendo aos seus atributos
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Anotações para definir que este atributo é um id e é gerado											// automáticamente
	private Long id;
	@Column(nullable = false, length = 50, unique = true) // Estas anotações definem NOT NULL e limite de caracteres
	private String name;

	@JsonIgnore // Esta anotação serve para impedir o loop infinito de chamada de objetos
	@OneToMany(mappedBy = "classroom") // Definindo relação um para muitos
	private List<Scheduling> schedulings = new ArrayList<>();
	
	@Column(nullable = false, length = 50, unique = true)
	private Color color;

	// Construtor vazio
	public Classroom() {
	}

	// Sobrecarga de construtor com parametros carregados
	public Classroom(Long id, String name, Color color) {
		this.id = id;
		this.name = name;
		this.color = color;
	}

	// Método to string para imprimir o objeto em forma de string
	@Override
	public String toString() {
		return String.format("Classroom: id=%s | name=%s | color=%s", id, name, color);
	}

	// Métodos getters e setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void addScheduling(Scheduling scheduling) {
		this.schedulings.add(scheduling);
	}

	public List<Scheduling> getSchedulings() {
		return schedulings;
	}

	// Métodos hashCode e equals para comprar objetos se necessário
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
		Classroom other = (Classroom) obj;
		return Objects.equals(id, other.id);
	}
}
