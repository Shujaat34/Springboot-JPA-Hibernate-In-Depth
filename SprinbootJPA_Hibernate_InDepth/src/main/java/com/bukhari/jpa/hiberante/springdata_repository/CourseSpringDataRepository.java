package com.bukhari.jpa.hiberante.springdata_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.bukhari.jpa.hiberante.entity.Course;

@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
	// Defining our own custom Methods

	List<Course> findByName(String name);

	// How many Course are with this Name
	List<Course> countByName(String name);

	// find By Name and Order By Id
	List<Course> findByNameOrderByIdDesc(String name);

	// Delete by Name
	List<Course> deleteByName(String name);

	// JPQL query
	@Query("Select c from Course c where name like :name")
	List<Course> courseWithJPQL(String name);

	// Native Query
	@Query(value="Select * From Course where name =:name and is_deleted=0 ",nativeQuery=true)
	List<Object[]> courseWithNative(String name);
	
	//Named Query
	@Query(name="fetch_all")
	List<Course> namedQuery();

}
