package org.comcast.adserver.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Ad {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int adId;
	private String partnerId;
	private int duration;
	private String content;
	private boolean active=true;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated=new Date();
	

	public Ad(){
		
	}
	
	public Ad(int adId, String partnerId, int duration, String content, boolean active, Date dateCreated) {
		super();
		this.adId = adId;
		this.partnerId = partnerId;
		this.duration = duration;
		this.content = content;
		this.active = active;
		this.dateCreated = dateCreated;
	}

	public Date getDateCreated(){
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
