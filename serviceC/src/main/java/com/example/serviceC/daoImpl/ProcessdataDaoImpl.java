package com.example.serviceC.daoImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.example.serviceC.Util.ServiceCUtility;
import com.example.serviceC.dao.ProessDataDao;
import com.example.serviceC.model.ApplicationAccess;
import com.example.serviceC.model.Department;
import com.example.serviceC.rowmapper.ProcessDataMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

@Repository
@PropertySource("classpath:mysqlquery.properties")
public class ProcessdataDaoImpl implements ProessDataDao {

	private static Gson gson = new Gson();

	@Autowired
	JdbcTemplate jdbcJdbcTemplate;

	@Value("${GETROLEACCESS}")
	private String getRoleAccess;

	@Value("${GETDATAFROMAPPLICATIONACCESS}")
	private String updateRoleAccess;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public String updateKafkaData(String data) {
		String responsedata = null;
		try {
			JsonArray jsonObject = ServiceCUtility.fromJsonToObjec(data, JsonArray.class);
			List<Department> departments = new ArrayList<Department>();
			for (JsonElement jsonObject2 : jsonObject) {
				Department d = gson.fromJson(jsonObject2, Department.class);
				if (d.getId() == 1) {
					d.setGrade("A");
				} else {
					d.setGrade("B");
				}
				departments.add(d);
			}
			jdbcJdbcTemplate.batchUpdate(updateRoleAccess, new BatchPreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, departments.get(i).getId());
					ps.setString(2, departments.get(i).getRole());
				}

				@Override
				public int getBatchSize() {
					return departments.size();
				}
			});

			responsedata = ServiceCUtility.fromObjectToJson(departments);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responsedata;
	}

	@Override
	public List<ApplicationAccess> getAppliationAccess(String department) {
		List<ApplicationAccess> applicationAccesses = null;
		try {
			System.out.println("Data Call");
			applicationAccesses = jdbcJdbcTemplate.query(getRoleAccess, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, department);
				}
			}, new ProcessDataMapper());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return applicationAccesses;
	}

	@Override
	public void saveDataInRedis(String key, String data) {
	    redisTemplate.opsForValue().set(key, data);
	}
	
	@Override
	public String getDataFromRedis(String key) {
	    return (String) redisTemplate.opsForValue().get(key);
	}
}
