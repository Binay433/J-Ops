package com.xchanging.ops.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.springframework.web.multipart.MultipartFile;

/**
 * The persistent class for the knowledge_base database table.
 * 
 */
@Entity
@Indexed
@Table(name="knowledge_base")
@NamedQuery(name="KnowledgeBase.findAll", query="SELECT k FROM KnowledgeBase k")
public class KnowledgeBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String description;

	 @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String keywords;
	 
	 private String solution;

	 @Temporal(TemporalType.TIMESTAMP)
	 private Date created;
	 
	 @ManyToOne
	 @JoinColumn(name="createdby")
	 private User createdby;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date lastupdated;
	 
	 @ManyToOne
	 @JoinColumn(name="updatedby")
	 private User updatedby;
	 
	 private boolean approved;
	 
	 private boolean spadded;
	 
	public boolean isSpadded() {
		return spadded;
	}

	public void setSpadded(boolean spadded) {
		this.spadded = spadded;
	}

	@Transient
	ArrayList<MultipartFile> files;
	
	@Transient
	ArrayList<OpsDocument> documents;


	public KnowledgeBase() {
		super();
	}

	public KnowledgeBase(Integer id, String description, String keywords,
			String solution, Date created, User createdby, Date lastupdated,
			User updatedby, ArrayList<MultipartFile> files,
			ArrayList<OpsDocument> documents,boolean approved) {
		super();
		this.id = id;
		this.description = description;
		this.keywords = keywords;
		this.solution = solution;
		this.created = created;
		this.createdby = createdby;
		this.lastupdated = lastupdated;
		this.updatedby = updatedby;
		this.files = files;
		this.documents = documents;
		this.approved=approved;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getCreatedby() {
		return createdby;
	}

	public void setCreatedby(User createdby) {
		this.createdby = createdby;
	}

	public Date getUpdated() {
		return lastupdated;
	}

	public void setUpdated(Date updated) {
		this.lastupdated = updated;
	}

	public User getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(User updatedby) {
		this.updatedby = updatedby;
	}

	public Date getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Date lastupdated) {
		this.lastupdated = lastupdated;
	}

	public ArrayList<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<MultipartFile> files) {
		this.files = files;
	}

	
	public ArrayList<OpsDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<OpsDocument> documents) {
		this.documents = documents;
	}

	
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "KnowledgeBase [id=" + id + ", description=" + description
				+ ", keywords=" + keywords + ", solution=" + solution
				+ ", created=" + created + ", createdby=" + createdby
				+ ", lastupdated=" + lastupdated + ", updatedby=" + updatedby
				+ ", files=" + files + " approved=" + approved + "]";
	}

}