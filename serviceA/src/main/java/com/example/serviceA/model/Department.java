package com.example.serviceA.model;

public class Department {
	private int id;
	private String deptname;
	private String role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", deptname=" + deptname + ", role=" + role + "]";
	}
	
}
