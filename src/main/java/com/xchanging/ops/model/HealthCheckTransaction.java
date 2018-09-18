package com.xchanging.ops.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.Date;


/**
 * The persistent class for the health_check_transaction database table.
 * 
 */
@Entity
@Table(name="health_check_transaction")
@NamedQueries(
		{@NamedQuery(name = "HealthCheckTransaction.findAll", query="SELECT h FROM HealthCheckTransaction h"),
		@NamedQuery(name = "HealthCheckTransaction.findByHcIdAndDate", query="SELECT h FROM HealthCheckTransaction h where h.entryDate > :entryDate and h.healthCheck = :healthCheck"),
		@NamedQuery(name = "HealthCheckTransaction.findByDateIdAndEntry", query="SELECT h FROM HealthCheckTransaction h where h.entryDate = :entryDate and h.healthCheck = :healthCheck"),
		@NamedQuery(name = "HealthCheckTransaction.findByServiceAndDate", query="SELECT h FROM HealthCheckTransaction h where h.entryDate = :entryDate and h.serviceModel = :serviceModel"),
		@NamedQuery(name = "HealthCheckTransaction.findWeekTrans", query="SELECT h FROM HealthCheckTransaction h where h.entryDate >= :fromDate and h.entryDate <= :toDate"),
		@NamedQuery(name = "HealthCheckTransaction.findRowsDateWise", query="SELECT h FROM HealthCheckTransaction h where h.entryDate >= :fromDate and h.entryDate <= :toDate"
				+ " and h.healthCheck=:healthCheck and h.serviceModel=:serviceModel and h.component=:component")		
					
	}
		
	)  
public class HealthCheckTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="check_by")
	private User checkBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="check_time")
	private Date checkTime;

	private String comments;
	
	private Date entryDate;

	
	
	@Column(name="entry_id")
	private Integer entryId;
	
	
	@ManyToOne
	@JoinColumn(name="component_id")
	private ServiceComponent component;

	@Column(name="kb_id")
	private Integer kbId;

	@ManyToOne
	@JoinColumn(name="service_id")
	private ServiceModel serviceModel;

	@NotNull @Min(1) @Max(3)
	private Integer status;

	//bi-directional many-to-one association to HealthCheck
	@ManyToOne
	@JoinColumn(name="hc_id")
	private HealthCheck healthCheck;

	public HealthCheckTransaction() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getCheckBy() {
		return this.checkBy;
	}
	

	public Integer getEntryId() {
		return entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	public void setCheckBy(User checkBy) {
		this.checkBy = checkBy;
	}

	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	public Integer getKbId() {
		return this.kbId;
	}

	public void setKbId(Integer kbId) {
		this.kbId = kbId;
	}


	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public HealthCheck getHealthCheck() {
		return this.healthCheck;
	}

	public void setHealthCheck(HealthCheck healthCheck) {
		this.healthCheck = healthCheck;
	}

	public ServiceComponent getComponent() {
		return component;
	}

	public void setComponent(ServiceComponent component) {
		this.component = component;
	}

	public ServiceModel getServiceModel() {
		return serviceModel;
	}

	public void setServiceModel(ServiceModel serviceModel) {
		this.serviceModel = serviceModel;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@Override
	public String toString() {
		return "HealthCheckTransaction [id=" + id + ", checkBy=" + checkBy
				+ ", checkTime=" + checkTime + ", comments=" + comments
				+ ", entryDate=" + entryDate + ", component=" + component
				+ ", kbId=" + kbId + ", serviceModel=" + serviceModel
				+ ", status=" + status + ", healthCheck=" + healthCheck + "]";
	}

	
	
	
	

}