package com.project2.BlogPostDAO;

import java.util.List;

import com.project2.BlogPostComment.BlogComment;
import com.project2.model.BlogPost;

public interface BlogPostDAO {

	public void saveBlogPost(BlogPost blogPost);
	
	List<BlogPost> getAllBlogs(int approved);
	
	BlogPost getBlogPost(int id);
	
	public void updateBlogPost(BlogPost blogPost);

	List<BlogPost> getApprovalStatus(String username);

	//blogcomment
	
	void addBlogComment(BlogComment blogComment);
	
	List<BlogComment> getBlogComments(int id);
	
	void updateViewedStatus (List<BlogPost> blogPosts);

}
