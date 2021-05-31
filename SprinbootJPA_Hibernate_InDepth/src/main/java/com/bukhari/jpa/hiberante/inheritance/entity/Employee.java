package com.bukhari.jpa.hiberante.inheritance.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
// There is no need to define this because it is a by default strategy applied but for good approach we do it
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Emp_Type")
public abstract class Employee {
	
	//if I use @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) this will create a separate table for each concrete class
	//and It will not create a table for the abstract class which is the Employee class but create two tables for PartTime and FullTime classes


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	public Employee() {
		
	}

	public Employee(String name) {
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
	
	

}
