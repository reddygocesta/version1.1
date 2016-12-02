package com.sensiple.contactsrepository.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Session;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.util.Log4jConfigurer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This is class is used to create the beans while starting of the server
 * @author narasimhareddyk
 *
 */
@Configuration
@Import({SecurityConfig.class})
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = { "com.sensiple.contactsrepository.web.controller" })
@PropertySource("classpath:application.properties")
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Inject
	private Environment environment;
	
	private static final String DB_PASSWORD = "spring.datasource.password";
	private static final String DB_USERNAME = "spring.datasource.username";
	private static final String DB_JDBC_URL = "spring.datasource.url";
	private static final String DB_JDBC_DRIVER = "spring.datasource.driver-class-name";
	
	public static final String MAIL_SESSION = "java:comp/env/mail/Session";
	
    @Bean
    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
        return new ResourceUrlEncodingFilter();
    }
    
    @Bean(name = "jdbcTemlate")
	public JdbcTemplate getJdbcTemplate() throws NamingException {
		return new JdbcTemplate(getDataSource());
	}
    
    
    @Bean
	public DataSource getDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(environment.getProperty(DB_JDBC_URL));
		config.setUsername(environment.getProperty(DB_USERNAME));
		config.setPassword(environment.getProperty(DB_PASSWORD));
		config.setDriverClassName(environment.getProperty(DB_JDBC_DRIVER));
		config.setPoolName("Connection Pool");
		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    public void initLog4j() throws FileNotFoundException {
		Log4jConfigurer.initLogging("classpath:log4j.properties");
	}


    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }
    
    @Bean(name = "velocityEngine")
	public VelocityEngine velocityEngine() throws VelocityException, IOException{
		VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", 
				  "org.apache.velocity.runtime.resource.loader." + 
				  "ClasspathResourceLoader");
		factory.setVelocityProperties(props);
		return factory.createVelocityEngine();
	}
	
	/**
	 * This method is used to initialize the mail configuration.
	 * @return JavaMailSenderImpl
	 */
	@Bean(name = "mailSender")
	public JavaMailSenderImpl javaMailSenderImpl() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setSession(session());
	    return mailSender;
	}
	
	/**
	 * This method is used to configure the JNDI mail session.
	 * @return Session
	 */
	public Session session(){
	    JndiTemplate template = new JndiTemplate();
	    Session session = null;
	    try {
	         session = (Session) template.lookup(MAIL_SESSION);
	    } catch (NamingException e) {
	    }
	    return session;
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    //resolver.setMaxUploadSize(5242880);
	    return resolver;
	}
}
