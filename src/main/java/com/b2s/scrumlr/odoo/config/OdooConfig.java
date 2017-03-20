package com.b2s.scrumlr.odoo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import java.util.Properties;

@Configuration
public class OdooConfig {

    @Bean(name = "velocityEngine")
    public VelocityEngineFactoryBean getVelocityEngineFactoryBean() {
        final Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        final VelocityEngineFactoryBean bean = new VelocityEngineFactoryBean();
        bean.setVelocityProperties(properties);
        return bean;
    }

    @Bean
    public JavaMailSenderImpl getJavaMailSenderImpl() {
        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setUsername("b2s_china@163.com");
        mailSender.setPassword("b2ssymbio");
        mailSender.setPort(25);

        return mailSender;
    }
}
