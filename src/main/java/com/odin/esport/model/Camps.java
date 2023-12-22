package com.odin.esport.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Camps {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=16777215)
	private byte[] campImage;
	private String title;
	private String location;
	private String details;
	public Camps() {
		super();
	}
	
	public Camps(byte[] campImage, String title, String location, String details) {
		super();
		this.campImage = campImage;
		this.title = title;
		this.location = location;
		this.details = details;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getCampImage() {
		return campImage;
	}
	public void setCampImage(byte[] campImage) {
		this.campImage = campImage;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
