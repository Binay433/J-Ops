package com.xchanging.ops.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * The persistent class for the service_component database table.
 * 
 */
@Entity
@Table(name="service_component")
@NamedQuery(name="ServiceComponent.findAll", query="SELECT s FROM ServiceComponent s")
public class ServiceComponent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String name;
	
	@Transient
	MultipartFile file;
	
	@Column(name="DOC_ID")
	private Integer docId;
	
	@NotEmpty
	@Column(name="component_description")
	private String componentDescription;
	
	@Column(name="current_version")
	private String currentVersion;
	

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getComponentDescription() {
		return componentDescription;
	}

	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/*//bi-directional many-to-one association to KnowledgeBase
	@OneToMany(mappedBy="serviceComponent")
	private List<KnowledgeBase> knowledgeBases;*/

	//bi-directional many-to-one association to ServiceModel
	@ManyToOne
	@JoinColumn(name="service_id")
	private ServiceModel serviceModel;

	//bi-directional many-to-one association to ServiceComponentHealth
/*	@OneToMany(mappedBy="serviceComponent")
	private List<ServiceComponentHealth> serviceComponentHealths;*/

	public ServiceComponent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public List<KnowledgeBase> getKnowledgeBases() {
		return this.knowledgeBases;
	}

	public void setKnowledgeBases(List<KnowledgeBase> knowledgeBases) {
		this.knowledgeBases = knowledgeBases;
	}*/

	/*public KnowledgeBase addKnowledgeBas(KnowledgeBase knowledgeBas) {
		getKnowledgeBases().add(knowledgeBas);
		knowledgeBas.setServiceComponent(this);

		return knowledgeBas;
	}

	public KnowledgeBase removeKnowledgeBas(KnowledgeBase knowledgeBas) {
		getKnowledgeBases().remove(knowledgeBas);
		knowledgeBas.setServiceComponent(null);

		return knowledgeBas;
	}*/

	public ServiceModel getServiceModel() {
		return this.serviceModel;
	}

	public void setServiceModel(ServiceModel opsService) {
		this.serviceModel = opsService;
	}

/*	public List<ServiceComponentHealth> getServiceComponentHealths() {
		return this.serviceComponentHealths;
	}

	public void setServiceComponentHealths(List<ServiceComponentHealth> serviceComponentHealths) {
		this.serviceComponentHealths = serviceComponentHealths;
	}*/

	/*public ServiceComponentHealth addServiceComponentHealth(ServiceComponentHealth serviceComponentHealth) {
		getServiceComponentHealths().add(serviceComponentHealth);
		serviceComponentHealth.setServiceComponent(this);

		return serviceComponentHealth;
	}

	public ServiceComponentHealth removeServiceComponentHealth(ServiceComponentHealth serviceComponentHealth) {
		getServiceComponentHealths().remove(serviceComponentHealth);
		serviceComponentHealth.setServiceComponent(null);

		return serviceComponentHealth;
	}*/

	@Override
	public String toString() {
		return "ServiceComponent [id=" + id + ", name=" + name + ", file="
				+ file + ", docId=" + docId + ", componentDescription="
				+ componentDescription + ", currentVersion=" + currentVersion
				+ ", knowledgeBases=" + serviceModel + "]";
	}

	
	
	
}