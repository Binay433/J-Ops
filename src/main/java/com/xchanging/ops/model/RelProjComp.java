package com.xchanging.ops.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rel_proj_comp database table.
 * 
 */
@Entity
@Table(name="rel_proj_comp")
@NamedQuery(name="RelProjComp.findAll", query="SELECT r FROM RelProjComp r")
public class RelProjComp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int comp;

	private String description;

	private int proj;

	public RelProjComp() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getComp() {
		return this.comp;
	}

	public void setComp(int comp) {
		this.comp = comp;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProj() {
		return this.proj;
	}

	public void setProj(int proj) {
		this.proj = proj;
	}

}