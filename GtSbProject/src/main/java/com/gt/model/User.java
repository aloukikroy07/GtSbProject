package com.gt.model;

import java.sql.Timestamp;

public class User {
	private Integer  idUserKey;
	private Integer idUserVer;
	private Integer idUserModKey; 
	private Timestamp dtModTime;
	
	private String txUserName; 
	private String txUserFulName; 
	private String txUserEmail; 
	private String txUserPassword; 
	private String txUserMobile;
	
	private String loginMessage;
	
	private String oldPassword;
	private String newPassword;
	
	public User() {
		super();
	}

	public User(Integer idUserKey, Integer idUserVer, Integer idUserModKey, Timestamp dtModTime, String txUserName,
			String txUserFulName, String txUserEmail, String txUserPassword, String txUserMobile, String loginMessage,
			String oldPassword, String newPassword) {
		super();
		this.idUserKey = idUserKey;
		this.idUserVer = idUserVer;
		this.idUserModKey = idUserModKey;
		this.dtModTime = dtModTime;
		this.txUserName = txUserName;
		this.txUserFulName = txUserFulName;
		this.txUserEmail = txUserEmail;
		this.txUserPassword = txUserPassword;
		this.txUserMobile = txUserMobile;
		this.loginMessage = loginMessage;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public Integer getIdUserKey() {
		return idUserKey;
	}

	public void setIdUserKey(Integer idUserKey) {
		this.idUserKey = idUserKey;
	}

	public Integer getIdUserVer() {
		return idUserVer;
	}

	public void setIdUserVer(Integer idUserVer) {
		this.idUserVer = idUserVer;
	}

	public Integer getIdUserModKey() {
		return idUserModKey;
	}

	public void setIdUserModKey(Integer idUserModKey) {
		this.idUserModKey = idUserModKey;
	}

	public Timestamp getDtModTime() {
		return dtModTime;
	}

	public void setDtModTime(Timestamp dtModTime) {
		this.dtModTime = dtModTime;
	}

	public String getTxUserName() {
		return txUserName;
	}

	public void setTxUserName(String txUserName) {
		this.txUserName = txUserName;
	}

	public String getTxUserFulName() {
		return txUserFulName;
	}

	public void setTxUserFulName(String txUserFulName) {
		this.txUserFulName = txUserFulName;
	}

	public String getTxUserEmail() {
		return txUserEmail;
	}

	public void setTxUserEmail(String txUserEmail) {
		this.txUserEmail = txUserEmail;
	}

	public String getTxUserPassword() {
		return txUserPassword;
	}

	public void setTxUserPassword(String txUserPassword) {
		this.txUserPassword = txUserPassword;
	}

	public String getTxUserMobile() {
		return txUserMobile;
	}

	public void setTxUserMobile(String txUserMobile) {
		this.txUserMobile = txUserMobile;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "User [idUserKey=" + idUserKey + ", idUserVer=" + idUserVer + ", idUserModKey=" + idUserModKey
				+ ", dtModTime=" + dtModTime + ", txUserName=" + txUserName + ", txUserFulName=" + txUserFulName
				+ ", txUserEmail=" + txUserEmail + ", txUserPassword=" + txUserPassword + ", txUserMobile="
				+ txUserMobile + ", loginMessage=" + loginMessage + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + "]";
	}
	
}
