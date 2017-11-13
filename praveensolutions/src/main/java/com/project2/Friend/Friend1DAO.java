package com.project2.Friend;

import java.util.List;

import com.project2.model.User;

import com.project2.Friend.Friend1;;


public interface Friend1DAO {
	List<User> listOfSuggestedUser(String username);
	void friendRequest(String fromUsername,String toUsername);
	List<Friend1> pendingRequests(String username);
	//void updatePendingRequest(Friend1 friend);
	void updatePendingRequest(String fromUsername,String username,char status);
	List<Friend1> friends(String username);
	}
