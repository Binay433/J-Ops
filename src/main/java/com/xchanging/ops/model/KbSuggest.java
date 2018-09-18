package com.xchanging.ops.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 * The persistent class for the kb_suggest database table.
 * 
 */
@Entity
@Indexed
@Table(name = "kb_suggest")
@NamedQuery(name = "KbSuggest.findAll", query = "SELECT k FROM KbSuggest k")
public class KbSuggest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String description;
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String keyword;

	private short status;
	private Timestamp created_date;
	Integer updated_by;
	Integer created_by;

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	@Override
	public String toString() {
		return "KbSuggest [id=" + id + ", description=" + description + ", keyword=" + keyword + ", status=" + status
				+ ", created_date=" + created_date + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Timestamp getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

	public KbSuggest() {
	}

}