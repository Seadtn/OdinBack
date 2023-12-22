package com.odin.esport.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;



@Entity
@DiscriminatorValue("Agent")
public class Agent extends User{
	private String profile;
    private String deals ;
    
	public Agent() {
		super();
	}

	public Agent(String profile, String deals) {
		super();
		this.profile = profile;
		this.deals = deals;
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
	
	


}
