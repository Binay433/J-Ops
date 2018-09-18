package com.xchanging.ops.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


/**
 * The persistent class for the health_checks database table.
 * 
 */
@Entity
@Table(name="health_checks")
public class HealthCheck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(name="check_time")
	private String checkTime;

	@Column(name="DOC_ID")
	private Integer docId;

	@Column(name="email_cc")
	private String emailCc;

	@Min(1)
	@Column(name="email_type")
	private Short emailType;
	
	@Column(name="email_enabled")
	private boolean emailEnabled;

	@NotBlank
	@Column(name="email_to")
	private String emailTo;

	@NotBlank
	private String name;

	@NotBlank
	private String subject;
	
	public Short getEmailType() {
		return emailType;
	}

	public void setEmailType(Short emailType) {
		this.emailType = emailType;
	}
	
	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "health_check_services", 
             joinColumns = { @JoinColumn(name = "hc_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "service_id" ) })
	 @OrderBy(value="name")
	private Set<ServiceModel> services = new HashSet<ServiceModel>();
	
	
	
	
	@Transient
	MultipartFile file;
	

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public HealthCheck() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getDocId() {
		return this.docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getEmailCc() {
		return this.emailCc;
	}

	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}

	public boolean getEmailEnabled() {
		return this.emailEnabled;
	}

	public void setEmailEnabled(boolean emailEnabled) {
		this.emailEnabled = emailEnabled;
	}

	public String getEmailTo() {
		return this.emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Set<ServiceModel> getServices() {
		return services;
	}

	public void setServices(Set<ServiceModel> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "HealthCheck [id=" + id + ", checkTime=" + checkTime
				+ ", docId=" + docId + ", emailCc=" + emailCc + ", emailType="
				+ emailType + ", emailEnabled=" + emailEnabled + ", emailTo="
				+ emailTo + ", name=" + name + ", subject=" + subject
				+ ", services=" + services + ", file=" + file + "]";
	}

	
	
	
	
	

	

}