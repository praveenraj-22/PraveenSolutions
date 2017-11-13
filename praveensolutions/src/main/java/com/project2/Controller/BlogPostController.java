package com.project2.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.BlogPostDAO.BlogPostDAO;
import com.project2.UserDAO.UserDAO;
import com.project2.model.BlogPost;
import com.project2.model.User;
import com.project2.model.Error;

@RestController
public class BlogPostController {

	@Autowired
	BlogPostDAO blogPostDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	User user;
	
	
	
	@RequestMapping("/saveblogpost/{username}")
	public ResponseEntity<?> saveBlogpost(@RequestBody BlogPost blogPost,@PathVariable String username)
	{
		if(username==null)
		{
			Error error=new Error(1,"please login");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		try {
	
			
			System.out.println("user :"+user.getUsername());
			blogPost.setPostedOn(new Date());
			user = userDAO.getUserByUsername(username);
			blogPost.setPostedBy(user);
			System.out.println("posted by"+blogPost.getPostedBy().getUsername());
			blogPostDAO.saveBlogPost(blogPost);
			
			return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
		} catch (Exception e) {
			
			System.out.println("error in posting");
			System.out.println("Error : "+e.getMessage());
			Error error=new Error(1, "error in posting");
			return new ResponseEntity<Error> (error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

@RequestMapping("/getallblogs/{approved}")
public ResponseEntity<?> getAllBlogs (@PathVariable int approved)
{

	
	try {

		List<BlogPost> blogPosts=blogPostDAO.getAllBlogs(approved);
		return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
	
	} catch (Exception e) {
		System.out.println("Error : "+e.getMessage());
		Error error=new Error(2, "error in posting");
		return new ResponseEntity<Error> (error,HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
}

@RequestMapping("/getblogpost/{id}")
public ResponseEntity<BlogPost> getBlogPost(@PathVariable int id)
{
	BlogPost blogPost=blogPostDAO.getBlogPost(id);
	return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
}


@RequestMapping("/updateblogpost")
public ResponseEntity<?> updateBlogpost(@RequestBody BlogPost blogpost){
	try {
		System.out.println("blog is updated");
		if(!blogpost.isApproved() && blogpost.getRejectionReason()==null)
			System.out.println("------------------------------------- ptinting-------------------");
			blogpost.setRejectionReason("not mentioned");
		blogPostDAO.updateBlogPost(blogpost);
		return new ResponseEntity<BlogPost>(blogpost,HttpStatus.OK);
	} catch (Exception e) {
		Error error=new Error(2, "error in update posting");
		return new ResponseEntity<Error> (error,HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
}



@RequestMapping("/blogpostapprovalstatus/{username}")
public ResponseEntity<?> getBlogPostapproval(@PathVariable String username)
{
	
	List<BlogPost> blogPosts=blogPostDAO.getApprovalStatus(username);
	return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
}


@RequestMapping("/updateviewedstatus")
public ResponseEntity<?> updateViewedStatus(@RequestBody List<BlogPost> blogPosts){
	blogPostDAO.updateViewedStatus(blogPosts);
	return new ResponseEntity<List<BlogPost>>(blogPosts,HttpStatus.OK);
}

}











