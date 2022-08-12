package com.agendaeletro.project.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Professor implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 A classe professor representa um professor que irá navegar pelo site
	 ele implementa a interface Serializable para ser possível que ele seja
	 transformado para cadeia de bytes e trafegar na rede
	 */
	
	//Atributos
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private String role;

	
	//construtor padrão
	public Professor() {}

	//Sobrecarga de construtor usando todos os atributos
	public Professor(Long id, String name, String email, String password, String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	
	@Override
	public String toString() {
		return String.format("Professor: id=%s | name=%s | email=%s | password=%s | role=%s", id, name, email, password,
				role);
	}

	//Getters e Seters (para pegar ou atribuir atributos)
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	//Método hashcode para comparar objtos usando seu codigo Hash (mais rapido porem menos preciso)
	@Override
	public int hashCode() {
		return Objects.hash(email, name, password);
	}

	//Método equals para comparar objetos (mais lento porém mais preciso)
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}

}
