package com.agendaeletro.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendaeletro.project.entities.Equipment;
import com.agendaeletro.project.repositories.EquipmentRepository;

@Service
public class EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	public List<Equipment> queryAll() {
		return equipmentRepository.findAll();
	}

	public Equipment queryById(Long id) {
		Optional<Equipment> obj = equipmentRepository.findById(id);
		return obj.get();
	}

	public Equipment insert(Equipment equipment) {
		return equipmentRepository.save(equipment);
	}
	
	public void delete(Long id) {
		equipmentRepository.deleteById(id);;
	}
}
