package com.bukhari.jpa.hiberante.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bukhari.jpa.hiberante.entity.Course;

@Repository
public class CriteriaRepository {

	@Autowired
	EntityManager em;

	/**
	 * Note: The Criteria Query is not a professional way of querying the data from
	 * the database.
	 * 
	 * Using the Native Query and The JPQL Query is the best way to query the data.
	 * 
	 */

	public List<Course> criteriaQueryCourseList() {
		// Select c from Course c where name like %structures%. ==> JPQL Query

		// 1. Use Criteria Builder to create a criteria query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for the tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		// 3. Define predicates using criteria builder

		// 4. add predicate to the criteria Query

		// 5. build the TypedQuery using the enitity manager

		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> list = query.getResultList();
		return list;
	}

	public List<Course> criteriaQueryWithWhere() {
		// Select c from Course c. ==> JPQL Query

		// 1. Use Criteria Builder to create a criteria query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for the tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define predicates using criteria builder
		Predicate likeStructures = cb.like(courseRoot.get("name"), "%structures%"); // A course name which contains structures
		
		// 4. add predicate to the criteria Query
		cq.where(likeStructures);
		
		// 5. build the TypedQuery using the enitity manager
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> list = query.getResultList();
		return list;
	}
	
	public List<Course> criteriaQueryCoursesWithoutStudents() {
		// Select c from Course c where c.students is empty. ==> JPQL Query

		// 1. Use Criteria Builder to create a criteria query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for the tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define predicates using criteria builder
		Predicate noStudents = cb.isEmpty(courseRoot.get("students")); // A course which has empty students means no students
		
		// 4. add predicate to the criteria Query
		cq.where(noStudents);
		
		// 5. build the TypedQuery using the enitity manager
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> list = query.getResultList();
		return list;
	}
	
	public List<Course> criteriaQueryJoins() {
		// Select c from Course c join c.students s;  ==> JPQL Query

		// 1. Use Criteria Builder to create a criteria query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for the tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define predicates using criteria builder
		Join<Object,Object> noStudents = courseRoot.join("students"); // A course which has empty students means no students
		
		// 4. add predicate to the criteria Query
		
		// 5. build the TypedQuery using the enitity manager
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> list = query.getResultList();
		return list;
	}
	
	public List<Course> criteriaQueryLeftJoin() {
		// Select c from Course c join c.students s;  ==> JPQL Query

		// 1. Use Criteria Builder to create a criteria query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for the tables which are involved in the query
		Root<Course> courseRoot = cq.from(Course.class);
		
		// 3. Define predicates using criteria builder
		Join<Object,Object> noStudents = courseRoot.join("students",JoinType.LEFT); // A course which has empty students means no students
		
		// 4. add predicate to the criteria Query
		
		// 5. build the TypedQuery using the enitity manager
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		List<Course> list = query.getResultList();
		return list;
	}
}
