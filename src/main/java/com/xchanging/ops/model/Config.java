package com.xchanging.ops.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the config database table.
 * 
 */
@Entity
@Table(name="config")
@NamedQuery(name="Config.findAll", query="SELECT c FROM Config c")
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String code;

	private String value;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", key=" + code + ", value=" + value + "]";
	}

}