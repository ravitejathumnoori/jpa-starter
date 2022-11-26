package com.jpabegin;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAStarterRead {


	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Employee employee = entityManager.find(Employee.class,2);
		System.out.println(employee.getEmailGroup());
		
		EmailGroup emailGroup = entityManager.find(EmailGroup.class,7);
		System.out.println("Got Email group. Below are the members: ");
		System.out.println(emailGroup.getMembers());
		
	}
	
}
