package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rel_project database table.
 * 
 */
@Entity
@Table(name="rel_project")
@NamedQuery(name="RelProject.findAll", query="SELECT r FROM RelProject r")
public class RelProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="craeted_date")
	private Timestamp craetedDate;

	@ManyToOne
	@JoinColumn(name="createdby")
	private User createdby;

	private String name;

	@Column(name="proj_manager")
	private String projManager;

	@Column(name="rcm_lead")
	private String rcmLead;

	@Column(name="rel_id")
	private int relId;

	private int status;

	private String summary;

	@Column(name="test_manager")
	private String testManager;
	
	@Transient
	private List<RelDoc> docs;

	public RelProject() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCraetedDate() {
		return this.craetedDate;
	}

	public void setCraetedDate(Timestamp craetedDate) {
		this.craetedDate = craetedDate;
	}

	public User getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(User createdby) {
		this.createdby = createdby;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjManager() {
		return this.projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	public String getRcmLead() {
		return this.rcmLead;
	}

	public void setRcmLead(String rcmLead) {
		this.rcmLead = rcmLead;
	}

	public int getRelId() {
		return this.relId;
	}

	public void setRelId(int relId) {
		this.relId = relId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTestManager() {
		return this.testManager;
	}

	public void setTestManager(String testManager) {
		this.testManager = testManager;
	}

	public List<RelDoc> getDocs() {
		return docs;
	}
	

	public void setDocs(List<RelDoc> docs) {
		this.docs = docs;
	}
	
	
	

}