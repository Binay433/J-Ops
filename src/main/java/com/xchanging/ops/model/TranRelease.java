package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tran_release database table.
 * 
 */
@Entity
@Table(name="tran_release")
@NamedQuery(name="TranRelease.findAll", query="SELECT t FROM TranRelease t")
public class TranRelease implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name="createdby")
	private User createdby;

	private Timestamp createddate;

	private String managername;

	private String name;
	
	private short status;

	private String rcmleadname;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date releasedate;

	private String summary;
	
	@Transient
	private List<RelDoc> docs;
	
	
	public TranRelease() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(User createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreateddate() {
		return this.createddate;
	}

	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	public String getManagername() {
		return this.managername;
	}

	public void setManagername(String managername) {
		this.managername = managername;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRcmleadname() {
		return this.rcmleadname;
	}

	public void setRcmleadname(String rcmleadname) {
		this.rcmleadname = rcmleadname;
	}

	public Date getReleasedate() {
		return this.releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public short getStatus() {
		return status;
	}
	

	public void setStatus(short status) {
		this.status = status;
	}

	public List<RelDoc> getDocs() {
		return docs;
	}
	

	public void setDocs(List<RelDoc> docs) {
		this.docs = docs;
	}
	
	
	

}