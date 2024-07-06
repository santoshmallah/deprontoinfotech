package com.example.serviceC.service;

public interface ProcessDataservice {
	
	public String getApplicationAccess(String departement);

	public void ProcessKafkaData(String message);

}
