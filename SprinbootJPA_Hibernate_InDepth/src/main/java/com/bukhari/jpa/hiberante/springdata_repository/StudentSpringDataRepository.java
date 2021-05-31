package com.bukhari.jpa.hiberante.springdata_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bukhari.jpa.hiberante.entity.Student;

@RepositoryRestResource(path="studs")
public interface StudentSpringDataRepository extends JpaRepository<Student, Long>{
	List<Student> findByName(String name);
}
