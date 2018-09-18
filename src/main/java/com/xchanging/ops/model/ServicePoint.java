package com.xchanging.ops.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the service_points database table.
 * 
 */
@Entity
@Table(name="service_points")
@NamedQueries(
		{@NamedQuery(name="ServicePoint.findAll", query="SELECT s FROM ServicePoint s"),
				@NamedQuery(name="ServicePoint.findServicePoint", query="SELECT sum(s.point) FROM ServicePoint s where s.user =:user and s.credited =:credited")			
	}
	)  

public class ServicePoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private boolean credited;
	
	private int type;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn(name="createdby")
	private User createdby;

	public User getCreatedby() {
		return createdby;
	}

	public void setCreatedby(User createdby) {
		this.createdby = createdby;
	}

	private String description;

	private Integer point;
	
	private Integer kbid;
	
	private boolean kbapproved;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;

	@ManyToOne
	@JoinColumn(name="updatedby")
	private User updatedby;

	@ManyToOne
	@JoinColumn(name="user")
	private User user;

	public ServicePoint() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}


	public boolean getCredited() {
		return this.credited;
	}

	public void setCredited(boolean credited) {
		this.credited = credited;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPoint() {
		return this.point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public User getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(User updatedby) {
		this.updatedby = updatedby;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getKbid() {
		return kbid;
	}

	public void setKbid(Integer kbid) {
		this.kbid = kbid;
	}

	public boolean isKbapproved() {
		return kbapproved;
	}

	public void setKbapproved(boolean kbapproved) {
		this.kbapproved = kbapproved;
	}

	public ServicePoint(Integer id, Date created, boolean credited, int type,
			User createdby, String description, Integer point, Integer kbid,
			boolean kbapproved, Date updated, User updatedby, User user) {
		super();
		this.id = id;
		this.created = created;
		this.credited = credited;
		this.type = type;
		this.createdby = createdby;
		this.description = description;
		this.point = point;
		this.kbid = kbid;
		this.kbapproved = kbapproved;
		this.updated = updated;
		this.updatedby = updatedby;
		this.user = user;
	}



}