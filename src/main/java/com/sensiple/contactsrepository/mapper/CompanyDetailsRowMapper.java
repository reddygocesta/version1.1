package com.sensiple.contactsrepository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sensiple.contactsrepository.model.CompanyDetails;
import com.sensiple.contactsrepository.model.CompanySizeDetails;
import com.sensiple.contactsrepository.model.IndustryDetails;
import com.sensiple.contactsrepository.model.RevenueDetails;
import com.sensiple.contactsrepository.model.SicCodeDetails;

public class CompanyDetailsRowMapper implements RowMapper<CompanyDetails>{

	@Override
	public CompanyDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		CompanyDetails companyDetails = new CompanyDetails();
		companyDetails.setCompanyId(rs.getLong("company_id"));
		companyDetails.setCompanyName(rs.getString("company_name"));
		companyDetails.setCompanyEmailId(rs.getString("company_email_id"));
		companyDetails.setWebsite(rs.getString("website"));
		
		CompanySizeDetails companySizeDetails = new CompanySizeDetails();
		companySizeDetails.setSizeId(rs.getInt("company_size_id"));
		companySizeDetails.setCompanySizeName(rs.getString("company_size"));
		
		companyDetails.setCompanySizeDetails(companySizeDetails);
		
		RevenueDetails revenueDetails = new RevenueDetails();
		revenueDetails.setRevenueId(rs.getInt("revenue_id"));
		revenueDetails.setRevenueName(rs.getString("revenue_value"));
		
		companyDetails.setRevenueDetails(revenueDetails);
		
		IndustryDetails industryDetails = new IndustryDetails();
		industryDetails.setIndustryId(rs.getInt("industry_id"));
		industryDetails.setIndustryName(rs.getString("industry_name"));
		
		SicCodeDetails sicCodeDetails = new SicCodeDetails();
		sicCodeDetails.setSicCodeId(rs.getInt("sic_code_id"));
		sicCodeDetails.setSicCodeName(rs.getString("sic_code_name"));
		
		companyDetails.setIndustryDetails(industryDetails);
		companyDetails.setSicCodeDetails(sicCodeDetails);
		
		return companyDetails;
	}

}
