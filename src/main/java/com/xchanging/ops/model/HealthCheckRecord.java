package com.xchanging.ops.model;

import java.util.List;

public class HealthCheckRecord {

	private HealthCheck check;
	
	private String name;
	
	private String entryDate;
	
	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	private String saveOption;
	
	List<HealthCheckTransaction> records;

	public HealthCheck getCheck() {
		return check;
	}

	public void setCheck(HealthCheck check) {
		this.check = check;
	}

	public List<HealthCheckTransaction> getRecords() {
		return records;
	}

	public void setRecords(List<HealthCheckTransaction> records) {
		this.records = records;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSaveOption() {
		return saveOption;
	}

	public void setSaveOption(String saveOption) {
		this.saveOption = saveOption;
	}

	@Override
	public String toString() {
		return "HealthCheckRecord [check=" + check + ", name=" + name
				+ ", entryDate=" + entryDate + ", saveOption=" + saveOption
				+ ", records=" + records + "]";
	}

	
	
	
	



	
	
	
	
	
}
