package com.agendaeletro.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendaeletro.project.entities.Scheduling;
import com.agendaeletro.project.repositories.SchedulingReporitory;

@Service
public class SchedulingService {

	@Autowired
	private SchedulingReporitory schedulingReporitory;

	public List<Scheduling> queryAll() {
		return schedulingReporitory.findAll();
	}

	public Scheduling queryById(Long id) {
		Optional<Scheduling> obj = schedulingReporitory.findById(id);
		return obj.get();
	}

	public Scheduling insert(Scheduling scheduling) {
		return schedulingReporitory.save(scheduling);
	}
	
	public void delete(Long id) {
		schedulingReporitory.deleteById(id);;
	}
}
