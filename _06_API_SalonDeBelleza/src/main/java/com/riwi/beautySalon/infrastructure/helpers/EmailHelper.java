package com.riwi.beautySalon.infrastructure.helpers;

import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailHelper {
    // inyectar el servicio de email de la libreria
    private final JavaMailSender mailSender;

    public void sendMail(String destiny,String nameClient, String nameEmployee, LocalDateTime date){
        MimeMessage message = mailSender.createMimeMessage();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateAppointment = date.format(formatter);
        String htmlContent = this.readHTMLTemplate(nameClient, nameEmployee, nameEmployee);

        try {
            message.setFrom(new InternetAddress("carlito1999@live.com"));
            message.setSubject("Confirmacion de cita en Beauty Salon");

            message.setRecipients(MimeMessage.RecipientType.TO, destiny);
            message.setContent(htmlContent,MediaType.TEXT_HTML_VALUE);

            mailSender.send(message);
            System.out.println("Email enviado");
        } catch (Exception e) {
            System.out.println("no se pudo enviar el email"+e.getMessage());
        }

    }

    private String readHTMLTemplate(String nameClient, String nameEmployee, String date){
        //indicar en donde se encuentra el template
        final java.nio.file.Path path = Paths.get("src/main/resources/emails/email_template.html");

        try (var lines = Files.lines(path)){
            var html = lines.collect(Collectors.joining());

            return html.replace("{name}", nameClient).replace("{employee}", nameEmployee).replace("{date}", date);
        } catch (Exception e) {
            System.out.println("no se pudo leer el html");
            throw new  RuntimeException();
        }
    }

}
