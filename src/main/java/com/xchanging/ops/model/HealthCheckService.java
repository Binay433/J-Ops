package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the health_check_services database table.
 * 
 */
@Entity
@Table(name="health_check_services")
@NamedQuery(name="HealthCheckService.findAll", query="SELECT h FROM HealthCheckService h")
public class HealthCheckService implements Serializable {
	private static final long serialVersionUID = 1L;


	
	
	@EmbeddedId
	private HealthCheckServicePK id;

	public HealthCheckService() {
	}

	public HealthCheckServicePK getId() {
		return this.id;
	}

	public void setId(HealthCheckServicePK id) {
		this.id = id;
	}

}