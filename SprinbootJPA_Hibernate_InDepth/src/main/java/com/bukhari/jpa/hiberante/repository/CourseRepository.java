package com.bukhari.jpa.hiberante.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bukhari.jpa.hiberante.entity.Address;
import com.bukhari.jpa.hiberante.entity.Course;
import com.bukhari.jpa.hiberante.entity.Passport;
import com.bukhari.jpa.hiberante.entity.Review;
import com.bukhari.jpa.hiberante.entity.ReviewRating;
import com.bukhari.jpa.hiberante.entity.Student;

@Repository
@Transactional
public class CourseRepository {

	@Autowired
	EntityManager em;

	// find the course by Id
	public Course findById(Long id) {
		Course course = em.find(Course.class, id);
		return course;
	}

	public Course save(Course course) {
		// insert
		if (course.getId() == null) {
			em.persist(course);
		}
		// update
		else {
			course = em.merge(course);
		}
		course = em.merge(course);
		return course;
	}

	/**
	 * IF we are doing any changes in the data in the database that is what we deal
	 * with transactions.
	 * 
	 * Fetching the Data Does not need Transaction But Changing the Data i.e
	 * Save/Delete the data would need Transaction So we use
	 * 
	 * @Transactional in the repository
	 * 
	 *                Transaction means IF we want to do changes in the data in
	 *                database in a few steps. Either I want all the steps in which
	 *                changes are done to be successful or all the steps to be fail.
	 *                Like I want to transfer 100$ From my account to my Friends
	 *                account.
	 * 
	 *                So First we deduct 100 From my Account this would be relating
	 *                to a particular table in database And then add that amount to
	 *                my Friend's account and this would be relating to another
	 *                table in the database.
	 * 
	 *                So Either the amount is deducted and that is not sent so I
	 *                want to reverse the first change as well like my money should
	 *                come back.
	 * 
	 *                Simply In Transactions Either we want all steps to be
	 *                successful or if any step fails we will fail all the steps.
	 */

	public void deleteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		// It we wnat to save the data
		Course course = new Course("Python In Objects");
		em.persist(course);
		em.flush(); // Data is sent to database

		course.setName("Python In objects - updated1");
		em.flush(); // Data is sent to database

		Course course1 = new Course("Javascript is good");
		em.persist(course1);
		em.flush();

		em.detach(course1); // detached Code
//		em.clear(); we use detached or clear
		course1.setName("Javascript is good");

	}

	// =============== JPQL (Java Persistance Query Language) ================

	public List findAllJPQLRaw() {
		Query query = em.createQuery("select c from Course c");
		List list = query.getResultList();
		return list;
	}

	// These Queries are better
	public List<Course> findAllJPQLSpecific() {
		TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
		List<Course> list = query.getResultList();
		return list;
	}

	// Where clause
	public List<Course> findJPQLWhereClause() {
		TypedQuery<Course> query = em.createQuery("select c from Course c where c.name like '%JPA'", Course.class);
		List<Course> list = query.getResultList();
		return list;
	}

	// Named Queries
	public List<Course> jpqlNamedQuery() {
		TypedQuery<Course> query = em.createNamedQuery("fetch_by_like", Course.class);
		List<Course> list = query.getResultList();
		return list;
	}

	public List<Course> jpqlNamedQuery1() {
		TypedQuery<Course> query = em.createNamedQuery("fetch_all", Course.class);
		List<Course> list = query.getResultList();
		return list;
	}

	// Native Query
	public List<Course> nativeQuries() {
		Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
		List<Course> list = query.getResultList();
		return list;
	}
	
	public Course nativeQuriesWhereClause(Long id) {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id=?", Course.class);
		query.setParameter(1, id);
		Course course = (Course) query.getSingleResult();
		return course;
	}
	
	//This Query is More preferable in Native queris like we pass Named Parameter
	public Course nativeQuriesWhereClauseNameParamter(Long id) {
		Query query = em.createNativeQuery("SELECT * FROM COURSE WHERE id=:C_id", Course.class);
		query.setParameter("C_id", id);
		Course course = (Course) query.getSingleResult();
		return course;
	}
	
	// To do Mass update or mass insertion we prefer native queries
	public int nativeQuries_Insert() {
		Query query = em.createNativeQuery("insert into Course (id,name) VALUES "
				+ "	(?,?)", Course.class);
		query.setParameter(1, 1212L);
		query.setParameter(2, "Flutter Programming");
		int row = query.executeUpdate();
		return row;
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());
	public void addReviewsToCourse(Long course_id) {
		// Get the Course 1002
		// add two reviews to it and save it
		
		Course course = em.find(Course.class, course_id);
	
		Review review1 = new Review(ReviewRating.FIVE,"Amazing Course");
		Review review2 = new Review(ReviewRating.TWO,"Not So Good");
		
		course.addReview(review1);
		review1.setCourse(course);
		
		course.addReview(review2);
		review2.setCourse(course);
		
		//because we are just creating new reviews and attaching the id of existing course to the new reviews
		em.persist(review1);
		em.flush();
	
		em.persist(review2);	
		em.flush();
		
	}
	
	@Transactional
	public void getCourseAndAddReview() {
		Course c = em.find(Course.class,1004L);
		Review r = new Review(ReviewRating.FIVE, "Informative Course");
		
		em.persist(r);
		em.flush();
		
		Passport passport = new Passport("passport1");
		em.persist(passport);
		em.flush();
		
		Student s = new Student("Aslam", passport, new Address("line2","line3","Karach"));
		em.persist(s);
		em.flush();
		
		c.addStudent(s);
		c.addReview(r);
		
		r.setCourse(c);
		r.setStudent(s);
		
		em.persist(c);
		em.flush();
		
		
		

	}
	
	public void addReviewsToCourseGeneric(Long courseId, List<Review> reviews) {
		Course course = em.find(Course.class, courseId);
		
		for (Review review : reviews) {
			course.addReview(review);
			review.setCourse(course);
			em.persist(review);
		}
		
	}
	
	//get Student and Courses
	public void getStudentAndCourses(Long id) {
		Student student = em.find(Student.class, id);
		logger.info("Courses -> {}",student.getCourses());
	}
	
	public void getCourseWithStudents(Long id) {
		Course course = em.find(Course.class, id);
		logger.info("Students -> {}",course.getStudents());
	}
	
	public List<Review> getReviewsForACourse(Long course_id){
		Course course = em.find(Course.class, course_id);
		
//		logger.info("Course Reviews -> {} ",course.getReviews());
		return course.getReviews();
	}
	
	public void addStudentAndCourse(Student student,Course course) {
		Passport passport = new Passport("J35233");
		
		student.setPassport(passport);
		em.persist(passport);
		em.persist(student);
		em.persist(course);
		
		student.addCourse(course);
		course.addStudent(student);
		
		em.persist(student);
	}
	
	
	//list Courses which does not have students
	public List<Course> getCoursesWithoutStudents() {
		TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
		return query.getResultList();	
	}
	
	
	//get courses which have 2 or more students
	public List<Course> getCourseHaving2OrMoreStudents() {
		TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >=2",Course.class);
		return query.getResultList();
	}
	
	//get Courses which have students in increasing order
	public List<Course> getCoursesOrderByStudents(){
		// For descending order  => select c from Course c order by size(c.students) desc
		TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students)",Course.class);
		return query.getResultList();
	}

}
