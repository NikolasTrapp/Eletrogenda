package com.agendaeletro.project.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Equipment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*
	 * A classe Equipment representa um equipamento, ela implementa a interface
	 * Serializable para poder ser convertida em bytes e trafegar na rede, esta
	 * classe cria uma tabela equipment no banco de dados, com as colunas
	 * correspondendo aos seus atributos
	 */

	@Id //definindo o atributo o qual corresponderá ao id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Long id;
	@Column(nullable = false, length = 100) // Estas anotações definem NOT NULL e limite de caracteres
	private String name;
	@Column(length = 150) // Esta anotação define o limite de caracteres
	private String description;
	@Column(nullable = false) // Esta anotaçõ define restrição NOT NULL
	private Integer quantity;

	@JsonIgnore // Esta anotação serve para impedir o loop infinito de chamada de objetos
	@ManyToMany(mappedBy = "equipments") // Defindo a relação muitos para muitos
	private List<Scheduling> schedulings = new ArrayList<>();

	//Construtor vazio
	public Equipment() {
	}

	//Construtor carregado com parâmetros
	public Equipment(Long id, String name, String description, Integer quantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}

	// Getters e Setters
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Scheduling> getSchedulings() {
		return schedulings;
	}

	public void addSchedulings(Scheduling scheduling) {
		this.schedulings.add(scheduling);
	}

	// Métodos hashCode e equals para comprar obejtos caso necessário
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
		Equipment other = (Equipment) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return String.format("Equipment: id=%s | name=%s | description=%s | quantity=%s", id, name, description,
				quantity);
	}

}
