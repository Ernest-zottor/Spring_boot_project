package com.spring.eclipse.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Entity
@Table(name="USER_AUTH_TBL")
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY )
	private int id;
	
	private String userName;
	private String password;
	private boolean isActive;
	private String roles;
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	 public User() {}
	 
	 
	public int getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getRoles() {
		return roles;
	}
	
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", isActive=" + isActive + ", roles=" + roles
				+ "]";
	}
	 
	 
	

}
