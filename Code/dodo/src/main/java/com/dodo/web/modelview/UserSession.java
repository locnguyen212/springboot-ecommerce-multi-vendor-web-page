package com.dodo.web.modelview;

import java.io.Serializable;

public class UserSession implements Serializable {
	private int userId;
	private int ownerId;

	// Getters and setters

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}
