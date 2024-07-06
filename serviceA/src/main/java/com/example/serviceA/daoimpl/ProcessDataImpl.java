package com.example.serviceA.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.serviceA.dao.ProcessData;
import com.example.serviceA.model.Department;
import com.example.serviceA.model.Response;
import com.example.serviceA.rowmapper.ProcessDataMapper;
import com.example.serviceA.util.ServiceAUtility;

@Repository
@PropertySource("classpath:mysqlquery.properties")
public class ProcessDataImpl implements ProcessData{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	
	@Value("${GETDATA}")
	private String GETDATA;
	

	@Override
	public String getProcessData() {
		String response = null;
		Response<Department> reposResponse = new Response<Department>();
		try {
			System.out.println("Test"+GETDATA);
			List<Department> department=jdbcTemplate.query(GETDATA, new ProcessDataMapper());
			reposResponse.setTotalResponse(department.size());
			reposResponse.setSuccess(department);
			response = ServiceAUtility.fromObjectToJson(reposResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
