package com.bukhari.jpa.hiberante.inheritance.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class FullTimeEmployee extends Employee{
	
	
	private BigDecimal salary; 


	public FullTimeEmployee() {
		
	} 
	
	public FullTimeEmployee(String name , BigDecimal salary) {
		super(name);
		this.salary = salary;
	}

	@Override
	public String toString() {
		return super.toString()+" FullTimeEmployee [salary=" + salary + "]";
	}

	
}
