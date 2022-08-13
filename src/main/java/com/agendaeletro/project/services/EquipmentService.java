package com.agendaeletro.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.agendaeletro.project.entities.Equipment;
import com.agendaeletro.project.entities.Teacher;
import com.agendaeletro.project.repositories.EquipmentRepository;
import com.agendaeletro.project.services.exceptions.ResourceNotFoundException;

@Service
public class EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	public List<Equipment> queryAll() {
		return equipmentRepository.findAll();
	}

	public Equipment queryById(Long id) {
		Optional<Equipment> obj = equipmentRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Equipment insert(Equipment equipment) {
		return equipmentRepository.save(equipment);
	}

	public void delete(Long id) {
		try {
			equipmentRepository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public Equipment update(Long id, Equipment equipment) {
		Equipment entity = equipmentRepository.getReferenceById(id);
		updateData(entity, equipment);
		return equipmentRepository.save(entity);
	}

	private void updateData(Equipment entity, Equipment equipment) {
		if (equipment.getName() != null) {
			entity.setName(equipment.getName());
		}
		if (equipment.getQuantity() != null) {
			entity.setQuantity(equipment.getQuantity());
		}
		if (equipment.getDescription() != null) {
			entity.setDescription(equipment.getDescription());
		}
	}
}
