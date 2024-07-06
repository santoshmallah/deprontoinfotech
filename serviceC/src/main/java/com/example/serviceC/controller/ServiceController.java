package com.example.serviceC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.serviceC.Util.ServiceCUtility;
import com.example.serviceC.model.Response;
import com.example.serviceC.service.ProcessDataservice;
import com.google.gson.JsonObject;

@RestController
public class ServiceController {
	
	@Autowired
	ProcessDataservice processDataservice;
	
	@PostMapping("/getapplicationaccess")
	public ResponseEntity<String> getApplicationaccess(@RequestBody String request){
		String response = null;
		try {
			JsonObject jsonObject = ServiceCUtility.fromJsonToObjec(request, JsonObject.class);
			if(jsonObject.get("department")!=null) {
				response=processDataservice.getApplicationAccess(jsonObject.get("department").getAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
