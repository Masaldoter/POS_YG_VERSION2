package WebServiceDigifact;

import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import ModeloWebService.DatosPersonaCliente;
import ModeloWebService.DatosUsuario;
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
public class ConsultarCUIWebService {
    //static DatosPersonaCliente DPC;

    public DatosPersonaCliente ObtenerCliente(DatosPersonaCliente DPCEntrada, DatosUsuario DU, String Token) {
        DatosPersonaCliente DPC = new DatosPersonaCliente();
                try {
                    ObtenerToken OT = new ObtenerToken();
                    OT.ObtenerToken();
                    URL url = null;
                        url = new URL("https://felgtaws.digifact.com.gt/gt.com.fel.api.v3/api/sharedInfo?NIT=0000" + DU.getNit()
                                + "&DATA1=SHARED_GETINFOCUI&DATA2=CUI|" + DPCEntrada.getNIT_CUI() + "&USERNAME=" + DU.getUsuario());

                    HttpURLConnection http = (HttpURLConnection) url.openConnection();
                    http.setRequestMethod("GET");
                    http.setRequestProperty("Authorization", PARAMETROS_EMPRESA.TOKEN_CERTIFICADOR);

                    if (http.getResponseCode() == 200) {
                        DPC.setEstado(true);
                        StringBuilder response;
                        try ( BufferedReader in = new BufferedReader(
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
                    } else {
                        DPC.setEstado(false);
                        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                        DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "NO SE PUDO OBTENER LOS DATOS DEL CUI:" + DPCEntrada.getNIT_CUI() + "\n" + http.getResponseCode(), DesktopNotify.ERROR, 10000L);
                    }

                } catch (MalformedURLException ex) {
                    Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "NO SE PUDO OBTENER LOS DATOS DEL CUI:" + DPCEntrada.getNIT_CUI() + "\n"+ex, DesktopNotify.ERROR, 10000L);
                } catch (IOException ex) {
                    Logger.getLogger(CertificarFactura.class.getName()).log(Level.SEVERE, null, ex);
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ERRÓR EN LA CONEXIÓN CON CERTIFICADOR", "NO SE PUDO OBTENER LOS DATOS DEL CUI:" + DPCEntrada.getNIT_CUI() + "\n"+ex, DesktopNotify.ERROR, 10000L);
                }
        return DPC;
    }

    public static void writeFile(String yourXML) {
                BufferedWriter out = null;
                try {
                    out = new BufferedWriter(new FileWriter("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\ConsultaCUI.xml"));
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

    public static DatosPersonaCliente Lectura() {
                DatosPersonaCliente DPC = new DatosPersonaCliente();
                try {
                    Reader reader = new InputStreamReader(new FileInputStream("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\ConsultaCUI.xml"), "UTF-8");
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
                            DPC.setNombre(eElement.getElementsByTagName("NOMBRE").item(0).getTextContent());
                            DPC.setPais(eElement.getElementsByTagName("PAIS").item(0).getTextContent());
                            DPC.setDireccion("CIUDAD");
                            DPC.setNIT_CUI(eElement.getElementsByTagName("CUI").item(0).getTextContent());
                            DPC.setMunicipio("GUATEMALA");
                            DPC.setDepartamento("GUATEMALA");
                        }
                    }
                } catch (IOException | SAXException | ParserConfigurationException e) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                    DesktopNotify.showDesktopMessage("ERRÓR AL LEER CUI", "NO SE PUDO OBTENER LOS DATOS DEL CUI \n" + e, DesktopNotify.ERROR, 10000L);
                }
            

        return DPC;
    }

}
