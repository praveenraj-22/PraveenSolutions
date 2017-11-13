package com.project2.BlogPostDAO;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.project2.BlogPostComment.BlogComment;
import com.project2.model.BlogPost;

@Repository("BlogPostDAO")
@Transactional
public class BlogPostImpl implements BlogPostDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveBlogPost(BlogPost blogPost) {
		Session session = sessionFactory.getCurrentSession();
		session.save(blogPost);

	}

	public List<BlogPost> getAllBlogs(int approved) {
		// Session session = sessionFactory.getCurrentSession();

		if (approved == 1) {
			return sessionFactory.getCurrentSession()
					.createQuery("from BlogPost where approved=" + approved, BlogPost.class).list();
		} // query = session.createQuery("from BlogPost where approved=" +
			// approved);
		else {
			return sessionFactory.getCurrentSession()
					.createQuery("from BlogPost where approved=0 and rejectionReason is null", BlogPost.class).list();
		}
		// query = session.createQuery("from BlogPost where approved=0 and
		// rejectionReason is null");

		// return query.getResultList();
	}

	public BlogPost getBlogPost(int id) {

		Session session = sessionFactory.getCurrentSession();
		BlogPost blogPost = (BlogPost) session.get(BlogPost.class, id);
		return blogPost;
	}

	public void updateBlogPost(BlogPost blogPost) {
		Session session = sessionFactory.getCurrentSession();
		session.update(blogPost);

	}

	public List<BlogPost> getApprovalStatus(@PathVariable String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"from BlogPost where postedBy.username=? and viwedStatus=? and (approved=1 or rejectionReason!=null)");

		query.setParameter(0, username);
		query.setParameter(1, false);

		List<BlogPost> blogPosts = query.getResultList();
		return blogPosts;
	}

	public void addBlogComment(BlogComment blogComment) {
		Session session = sessionFactory.getCurrentSession();
		session.save(blogComment);

	}

	public List<BlogComment> getBlogComments(int id) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BlogComment where blogPost.id=?");
		query.setParameter(0, id);
		return query.getResultList();

	}

	public void updateViewedStatus(List<BlogPost> blogPosts) {
		Session session = sessionFactory.getCurrentSession();

		BlogPost blogPost = new BlogPost();
		blogPost.setViwedStatus(true);
		session.update(blogPost);
	}

}
