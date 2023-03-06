package WebServiceDigifact;

import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import ModeloWebService.DatosConsultarDTE;
import com.github.underscore.lodash.U;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
/**
 *
 * @author Masaldoter
 */
public class ConsultarDTE {

    public DatosConsultarDTE ObtenerDTE(DatosConsultarDTE DCDTE) {
        DatosConsultarDTE DPC = new DatosConsultarDTE();
        try {
            //ObtenerToken
            ObtenerToken OT = new ObtenerToken();
            OT.ObtenerToken();

            URL url = new URL("https://felgtaws.digifact.com.gt/felapiv2/api/sharedInfo?NIT=0000" + PARAMETROS_EMPRESA.NIT_EMPRESA + "&DATA1=SHARED_GETDTEINFO&DATA2=AUTHNUMBER|" + DCDTE.getGUID() + "&USERNAME=" + PARAMETROS_EMPRESA.USUARIO_CERTIFICADOR);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Authorization", PARAMETROS_EMPRESA.TOKEN_CERTIFICADOR);
            if (http.getResponseCode() == 200) {
                DPC.setEstado(true);
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

                DPC = Lectura();
                DPC.setEstado(true);
            } else {
                DPC.setEstado(false);
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "NO SE PUDO OBTENER LOS DATOS DEL DTE:" + DCDTE.getGUID() + "\n+" + http.getResponseCode(), DesktopNotify.ERROR, 10000L);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "NO SE PUDO OBTENER LOS DATOS DEL DTE:" + DCDTE.getGUID() + "\n" + ex, DesktopNotify.ERROR, 10000L);
        } catch (IOException ex) {
            Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "NO SE PUDO OBTENER LOS DATOS DEL DTE:" + DCDTE.getGUID() + "\n" + ex, DesktopNotify.ERROR, 10000L);
        }
        return DPC;
    }

    public static void writeFile(String yourXML) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\ConsultaDTE.xml"));
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

    public static DatosConsultarDTE Lectura() {
        DatosConsultarDTE DPC = new DatosConsultarDTE();
        try {

            Reader reader = new InputStreamReader(new FileInputStream("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\ConsultaDTE.xml"), "UTF-8");
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");
            DocumentBuilder docBuilder;
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docBuilder.parse(is);
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("RESPONSE");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    DPC.setNIT_EMISOR(eElement.getElementsByTagName("NIT_EMISOR").item(0).getTextContent());
                    DPC.setTIPO_DTE(eElement.getElementsByTagName("TIPO_DTE").item(0).getTextContent());
                    DPC.setGUID(eElement.getElementsByTagName("GUID").item(0).getTextContent());
                    DPC.setSERIE(eElement.getElementsByTagName("SERIE").item(0).getTextContent());
                    DPC.setESTATUS(eElement.getElementsByTagName("ESTATUS").item(0).getTextContent());
                    DPC.setNIT_COMPRADOR(eElement.getElementsByTagName("NIT_COMPRADOR").item(0).getTextContent());
                    DPC.setNOMBRE_COMPRADOR(eElement.getElementsByTagName("NOMBRE_COMPRADOR").item(0).getTextContent());
                    DPC.setACUSE_RECIBO_SAT_DTE(eElement.getElementsByTagName("ACUSE_RECIBO_SAT_DTE").item(0).getTextContent());
                    DPC.setACUSE_RECIBO_ANULACION(eElement.getElementsByTagName("ACUSE_RECIBO_ANULACION").item(0).getTextContent());
                    DPC.setReferenciaInterna(eElement.getElementsByTagName("ReferenciaInterna").item(0).getTextContent());
                }
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERRÓR AL LEER DTE CERTIFICADO", "NO SE PUDO OBTENER LOS DATOS DEL DTE CERTIFICADO \n" + e, DesktopNotify.ERROR, 10000L);
        }
        return DPC;
    }
}
