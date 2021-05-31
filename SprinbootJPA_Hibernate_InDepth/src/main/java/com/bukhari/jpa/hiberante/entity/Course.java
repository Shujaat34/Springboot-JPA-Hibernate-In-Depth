package com.bukhari.jpa.hiberante.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQueries(value = { 
		@NamedQuery(name = "fetch_by_like", query = "select c from Course c where c.name like '%JPA'"),
		@NamedQuery(name = "fetch_all", query = "select c from Course c") })
@Entity
/**
 * We apply Cacheable Feature on to that Entity where data is not changing like
 * (insert, update) and fetching of the same data is frequent So we assume
 * course Entity is not changing
 */
@Cacheable
/**
 * Two Types of Deletes Soft Delete and Hard Delete in Soft We just change the
 * column to true means making a row inactive and in Hard Delete we delete the
 * row completely
 * 
 * In Soft Delete we keep the history of the Row by changing its col to false
 * making it invisible for the user
 */
//On Every delete we will run this Query
@SQLDelete(sql = "update Course c set c.is_deleted=true where c.id=?")
@Where(clause="is_Deleted=false") // This condition will be used in fetch queries
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false) // This col can not be null
	private String name;

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
	private List<Review> reviews = new ArrayList<Review>();

	// A course can have multiple students
	@ManyToMany(mappedBy = "courses")
	@JsonIgnore
	private List<Student> students = new ArrayList<Student>();

	private boolean isDeleted;
	
	@PreRemove // Whenever a delete Query will run this thing be done first
	private void preRemove() {
		isDeleted = true;
	}

	public Course() {

	}

	public Course(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public void removeReview(Review review) {
		reviews.remove(review);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}
	
	

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}

}
