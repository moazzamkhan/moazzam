package com.pesqol.model;

import java.util.Date;
import java.util.UUID;

public class Image {
	private String id;
	private String name;
	private String imagePath;
	private Date uploadedOn;

	public Image() {
		setId(UUID.randomUUID().toString());
		setUploadedOn(new Date());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Date uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}