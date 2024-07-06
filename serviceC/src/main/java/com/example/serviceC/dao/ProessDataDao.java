package com.example.serviceC.dao;

import java.util.List;

import com.example.serviceC.model.ApplicationAccess;

public interface ProessDataDao {
	public String updateKafkaData(String data);

	public List<ApplicationAccess> getAppliationAccess(String department);

	void saveDataInRedis(String key, String data);

	public String getDataFromRedis(String key);
}
