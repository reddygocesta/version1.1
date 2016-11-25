package com.sensiple.contactsrepository.utils;


import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.sensiple.contactsrepository.model.MailConfig;

/** 
 * Mail Util class is used to configure Mail Properties.
 */
public class MailUtil {

	private static final Logger LOGGER = Logger.getLogger(MailUtil.class);
	
	/**
	 * send Mail method is used to set Mail headers and contents.
	 * @param velocityEngine
	 * @param mailSender
	 * @param mailConfig
	 * @return status
	 */
	public boolean sendMail(VelocityEngine velocityEngine,
			JavaMailSenderImpl mailSender, final MailConfig mailConfig) {

		LOGGER.info("Send Mail method invoked");

		MimeMessage message = mailSender.createMimeMessage();
		boolean isMailSent = false;
		final String KEY = "mailConfig";
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailConfig.getFromAddress());
			helper.setTo(mailConfig.getToAddress());
			helper.setSubject(mailConfig.getSubject());
			Map<String, Object> model = new HashMap<String, Object>();
			model.put(KEY, mailConfig);
			StringWriter result = new StringWriter();
			VelocityEngineUtils.mergeTemplate(velocityEngine,
					mailConfig.getBodyTemplate(), CharEncoding.UTF_8, model, result);
			helper.setText(result.toString(), true);
			mailSender.send(message);
			isMailSent = true;
			
		} catch (Exception e) {
			LOGGER.error("Exception occurred in send Mail method : "+ExceptionUtils.getStackTrace(e));
		}
		
		return isMailSent;
	}
	
}
