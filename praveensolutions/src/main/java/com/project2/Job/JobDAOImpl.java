package com.project2.Job;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("JobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveJob(Job job) {
		Session session = sessionFactory.getCurrentSession();
		session.save(job);
	}

	public List<Job> getAllJobs() {
		/*Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Job");
		List<Job> jobs = query.getResultList();
		return jobs;*/
		return sessionFactory.getCurrentSession().createQuery("from Job", Job.class).list();
	}

	public Job getJobById(int id) {
		return sessionFactory.getCurrentSession().createQuery("from Job where id=?",Job.class).setParameter(0, id).getSingleResult();
		
	}

}
