package com.b2s.scrumlr.odoo.service;

import com.b2s.scrumlr.odoo.model.User;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MailSendService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailSendService.class);
    private static final String EMAIL_USER = "b2s_china@163.com";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    public void sendEmailForLogTime(final List<User> users) {
        final List<String> mailAddresses =
            users
                .stream()
                .map(User::getMailAddress)
                .collect(Collectors.toList());

        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(final MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mailAddresses.toArray(new String[mailAddresses.size()]));
                message.setFrom(EMAIL_USER);
                message.setSubject("Odoo[Log Time]");
                final Map<String, Object> model = new HashMap<>();
                model.put("users", users);
                final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                    "template/mail-logtime.vm", "UTF-8", model);

                message.setText(text, true);
            }
        };
        this.javaMailSender.send(preparator);
        LOGGER.info("***************** Send Mail Success ! ******************");
    }

    public void sendEmailForSubmit(final List<User> users){
        final List<String> mailAddresses =
            users
                .stream()
                .map(User::getMailAddress)
                .collect(Collectors.toList());

        final MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(final MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mailAddresses.toArray(new String[mailAddresses.size()]));
                message.setFrom(EMAIL_USER);
                message.setSubject("Odoo[Submit Timesheet]");
                final Map<String, Object> model = new HashMap<>();
                model.put("users", users);
                final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                    "template/mail-submit.vm", "UTF-8", model);

                message.setText(text, true);
            }
        };
        this.javaMailSender.send(preparator);
        LOGGER.info("***************** Send Mail Success ! ******************");
    }
}
