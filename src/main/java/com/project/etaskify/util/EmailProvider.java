package com.project.etaskify.util;

import com.project.etaskify.model.entity.User;
import com.project.etaskify.model.entity.UserTemp;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class EmailProvider {

    private final JavaMailSender mailSender;

    public void sendRegistrationOtp(UserTemp user) throws MessagingException, UnsupportedEncodingException {
        String content = "Dear [[name]],<br>"
                + "Your otp code to verify your registration:<br>"
                + "[[OTP]]<br>"
                + "Thank you,<br>"
                + "Best Regards, etaskify organization.";
        String subject = "Please verify your registration";

        content = content.replace("[[name]]", user.getUsername());

        content = content.replace("[[OTP]]", String.valueOf(user.getOtpCode()));

        sendEmail(user.getEmail(), content, subject);
    }

    public void sendWelcomeToUser(User user, String adminUsername, String organizationName) throws MessagingException, UnsupportedEncodingException {
        String content = "Dear user [[USER_KEY]],<br>"
                + "You have been registered by admin.<br>"
                + "Your admin is [[ADMIN_KEY]].<br>"
                + "Your organization is [[ORGANIZATION_KEY]]<br>"
                + "Please ask the credentials from your admin.<br>"
                + "Thank you,<br>"
                + "Best Regards, etaskify organization.";
        String subject = "Welcome to etaskify!!!";

        content = content.replace("[[USER_KEY]]", user.getUsername());
        content = content.replace("[[ADMIN_KEY]]", adminUsername);
        content = content.replace("[[ORGANIZATION_KEY]]", organizationName);

        sendEmail(user.getEmail(), content, subject);
    }

    private void sendEmail(String toAddress, String content, String subject) throws MessagingException, UnsupportedEncodingException {
        String fromAddress = "todolistorganization@gmail.com";
        String senderName = "etaskify organization";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

}