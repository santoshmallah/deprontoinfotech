package com.example.serviceC.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.serviceC.model.ApplicationAccess;

public class ProcessDataMapper implements RowMapper<ApplicationAccess>{

	@Override
	public ApplicationAccess mapRow(ResultSet rs, int rowNum) throws SQLException {
		ApplicationAccess applicationAccess =new ApplicationAccess();
		applicationAccess.setId(rs.getInt("id"));
		applicationAccess.setRole(rs.getString("role"));
		return applicationAccess;
	}

}
