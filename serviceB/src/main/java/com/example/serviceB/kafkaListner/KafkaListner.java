package com.example.serviceB.kafkaListner;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.serviceB.service.ProcessDataservice;

@Service
public class KafkaListner {
	
	@Autowired
	ProcessDataservice processDataservice;

	@KafkaListener(topics = "ProcessData_topic", groupId = "group_id")
    public void consume(String message) throws IOException {
        System.out.println("Message==>"+message);
        processDataservice.ProcessKafkaData(message);
    }
	
}
