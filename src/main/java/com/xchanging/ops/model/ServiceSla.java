package com.xchanging.ops.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


/**
 * The persistent class for the service_sla database table.
 * 
 */
@Entity
@Table(name="service_sla")
@NamedQuery(name="ServiceSla.findAll", query="SELECT s FROM ServiceSla s")
public class ServiceSla implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String description;

	@Column(name="DOC_ID")
	private Integer docId;

	@NotEmpty
	private String name;

	@NotNull
	@Column(name="target_amber")
	private double targetAmber;

	@NotNull
	@Column(name="target_green")
	private double targetGreen;

	
	@Transient
	MultipartFile file;
	
	@NotNull
	int sla_type;
	
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	//bi-directional many-to-one association to OpsService
	@ManyToOne
	@JoinColumn(name="service_id")
	private ServiceModel serviceModel;

	//bi-directional many-to-one association to SlaTransaction
	@OneToMany(mappedBy="serviceSla")
	private List<SlaTransaction> slaTransactions;

	public ServiceSla() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDocId() {
		return this.docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTargetAmber() {
		return this.targetAmber;
	}

	public void setTargetAmber(double targetAmber) {
		this.targetAmber = targetAmber;
	}

	public double getTargetGreen() {
		return this.targetGreen;
	}

	public void setTargetGreen(double targetGreen) {
		this.targetGreen = targetGreen;
	}


	public ServiceModel getServiceModel() {
		return this.serviceModel;
	}

	public void setServiceModel(ServiceModel opsService) {
		this.serviceModel = opsService;
	}

	public List<SlaTransaction> getSlaTransactions() {
		return this.slaTransactions;
	}

	public void setSlaTransactions(List<SlaTransaction> slaTransactions) {
		this.slaTransactions = slaTransactions;
	}

	public SlaTransaction addSlaTransaction(SlaTransaction slaTransaction) {
		getSlaTransactions().add(slaTransaction);
		slaTransaction.setServiceSla(this);

		return slaTransaction;
	}

	public SlaTransaction removeSlaTransaction(SlaTransaction slaTransaction) {
		getSlaTransactions().remove(slaTransaction);
		slaTransaction.setServiceSla(null);

		return slaTransaction;
	}
	
	
	public int getSla_type() {
		return sla_type;
	}

	public void setSla_type(int sla_type) {
		this.sla_type = sla_type;
	}

	@Override
	public String toString() {
		return "ServiceSla [id=" + id + ", description=" + description
				+ ", docId=" + docId + ", name=" + name + ", targetAmber="
				+ targetAmber + ", targetGreen=" + targetGreen  + ", serviceModel=" + serviceModel
				+ "]";
	}

	

}