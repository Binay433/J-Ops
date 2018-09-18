package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;


/**
 * The persistent class for the rel_docs database table.
 * 
 */
@Entity
@Table(name="rel_docs")
@NamedQuery(name="RelDoc.findAll", query="SELECT r FROM RelDoc r")
public class RelDoc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="craeted_date")
	private Date craetedDate;

	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;

	private String ext;

	private String location;

	private String name;
	
	@Transient
	MultipartFile docFile;
	
	@Column(name="parent_id")
	private int parentId;

	private int type;

	public RelDoc() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCraetedDate() {
		return this.craetedDate;
	}

	public void setCraetedDate(Date craetedDate) {
		this.craetedDate = craetedDate;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public MultipartFile getDocFile() {
		return docFile;
	}
	

	public void setDocFile(MultipartFile docFile) {
		this.docFile = docFile;
	}
	


	

}