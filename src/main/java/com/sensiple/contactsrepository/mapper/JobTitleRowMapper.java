package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.JobTitle;

public class JobTitleRowMapper implements RowMapper<JobTitle> {

	@Override
	public JobTitle mapRow(ResultSet rs, int rowNo) throws SQLException {
		
		
		JobTitle jobTitle=new JobTitle();
		jobTitle.setJobTitleId(rs.getInt("job_title_id"));
		jobTitle.setJobTitleName(rs.getString("job_title_value"));
		
		return jobTitle;
	}

}
