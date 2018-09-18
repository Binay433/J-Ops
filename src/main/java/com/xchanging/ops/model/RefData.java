package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ref_data database table.
 * 
 */
@Entity
@Table(name="ref_data")
@NamedQueries(
		{@NamedQuery(name="RefData.findByCode", query="SELECT r FROM RefData r Where r.code = :code"),
		@NamedQuery(name="RefData.findAll", query="SELECT r FROM RefData r")
	})
public class RefData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String code;

	private String description;

	private String key;

	private String value;

	public RefData() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}