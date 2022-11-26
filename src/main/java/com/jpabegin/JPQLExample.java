package com.jpabegin;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JPQLExample {
	
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		//Below is basic query in JPQL
		TypedQuery<Employee> createQuery = entityManager.createQuery(
				"select e from Employee e where e.age>25"
				,Employee.class);
		List<Employee> resultList = createQuery.getResultList();
		resultList.forEach(System.out::println);
		
		System.out.println("**********Secound query*******");
		//Below is the query on how to use LIKE operator in JPQL
		TypedQuery<Employee> createQuery2 = entityManager.createQuery(
				"select e from Employee e where e.name like '%Bar%'"
				,Employee.class);
		List<Employee> resultList2 = createQuery2.getResultList();
		resultList2.forEach(System.out::println);
		
		System.out.println("**********Join Query*******");
		//Below is the query on how to get value from doing JOIN operation
		TypedQuery<Employee> createQuery3 = entityManager.createQuery(
				"select e from Employee e where e.card.firmwareVersion = '1.0.0'"
				,Employee.class);
		List<Employee> resultList3 = createQuery3.getResultList();
		resultList3.forEach(System.out::println);
		
		System.out.println("**********Custom columns Query*******");
		//Below is the query on how to get custom columns(only required columns from database)
		TypedQuery<Object[]> createQuery4 = entityManager.createQuery(
				"select e.name, e.age, e.dob from Employee e"
				,Object[].class);
		List<Object[]> resultList4 = createQuery4.getResultList();
		resultList4.forEach(e-> System.out.println(e[0]+"  "+e[1]+"  "+e[2]));
		
		System.out.println("**********Query to avoid SQL injection attack*******");
		//Below is the query we are avoiding SQL injection attack by creating variable :minAge and adding value
		//using createQuery5.setParameter parameter
		TypedQuery<Employee> createQuery5 = entityManager.createQuery(
				"select e from Employee e where e.age> :minAge"
				,Employee.class);
		int minAge = 25;
		createQuery5.setParameter("minAge", minAge);
		List<Employee> resultList5 = createQuery5.getResultList();
		resultList5.forEach(System.out::println);

		
		System.out.println("**********Query to demonstrate named queries*******");
		//Below is the query we are avoiding SQL injection attack by creating variable :minAge and adding value
		//using createQuery5.setParameter parameter
		TypedQuery<Employee> createQuery6 = entityManager.createNamedQuery("emp name asc",Employee.class);
		createQuery6.setParameter("age", minAge);
		List<Employee> resultList6 = createQuery6.getResultList();
		resultList6.forEach(System.out::println);
		entityManager.close();
		entityManagerFactory.close();
	}

}
