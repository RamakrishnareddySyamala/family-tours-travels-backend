/*
 * package com.familytours.familytourstravels.controller;
 * 
 * import org.springframework.mail.javamail.JavaMailSender; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import jakarta.mail.Transport;
 * 
 * @RestController public class MailTestController {
 * 
 * private final JavaMailSender mailSender;
 * 
 * public MailTestController(JavaMailSender mailSender) { this.mailSender =
 * mailSender; }
 * 
 * @GetMapping("/api/test/mail") public String testMailConnection() {
 * 
 * try { var senderImpl = (org.springframework.mail.javamail.JavaMailSenderImpl)
 * mailSender;
 * 
 * Transport transport = senderImpl.getSession().getTransport("smtp");
 * 
 * transport.connect( senderImpl.getHost(), senderImpl.getPort(),
 * senderImpl.getUsername(), senderImpl.getPassword() );
 * 
 * transport.close();
 * 
 * return "SMTP connection successful";
 * 
 * } catch (Exception e) {
 * 
 * e.printStackTrace();
 * 
 * return "SMTP connection failed: " + e.getClass().getSimpleName() + " - " +
 * e.getMessage(); } } }
 */