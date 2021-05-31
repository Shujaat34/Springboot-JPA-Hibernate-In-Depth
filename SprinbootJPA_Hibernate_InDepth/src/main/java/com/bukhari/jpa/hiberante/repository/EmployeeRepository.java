package com.bukhari.jpa.hiberante.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bukhari.jpa.hiberante.inheritance.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {
	
	@Autowired
	EntityManager em;

	//Insert an employee
	public void insert(Employee employee) {
		em.persist(employee);
	}
	
	//fetch all employees
	public List<Employee> findAll(){
		TypedQuery<Employee> query = em.createQuery("Select e from Employee e",Employee.class);
		return query.getResultList();
	}
	//fetch all employees in one line
	public List<Employee> findAllEmps(){
		return em.createQuery("Select e from Employee e",Employee.class).getResultList();
	}
	
}
