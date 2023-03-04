
package WebServiceDigifact;

import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import ModeloWebService.TokenParametros;
import com.github.underscore.lodash.U;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ObtenerToken {
    PARAMETROS_EMPRESA P_E;
    public void ObtenerToken(){
            try {
                P_E = new PARAMETROS_EMPRESA();
              //URL url = new URL("https://felgttestaws.digifact.com.gt/gt.com.fel.api.v3/api/login/get_token?Username=GT.000044653948.FELTEST48&Password=Aldo_40805837");
                //URL url = new URL("https://felgtaws.digifact.com.gt/gt.com.fel.api.v3/api/login/get_token?Username=GT.0000" + PARAMETROS_EMPRESA.NIT_EMPRESA + "." + PARAMETROS_EMPRESA.USUARIO_CERTIFICADOR+"&Password="+PARAMETROS_EMPRESA.CONTRASENIA_CERTIFICADOR);
                /*URL url = new URL("https://felgtaws.digifact.com.gt/gt.com.fel.api.v3/api/login/get_token?Username="+
                        "GT.0000"+P_E.NIT_EMPRESA+"."+P_E.USUARIO_CERTIFICADOR+"&Password="+P_E.CONTRASENIA_CERTIFICADOR);*/
                URL url = new URL("https://felgtaws.digifact.com.gt/gt.com.fel.api.v3/api/login/get_token?Username="+
                        "GT.000047896272.47896272&Password=EPyg_1980");
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setRequestProperty("Content-Type", "application/json");
                http.setRequestProperty("Accept", "application/json");
                String data = "{\n" +
                "\"Username\":\""+"GT.000047896272.47896272\",\n" +
                "\"Password\":\"EPyg_1980\"\n" +        
                "}";
                byte[] out = data.getBytes(StandardCharsets.UTF_8);
                
                OutputStream stream = http.getOutputStream();
                stream.write(out);
                if(http.getResponseCode()==200){
                StringBuilder response;
                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(http.getInputStream()))) {
                    String inputLine;
                    response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }
                    String xml = U.jsonToXml(response.toString());
                    writeFile(xml);
                    http.disconnect();

                    TokenParametros TP = new TokenParametros();
                    TP = Lectura();
                    P_E.TOKEN_CERTIFICADOR = TP.getTokenGenerado();
                } else {
                    System.out.println("jijijija");
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "NO SE PUDO OBTENER EL TOKEN", DesktopNotify.ERROR, 10000L);
                }

            } catch (MalformedURLException ex) {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "NO SE PUDO OBTENER EL TOKEN\n VERIFICA TU CONEXIÓN A LA RED", DesktopNotify.ERROR, 10000L);
            Logger.getLogger(ObtenerToken.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObtenerToken.class.getName()).log(Level.SEVERE, null, ex);
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "NO SE PUDO OBTENER EL TOKEN\n VERIFICA TU CONEXIÓN A LA RED", DesktopNotify.ERROR, 10000L);
        }
    }
    
    public void writeFile(String yourXML){
    BufferedWriter out = null;
    try { 
            out = new BufferedWriter(new FileWriter("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\GenerarToken.xml"));
            try {
                out.write(yourXML);
            } catch (IOException e) {
            } finally {
                out.close();
            }
    } catch (IOException ex) {
            Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
   
   public TokenParametros Lectura(){
       TokenParametros TP= new TokenParametros();
       try {
            Reader reader = new InputStreamReader(new FileInputStream("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\GenerarToken.xml"),"UTF-8");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");
            DocumentBuilder docBuilder;
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docBuilder.parse(is);
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("root");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    TP.setTokenGenerado(eElement.getElementsByTagName("Token").item(0).getTextContent());
                }
            }
        }
        catch(IOException e) {
            System.out.println(e);
        } catch (SAXException | ParserConfigurationException ex) {
            Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
        } 
       return TP;
   }
}
