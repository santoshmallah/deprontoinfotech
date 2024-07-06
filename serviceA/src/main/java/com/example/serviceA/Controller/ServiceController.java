package com.example.serviceA.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.serviceA.service.ProcessDataSevice;

@Controller
public class ServiceController {
	
	@Autowired
	ProcessDataSevice processDataSevice;
	
	@PostMapping("/process")
	public ResponseEntity<String> getData() {
		String reposString = null;
		try {
			reposString=processDataSevice.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(reposString);
		
	}

}
