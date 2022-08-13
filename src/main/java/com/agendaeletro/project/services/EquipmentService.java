package com.agendaeletro.project.services;

import java.util.List;

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
}
