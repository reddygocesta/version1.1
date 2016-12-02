USE `contactrepository`;
DROP procedure IF EXISTS `DELETE_CONTACT`;

DELIMITER $$
USE `contactrepository`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `DELETE_CONTACT`(IN p_contact_ids VARCHAR(250), OUT p_result INT)
BEGIN
	DECLARE count INT Default 0 ;
      DECLARE tmp_contact_id VARCHAR(255);
      
      DROP TEMPORARY TABLE IF EXISTS tmp_contat_id_list;
      
       create TEMPORARY table tmp_contat_id_list(id int);
      contact_Id_loop: LOOP
         SET count=count+1;
         SET tmp_contact_id = SPLIT_STR(p_contact_ids,",",count);
         IF tmp_contact_id = '' THEN
            LEAVE contact_Id_loop;
         END IF;
         #Do Inserts into temp table here with str going into the row
         insert into  tmp_contat_id_list values (tmp_contact_id);
   END LOOP contact_Id_loop;
   
    DELETE FROM contact_company_relationship WHERE contact_id IN (SELECT id FROM tmp_contat_id_list);
    DELETE FROM contacts_details WHERE contact_id IN (SELECT id FROM tmp_contat_id_list);
	#drop  table tmp_contat_id_list;
    
    SET p_result := 1;
END$$

DELIMITER ;