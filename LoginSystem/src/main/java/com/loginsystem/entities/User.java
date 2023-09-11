package com.loginsystem.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document(collection = "user")
public class User {
	@Id
    private String id;
	
    private String email;
    private String name;
    private String password;
    private String contact;
    private String address;
    private Long pincode;
    private String role;
    
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", password=" + password + ", contact="
				+ contact + ", address=" + address + ", pincode=" + pincode + "]";
	}
    
    

}
