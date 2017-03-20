package com.b2s.scrumlr.odoo.listener;

import com.b2s.scrumlr.odoo.model.EmailEvent;
import com.b2s.scrumlr.odoo.model.EmailType;
import com.b2s.scrumlr.odoo.service.MailSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    private static final Logger LOG = LoggerFactory.getLogger(EmailListener.class);

    @Autowired
    private MailSendService mailSendService;

    @EventListener
    public void sendNotification(final EmailEvent emailEvent) {
        LOG.info("Received email event-{}", emailEvent.getEmailType());
        if(emailEvent.getEmailType()== EmailType.LOGTIME){
            mailSendService.sendEmailForLogTime(emailEvent.getUsers());
        }else{
            mailSendService.sendEmailForSubmit(emailEvent.getUsers());
        }
        LOG.info("successfully called the service to send mail-{}", emailEvent.getEmailType());
    }
}
