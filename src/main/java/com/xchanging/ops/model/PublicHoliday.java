package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the public_holidays database table.
 * 
 */
@Entity
@Table(name="public_holidays")
@NamedQuery(name="PublicHoliday.findAll", query="SELECT p FROM PublicHoliday p")
public class PublicHoliday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int day;

	private String description;

	private int month;

	private String name;

	private int year;

	public PublicHoliday() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}