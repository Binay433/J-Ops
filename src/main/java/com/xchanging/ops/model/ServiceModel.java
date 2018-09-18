package com.xchanging.ops.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * The persistent class for the ops_service database table.
 * 
 */
@Entity
@Table(name="ops_service")
@NamedQuery(name="ServiceModel.findAll", query="SELECT s FROM ServiceModel s")
public class ServiceModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	private String brief;
	
	@NotBlank
	@Column(name="BUSINESS_OWNER")
	private String businessOwner;

	@NotBlank
	@Column(name="HRS_END")
	private String hrsEnd;

	@NotBlank
	@Column(name="HRS_START")
	private String hrsStart;

	@NotBlank
	private String name;

	@NotBlank
	@Column(name="SERVICE_OWNER")
	private String serviceOwner;

	@NotBlank
	@Column(name="SUPPORT_BY")
	private String supportBy;

	@NotBlank
	@Column(name="SYSTEM_MANAGER")
	private String systemManager;
	
	@Column(name="DOC_ID")
	private Integer docId;
	
	@Transient
	int changecount;

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;
	
	@Transient
	MultipartFile file;
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	//bi-directional many-to-one association to ServiceComponent
	@Transient
	@OneToMany(mappedBy="serviceModel")
	 @OrderBy("name ASC")
	private List<ServiceComponent> serviceComponents;
	
	
	@Transient
	@OneToMany(mappedBy="serviceModel")
	private List<EmergencyChange> changes;

	//bi-directional many-to-one association to ServiceSla
	@Transient
	@OneToMany(mappedBy="serviceModel")
	private List<ServiceSla> serviceSlas;

	public ServiceModel() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getBusinessOwner() {
		return this.businessOwner;
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}

	public String getHrsEnd() {
		return this.hrsEnd;
	}

	public void setHrsEnd(String hrsEnd) {
		this.hrsEnd = hrsEnd;
	}

	public String getHrsStart() {
		return this.hrsStart;
	}

	public void setHrsStart(String hrsStart) {
		this.hrsStart = hrsStart;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceOwner() {
		return this.serviceOwner;
	}

	public void setServiceOwner(String serviceOwner) {
		this.serviceOwner = serviceOwner;
	}

	public String getSupportBy() {
		return this.supportBy;
	}

	public void setSupportBy(String supportBy) {
		this.supportBy = supportBy;
	}

	public String getSystemManager() {
		return this.systemManager;
	}

	public void setSystemManager(String systemManager) {
		this.systemManager = systemManager;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<ServiceComponent> getServiceComponents() {
		return this.serviceComponents;
	}

	public void setServiceComponents(List<ServiceComponent> serviceComponents) {
		this.serviceComponents = serviceComponents;
	}

	public ServiceComponent addServiceComponent(ServiceComponent serviceComponent) {
		getServiceComponents().add(serviceComponent);
		serviceComponent.setServiceModel(this);

		return serviceComponent;
	}

	public ServiceComponent removeServiceComponent(ServiceComponent serviceComponent) {
		getServiceComponents().remove(serviceComponent);
		serviceComponent.setServiceModel(null);

		return serviceComponent;
	}

	public List<ServiceSla> getServiceSlas() {
		return this.serviceSlas;
	}

	public void setServiceSlas(List<ServiceSla> serviceSlas) {
		this.serviceSlas = serviceSlas;
	}

	public ServiceSla addServiceSla(ServiceSla serviceSla) {
		getServiceSlas().add(serviceSla);
		serviceSla.setServiceModel(this);

		return serviceSla;
	}

	public ServiceSla removeServiceSla(ServiceSla serviceSla) {
		getServiceSlas().remove(serviceSla);
		serviceSla.setServiceModel(null);

		return serviceSla;
	}

	public List<EmergencyChange> getChanges() {
		return changes;
	}

	public void setChanges(List<EmergencyChange> changes) {
		this.changes = changes;
	}

	public int getChangecount() {
		return changecount;
	}

	public void setChangecount(int changecount) {
		this.changecount = changecount;
	}

	@Override
	public String toString() {
		return "ServiceModel [id=" + id + ", brief=" + brief
				+ ", businessOwner=" + businessOwner + ", hrsEnd=" + hrsEnd
				+ ", hrsStart=" + hrsStart + ", name=" + name
				+ ", serviceOwner=" + serviceOwner + ", supportBy=" + supportBy
				+ ", systemManager=" + systemManager + ", docId=" + docId
				+ "]";
	}

	


	
	


}