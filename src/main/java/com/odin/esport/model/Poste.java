package com.odin.esport.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Poste {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String date;
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=16777215)
    private byte[] posteImage ;
	@OneToMany
	private List<Comment> comments = new ArrayList<Comment>();
	
	public Poste() {
		super();
	}
	
	public Poste(String description, byte[] posteImage,String date) {
		super();
		this.description = description;
		this.date=date;
		this.posteImage = posteImage;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPosteImage() {
		return posteImage;
	}

	public void setPosteImage(byte[] posteImage) {
		this.posteImage = posteImage;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
