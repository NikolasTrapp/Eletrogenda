package com.nikolastrapp.agendaeletro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nikolastrapp.agendaeletro.entities.Equipment;
import com.nikolastrapp.agendaeletro.entities.Scheduling;
import com.nikolastrapp.agendaeletro.repositories.SchedulingReporitory;
import com.nikolastrapp.agendaeletro.services.exceptions.DatabaseException;
import com.nikolastrapp.agendaeletro.services.exceptions.ResourceNotFoundException;

@Service
public class SchedulingService {
	/*
	 * Esta classe guarda as funções que realizam as operações do banco de dados
	 * que são chamadas pela camada de recursos
	 */

	@Autowired // Injeção de dependencia automático
	private SchedulingReporitory schedulingReporitory;

	public List<Scheduling> queryAll() {
		return schedulingReporitory.findAll();
	}

	public Scheduling queryById(Long id) {
		Optional<Scheduling> obj = schedulingReporitory.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Scheduling insert(Scheduling scheduling) {
		if (!scheduling.compareTime()) {
			System.out.println("oi");
			throw new DatabaseException("Invalid date format.");
		}
		System.out.println("linguiça");
		System.out.println(scheduling);
		return schedulingReporitory.save(scheduling);
	}

	public void delete(Long id) {
		try {
			schedulingReporitory.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public Scheduling update(Long id, Scheduling scheduling) {
		try {
			Scheduling entity = schedulingReporitory.getReferenceById(id);
			updateData(entity, scheduling);
			return schedulingReporitory.save(entity);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Scheduling entity, Scheduling scheduling) {
		if (scheduling.getInitialDate() != null) {
			entity.setInitialDate(scheduling.getInitialDate());
		}
		if (scheduling.getFinalDate() != null) {
			entity.setFinalDate(scheduling.getFinalDate());
		}
		if (scheduling.getTeacher() != null) {
			entity.setTeacher(scheduling.getTeacher());
		}
		if (scheduling.getClassroom() != null) {
			entity.setClassroom(scheduling.getClassroom());
		}
		if (!scheduling.getEquipment().isEmpty()) {
			entity.clearEquipments();
			for (Equipment e : scheduling.getEquipment()) {
				entity.addEquipment(e);
			}
		}
	}
}
