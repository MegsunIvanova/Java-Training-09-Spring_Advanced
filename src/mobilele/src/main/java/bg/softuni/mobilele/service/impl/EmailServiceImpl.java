package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.service.EmailService;
import bg.softuni.mobilele.service.aop.WarnIfExecutionExceeds;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final String mobileleEmail;

    public EmailServiceImpl(TemplateEngine templateEngine,
                            JavaMailSender javaMailSender,
                            @Value("${mail.mobilele}") String mobileleEmail) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.mobileleEmail = mobileleEmail;
    }

    @WarnIfExecutionExceeds(timeInMillis = 600 )
    @Override
    public void sendRegistrationEmail(String userEmail, String userName, String activationCode) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage); //Builder for mimeMessage

        try {
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setFrom(mobileleEmail);
            mimeMessageHelper.setReplyTo(mobileleEmail);
            mimeMessageHelper.setSubject("Welcome to Mobilele");
            mimeMessageHelper.setText(generateRegistrationEmailBody(userName, activationCode), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateRegistrationEmailBody(String username, String activationCode) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("activation_code", activationCode);

        return templateEngine.process("email/registration-email", context);
    }
}
