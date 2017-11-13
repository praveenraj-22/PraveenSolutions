package com.project2.UserDAO;






import com.project2.model.User;

public interface UserDAO

{

	public void saveUser(User user);
	
	public void updateUser(User user);
	
	boolean validateusername(String username);
	
	boolean validateemail(String email);
	
	User validate(String username,String password);   	//validate=login
	
	User getUserByUsername(String username);
	/*
	public User getUserById(int id);
	//frnd
	public List<User> friendSearch(String name);
	public void sendFriendRequest(Friend friends);
	public void acceptFriendRequest(Friend friends,User user);
	public List<Friend> getFriendsList(int userId);
	public Friend getFriendById(int id);*/
}
