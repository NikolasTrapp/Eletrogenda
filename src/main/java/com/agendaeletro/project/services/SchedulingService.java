package com.agendaeletro.project.services;

import java.util.List;

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
}
