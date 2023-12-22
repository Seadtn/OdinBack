package com.odin.esport.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;

@Entity
@DiscriminatorValue("Footballer")
public class Footballer extends User {
	private String pos1;
	private String pos2;
	private String foot;
	private String poids;
	private String taille;
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(length=16777215)
    private byte[] license ;
	public Footballer() {
		super();
	}


	public Footballer(String username, String email, String password, String firstName, UserRole userRole,
			String lastName, String date, String country, String number, byte[] profileImage, String pos1, String pos2,
			String foot, byte[] license, String poids, String taille,String exp) {
		super(username, email, password, firstName, userRole, lastName, date, country, number, profileImage,exp);
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.foot = foot;
		this.license = license;
		this.poids = poids;
		this.taille = taille;
	}
	

	public String getPos1() {
		return pos1;
	}

	public void setPos1(String pos1) {
		this.pos1 = pos1;
	}

	public String getPos2() {
		return pos2;
	}

	public void setPos2(String pos2) {
		this.pos2 = pos2;
	}

	public byte[] getLicense() {
		return license;
	}

	public void setLicense(byte[] license) {
		this.license = license;
	}

	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}


	public String getPoids() {
		return poids;
	}


	public void setPoids(String poids) {
		this.poids = poids;
	}


	public String getTaille() {
		return taille;
	}


	public void setTaille(String taille) {
	this.taille = taille;
	}

	
	
}
