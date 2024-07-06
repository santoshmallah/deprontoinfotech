package com.example.serviceC.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.serviceC.Util.ServiceCUtility;
import com.example.serviceC.dao.ProessDataDao;
import com.example.serviceC.model.ApplicationAccess;
import com.example.serviceC.model.Response;
import com.example.serviceC.service.ProcessDataservice;

@Service
public class ProcessDataserviceImpl implements ProcessDataservice{
	
	@Autowired
	ProessDataDao proessDataDao;

	@Override
	public String getApplicationAccess(String departement) {
		String resposenString = null;
		Response<ApplicationAccess> reposne = new Response<ApplicationAccess>();
		try {
			if(proessDataDao.getDataFromRedis(departement)!=null) {
				return proessDataDao.getDataFromRedis(departement);
			}
			List<ApplicationAccess> response =proessDataDao.getAppliationAccess(departement);
			if(response!=null && response.size()>0) {
				reposne.setTotalResponse(response.size());
				reposne.setSuccess(response);
			}
			resposenString = ServiceCUtility.fromObjectToJson(reposne);
			proessDataDao.saveDataInRedis(departement, resposenString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resposenString;
	}

	@Override
	public void ProcessKafkaData(String message) {
		try {
			proessDataDao.updateKafkaData(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
