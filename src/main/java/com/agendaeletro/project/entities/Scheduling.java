package com.agendaeletro.project.entities;

import java.io.Serializable;
import java.util.Date;

public class Scheduling implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Date initialDate;
	private Date finalDate;
	private Professor professor;
	private Classroom classroom;
	private Equipment equipment;
	
	public Scheduling() {}

	public Scheduling(Date initialDate, Date finalDate, Professor professor, Classroom classroom, Equipment equipment) {
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.professor = professor;
		this.classroom = classroom;
		this.equipment = equipment;
	}

	@Override
	public String toString() {
		return String.format("Scheduling: initialDate=%s | finalDate=%s | professor=%s | classroom=%s | equipment=%s",
				initialDate, finalDate, professor, classroom, equipment);
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
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Classroom getClassroom() {
		return classroom;
	}
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	
	

}
