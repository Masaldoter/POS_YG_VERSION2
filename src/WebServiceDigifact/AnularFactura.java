
package WebServiceDigifact;

import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import ModeloWebService.RespuestaDatosAnularFactura;
import com.github.underscore.lodash.U;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Masaldoter
 */
public class AnularFactura {
    public RespuestaDatosAnularFactura AnularFactura(){
        RespuestaDatosAnularFactura RDAF = new RespuestaDatosAnularFactura();
            try {
                ObtenerToken OT = new ObtenerToken();
                OT.ObtenerToken();

                URL url = new URL("https://felgtaws.digifact.com.gt/gt.com.fel.api.v3/api/FelRequestV2?NIT=0000"+PARAMETROS_EMPRESA.NIT_EMPRESA+"&TIPO=ANULAR_FEL_TOSIGN&FORMAT=XML&USERNAME=" + PARAMETROS_EMPRESA.USUARIO_CERTIFICADOR);
                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.setRequestProperty("Content-Type", "application/json");
                http.setRequestProperty("Accept", "application/json");
                http.setRequestProperty("Authorization", PARAMETROS_EMPRESA.TOKEN_CERTIFICADOR);

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(new File("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\GTAnulacionDocumento.xml"));
                document.getDocumentElement().normalize();

                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer t = tf.newTransformer();
                StringWriter sw = new StringWriter();
                t.transform(new DOMSource(document), new StreamResult(sw));
                String XMLSinCertificar = sw.toString();
                byte[] out = XMLSinCertificar.getBytes(StandardCharsets.UTF_8);

                OutputStream stream = http.getOutputStream();
                stream.write(out);
                if (http.getResponseCode() == 200) {
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
                RDAF = Lectura();
                    RDAF.setESTADO(true);
            } else {
                    RDAF.setESTADO(false);
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "¡NO SE PUDO REALIZAR LA ANULACIÓN!\n"+http.getResponseMessage(), DesktopNotify.ERROR, 10000L);
            }
            }   catch (MalformedURLException ex) {
            Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "¡NO SE PUDO REALIZAR LA ANULACIÓN!\n", DesktopNotify.ERROR, 10000L);
        } catch (IOException ex) {
            Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RDAF;
    }
    
    public void writeFile(String yourXML){
    BufferedWriter out = null;
    try { 
            out = new BufferedWriter(new FileWriter("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\AnularFactura.xml"));
            try {
                out.write(yourXML);
            } catch (IOException e) {
            } finally {
                out.close();
            }
    } catch (IOException ex) {
            Logger.getLogger(AnularFactura.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(AnularFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
   
    public RespuestaDatosAnularFactura Lectura(){
       RespuestaDatosAnularFactura RDAF = new RespuestaDatosAnularFactura();
       try {
            Reader reader = new InputStreamReader(new FileInputStream("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\AnularFactura.xml"),"UTF-8");
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
                    RDAF.setCodigo(eElement.getAttribute("Codigo"));
                    RDAF.setMensaje(eElement.getElementsByTagName("Mensaje").item(0).getTextContent());
                    RDAF.setAcuseReciboSAT(eElement.getElementsByTagName("AcuseReciboSAT").item(0).getTextContent());
                    RDAF.setCodigosSAT(eElement.getAttribute("CodigosSAT"));  
                    RDAF.setResponseDATA1(eElement.getElementsByTagName("ResponseDATA1").item(0).getTextContent());
                    RDAF.setResponseDATA2(eElement.getElementsByTagName("ResponseDATA2").item(0).getTextContent());
                    RDAF.setResponseDATA3(eElement.getElementsByTagName("ResponseDATA3").item(0).getTextContent());
                    RDAF.setAutorizacion(eElement.getElementsByTagName("Autorizacion").item(0).getTextContent());
                    RDAF.setNIT_COMPRADOR(eElement.getElementsByTagName("NIT_COMPRADOR").item(0).getTextContent());
                    RDAF.setNOMBRE_COMPRADOR(eElement.getElementsByTagName("NOMBRE_COMPRADOR").item(0).getTextContent());
                }
            }
        }
        catch(IOException | SAXException | ParserConfigurationException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                DesktopNotify.showDesktopMessage("ERRÓR AL LEER ANULACIÓN", "NO SE PUDO OBTENER LOS DATOS DE LA ANULACIÓN \n"+e, DesktopNotify.ERROR, 10000L);
        } 
        return RDAF;
   }
}
