package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the health_check_services database table.
 * 
 */
@Embeddable
public class HealthCheckServicePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="service_id", insertable=false, updatable=false)
	private int serviceId;

	@Column(name="hc_id", insertable=false, updatable=false)
	private int hcId;

	public HealthCheckServicePK() {
	}
	public int getServiceId() {
		return this.serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getHcId() {
		return this.hcId;
	}
	public void setHcId(int hcId) {
		this.hcId = hcId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HealthCheckServicePK)) {
			return false;
		}
		HealthCheckServicePK castOther = (HealthCheckServicePK)other;
		return 
			(this.serviceId == castOther.serviceId)
			&& (this.hcId == castOther.hcId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.serviceId;
		hash = hash * prime + this.hcId;
		
		return hash;
	}
}