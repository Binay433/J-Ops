package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * The persistent class for the emergency_change_components database table.
 * 
 */
@Entity
@Table(name="emergency_change_components")
@NamedQueries(
		{@NamedQuery(name="EmergencyChangeComponent.findAll", query="SELECT e FROM EmergencyChangeComponent e"),
		@NamedQuery(name = "EmergencyChangeComponent.findbyEmergencyChangeId", query="SELECT e FROM EmergencyChangeComponent e where e.emergencyChage = :emergencyId")
	})
public class EmergencyChangeComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="dre_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dreDate;

	@Column(name="dre_time")
	private String dreTime;
	
	@Transient
	private String drehour;
	
	@Transient
	private String dreminute;

	@ManyToOne
	@JoinColumn(name="emergency_change_id")
	private EmergencyChange emergencyChage;

	@Temporal(TemporalType.DATE)
	@Column(name="prd_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date prdDate;

	@Column(name="prd_time")
	private String prdTime;
	
	@Transient
	private String prdhour;
	
	@Transient
	private String prdminute;

	@Column(name="system_component")
	private String systemComponent;

	@Column(name="system_package")
	private String systemPackage;

	@Temporal(TemporalType.DATE)
	@Column(name="trn_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date trnDate;

	@Column(name="trn_time")
	private String trnTime;
	
	@Transient
	private String trnhour;
	
	@Transient
	private String trnminute;

	@Temporal(TemporalType.DATE)
	@Column(name="tsa_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tsaDate;

	@Column(name="tsa_time")
	private String tsaTime;
	
	@Transient
	private String tsahour;
	
	@Transient
	private String tsaminute;

	@Temporal(TemporalType.DATE)
	@Column(name="tsb_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tsbDate;

	@Column(name="tsb_time")
	private String tsbTime;
	
	@Transient
	private String tsbhour;
	
	@Transient
	private String tsbminute;

	@Temporal(TemporalType.DATE)
	@Column(name="tsc_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tscDate;

	@Column(name="tsc_time")
	private String tscTime;
	
	
	@Transient
	private String tschour;
	
	@Transient
	private String tscminute;
	
	@Temporal(TemporalType.DATE)
	@Column(name="tsd_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tsdDate;

	@Column(name="tsd_time")
	private String tsdTime;
	
	@Transient
	private String tsdhour;
	
	@Transient
	private String tsdminute;
	
	@Transient
	private String hour;
	
	@Transient
	private String minute;
	
	

	public String getDrehour() {
		return drehour;
	}

	public void setDrehour(String drehour) {
		this.drehour = drehour;
	}

	public String getDreminute() {
		return dreminute;
	}

	public void setDreminute(String dreminute) {
		this.dreminute = dreminute;
	}

	public String getPrdhour() {
		return prdhour;
	}

	public void setPrdhour(String prdhour) {
		this.prdhour = prdhour;
	}

	public String getPrdminute() {
		return prdminute;
	}

	public void setPrdminute(String prdminute) {
		this.prdminute = prdminute;
	}

	public String getTrnhour() {
		return trnhour;
	}

	public void setTrnhour(String trnhour) {
		this.trnhour = trnhour;
	}

	public String getTrnminute() {
		return trnminute;
	}

	public void setTrnminute(String trnminute) {
		this.trnminute = trnminute;
	}

	public String getTsahour() {
		return tsahour;
	}

	public void setTsahour(String tsahour) {
		this.tsahour = tsahour;
	}

	public String getTsaminute() {
		return tsaminute;
	}

	public void setTsaminute(String tsaminute) {
		this.tsaminute = tsaminute;
	}

	public String getTsbhour() {
		return tsbhour;
	}

	public void setTsbhour(String tsbhour) {
		this.tsbhour = tsbhour;
	}

	public String getTsbminute() {
		return tsbminute;
	}

	public void setTsbminute(String tsbminute) {
		this.tsbminute = tsbminute;
	}

	public String getTschour() {
		return tschour;
	}

	public void setTschour(String tschour) {
		this.tschour = tschour;
	}

	public String getTscminute() {
		return tscminute;
	}

	public void setTscminute(String tscminute) {
		this.tscminute = tscminute;
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

	public EmergencyChangeComponent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDreDate() {
		return this.dreDate;
	}

	public void setDreDate(Date dreDate) {
		this.dreDate = dreDate;
	}

	public String getDreTime() {
		return this.dreTime;
	}

	public void setDreTime(String dreTime) {
		this.dreTime = dreTime;
	}

	

	public EmergencyChange getEmergencyChage() {
		return emergencyChage;
	}

	public void setEmergencyChage(EmergencyChange emergencyChage) {
		this.emergencyChage = emergencyChage;
	}

	public Date getPrdDate() {
		return this.prdDate;
	}

	public void setPrdDate(Date prdDate) {
		this.prdDate = prdDate;
	}

	public String getPrdTime() {
		return this.prdTime;
	}

	public void setPrdTime(String prdTime) {
		this.prdTime = prdTime;
	}

	public String getSystemComponent() {
		return this.systemComponent;
	}

	public void setSystemComponent(String systemComponent) {
		this.systemComponent = systemComponent;
	}

	public String getSystemPackage() {
		return this.systemPackage;
	}

	public void setSystemPackage(String systemPackage) {
		this.systemPackage = systemPackage;
	}

	public Date getTrnDate() {
		return this.trnDate;
	}

	public void setTrnDate(Date trnDate) {
		this.trnDate = trnDate;
	}

	public String getTrnTime() {
		return this.trnTime;
	}

	public void setTrnTime(String trnTime) {
		this.trnTime = trnTime;
	}

	public Date getTsaDate() {
		return this.tsaDate;
	}

	public void setTsaDate(Date tsaDate) {
		this.tsaDate = tsaDate;
	}

	public String getTsaTime() {
		return this.tsaTime;
	}

	public void setTsaTime(String tsaTime) {
		this.tsaTime = tsaTime;
	}

	public Date getTsbDate() {
		return this.tsbDate;
	}

	public void setTsbDate(Date tsbDate) {
		this.tsbDate = tsbDate;
	}

	public String getTsbTime() {
		return this.tsbTime;
	}

	public void setTsbTime(String tsbTime) {
		this.tsbTime = tsbTime;
	}

	public Date getTscDate() {
		return this.tscDate;
	}

	public void setTscDate(Date tscDate) {
		this.tscDate = tscDate;
	}

	public String getTscTime() {
		return this.tscTime;
	}

	public void setTscTime(String tscTime) {
		this.tscTime = tscTime;
	}
	
	

	public Date getTsdDate() {
		return tsdDate;
	}

	public void setTsdDate(Date tsdDate) {
		this.tsdDate = tsdDate;
	}

	public String getTsdTime() {
		return tsdTime;
	}

	public void setTsdTime(String tsdTime) {
		this.tsdTime = tsdTime;
	}
	
	

	public String getTsdhour() {
		return tsdhour;
	}

	public void setTsdhour(String tsdhour) {
		this.tsdhour = tsdhour;
	}

	public String getTsdminute() {
		return tsdminute;
	}

	public void setTsdminute(String tsdminute) {
		this.tsdminute = tsdminute;
	}

	@Override
	public String toString() {
		return "EmergencyChangeComponent [id=" + id + ", dreDate=" + dreDate + ", dreTime=" + dreTime
				+ ", emergencyChage=" + emergencyChage + ", prdDate=" + prdDate + ", prdTime=" + prdTime
				+ ", systemComponent=" + systemComponent + ", systemPackage=" + systemPackage + ", trnDate=" + trnDate
				+ ", trnTime=" + trnTime + ", tsaDate=" + tsaDate + ", tsaTime=" + tsaTime + ", tsbDate=" + tsbDate
				+ ", tsbTime=" + tsbTime + ", tscDate=" + tscDate + ", tscTime=" + tscTime + ", hour=" + hour
				+ ", minute=" + minute + "]";
	}
	
	
}