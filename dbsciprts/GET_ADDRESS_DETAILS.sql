USE `contactrepository`;
DROP procedure IF EXISTS `GET_ADDRESS_DETAILS`;

DELIMITER $$
USE `contactrepository`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GET_ADDRESS_DETAILS`(
	IN `p_company_id` INT
)
BEGIN

SELECT ad.address_id,ad.address_line1,ad.address_line2,ad.country_id,ad.state_id,
		cd.country_name,sd.state_name,city,zipcode,suite,head_quarters 
FROM address_details ad 
INNER JOIN company_address_relationship car ON  ad.address_id = car.address_id 
INNER JOIN country_details cd ON ad.country_id=cd.country_id
INNER JOIN state_details sd ON ad.state_id=sd.state_id
WHERE  car.company_id=p_company_id;

END$$

DELIMITER ;