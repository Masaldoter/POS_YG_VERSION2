/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FEL.XML_DTE;

import ModeloWebService.EnvioDatosAnularFactura;
import ModeloWebService.RespuestaDatosAnularFactura;
import ModeloWebService.EnvioDatosFacturar;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Masaldoter
 */
public class XMLAnularFactura {
    
    public Boolean GenerarXMLAnularFactura(EnvioDatosAnularFactura EDAF){
                Boolean EstadoGeneracionXML=false;
                try {
                    DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
                    dbfac.setNamespaceAware(true);
                    DocumentBuilder docBuilder;
                    docBuilder = dbfac.newDocumentBuilder();
                    DOMImplementation domImpl = docBuilder.getDOMImplementation();
                    
                    
                    Document doc = domImpl.createDocument("", "dte:GTAnulacionDocumento", null);
                    doc.setXmlVersion("1.0");

                    /*
                    <dte:GTDocumento xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xmlns:dte="http://www.sat.gob.gt/dte/fel/0.2.0" Version="0.1">
                    */
                    Element cancelacion = doc.getDocumentElement();
                    Attr xmlns_xsi = doc.createAttribute("xmlns:xsi");
                    xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
                    
                    Attr xmlns_dte = doc.createAttribute("xmlns:dte");
                    xmlns_dte.setValue("http://www.sat.gob.gt/dte/fel/0.1.0");
                    
                    Attr attrVersion = doc.createAttribute("Version");
                    attrVersion.setValue("0.1");
                    
                    cancelacion.setAttributeNodeNS(xmlns_xsi);
                    cancelacion.setAttributeNodeNS(xmlns_dte);
                    cancelacion.setAttributeNode(attrVersion);
                    
                    
                    
                    /*
                    <dte:SAT ClaseDocumento="dte">
                    */
                    Element elemento2 = doc.createElement("dte:SAT");
                    cancelacion.appendChild(elemento2);
                    
                    
                    Element ANULARDTE = doc.createElement("dte:AnulacionDTE");
                    Attr ID = doc.createAttribute("ID");
                    ID.setValue("DatosCertificados");
                    elemento2.appendChild(ANULARDTE);
                    ANULARDTE.setAttributeNode(ID);
                    
                    Element DatosGenerales = doc.createElement("dte:DatosGenerales");
                    Attr IDDatosGenerales = doc.createAttribute("ID");
                    Attr NumeroDocumentoAAnularDatosGenerales = doc.createAttribute("NumeroDocumentoAAnular");
                    Attr NITEmisor = doc.createAttribute("NITEmisor");
                    Attr IDReceptor = doc.createAttribute("IDReceptor");
                    Attr FechaEmisionDocumentoAnular = doc.createAttribute("FechaEmisionDocumentoAnular");
                    Attr FechaHoraAnulacion = doc.createAttribute("FechaHoraAnulacion");
                    Attr MotivoAnulacion = doc.createAttribute("MotivoAnulacion");
                    
                    IDDatosGenerales.setValue("DatosAnulacion");                                  
                    NumeroDocumentoAAnularDatosGenerales.setValue(EDAF.getNumeroDocumentoAAnularDatosGenerales());      
                    NITEmisor.setValue(EDAF.getNITEmisor());      
                    IDReceptor.setValue(EDAF.getNitReceptor());     
                    FechaEmisionDocumentoAnular.setValue(EDAF.getFechaEmisionDocumentoAnular());    
                    FechaHoraAnulacion.setValue(EDAF.getFechaHoraAnulacion());     
                    MotivoAnulacion.setValue(EDAF.getMotivoAnulacion());     
                    
                    ANULARDTE.appendChild(DatosGenerales);
                    DatosGenerales.setAttributeNode(IDDatosGenerales);
                    DatosGenerales.setAttributeNode(NumeroDocumentoAAnularDatosGenerales);
                    DatosGenerales.setAttributeNode(NITEmisor);
                    DatosGenerales.setAttributeNode(IDReceptor);
                    DatosGenerales.setAttributeNode(FechaEmisionDocumentoAnular);
                    DatosGenerales.setAttributeNode(FechaHoraAnulacion);
                    DatosGenerales.setAttributeNode(MotivoAnulacion);
                    
                    Source source = new DOMSource(cancelacion);
                    Result resultado = new StreamResult(new File("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\GTAnulacionDocumento.xml"));

                    Transformer transformar = TransformerFactory.newInstance().newTransformer();
                    transformar.transform(source, resultado);
                    EstadoGeneracionXML= true;
                } catch (ParserConfigurationException | TransformerException ex) {
                    Logger.getLogger(GenerarXML.class.getName()).log(Level.SEVERE, null, ex);
                    DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                    DesktopNotify.showDesktopMessage("XML FALLÓ", "¡NO SE PUDO REALIZAR LA CERTIFICACIÓN POR MALO INGRESO DE DATOS AL XML\n"+ex, DesktopNotify.ERROR, 10000L);
                  EstadoGeneracionXML= false;
                }
          return EstadoGeneracionXML;
            }
}
