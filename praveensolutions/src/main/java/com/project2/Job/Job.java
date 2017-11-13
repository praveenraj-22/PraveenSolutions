package com.project2.Job;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
@Id
@GeneratedValue(strategy =GenerationType.AUTO )
private int id;
private String jobTitle;
private String jobDescription;
private String yearOfExperience;
private String skillsRequired;
private String salary;
private String location;
private String companyName;
private Date postedOn;



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getJobTitle() {
	return jobTitle;
}
public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
}
public String getJobDescription() {
	return jobDescription;
}
public void setJobDescription(String jobDescription) {
	this.jobDescription = jobDescription;
}
public String getYearOfExperience() {
	return yearOfExperience;
}
public void setYearOfExperience(String yearOfExperience) {
	this.yearOfExperience = yearOfExperience;
}
public String getSkillsRequired() {
	return skillsRequired;
}
public void setSkillsRequired(String skillsRequired) {
	this.skillsRequired = skillsRequired;
}
public String getSalary() {
	return salary;
}
public void setSalary(String salary) {
	this.salary = salary;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public Date getPostedOn() {
	return postedOn;
}
public void setPostedOn(Date postedOn) {
	this.postedOn = postedOn;
}


}
