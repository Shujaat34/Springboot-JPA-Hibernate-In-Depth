package com.bukhari.jpa.hiberante.inheritance.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

	
	private BigDecimal hourlyWage;

	public PartTimeEmployee() {

	}

	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}

	@Override
	public String toString() {
		return super.toString() + " PartTimeEmployee [hourlyWage=" + hourlyWage + "]";
	}

}
