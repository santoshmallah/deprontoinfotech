package com.example.serviceA.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.serviceA.dao.ProcessData;
import com.example.serviceA.model.Department;
import com.example.serviceA.model.Response;
import com.example.serviceA.service.ProcessDataSevice;
import com.example.serviceA.util.ServiceAUtility;

@Service
public class ProcessDataServiceImpl implements ProcessDataSevice{
	
	@Autowired
	ProcessData processData;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public String getData() {
		String response =null;
		try {
			String res=processData.getProcessData();
			Response<Department> responseData = ServiceAUtility.fromJsonToObjec(res,Response.class);
			kafkaTemplate.send("ProcessData_topic",ServiceAUtility.fromObjectToJson(responseData.getSuccess()));
			responseData.setMessageStatusForKafka(true);
			response = ServiceAUtility.fromObjectToJson(responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
