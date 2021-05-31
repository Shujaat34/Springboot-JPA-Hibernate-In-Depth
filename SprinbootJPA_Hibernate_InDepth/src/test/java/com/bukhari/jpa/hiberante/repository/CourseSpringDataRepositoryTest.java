package com.bukhari.jpa.hiberante.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bukhari.jpa.hiberante.entity.Course;
import com.bukhari.jpa.hiberante.springdata_repository.CourseSpringDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseSpringDataRepositoryTest {
	@Autowired
	CourseSpringDataRepository repository;
	
	@Test
	public void testFindById() {
		Optional<Course> optional = repository.findById(1001L);
		assertTrue(optional.isPresent());
		
	}
	
	@Test
	public void testFindById_() {
		Optional<Course> optional = repository.findById(1401L);
		assertFalse(optional.isPresent());
		
	}
	
	
}
