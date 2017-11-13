package com.project2.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project2.UserDAO.UserDAO;

import com.project2.model.Error;
import com.project2.model.User;

@RestController
public class UserController {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	User user;
	
	@RequestMapping("/registerUser")

	public ResponseEntity<?> registerUser(@RequestBody User user) {
		System.out.println("content  reached to registerController");
		// validating username
		if (!userDAO.validateusername(user.getUsername())) {
			System.out.println("user : " + user.getUsername() + " is already exixts");

			Error error = new Error(202, "username is already exists");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);
		}

		// validating email-id

		if (!userDAO.validateemail(user.getEmail())) {
			System.out.println("email-id :" + user.getEmail() + "is already exists");
			Error error = new Error(303, "email id is already exists");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_ACCEPTABLE);

		}

		try {
			System.out.println("content of the " + user.getUsername() + " is going to save");
			userDAO.saveUser(user);
			System.out.println("user : " + user.getUsername() + " is saved successfully");
			return new ResponseEntity<User>(user, HttpStatus.OK);

		} catch(Exception e){
			Error error=new Error(1,"Unable to register.." + e.getMessage());
			System.out.println("error "+e.getMessage());
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);		}

	}

	@RequestMapping("/loginUser")

	public ResponseEntity<?> login(@RequestBody User user,HttpSession session) {
		System.out.println("login is invoked");
		this.user = userDAO.validate(user.getUsername(), user.getPassword());
		
		try {
			System.out.println("login is invoked");
			this.user.setOnline(true);
			userDAO.updateUser(this.user);
			System.out.println("user :" + this.user.getUsername() + " successfully logged in");
			session.getServletContext().setAttribute("currentUser", this.user);
			return new ResponseEntity<User>(this.user, HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("invalid username/password");
			Error error = new Error(405, "username/password is invalid");
			return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/logoutUser")
	public ResponseEntity<User> logout(@RequestBody User user)
	{
		
		System.out.println("logout is invoked");
		user.setOnline(false);
		userDAO.updateUser(user);
		this.user = null;
		System.out.println("user :" + user.getUsername() + " successfully logged out");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping("/getuser/{username}")
	public ResponseEntity<?> getUser(@PathVariable String username,@RequestBody User user)
	{
		this.user=userDAO.getUserByUsername(username);
		return new ResponseEntity<User>(this.user, HttpStatus.OK);
	}
	
	@RequestMapping("/updateprofile")
	public ResponseEntity<?> update(@RequestBody User user)
	{
		try {
			System.out.println(" user " + user.getUsername() +"successfully updated");
			userDAO.updateUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			Error error=new Error(707, "unable to update");
			return new ResponseEntity<Error> (error,HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	/*
	

	@RequestMapping("/search")
	public List<User> friendSearch(@RequestBody String name, HttpSession session) {

		return userDAO.friendSearch(name);
	}

	@RequestMapping("/list/friends")
	public List<Friend> friendLIst(@RequestBody User user, HttpSession session) {

		return userDAO.getFriendsList(user.getId());
	}

	@RequestMapping("/send/friend/request")
	public ResponseEntity<Friend> frndRequest(@RequestBody Friend friend) {
		
		
		
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@RequestMapping("/get/friend/request")
	public List<Friend> getFriendRequest(@RequestBody User user, HttpSession session) {

		return userDAO.getFriendsList(user.getId());
	}

	@RequestMapping("/friend/accept/{currentUserId}")
	public ResponseEntity<Friend> frndRequestAccept(@RequestBody int id,@PathVariable("currentUserId") int currentUserId) {
		
		User user = new User();
		user = userDAO.getUserById(currentUserId);
		Friend friends = userDAO.getFriendById(id);
		friends.setStatus("accepted");
		userDAO.acceptFriendRequest(friends,user);

		return new ResponseEntity<Friend>(friends, HttpStatus.OK);
	}
*/
}

