package com.odin.esport.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=16777215)
    private byte[] Image;
	@ManyToOne
	private User user;
	
	public Image() {
		super();
	}
	public Image(byte[] image, User user) {
		super();
		Image = image;
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getImage() {
		return Image;
	}
	public void setImage(byte[] image) {
		Image = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
