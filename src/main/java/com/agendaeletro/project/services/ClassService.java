package com.agendaeletro.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendaeletro.project.entities.Class;
import com.agendaeletro.project.repositories.ClassRepository;

@Service
public class ClassService {
    
    @Autowired
    private ClassRepository classRepository;


    public List<Class> queryAll(){
        return classRepository.findAll();
    }

    public Class insert(Class c){
        return classRepository.save(c);
    }

    public void delete(Long id){
        classRepository.deleteById(id);
    }

    public Class update(Class c, Long id){
        Class entity = classRepository.getReferenceById(id);
        updateData(c, entity);
        return classRepository.save(entity);
    }

    private void updateData(Class c, Class entity){
        if (c.getName() != null){
            entity.setName(c.getName());
        }
    }

}
