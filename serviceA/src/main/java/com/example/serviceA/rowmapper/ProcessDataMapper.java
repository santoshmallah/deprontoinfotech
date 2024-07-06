package com.example.serviceA.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.serviceA.model.Department;

public class ProcessDataMapper implements RowMapper<Department>{

	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		Department department =new Department();
		department.setId(rs.getInt("id"));
		department.setDeptname(rs.getString("deptname"));
		department.setRole(rs.getString("role"));
		return department;
	}

}
