package com.agendaeletro.project.entities;

import java.io.Serializable;
import java.util.Objects;

public class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 A classe Equipamento representa um equipamento que representar os equipamentos
	 a serem agendados, ele implementa a interface Serializable para ser possível que
	 ele seja transformado para cadeia de bytes e trafegar na rede
	 */
	
	//Atributos
	private Long id;
	private String name;
	private String description;
	private Integer quantity;
	
	public Equipment() {}

	public Equipment(Long id, String name, String description, Integer quantity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
	}
	
	//Getters e Setters
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
