package com.xchanging.ops.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class SlaEntry {
	
	@Valid
	private List<SlaTransaction> transactions;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entrydate;

	public List<SlaTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<SlaTransaction> transactions) {
		this.transactions = transactions;
	}
	
	public SlaTransaction addSlaTransaction(SlaTransaction slaTransaction) {
		if(getTransactions()==null){
			setTransactions(new ArrayList<SlaTransaction>());
		}
		getTransactions().add(slaTransaction);
		return slaTransaction;
	}

	public SlaTransaction removeSlaTransaction(SlaTransaction slaTransaction) {
		getTransactions().remove(slaTransaction);
		return slaTransaction;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	@Override
	public String toString() {
		return "SlaEntry [transactions=" + transactions + ", entrydate="
				+ entrydate + "]";
	}

	

	
	
	
}
