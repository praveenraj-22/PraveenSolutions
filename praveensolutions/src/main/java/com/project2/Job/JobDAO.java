package com.project2.Job;

import java.util.List;

public interface JobDAO {

	public void saveJob(Job job);
	
	List<Job> getAllJobs();
	
	Job getJobById(int id);
}
