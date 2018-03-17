//package com.tecsoluction.tecshop.servico;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SenderMailService  {
//	
//	@Autowired
//    private JavaMailSender mailSender;
//
//	
//    public  void enviar() {
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo("windson.m.bezerra@gmail.com");
//        email.setSubject("Teste envio de e-mail");
//        email.setText("Enviei este e-mail usando Spring Boot.");
//        mailSender.send(email);
//    }
//
//}
