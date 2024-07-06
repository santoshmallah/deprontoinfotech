package com.example.serviceB.daoImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.example.serviceB.Util.ServiceBUtility;
import com.example.serviceB.dao.ProessDataDao;
import com.example.serviceB.model.Department;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Repository
@PropertySource("classpath:mysqlquery.properties")
public class ProcessdataDaoImpl implements ProessDataDao{
	
	private static Gson gson = new Gson();
	
	@Autowired
	JdbcTemplate jdbcJdbcTemplate;
	
	@Value("${UPDATEDATA}")
	private String updateData;
	
	@Override
	public String updateKafkaData(String data) {
		String responsedata = null;
		try {
			System.out.println("data===>"+data);
			JsonArray jsonObject = ServiceBUtility.fromJsonToObjec(data, JsonArray.class);
			List<Department> departments = new ArrayList<Department>();
			for(JsonElement jsonObject2: jsonObject) {
				Department d = gson.fromJson(jsonObject2, Department.class);
				if(d.getId()==1) {
					d.setGrade("A");
				}else {
					d.setGrade("B");
				}
				departments.add(d);
			}
			System.out.println("departments===>"+departments.size());
			jdbcJdbcTemplate.batchUpdate(updateData, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, departments.get(i).getGrade());
					ps.setInt(2, departments.get(i).getId());
				}
				
				@Override
				public int getBatchSize() {
					return departments.size();
				}
			});
			
			responsedata = ServiceBUtility.fromObjectToJson(departments);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responsedata;
	}

}
