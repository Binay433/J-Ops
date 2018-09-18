package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rel_proj_comp database table.
 * 
 */
@Entity
@Table(name="rel_comp")
@NamedQuery(name="ReljComp.findAll", query="SELECT r FROM RelComp r")
public class RelComp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="build_date")
	private Date buildDate;

	@Column(name="build_version")
	private String buildVersion;

	@Column(name="change_summary")
	private String changeSummary;

	@Column(name="craeted_date")
	private Timestamp craetedDate;

	@ManyToOne
	@JoinColumn(name="created_by")
	private User createdBy;

	
	private String name;

	@Column(name="rel_id")
	private int relId;
	
	
	@Transient
	private List<RelDoc> docs;

	public RelComp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBuildDate() {
		return this.buildDate;
	}

	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}

	public String getBuildVersion() {
		return this.buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

	public String getChangeSummary() {
		return this.changeSummary;
	}

	public void setChangeSummary(String changeSummary) {
		this.changeSummary = changeSummary;
	}

	public Timestamp getCraetedDate() {
		return this.craetedDate;
	}

	public void setCraetedDate(Timestamp craetedDate) {
		this.craetedDate = craetedDate;
	}

	public User getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public int getRelId() {
		return relId;
	}
	

	public void setRelId(int relId) {
		this.relId = relId;
	}
	

	public List<RelDoc> getDocs() {
		return docs;
	}
	

	public void setDocs(List<RelDoc> docs) {
		this.docs = docs;
	}
	

	

}