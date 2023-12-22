package com.odin.esport.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RegisterRequest {
		private String certificate;
		private String email;
		private String uname;
		private String pwd;
		private String fname;
		@Enumerated(EnumType.STRING)
		private UserRole userRole;
		private String lname;
		private String date;
		private String country;
		private String contact;
		private String profile;
	    private String deals ;
	    private String pos1;
		private String pos2;
		private String foot;
	    private String license ;
	    private String image;
	    private String poids;
	    private String taille;
	    private String exp;
		public RegisterRequest() {
			super();
		}
		
		
		
		public RegisterRequest(String certificate, String email, String uname, String pwd, String fname,
				UserRole userRole, String lname, String date, String country, String contact, String profile,
				String deals, String pos1, String pos2, String foot, String license, String image, String poids,
				String taille,String exp) {
			super();
			this.certificate = certificate;
			this.email = email;
			this.uname = uname;
			this.pwd = pwd;
			this.fname = fname;
			this.userRole = userRole;
			this.lname = lname;
			this.exp=exp;
			this.date = date;
			this.country = country;
			this.contact = contact;
			this.profile = profile;
			this.deals = deals;
			this.pos1 = pos1;
			this.pos2 = pos2;
			this.foot = foot;
			this.license = license;
			this.image = image;
			this.poids = poids;
			this.taille = taille;
		}



		public String getCertificate() {
			return certificate;
		}
		public void setCertificate(String certificate) {
			this.certificate = certificate;
		}
		public String getUname() {
			return uname;
		}
		public void setUname(String uname) {
			this.uname = uname;
		}
		public String getPwd() {
			return pwd;
		}
		public void setPwd(String pwd) {
			this.pwd = pwd;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public UserRole getUserRole() {
			return userRole;
		}
		public void setUserRole(UserRole userRole) {
			this.userRole = userRole;
		}
		public String getLname() {
			return lname;
		}
		public void setLname(String lame) {
			this.lname = lame;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getContact() {
			return contact;
		}
		public void setContact(String contact) {
			this.contact = contact;
		}
		public String getProfile() {
			return profile;
		}
		public void setProfile(String profile) {
			this.profile = profile;
		}
		public String getDeals() {
			return deals;
		}
		public void setDeals(String deals) {
			this.deals = deals;
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
		public String getLicense() {
			return license;
		}
		public void setLicense(String license) {
			this.license = license;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public String toString() {
			return "RegisterRequest [profile=" + profile + ", pos1=" + pos1 + ", pos2=" + pos2 + "]";
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



		public String getExp() {
			return exp;
		}



		public void setExp(String exp) {
			this.exp = exp;
		}
	    
}
