package com.project2.DBconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.project2")

@Configuration
@EnableTransactionManagement

public class Dbconfig {

	//data source 
	@Bean(name = "dataSource")
	@Autowired
	public DataSource getDataSource()
	{
	
		 BasicDataSource dataSource = new BasicDataSource();
		    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		    dataSource.setUsername("praveen");
		    dataSource.setPassword("praveen");
		   
		System.out.println("database connection is established");
		 return dataSource;
	
	}


	
	// creating session factory

	//session-factory
	
	
		@Bean( "sessionFactory")
		@Autowired
		public SessionFactory sessionFactory() {
			System.out.println("session is created");
			LocalSessionFactoryBuilder lsf=
					new LocalSessionFactoryBuilder(getDataSource());
			Properties hibernateProperties=new Properties();
			hibernateProperties.setProperty(
					"hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
			hibernateProperties.setProperty("hibernate.show_sql", "true");
			hibernateProperties.setProperty("hibernate.format_sql", "true");
		lsf.addProperties(hibernateProperties);
		lsf.scanPackages("com.project2");
			
		    return lsf.buildSessionFactory();
		}
	// hibernate transaction manager

	@Bean("transactionManager")

	@Autowired
	public HibernateTransactionManager hibTransManagement(){
		System.out.println("transaction is created");
		return new HibernateTransactionManager(sessionFactory());
	}


}
