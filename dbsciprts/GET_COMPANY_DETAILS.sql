
USE `contactrepository`;
DROP procedure IF EXISTS `GET_COMPANY_DETAILS`;

DELIMITER $$
USE `contactrepository`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_COMPANY_DETAILS`(IN `p_company_name` VARCHAR(250))
BEGIN
	SELECT cd.company_id,cd.company_name,cd.website,cd.company_email_id,cd.company_size,cd.revenue,
		czd.company_size_id,czd.company_size,rd.revenue_id,rd.revenue_value,id.industry_id,id.industry_name,
        scd.sic_code_id,scd.sic_code_name
        FROM company_details cd
	INNER JOIN company_size_details czd ON czd.company_size_id = cd.company_size
	INNER JOIN revenue_details rd ON rd.revenue_id = cd.revenue
	INNER JOIN industry_details id ON id.industry_id = cd.industry
    INNER JOIN sic_code_details scd ON scd.sic_code_id = cd.sic_code
	WHERE  company_name LIKE CONCAT('%', p_company_name , '%'); 
END$$

DELIMITER ;
