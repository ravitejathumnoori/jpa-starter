package com.jpabegin;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAStarterWriter {
	

	public static void main(String[] args) {
		
//		EntityManagerFactory entityManagerFactory = Persistence.
//		createEntityManagerFactory("myApp");
//
//         EntityManager entityManager = entityManagerFactory.createEntityManager();	
//         
//         Employee employee1 = entityManager.find(Employee.class,1);
//         Employee employee2 = entityManager.find(Employee.class,2);
//         System.out.println("======================================");
//         System.out.println(employee1);
//         System.out.println(employee2);
//         employee1.setAge(60);
//         employee2.setAge(30);
//         EntityTransaction transaction = entityManager.getTransaction();
// 		 transaction.begin();
// 		 entityManager.persist(employee1);
// 		 entityManager.persist(employee2);
// 		 transaction.commit();
         
		
		
		Employee employee = new Employee();
		employee.setName("Foo Bar");
		employee.setSsn("123");
		employee.setDob(new Date());
		employee.setAge(20);
	
		Employee employee1 = new Employee();
		employee1.setName("Bar Baz");
		employee1.setSsn("1234");
		employee1.setDob(new Date());
		employee1.setAge(30);
		
		AccessCard card1 = new AccessCard();
		card1.setIssueDate(new Date());
		card1.setActive(true);
		card1.setFirmwareVersion("1.0.0");
		employee.setCard(card1);
		card1.setOwner(employee);
		
		PayStub payStub1 = new PayStub();
		payStub1.setPayPeriodStart(new Date());
		payStub1.setPayPeriodEnd(new Date());
		payStub1.setEmployee(employee);
		payStub1.setSalary(1000);
		
		PayStub payStub2 = new PayStub();
		payStub2.setPayPeriodStart(new Date());
		payStub2.setPayPeriodEnd(new Date());
		payStub2.setEmployee(employee);
		payStub2.setSalary(2000);
		
		EmailGroup group1 = new EmailGroup();
		group1.setName("Company Watercooler discussions");
		group1.addMember(employee);
		group1.addMember(employee1);
		employee.addEmailSubscription(group1);
		employee1.addEmailSubscription(group1);
		
		
		EmailGroup group2 = new EmailGroup();
		group2.setName("Engineering");
		group2.addMember(employee);
		employee.addEmailSubscription(group2);
		
		//We need to write the below line because we might need to manipulate paystub data in between before saving in DB
		employee.setPayStub(List.of(payStub1,payStub2));
		
		
		AccessCard card2 = new AccessCard();
		card2.setIssueDate(new Date());
		card2.setActive(false);
		card2.setFirmwareVersion("1.2.0");
		employee1.setCard(card2);
		card2.setOwner(employee1);
		
		EntityManagerFactory entityManagerFactory = Persistence.
				createEntityManagerFactory("myApp");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(employee);
		entityManager.persist(employee1);
		entityManager.persist(card1);
		entityManager.persist(card2);
		entityManager.persist(payStub1);
		entityManager.persist(payStub2);
		entityManager.persist(group1);
		entityManager.persist(group2);
		transaction.commit();
		//entityManager.close();
		//entityManagerFactory.close();
		
		
	}

}
