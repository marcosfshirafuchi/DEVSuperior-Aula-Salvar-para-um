package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.PersonDTO;
import com.devsuperior.aula.dto.PersonDepartmentDTO;
import com.devsuperior.aula.entities.Department;
import com.devsuperior.aula.entities.Person;
import com.devsuperior.aula.repositories.DepartmentRepository;
import com.devsuperior.aula.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    //Fazer a injeção de dependencia do repository
    @Autowired
    private PersonRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public PersonDepartmentDTO insert(PersonDepartmentDTO dto){

        //Criar e instanciar a entidade Person
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());
        //Vai instanciado um objeto gerenciado pela JPA
        Department dept = departmentRepository.getReferenceById(dto.getDepartment().getId());
        //Department dept = new Department();
        //dept.setId(dto.getDepartment().getId());

        // Associar person ao department
        entity.setDepartment(dept);

        entity = repository.save(entity);

        return new PersonDepartmentDTO(entity);
    }

    //Sobrecarga é método com mesmo nome, mas com parametros diferentes
    public PersonDTO insert(PersonDTO dto){

        //Criar e instanciar a entidade Person
        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());
        //Vai instanciado um objeto gerenciado pela JPA
        //Department dept = departmentRepository.getReferenceById(dto.getDepartmentId());
        Department dept = new Department();
        dept.setId(dto.getDepartmentId());

        // Associar person ao department
        entity.setDepartment(dept);

        entity = repository.save(entity);

        return new PersonDTO(entity);
    }
}
