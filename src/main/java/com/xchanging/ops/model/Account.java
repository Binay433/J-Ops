package com.xchanging.ops.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to ServiceModel
	@OneToMany(mappedBy="account")
	private List<ServiceModel> opsServices;

	public Account() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ServiceModel> getOpsServices() {
		return this.opsServices;
	}

	public void setOpsServices(List<ServiceModel> opsServices) {
		this.opsServices = opsServices;
	}

	public ServiceModel addOpsService(ServiceModel opsService) {
		getOpsServices().add(opsService);
		opsService.setAccount(this);

		return opsService;
	}

	public ServiceModel removeOpsService(ServiceModel opsService) {
		getOpsServices().remove(opsService);
		opsService.setAccount(null);

		return opsService;
	}

}