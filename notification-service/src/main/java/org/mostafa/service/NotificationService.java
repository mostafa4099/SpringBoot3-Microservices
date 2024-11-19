package org.mostafa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mostafa.model.OrderPlaceEventModel;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 9/25/2024 11:50 AM
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final JavaMailSender javaMailSender;

    public void send(OrderPlaceEventModel eventModel) {
//        MimeMessagePreparator messagePreparator = mimeMessage -> {
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//            messageHelper.setFrom("mostafa.sna@gmail.com");
//            messageHelper.setTo(eventModel.getEmail());
//            messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully", eventModel.getOrderNumber()));
//            messageHelper.setText("""
//                    Hi Mr. X
//
//                    Your order with order number %s is now placed successfully.
//
//                    Best Regards
//                    Spring Shop
//                    """, eventModel.getOrderNumber());
//        };
//        try {
//            javaMailSender.send(messagePreparator);
//            log.info("Order Notifcation email sent!!");
//        } catch (MailException e) {
//            log.error("Exception occurred when sending mail", e);
//            throw new RuntimeException("Exception occurred when sending mail to springshop@email.com", e);
//        }

        log.info(eventModel.getOrderNumber());
    }
}
