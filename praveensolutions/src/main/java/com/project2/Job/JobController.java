package com.project2.Job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project2.UserDAO.UserDAO;
import com.project2.model.User;
import com.project2.model.Error;

@Controller
public class JobController {

	@Autowired
	private JobDAO jobDAO;
	
	@Autowired 
	private UserDAO userDAO;

	@Autowired
	User user;
	
	
	@RequestMapping("/savejob/{username}")
	public ResponseEntity<?> saveJob(@RequestBody Job job,@PathVariable String username)
	{

		if(username==null)
		{
			Error error=new Error(1,"please login");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		user=userDAO.getUserByUsername(username);
		if(user.getRole().equals("ADMIN"))
		{
			try 
			{
				job.setPostedOn(new Date());
				jobDAO.saveJob(job);
				return new ResponseEntity<Job> (job,HttpStatus.OK);
			} catch (Exception e) 
			{

				Error error=new Error(7, "unable to update job");
				return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
					
			}
			
		}
			else 
			{

				Error error=new Error(6, "unauthorized access");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
			}
		}
	@RequestMapping("/getalljobs")
	public ResponseEntity<List<Job>> GetAllJobs(){
		List<Job> jobs=jobDAO.getAllJobs();
		return new ResponseEntity<List<Job>> (jobs,HttpStatus.OK);
	}
	

	@RequestMapping("/getjob/{id}")
	public ResponseEntity<?> getJob(@PathVariable int id, Job job){
		try {
		 job=jobDAO.getJobById(id);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			Error error=new Error(6, "unauthorized access");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		
		}
}


