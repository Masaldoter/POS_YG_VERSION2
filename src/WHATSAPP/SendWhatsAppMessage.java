/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WHATSAPP;

import com.twilio.Twilio;
import com.twilio.base.Creator;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.twiml.messaging.Media;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;  

public class SendWhatsAppMessage {
  // Ingresar el Account SID y Auth Token de Twilio
  public static final String ACCOUNT_SID = "Tu Account SID";
  public static final String AUTH_TOKEN = "Tu Auth Token";
  // Ingresar el número de teléfono de tu cuenta de WhatsApp Business
  public static final String FROM_NUMBER = "whatsapp:+14155238886";
  // Ingresar el número de teléfono del destinatario
  public static final String TO_NUMBER = "whatsapp:+1234567890";
  // Ingresar la URL del archivo PDF que deseas enviar
  public static final String PDF_URL = "https://ejemplo.com/ejemplo.pdf";
/*
  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    // Crear un objeto Media con la URL del archivo PDF
    Media media = new Media.Builder(URI.create(PDF_URL)).build();

    // Agregar el objeto Media a una lista de medios
    List<Media> mediaList = new ArrayList<>();
    mediaList.add(media);

    // Crear un mensaje con la lista de medios adjuntos
    Creator messageCreator = Message.creator(new PhoneNumber(TO_NUMBER),new PhoneNumber(FROM_NUMBER), mediaList);

    // Enviar el mensaje
    Message message = (Message) messageCreator.create();
    System.out.println(message.getSid());
  }*/
}
