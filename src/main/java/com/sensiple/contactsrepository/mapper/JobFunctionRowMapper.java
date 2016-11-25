package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.JobFunction;

public class JobFunctionRowMapper implements RowMapper<JobFunction> {

	@Override
	public JobFunction mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		JobFunction jobFunction=new JobFunction();
		jobFunction.setJobFunctionId(rs.getInt("job_function_id"));
		jobFunction.setJobFunctionName(rs.getString("job_function_value"));
		
		return jobFunction;
	}

}
