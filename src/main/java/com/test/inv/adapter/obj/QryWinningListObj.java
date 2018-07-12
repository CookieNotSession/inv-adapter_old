package com.test.inv.adapter.obj;

public class QryWinningListObj {
	
	private double version = 0.2;

	private String action = "QryWinningList";
	
	private String invTerm;
	
	private String UUID;
	
	private String appID;
	
	public QryWinningListObj() {
		
	}
	
	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getInvTerm() {
		return invTerm;
	}

	public void setInvTerm(String invTerm) {
		this.invTerm = invTerm;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	@Override
	public String toString() {
		return "QryWinningListObj [version=" + version + ", action=" + action + ", invTerm=" + invTerm + ", UUID="
				+ UUID + ", appID=" + appID + "]";
	}

	
}
