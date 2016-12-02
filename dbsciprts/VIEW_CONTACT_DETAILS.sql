USE `contactrepository`;
DROP procedure IF EXISTS `View_CONTACT_DETAILS`;

DELIMITER $$
USE `contactrepository`$$
CREATE  PROCEDURE `View_CONTACT_DETAILS`(
	IN `contact_Id` INT

)
BEGIN
SELECT contact_id,contact_email_id,first_name,middle_name,last_name,contact_status,job_title,job_function,job_title_value,job_function_value,contact_source,
	created_by,phone_number,extension,mobile,created_date,approved_by, company_id,company_name,website,company_size,revenue_value,sic_code_id,sic_code_name,industry_id,industry_name,company_email_id,address_id,address_line1,address_line2,
	suite,country_id,country_name,state_id,state_name,city,zipcode,head_quarters
    FROM(
		SELECT cnd.contact_id,cnd.contact_email_id,cnd.first_name,cnd.middle_name,cnd.last_name,cnd.contact_status,cnd.job_title,cnd.job_function,
		jtd.job_title_value,jfd.job_function_value,cnd.contact_source,cnd.created_by,cnd.phone_number,cnd.extension,cnd.mobile,cnd.created_date,
		cnd.approved_by,cnd.company_id,cmd.company_name,cmd.website,csd.company_size,rd.revenue_value,scd.sic_code_id,scd.sic_code_name,id.industry_id,id.industry_name,
		cmd.company_email_id,
		ad.address_id,ad.address_line1,ad.address_line2,ad.suite,ct.country_id,ct.country_name,st.state_id,st.state_name,ad.city,ad.zipcode,ad.head_quarters
		from contacts_details cnd INNER JOIN company_details 
		cmd ON cnd.company_id = cmd.company_id
		INNER JOIN job_title_details jtd ON cnd.job_title=jtd.job_title_id
		INNER JOIN job_function_details jfd ON jfd.job_title_id=cnd.job_function
		INNER JOIN revenue_details rd ON cmd.revenue = rd.revenue_id
		INNER JOIN company_size_details csd ON cmd.company_size = csd.company_size_id
		INNER JOIN industry_details id ON cmd.industry = id.industry_id 
		INNER JOIN sic_code_details scd ON cmd.sic_code = scd.sic_code_id
		INNER JOIN contact_company_relationship ccr ON cnd.contact_id=ccr.contact_id
		INNER JOIN company_address_relationship car ON ccr.company_address_id =  car.company_address_id
		INNER JOIN address_details ad on car.address_id=ad.address_id
		INNER JOIN state_details st on ad.state_id=st.state_id
		INNER JOIN country_details ct on ad.country_id=ct.country_id
		where cnd.contact_id =contact_Id
		) as contactrow;
END$$

DELIMITER ;