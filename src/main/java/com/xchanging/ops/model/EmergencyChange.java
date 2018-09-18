package com.xchanging.ops.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * The persistent class for the emergency_changes database table.
 * 
 */
@Entity
@Table(name="emergency_changes")
@NamedQueries(
		{@NamedQuery(name="EmergencyChange.findTotalCount", query="SELECT max(e.id) FROM EmergencyChange e"),
		@NamedQuery(name="EmergencyChange.findAll", query="SELECT e FROM EmergencyChange e")
	})
public class EmergencyChange implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="backout_plan")
	private String backoutPlan;

	@Column(name="change_requestor")
	private String changeRequestor;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date changedate;

	private String communication;
	
	@Column(name="crq_ref")
	private String crqRef;

	@Column(name="change_environment")
	private String changeEnvironment;
	
	@Column(name="change_category")
	private String changeCategory;
	
	@Column(name="impact_assesment")
	private String impactAssesment;
	
	@Column(name="customer_affected")
	private String customerAffected;

	private String description;
	
	private String subjectline;

	@Column(name="email_cc")
	private String emailCc;

	@Column(name="email_to")
	private String emailTo;

	@Column(name="email_rcm")
	private String rcmMail;
	
	private String emergency_No;

	@Column(name="incidence_ref")
	private String incidenceRef;

	@Column(name="rcm_lead")
	private String rcmLead;
	
	@ManyToOne
	@JoinColumn(name="request_by")
	private User requestBy;

//	@Column(name="request_by")
//	private java.math.BigInteger requestBy;

	@Temporal(TemporalType.DATE)
	@Column(name="request_date")
	private Date requestDate;

	@Column(name="risk_mitigation")
	private String riskMitigation;

	private String status;
	
	private String remarks;

	@Column(name="system_manager")
	private String systemManager;

	@Column(name="urgency_reason")
	private String urgencyReason;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "change_services", 
             joinColumns = { @JoinColumn(name = "change_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "service_id") })
	private Set<ServiceModel> services = new HashSet<ServiceModel>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "change_environment", 
             joinColumns = { @JoinColumn(name = "change_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "environment_id") })
	private Set<Environment> environments = new HashSet<Environment>();
	
	
	@Transient
	private String hour;
	
	@Transient
	private String minute;
	
	@Column(name="change_time")
	private String changeTime;
	
	@OneToMany(mappedBy="emergencyChage")
	private List<EmergencyChangeComponent> emergencyChangeComponent;
	
	@Transient
	private ArrayList<String> hourList = null;
	
	@Transient
	private ArrayList<String> minutesList = null;
	
	@Transient
	private boolean saveOnly;
	
	@Transient
	private List<RefData> statusList;
	
	@Transient
	private Date latestProdDate;
	
	@Transient
	private String dateColor;
	
	private Integer critical;
	
	private Integer bloker;
	
	@Column(name="defect_count")
	private Integer defectCount;
	
	
	public String getDateColor() {
		return dateColor;
	}

	public void setDateColor(String dateColor) {
		this.dateColor = dateColor;
	}

	public List<RefData> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<RefData> statusList) {
		this.statusList = statusList;
	}

	public boolean isSaveOnly() {
		return saveOnly;
	}

	public void setSaveOnly(boolean saveOnly) {
		this.saveOnly = saveOnly;
	}

	@Column(name="additional_cc")
	private String additionalCc;
	
	
	public String getAdditionalCc() {
		return additionalCc;
	}

	public void setAdditionalCc(String additionalCc) {
		this.additionalCc = additionalCc;
	}

	public String getRcmMail() {
		return rcmMail;
	}

	public void setRcmMail(String rcmMail) {
		this.rcmMail = rcmMail;
	}

	public ArrayList<String> getHourList() {
		return hourList;
	}

	public void setHourList(ArrayList<String> hourList) {
		this.hourList = hourList;
	}

	public ArrayList<String> getMinutesList() {
		return minutesList;
	}

	public void setMinutesList(ArrayList<String> minutesList) {
		this.minutesList = minutesList;
	}

	public List<EmergencyChangeComponent> getEmergencyChangeComponent() {
		return emergencyChangeComponent;
	}

	public void setEmergencyChangeComponent(List<EmergencyChangeComponent> emergencyChangeComponent) {
		this.emergencyChangeComponent = emergencyChangeComponent;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public User getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(User requestBy) {
		this.requestBy = requestBy;
	}

	

	public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getImpactAssesment() {
		return impactAssesment;
	}

	public void setImpactAssesment(String impactAssesment) {
		this.impactAssesment = impactAssesment;
	}

	public String getCustomerAffected() {
		return customerAffected;
	}

	public void setCustomerAffected(String customerAffected) {
		this.customerAffected = customerAffected;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public Set<ServiceModel> getServices() {
		return services;
	}

	public void setServices(Set<ServiceModel> services) {
		this.services = services;
	}

	public EmergencyChange() {
	}


	public String getBackoutPlan() {
		return this.backoutPlan;
	}

	public void setBackoutPlan(String backoutPlan) {
		this.backoutPlan = backoutPlan;
	}

	public String getChangeRequestor() {
		return this.changeRequestor;
	}

	public void setChangeRequestor(String changeRequestor) {
		this.changeRequestor = changeRequestor;
	}

	public Date getChangedate() {
		return this.changedate;
	}

	public void setChangedate(Date changedate) {
		this.changedate = changedate;
	}

	public String getCommunication() {
		return this.communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public String getCrqRef() {
		return this.crqRef;
	}

	public void setCrqRef(String crqRef) {
		this.crqRef = crqRef;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmailCc() {
		return this.emailCc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	public String getEmailTo() {
		return this.emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	
	

	public String getEmergency_No() {
		return emergency_No;
	}

	public void setEmergency_No(String emergency_No) {
		this.emergency_No = emergency_No;
	}

	public String getIncidenceRef() {
		return this.incidenceRef;
	}

	public void setIncidenceRef(String incidenceRef) {
		this.incidenceRef = incidenceRef;
	}

	public String getRcmLead() {
		return this.rcmLead;
	}

	public void setRcmLead(String rcmLead) {
		this.rcmLead = rcmLead;
	}

	public String getRiskMitigation() {
		return this.riskMitigation;
	}

	public void setRiskMitigation(String riskMitigation) {
		this.riskMitigation = riskMitigation;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSystemManager() {
		return this.systemManager;
	}

	public void setSystemManager(String systemManager) {
		this.systemManager = systemManager;
	}

	public String getUrgencyReason() {
		return this.urgencyReason;
	}

	public void setUrgencyReason(String urgencyReason) {
		this.urgencyReason = urgencyReason;
	}
	

	

	public String getChangeEnvironment() {
		return changeEnvironment;
	}

	public void setChangeEnvironment(String changeEnvironment) {
		this.changeEnvironment = changeEnvironment;
	}

	public String getChangeCategory() {
		return changeCategory;
	}

	public void setChangeCategory(String changeCategory) {
		this.changeCategory = changeCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<Environment> getEnvironments() {
		return environments;
	}

	public void setEnvironments(Set<Environment> environments) {
		this.environments = environments;
	}

	public String getSubjectline() {
		return subjectline;
	}

	public void setSubjectline(String subjectline) {
		this.subjectline = subjectline;
	}
	
	
	public Date getLatestProdDate() {
		return latestProdDate;
	}

	public void setLatestProdDate(Date latestProdDate) {
		this.latestProdDate = latestProdDate;
	}
	
	public Integer getCritical() {
		return critical;
	}

	public void setCritical(Integer critical) {
		this.critical = critical;
	}

	public Integer getBloker() {
		return bloker;
	}

	public void setBloker(Integer bloker) {
		this.bloker = bloker;
	}
	
	
	public Integer getDefectCount() {
		return defectCount;
	}

	public void setDefectCount(Integer defectCount) {
		this.defectCount = defectCount;
	}

	@Override
	public String toString() {
		return "EmergencyChange [id=" + id + ", backoutPlan=" + backoutPlan + ", changeRequestor=" + changeRequestor
				+ ", changedate=" + changedate + ", communication=" + communication + ", crqRef=" + crqRef
				+ ", changeEnvironment=" + changeEnvironment + ", changeCategory=" + changeCategory
				+ ", impactAssesment=" + impactAssesment + ", customerAffected=" + customerAffected + ", description="
				+ description + ", emailCc=" + emailCc + ", emailTo=" + emailTo + ", rcmMail=" + rcmMail
				+ ", emergency_No=" + emergency_No + ", incidenceRef=" + incidenceRef + ", rcmLead=" + rcmLead
				+ ", requestBy=" + requestBy + ", requestDate=" + requestDate + ", riskMitigation=" + riskMitigation
				+ ", status=" + status + ", remarks=" + remarks + ", systemManager=" + systemManager
				+ ", urgencyReason=" + urgencyReason + ", services=" + services + ", hour=" + hour + ", minute="
				+ minute + ", changeTime=" + changeTime + ", emergencyChangeComponent=" + emergencyChangeComponent
				+ ", hourList=" + hourList + ", minutesList=" + minutesList + ", saveOnly=" + saveOnly + ", statusList="
				+ statusList + ", additionalCc=" + additionalCc + "]";
	}

	
	
	

}