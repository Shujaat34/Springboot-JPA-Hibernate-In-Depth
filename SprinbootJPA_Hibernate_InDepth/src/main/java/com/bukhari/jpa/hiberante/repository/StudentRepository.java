package com.bukhari.jpa.hiberante.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bukhari.jpa.hiberante.entity.Address;
import com.bukhari.jpa.hiberante.entity.Passport;
import com.bukhari.jpa.hiberante.entity.Student;

@Repository
public class StudentRepository {
	
	@Autowired
	EntityManager em;
	
	
	//Students who are not taking any course
	public List<Student> getStudentsWithoutCourse(){
		TypedQuery<Student> query = em.createQuery("select s from Student s where s.courses is empty",Student.class);
		return query.getResultList();
	}
	
	// Need students who have passport containing 34 in the passport number
	
	public List<Student> getStudentsByPassport(){
		//All in single line
		return em.createQuery("select s from Student s where s.passport.number like '%34%'",Student.class)
				.getResultList();
	}
	
	@Transactional
	public void createStudent() {
		
		Passport passport = new Passport("2214124");
		em.persist(passport);
		em.flush();
		
		Address addres = new Address("line1","line2","Larkana");
		
		Student s = new Student("Sheeraz",passport,addres);
		em.persist(s);
		em.flush();
	}
}
