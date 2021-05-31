package com.bukhari.jpa.hiberante.repository;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bukhari.jpa.hiberante.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {
	
	@Autowired
	CourseRepository repository;

	@Test
	public void courseRepositoryTest() {
		Course course1 = repository.findById(1001L);
		assertEquals("Java Fundamentals", course1.getName());
	
		Course course2 = repository.findById(1002L);
		assertEquals("Data structures in Java", course2.getName());
		
		//After Deleting we get Null
		repository.deleteById(1003L);
		assertNull(repository.findById(1003L));
		
		Course course = repository.findById(1002L);
		course.setName("Data Structures in C++");
		
		assertEquals("Data Structures in C++", course.getName());
	}

}
