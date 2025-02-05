package dev.sandboxapp.totp.services;

import dev.sandboxapp.totp.config.MailProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@AllArgsConstructor
public class EmailService {
  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;
  private final MailProperties mailProperties;

  public String generateEmailBody(String template, Map<String, Object> inputs) {
    Context context = new Context();
    if (inputs != null) {
      inputs.forEach(context::setVariable);
    }
    return templateEngine.process(template, context);
  }

  public void sendEmail(String to, String subject, String body) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setFrom(mailProperties.getUsername());
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(body, true);
    mailSender.send(message);
  }

  public void sendEmail(String to, String subject, String template, Map<String, Object> inputs) throws MessagingException {
    String body = generateEmailBody(template, inputs);
    this.sendEmail(to, subject, body);
  }
}
