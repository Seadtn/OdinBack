package com.odin.esport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tuto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String iframe;
	private String titre;
	private String description;
	public Tuto( String iframe, String titre,String desc) {
		super();
		this.iframe = iframe;
		this.titre = titre;
		this.description=desc;
	}
	public Tuto() {
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIframe() {
		return iframe;
	}
	public void setIframe(String iframe) {
		this.iframe = iframe;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
}
