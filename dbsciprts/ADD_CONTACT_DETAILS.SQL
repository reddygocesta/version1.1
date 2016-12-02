USE `contactrepository`;
DROP procedure IF EXISTS `ADD_CONTACT_DETAILS`;

DELIMITER $$
CREATE PROCEDURE `ADD_CONTACT_DETAILS`(
	IN `ContactEmailID` varchar(100),
	IN `FirstName` varchar(100),
	IN `MiddleName` varchar(100),
	IN `LastName` varchar(250),
	IN `JobTitle` varchar(100),
	IN `JobFunction` varchar(100),
	IN `contactSource` varchar(100),
	IN `PhoneNumber` varchar(250),
	IN `Extension` varchar(100),
	IN `Mobile` varchar(100),
	IN `CompanyId` int,
	IN `CompanyName` varchar(100),
	IN `companyWebSite` varchar(100),
	IN `Revenue` int,
	IN `CompanySize` varchar(250),
	IN `Industry` varchar(100),
	IN `SicCode` int,
	IN `CompanyEmailId` varchar(250),
	IN `AddressId` int,
	IN `AdressLine1` varchar(100),
	IN `AddressLine2` varchar(100),
	IN `Suite_no` varchar(100),
	IN `Country` int,
	IN `State` int,
	IN `City_id` varchar(100),
	IN `ZipCode_id` varchar(250),
	IN `HeadQuarters` varchar(100),
	IN `createdBy` varchar(100),
	IN `contactListName` varchar(100),
	OUT `p_result` varchar(100)



)
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN

declare lastinsertComapanyId int;
declare lastinsertAddressId int;
declare lastinsertcontactId int;
declare lastinsertrelAddressID int;

if(CompanyId > 0) then

	insert into contacts_details(contact_email_id,company_id,first_name,middle_name,last_name,
    job_title,job_function,contact_source,phone_number,extension,mobile,created_by,created_date,
    contact_list_name)values(ContactEmailID,CompanyId,FirstName,MiddleName,LastName,
    JobTitle,JobFunction,contactSource,PhoneNumber,Extension,Mobile,createdBy, now(),
    contactListName);
    SET  @lastinsertcontactId = LAST_INSERT_ID();
	
	if(AddressId > 0) then
	
		SELECT company_address_id INTO lastinsertrelAddressID FROM company_address_relationship WHERE company_id = CompanyId AND address_id = AddressId;

	else
	
		insert into address_details(address_line1,address_line2,country_id,state_id,city,zipcode,
		suite,head_quarters,created_by,created_date)values(AdressLine1,AddressLine2,Country,State,
		City_id,ZipCode_id,Suite_no,HeadQuarters,createdBy, now());
		SET @lastinsertAddressId = LAST_INSERT_ID();
		
		insert into company_address_relationship(address_id,company_id,created_by,created_date)
 	    values(@lastinsertAddressId,CompanyId,createdBy, now());
		SET @lastinsertrelAddressID= LAST_INSERT_ID();
		
	end if;
	
    insert into contact_company_relationship(contact_id,company_address_id,created_by,created_date)
    values(@lastinsertcontactId,@lastinsertrelAddressID,createdBy,now());
else

    insert into company_details(company_name,website,revenue,company_size,industry,sic_code,
    company_email_id,created_by,created_date) values(CompanyName,companyWebSite,Revenue,CompanySize,
    Industry,SicCode,CompanyEmailId,createdBy, now());
    SET @lastinsertComapanyId = LAST_INSERT_ID();
	
	insert into contacts_details(contact_email_id,company_id,first_name,middle_name,last_name,
    job_title,job_function,contact_source,phone_number,extension,mobile,created_by,created_date,
    contact_list_name)values(ContactEmailID,@lastinsertComapanyId ,FirstName,MiddleName,LastName,
    JobTitle,JobFunction,contactSource,PhoneNumber,Extension,Mobile,createdBy, now(),
    contactListName);
    SET  @lastinsertcontactId = LAST_INSERT_ID();
	
	insert into address_details(address_line1,address_line2,country_id,state_id,city,zipcode,
    suite,head_quarters,created_by,created_date)values(AdressLine1,AddressLine2,Country,State,
    City_id,ZipCode_id,Suite_no,HeadQuarters,createdBy, now());
	SET @lastinsertAddressId = LAST_INSERT_ID();
                 
    insert into company_address_relationship(address_id,company_id,created_by,created_date)
 	values(@lastinsertAddressId,@lastinsertComapanyId,createdBy,now());
	SET @lastinsertrelAddressID= LAST_INSERT_ID();
	
	insert into contact_company_relationship(contact_id,company_address_id,created_by,created_date)
    values(@lastinsertcontactId,@lastinsertrelAddressID,createdBy, now());
	
end if;
	
	SET p_result := 1 ;
	
  COMMIT;


END $$

DELIMITER ;