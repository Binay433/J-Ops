package com.xchanging.ops.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the environment database table.
 * 
 */
@Entity
@NamedQuery(name="Environment.findAll", query="SELECT e FROM Environment e")
public class Environment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	public Environment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Environment [id=" + id + ", name=" + name + "]";
	}

	
	
}