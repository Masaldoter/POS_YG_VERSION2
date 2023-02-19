/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gmail;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Masaldoter
 */
public class EnvioGmail {
    
    public List LeerMensajes() throws MessagingException, IOException {
        List<Gmail> Listapro = new ArrayList();
        Gmail pro1;
        
        String host = "mail.ferreteriaelamigo.space";
    String username = "ElAmigo@ferreteriaelamigo.space";
    String password = "Aldo-40805837";

    // Create empty properties
    Properties props = new Properties();

    // Get session
    Session session = Session.getDefaultInstance(props, null);

    // Get the store
    Store store = session.getStore("pop3");

    // Connect to store
    store.connect(host, username, password);

    // Get folder
    Folder folder = store.getFolder("INBOX");

    // Open read-only
    folder.open(Folder.READ_ONLY);

    BufferedReader reader = new BufferedReader (
      new InputStreamReader(System.in));

    // Get directory
    Message message[] = folder.getMessages();
    int Total= folder.getMessageCount();

    for (int i=0, n=message.length; i<n; i++) {

       // Display from field and subject
       System.out.println(i + ": " + message[i].getFrom()[0] 
         + "\t" + message[i].getSubject());
      String line = reader.readLine();
      
       pro1= new Gmail();
                pro1.setReceptor(i + ": " + message[i].getFrom()[Total] 
         + "\t" + message[i].getSubject());
               Listapro.add(pro1);
    }

    // Close connection 
    folder.close(false);
    store.close();
    return Listapro;
    }
    
    /**
     * Create a message from an email.
     *
     * @param emailContent Email to be set to raw of message
     * @return a message containing a base64url encoded email
     * @throws IOException
     * @throws MessagingException
     */
   /* public static Message createMessageWithEmail(MimeMessage emailContent)
            throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }
    */
    
    
    
    
    public void enviarConGmail(String destinatario, String destinatarioAlterno, String asunto, String bodyText, String Remitente, String Contra, File file, File Foto, String Factura) {
    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
   /*  String remitente = Remitente;  //Para la dirección nomcuenta@gmail.com
     String Clave =Contra;
    Properties props = System.getProperties();
    
    props.put("mail.smtp.host", "mail.ferreteriaelamigo.space");  //El servidor SMTP de Google
    props.put("mail.smtp.user", remitente);
    props.put("mail.smtp.clave", Clave);    //La clave de la cuenta
    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
        message.setFrom(new InternetAddress(remitente));
        
        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
        if(destinatarioAlterno != null){
        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera    
        }
        message.setHeader("FERRETERÍA EL AMIGO", ":)");
        message.setSubject(asunto);
        message.setText(bodyText);
        
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(bodyText, "text/plain");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        mimeBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        //DataSource sourceImagen = new FileDataSource(Foto);

        message.setSentDate(new Date());


        mimeBodyPart.setDataHandler(new DataHandler(source));
        //mimeBodyPart.setDataHandler(new DataHandler(sourceImagen));
        mimeBodyPart.setFileName(file.getName());
        //mimeBodyPart.setFileName(Foto.getName());
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        
         try (Transport transport = session.getTransport("smtp")) {
             transport.connect("mail.ferreteriaelamigo.space", remitente, Clave);
             transport.sendMessage(message, message.getAllRecipients());
             
             DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("¡CORREO ENVIADO!","¡EL CORREO CON FACTURA "+Factura+" SE ENVIÓ CORRECTAMENTE!", DesktopNotify.SUCCESS, 14000L);
         }
    }
    catch (MessagingException me) {
        me.printStackTrace();   //Si se produce un error
    }*/
}
}
