package com.bukhari.jpa.hiberante;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.bukhari.jpa.hiberante.entity.Course;
import com.bukhari.jpa.hiberante.repository.CourseRepository;

@SpringBootTest
class ApplicationTests {

	@Autowired
	CourseRepository repository;

	@Test
	public void findByIdTest() {
		Course course1 = repository.findById(1001L);
		assertEquals("Java Fundamentals", course1.getName());

	}

	@Test
	@DirtiesContext // This will just check this method and reset the data means no changes are done in the database
	public void deteleTest() {
		// After Deleting we get Null
		repository.deleteById(1003L);
		assertNull(repository.findById(1003L));
	}
	
	@Test
	@DirtiesContext 
	public void updateTest() {
		Course course2 = repository.findById(1002L);
		assertEquals("Data structures in Java", course2.getName());
		course2.setName("Data structures in C++");
		repository.save(course2);
	}
	
	@Test
	public void saveTest() {
		Course course2 = new Course();
		course2.setName("No SQL Database");
		repository.save(course2);
		assertEquals("No SQL Database", course2.getName());
		
	}

}
