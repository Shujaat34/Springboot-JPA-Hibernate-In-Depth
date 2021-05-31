package com.bukhari.jpa.hiberante;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.bukhari.jpa.hiberante.entity.Course;
import com.bukhari.jpa.hiberante.entity.Review;
import com.bukhari.jpa.hiberante.entity.ReviewRating;
import com.bukhari.jpa.hiberante.repository.CourseRepository;
import com.bukhari.jpa.hiberante.repository.EmployeeRepository;
import com.bukhari.jpa.hiberante.repository.StudentRepository;
import com.bukhari.jpa.hiberante.springdata_repository.CourseSpringDataRepository;
import com.bukhari.jpa.hiberante.springdata_repository.StudentSpringDataRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CourseSpringDataRepository jpaRepository;
	
	@Autowired
	StudentSpringDataRepository jpaRepoStudent;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		logger.info("Course findById -> {}",courseRepository.findById(1001L));
//		courseRepository.deleteById(1001L);
//		
//		logger.info("New Course Added -> {}",courseRepository.save(new Course("Microservices")));

		// Updating a Course
//		Course course = courseRepository.findById(1002L);
//		course.setName("Data Structures in C++");
//		logger.info("Course updated -> {}",courseRepository.save(course));
//		courseRepository.playWithEntityManager();

//		logger.info("Course List JPQLRaw ->{}",courseRepository.findAllJPQLRaw());
//		logger.info("Course List JOQL Specific ->{}",courseRepository.findAllJPQLSpecific());
//		logger.info("Course List By Where clause ->{}",courseRepository.findJPQLWhereClause());

//		logger.info("Native Qurries ->{}",courseRepository.nativeQuries());

//		logger.info("Native Qurries with Where ->{}",courseRepository.nativeQuriesWhereClause(1002L));

//		logger.info("Native Quries with Where Named Parameter -> {}",courseRepository.nativeQuriesWhereClauseNameParamter(1002L));
//		
//		logger.info("Native Quries Row Insertion -> {}",courseRepository.nativeQuries_Insert());

//		courseRepository.addReviewsToCourse(1003L);

//		List<Review> list = new ArrayList<Review>();
//		list.add(new Review("5","Amazingly Awesome"));
//		list.add(new Review("5","Great Good"));
//		courseRepository.addReviewsToCourseGeneric(1003L,list);
//		
//		courseRepository.getStudentAndCourses(2001L);
//		courseRepository.getCourseWithStudents(1001L);

//		courseRepository.addStudentAndCourse(new Student("Jameel"), new Course("Microservices"));

//		courseRepository.getReviewsForACourse(1002L);
//		logger.info("Course Reviews -> {} ",courseRepository.getReviewsForACourse(1002L));

//		employeeRepository.insert(new PartTimeEmployee("Javed",new BigDecimal("50"))); 
//		employeeRepository.insert(new FullTimeEmployee("Mukesh",new BigDecimal("1000"))); 
//	
//		logger.info("Employees -> {} ",employeeRepository.findAll());

//		courseRepository.getCoursesWithoutStudents();

		// Courses which are not taken by any students
//		logger.info("####Courses {} ",courseRepository.getCoursesWithoutStudents());

//		logger.info("###Student Not Taking any course {}",studentRepository.getStudentsWithoutCourse());

//		logger.info("###Course having 2 or more students{}",courseRepository.getCourseHaving2OrMoreStudents());

//		logger.info("###Course with order by students{}", courseRepository.getCoursesOrderByStudents());
//		logger.info("###Students containing 34 in their Passport",studentRepository.getStudentsByPassport());

		/***
		 * SpringDataJpa Testing
		 */

		// Sorting all the courses by name in descending order;

//		Sort sort = Sort.by(Sort.Direction.DESC, "id");
//
//		List<Course> list = jpaRepository.findAll(sort);
//
//		logger.info("All Courses{}", list);
		
//		pagination();
		
//		logger.info("Find By Name {}",jpaRepository.findByName("Data Science"));

//		courseRepository.deleteById(1008L);
//	
//		logger.info("Deleted {}",jpaRepository.findByName("Data Science"));
		
//		studentRepository.createStudent();
//
//		logger.info("Student is {}",jpaRepoStudent.findByName("Sheeraz"));
		
		courseRepository.getCourseAndAddReview();
	}

	public void pagination(){
		// In a fetch Query how much records i want on the first Page
		// It starts with 0 which means first page and 3 means i need 3 records
		PageRequest pageRequest = PageRequest.of(0, 3);
		Page <Course> firstPage = jpaRepository.findAll(pageRequest);
		
		logger.info("First Page {}",firstPage.getContent());
		
		// I can also get the second Page by
		
		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = jpaRepository.findAll(secondPageable);
		logger.info("Second Page {}",secondPage.getContent());
		
	}
	

}
