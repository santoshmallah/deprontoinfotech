package com.example.serviceB.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.serviceB.Util.ServiceBUtility;
import com.example.serviceB.dao.ProessDataDao;
import com.example.serviceB.model.Department;
import com.example.serviceB.service.ProcessDataservice;

@Service
public class ProcessDataserviceImpl implements ProcessDataservice{
	
	@Autowired
	ProessDataDao proessDataDao;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public String ProcessKafkaData(String data) {
		String response =null;
		try {
			response =proessDataDao.updateKafkaData(data);
			kafkaTemplate.send("ServiceC_topic",response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
