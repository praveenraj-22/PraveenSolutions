package com.project2.profilepicture;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.project2.model.User;
import com.project2.model.Error;

@Controller
public class PicController {

	@Autowired
	User user;

	@Autowired
	HttpServletRequest request;
	
	
	
	

	@RequestMapping("/uploadprofilepic")
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession sesssion) {

		try {
			User user =  (User)sesssion.getServletContext().getAttribute("currentUser");
			String imagePath = request.getServletContext().getRealPath("/resources/images");
			System.out.println("------- Context Path set -------");
			File dir = new File(imagePath + File.separator);
			System.out.println("------- Directory set to" + dir + "-------");
			if (!dir.exists())
				dir.mkdirs();
			System.out.println();
			String filePath = imagePath + File.separator + user.getUsername() + ".jpg";
			File dest = new File(filePath);
			image.transferTo(dest);
			
			System.out.println("------- Image uploaded to " + dest + "-------");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.toString());
			Error error = new Error(1, "unauthorized access");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);

		}

	}

}
