package com.xchanging.ops.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the service_component_health database table.
 * 
 */
@Entity
@Table(name="service_component_health")
@NamedQuery(name="ServiceComponentHealth.findAll", query="SELECT s FROM ServiceComponentHealth s")
public class ServiceComponentHealth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="execution_time")
	private Date executionTime;

	@Column(name="issue_ref")
	private String issueRef;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="recording_time")
	private Date recordingTime;

	private int status;

	//bi-directional many-to-one association to ServiceComponent
	@ManyToOne
	@JoinColumn(name="component_id")
	private ServiceComponent serviceComponent;

	public ServiceComponentHealth() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getExecutionTime() {
		return this.executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public String getIssueRef() {
		return this.issueRef;
	}

	public void setIssueRef(String issueRef) {
		this.issueRef = issueRef;
	}

	public Date getRecordingTime() {
		return this.recordingTime;
	}

	public void setRecordingTime(Date recordingTime) {
		this.recordingTime = recordingTime;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ServiceComponent getServiceComponent() {
		return this.serviceComponent;
	}

	public void setServiceComponent(ServiceComponent serviceComponent) {
		this.serviceComponent = serviceComponent;
	}

}