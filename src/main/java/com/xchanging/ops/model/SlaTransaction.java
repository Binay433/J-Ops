package com.xchanging.ops.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * The persistent class for the sla_transaction database table.
 * 
 */
@Entity
@Table(name="sla_transaction")
@NamedQueries(
		{@NamedQuery(name = "SlaTransaction.findAll", query="SELECT h FROM SlaTransaction h"),
		@NamedQuery(name = "SlaTransaction.findRowsDateWise", query="SELECT h FROM SlaTransaction h where h.date >= :fromdate and h.date <= :todate"
		+ " and h.serviceSla=:serviceSla")
	})
public class SlaTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	
	private String comments;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date date;

	@Column(name="passed_record")
	private double passedRecord;

	private Double percent;

	@Column(name="total_record")
	private double totalRecord;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_on")
	private Date updatedOn;

	//bi-directional many-to-one association to ServiceSla
	@ManyToOne
	@JoinColumn(name="sla_id")
	private ServiceSla serviceSla;

	//bi-directional many-to-one association to AppUser
	@ManyToOne
	@JoinColumn(name="user_id")
	private User appUser;

	public SlaTransaction() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPassedRecord() {
		return this.passedRecord;
	}

	public void setPassedRecord(double passedRecord) {
		this.passedRecord = passedRecord;
	}

	public Double getPercent() {
		if(this.percent==null){
			this.percent=0.0;	
		}
		return this.percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public double getTotalRecord() {
		return this.totalRecord;
	}

	public void setTotalRecord(double totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public ServiceSla getServiceSla() {
		return this.serviceSla;
	}

	public void setServiceSla(ServiceSla serviceSla) {
		this.serviceSla = serviceSla;
	}

	public User getAppUser() {
		return this.appUser;
	}

	public void setAppUser(User appUser) {
		this.appUser = appUser;
	}

	@Override
	public String toString() {
		return "SlaTransaction [id=" + id + ", comments=" + comments
				+ ", date=" + date + ", passedRecord=" + passedRecord
				+ ", percent=" + percent + ", totalRecord=" + totalRecord
				+ ", updatedOn=" + updatedOn + ", serviceSla=" + serviceSla
				+ ", appUser=" + appUser + "]";
	}

	
	
	
}