package com.jpabegin;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "EMPLOYEE_DATA")
@NamedQuery(query = "select e from Employee e where e.age> :age order by e.name", name = "emp name asc")
public class Employee {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "employee_name")
	private String name;
	
	@Column(unique = true, length = 10, nullable = false)
	private String ssn;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	private int age;
	
	@OneToOne
	private AccessCard card;
	
	//CascadeType.REMOVE will enable in deleting the PayStub when we delete the employee
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<PayStub> payStub = new ArrayList<>();
	
	@ManyToMany//(fetch = FetchType.EAGER)
	@JoinTable(name = "Email_Group_Subscriptions",
	joinColumns = @JoinColumn(name = "Employee_ID"), 
	inverseJoinColumns = @JoinColumn(name = "Subscription_MailID"))
	//Above annotation is customizing the Join Table itself(It is in many to many relationship)
	private List<EmailGroup> emailGroup = new ArrayList<>();
	
	
	public List<EmailGroup> getEmailGroup() {
		return emailGroup;
	}
	public void setEmailGroup(List<EmailGroup> emailGroup) {
		this.emailGroup = emailGroup;
	}
	public List<PayStub> getPayStub() {
		return payStub;
	}
	public void setPayStub(List<PayStub> payStub) {
		this.payStub = payStub;
	}
	public AccessCard getCard() {
		return card;
	}
	public void setCard(AccessCard card) {
		this.card = card;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addEmailSubscription(EmailGroup group) {
		this.emailGroup.add(group);
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", ssn=" + ssn + ", dob=" + dob + ", age=" + age + "]";
	}
}
