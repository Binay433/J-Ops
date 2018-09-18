package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the service_sla_tracking database table.
 * 
 */
@Entity
@Table(name="service_sla_tracking")
@NamedQuery(name="ServiceSlaTracking.findAll", query="SELECT s FROM ServiceSlaTracking s")
public class ServiceSlaTracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private String item;

	private int status;
	
	@Column(name="record_total")
	private Integer recordTotal;
	
	@Column(name="record_pass")
	private Integer recordPass;

	//bi-directional many-to-one association to KnowledgeBase
	@ManyToOne
	@JoinColumn(name="kb_id")
	private KnowledgeBase knowledgeBase;

	//bi-directional many-to-one association to ServiceSla
	@ManyToOne
	@JoinColumn(name="sla_id")
	private ServiceSla serviceSla;

	public ServiceSlaTracking() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public KnowledgeBase getKnowledgeBase() {
		return this.knowledgeBase;
	}

	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}

	public ServiceSla getServiceSla() {
		return this.serviceSla;
	}

	public void setServiceSla(ServiceSla serviceSla) {
		this.serviceSla = serviceSla;
	}

	public Integer getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(Integer recordTotal) {
		this.recordTotal = recordTotal;
	}

	public Integer getRecordPass() {
		return recordPass;
	}

	public void setRecordPass(Integer recordPass) {
		this.recordPass = recordPass;
	}

	@Override
	public String toString() {
		return "ServiceSlaTracking [id=" + id + ", date=" + date + ", item="
				+ item + ", status=" + status + ", recordTotal=" + recordTotal
				+ ", recordPass=" + recordPass + ", knowledgeBase="
				+ knowledgeBase + ", serviceSla=" + serviceSla + "]";
	}
	
	

}