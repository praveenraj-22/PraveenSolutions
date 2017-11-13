package com.project2.BlogPostComment;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project2.BlogPostDAO.BlogPostDAO;
import com.project2.UserDAO.UserDAO;
import com.project2.model.User;

@Controller
public class BlogCommentController {

	@Autowired
	private BlogPostDAO blogPostDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	User user;
	
	
	@RequestMapping("/addcomment/{username}")
	private ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,@PathVariable String username){
		user=userDAO.getUserByUsername(username);
		blogComment.setCommentedBy(user);
		blogComment.setCommentedOn(new Date());
		System.out.println("comment by"+user.getUsername() + "commented"+ blogComment.getCommentText());
		System.out.println(blogComment);
		blogPostDAO.addBlogComment(blogComment);
		return new ResponseEntity<BlogComment> (blogComment,HttpStatus.OK);
	}
	
	
	@RequestMapping("/getcomments/{id}")
	private ResponseEntity<?> getBlogComments(@PathVariable int id)
	{
		List<BlogComment> blogComments=blogPostDAO.getBlogComments(id);
		return new ResponseEntity<List<BlogComment>>(blogComments,HttpStatus.OK);
	}
}
