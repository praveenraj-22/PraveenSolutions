package com.project2.UserDAO;



import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.project2.model.User;

@Repository("UserDAO")
@Transactional

public class UserDAOimpl implements UserDAO {

	@Autowired

	private SessionFactory sessionFactory;

	public void saveUser(User user) {

		
		Session session = sessionFactory.getCurrentSession();

		session.save(user);
		

	}

	// validating username
	public boolean validateusername(String username) {

		Session session = sessionFactory.getCurrentSession();
		User user = new User();

		try {

			user = session.createQuery("from User where username='" + username + "'", User.class).uniqueResult();
			System.out.println("validating username " + user.getUsername());
		} catch (Exception e) {
			System.out.println("user not found");
		}
		if (user == null)
			return true;
		else
			return false;
	}

	// validating emailaddress
	public boolean validateemail(String email) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		try {
			user = session.createQuery("from User where email='" + email + "'", User.class).uniqueResult();
			System.out.println("validating email id " + user.getEmail());

		} catch (Exception e) {
			System.out.println("email-id is not found ");
		}
		if (user == null)
			return true;
		else
			return false;
	}

	// login validation
	public User validate(String username, String password) {

		return sessionFactory.getCurrentSession()
				.createQuery("from User where username='" + username + "'and password='" + password + "'", User.class)
				.uniqueResult();
	}

	// update user
	public void updateUser(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		session.flush();
	}

	public User getUserByUsername(String username) {

		Session session = sessionFactory.getCurrentSession();
		User user = new User();

		try {

			System.out.println("Fetching user : "+username);
			user = session.createQuery("from User where username='" + username + "'", User.class).uniqueResult();
			System.out.println("user fetched : " + user.getUsername());
		} catch (Exception e) {
			System.out.println("user not found");
		}
		return user;
		
	}

		
}