/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gmail;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
   public void ENVIAR_CORREO(String EMISOR, String CONTRASENIA, String RECEPTOR, String ASUNTO, String CUERPO, String RUTA_ARCHIVO) {
      String host = "smtp.gmail.com";

      Properties properties = System.getProperties();
      properties.put("mail.smtp.host", host);
      properties.put("mail.smtp.port", "587");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");

      Session session = Session.getDefaultInstance(properties,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(EMISOR, CONTRASENIA);
            }
         });

      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(EMISOR));
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(RECEPTOR));
         message.setSubject(ASUNTO);

         // Crear MimeMultipart y MimeBodyPart
         MimeMultipart multipart = new MimeMultipart();
         BodyPart messageBodyPart = new MimeBodyPart();

          // Agregar texto al cuerpo del correo electrónico
          messageBodyPart.setText(CUERPO);

          // Agregar el cuerpo del correo electrónico al MimeMultipart
          multipart.addBodyPart(messageBodyPart);

          // Crear un objeto MimeBodyPart para el archivo adjunto
          messageBodyPart = new MimeBodyPart();
         
         File ARCHIVO = new File(RUTA_ARCHIVO);
         // Ubicación del archivo PDF que quieres enviar
         String filename = RUTA_ARCHIVO;

         // Cargar el archivo PDF en un objeto DataSource
         DataSource source = new FileDataSource(ARCHIVO);

         // Adjuntar el archivo DataSource al objeto MimeBodyPart
         messageBodyPart.setDataHandler(new DataHandler(source));

         // Establecer el nombre del archivo adjunto
         messageBodyPart.setFileName(ARCHIVO.getName());

         // Agregar el objeto MimeBodyPart al MimeMultipart
         multipart.addBodyPart(messageBodyPart);

         // Establecer el contenido del correo electrónico en el MimeMultipart
         message.setContent(multipart);

         // Enviar el correo electrónico
         Transport.send(message);
         System.out.println("Correo enviado exitosamente...");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}
