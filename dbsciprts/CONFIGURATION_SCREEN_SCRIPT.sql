DROP TABLE IF EXISTS `configuration`;
CREATE TABLE IF NOT EXISTS `configuration` (
  `configuration_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `campaign_lead_time` int(11) NOT NULL,
  `allow_assign_contacts` int(11) NOT NULL,
  `delete_threshold` int(11) NOT NULL,
  `save_search_threshold` int(11) NOT NULL,
  `maximum_campaign_allowed` int(11) NOT NULL,
  `created_by` bigint(20) NOT NULL DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`configuration_id`),
  KEY `FK_configuration_users` (`created_by`),
  CONSTRAINT `FK_configuration_users` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- Dumping structure for table contactrepository.campaign_contact_status
DROP TABLE IF EXISTS `campaign_contact_status`;
CREATE TABLE IF NOT EXISTS `campaign_contact_status` (
  `campaign_contact_status_id` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`campaign_contact_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table contactrepository.campaign_contact_status: ~6 rows (approximately)
DELETE FROM `campaign_contact_status`;
/*!40000 ALTER TABLE `campaign_contact_status` DISABLE KEYS */;
INSERT INTO `campaign_contact_status` (`campaign_contact_status_id`, `status_name`) VALUES
	(1, 'Opened Mail'),
	(2, 'Did not Open Mail'),
	(3, 'Clicked Mail'),
	(4, 'Bounced'),
	(5, 'Unsubscribed'),
	(6, 'Interested');
	
DROP TABLE IF EXISTS `campaign_contact_status_relate`;
CREATE TABLE IF NOT EXISTS `campaign_contact_status_relate` (
  `campaign_contact_status_relate_id` int(11) NOT NULL AUTO_INCREMENT,
  `contact_status_id` int(11) DEFAULT NULL,
  `campaign_contact_status_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`campaign_contact_status_relate_id`),
  KEY `FK__contact_status` (`contact_status_id`),
  KEY `FK__campaign_contact_status` (`campaign_contact_status_id`),
  CONSTRAINT `FK__campaign_contact_status` FOREIGN KEY (`campaign_contact_status_id`) REFERENCES `campaign_contact_status` (`campaign_contact_status_id`),
  CONSTRAINT `FK__contact_status` FOREIGN KEY (`contact_status_id`) REFERENCES `contact_status` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP PROCEDURE IF EXISTS `SAVE_CAMPAING_CONFIGURATION`;
DELIMITER //
CREATE PROCEDURE `SAVE_CAMPAING_CONFIGURATION`(
	IN `p_user_id` BIGINT,
	IN `p_service_offering` INT,
	IN `p_state_id` INT,
	IN `p_country_id` INT,
	OUT `p_result` INT

)
BEGIN
		
      DECLARE IS_ALREADY_EXIST INT;
		SELECT count(*) INTO IS_ALREADY_EXIST FROM campaign_configuration cc WHERE owner_id = p_user_id AND service_offering_id = p_service_offering
		AND state_id = p_state_id AND country_id = p_country_id;
		CASE 
			WHEN IS_ALREADY_EXIST <= '0' THEN  
						INSERT INTO campaign_configuration(owner_id,service_offering_id,state_id,country_id)
						VALUES(p_user_id,p_service_offering,p_state_id,p_country_id);
					SET	p_result = 1;
		 	WHEN  IS_ALREADY_EXIST >= '1' THEN
					SET	p_result = -1;
		ELSE 
					SET	p_result = 0;
		END CASE;
END//
DELIMITER ;

/* Procedure to save field configuration*/

DROP PROCEDURE IF EXISTS `SAVE_FILED_CONFIGURATION`;
DELIMITER //
CREATE  PROCEDURE `SAVE_FILED_CONFIGURATION`(
	IN `p_user_id` BIGINT,
	IN `p_campaign_lead_time` INT,
	IN `p_allow_assign_contacts` INT,
	IN `p_delete_threshold` INT,
	IN `p_save_search_threshold` INT,
	IN `p_maximum_campaign_allowed` INT,
	IN `p_can_be_reached_contact_status` VARCHAR(50),
	IN `p_can_not_be_reached_contact_status` VARCHAR(50),
	OUT `p_result` INT
)
BEGIN
		DECLARE IS_RECORD_EXIST INT;
		DECLARE _next TEXT DEFAULT NULL;
		DECLARE _nextlen INT DEFAULT NULL;
		DECLARE _value TEXT DEFAULT NULL;
		DECLARE _p_next TEXT DEFAULT NULL;
		DECLARE _p_nextlen INT DEFAULT NULL;
		DECLARE _p_value TEXT DEFAULT NULL;
		DECLARE CAN_BE_REACHED_ID INT;
		DECLARE CAN_NOT_BE_REACHED_ID INT;
		
		SELECT  CASE 
        WHEN COUNT(*) >=1 THEN configuration.configuration_id
        ELSE 0
    END AS EXIST INTO IS_RECORD_EXIST FROM configuration;
    
    IF(IS_RECORD_EXIST = 0) THEN 

		INSERT INTO configuration(campaign_lead_time,allow_assign_contacts,
		delete_threshold,save_search_threshold,
		maximum_campaign_allowed,created_by,created_date) 
		VALUES(p_campaign_lead_time,p_allow_assign_contacts,
		p_delete_threshold,p_save_search_threshold,
		p_maximum_campaign_allowed,p_user_id,CURDATE());
		
	ELSE
		UPDATE configuration SET campaign_lead_time = p_campaign_lead_time
		,allow_assign_contacts = p_allow_assign_contacts
		,delete_threshold = p_delete_threshold
		,save_search_threshold = p_save_search_threshold
		,maximum_campaign_allowed = p_maximum_campaign_allowed
		,updated_time = CURDATE() WHERE configuration_id = IS_RECORD_EXIST ;
	
	END IF;
SELECT status_id INTO CAN_BE_REACHED_ID FROM contact_status WHERE status_name = 'Can Be Reached';	
SELECT status_id INTO CAN_NOT_BE_REACHED_ID FROM contact_status WHERE status_name = 'Can Not Be Reached';
TRUNCATE TABLE campaign_contact_status_relate;
	
iterator:
LOOP
  -- exit the loop if the list seems empty or was null;
  -- this extra caution is necessary to avoid an endless loop in the proc.
  IF LENGTH(TRIM(p_can_be_reached_contact_status)) = 0 OR p_can_be_reached_contact_status IS NULL THEN
    LEAVE iterator;
  END IF;

  -- capture the next value from the list
  SET _next = SUBSTRING_INDEX(p_can_be_reached_contact_status,',',1);

  -- save the length of the captured value; we will need to remove this
  -- many characters + 1 from the beginning of the string 
  -- before the next iteration
  SET _nextlen = LENGTH(_next);

  -- trim the value of leading and trailing spaces, in case of sloppy CSV strings
  SET _value = TRIM(_next);

  -- insert the extracted value into the target table
 INSERT INTO campaign_contact_status_relate(contact_status_id,campaign_contact_status_id) VALUES(CAN_BE_REACHED_ID,_next);

  -- rewrite the original string using the `INSERT()` string function,
  -- args are original string, start position, how many characters to remove, 
  -- and what to "insert" in their place (in this case, we "insert"
  -- an empty string, which removes _nextlen + 1 characters)
  SET p_can_be_reached_contact_status = INSERT(p_can_be_reached_contact_status,1,_nextlen + 1,'');
END LOOP;

statusiterator:
LOOP
  -- exit the loop if the list seems empty or was null;
  -- this extra caution is necessary to avoid an endless loop in the proc.
  IF LENGTH(TRIM(p_can_not_be_reached_contact_status)) = 0 OR p_can_not_be_reached_contact_status IS NULL THEN
    LEAVE statusiterator;
  END IF;

  -- capture the next value from the list
  SET _p_next = SUBSTRING_INDEX(p_can_not_be_reached_contact_status,',',1);

  -- save the length of the captured value; we will need to remove this
  -- many characters + 1 from the beginning of the string 
  -- before the next iteration
  SET _p_nextlen = LENGTH(_p_next);

  -- trim the value of leading and trailing spaces, in case of sloppy CSV strings
  SET _p_value = TRIM(_p_next);

  -- insert the extracted value into the target table
 INSERT INTO campaign_contact_status_relate(contact_status_id,campaign_contact_status_id) VALUES(CAN_NOT_BE_REACHED_ID,_p_next);

  -- rewrite the original string using the `INSERT()` string function,
  -- args are original string, start position, how many characters to remove, 
  -- and what to "insert" in their place (in this case, we "insert"
  -- an empty string, which removes _nextlen + 1 characters)
  SET p_can_not_be_reached_contact_status = INSERT(p_can_not_be_reached_contact_status,1,_p_nextlen + 1,'');
END LOOP;
		
		 SET p_result = ROW_COUNT();
END//
DELIMITER ;



-- Dumping structure for procedure contactrepository.GET_CONFIGURATION_CAMPAIGN_STATUS
DROP PROCEDURE IF EXISTS `GET_CONFIGURATION_CAMPAIGN_STATUS`;
DELIMITER //
CREATE  PROCEDURE `GET_CONFIGURATION_CAMPAIGN_STATUS`(
	IN `p_status_name` VARCHAR(50)

)
BEGIN
		DECLARE P_STATUS_ID INT;
		SELECT status_id INTO P_STATUS_ID FROM contact_status WHERE status_name = p_status_name;
		SELECT ccsr.campaign_contact_status_id as status_id,ccs.status_name as status_name from campaign_contact_status_relate ccsr 
		INNER JOIN contact_status cs ON cs.status_id = ccsr.contact_status_id
		INNER JOIN campaign_contact_status ccs ON ccs.campaign_contact_status_id = ccsr.campaign_contact_status_id
		WHERE cs.status_name = p_status_name;


END//
DELIMITER ;





