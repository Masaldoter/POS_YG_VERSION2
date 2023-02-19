package FEL.XML_DTE;

import ModeloWebService.EnviarDatosNotaDeCredito;
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
public class XMLNotaDeCredito {
    
    Double CalcularImpuestoMontoGravable;
            Double CalcularImpuestoMontoImpuesto;
            Double TotalImpuestoCalculado=0.00;
            Double Total=0.00;
            public Boolean GenerarXMLNotaDeCredito(EnviarDatosNotaDeCredito EDNDC, JTable TablaVentas){
                Boolean EstadoGeneracionXML=false;
                try {
                    DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
                    dbfac.setNamespaceAware(true);
                    DocumentBuilder docBuilder;
                    docBuilder = dbfac.newDocumentBuilder();
                    DOMImplementation domImpl = docBuilder.getDOMImplementation();
                    
                    
                    Document doc = domImpl.createDocument("", "dte:GTDocumento", null);
                    doc.setXmlVersion("1.0");

                    /*
                    <dte:GTDocumento xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xmlns:dte="http://www.sat.gob.gt/dte/fel/0.2.0" Version="0.1">
                    */
                    Element cancelacion = doc.getDocumentElement();
                    Attr xmlns_xsi = doc.createAttribute("xmlns:xsi");
                    xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
                    
                    Attr xmlns_dte = doc.createAttribute("xmlns:dte");
                    xmlns_dte.setValue("http://www.sat.gob.gt/dte/fel/0.2.0");
                    
                    Attr attrVersion = doc.createAttribute("Version");
                    attrVersion.setValue("0.1");
                    
                    cancelacion.setAttributeNodeNS(xmlns_xsi);
                    cancelacion.setAttributeNodeNS(xmlns_dte);
                    cancelacion.setAttributeNode(attrVersion);
                    
                    
                    
                    /*
                    <dte:SAT ClaseDocumento="dte">
                    */
                    Element elemento2 = doc.createElement("dte:SAT");
                    
                    Attr ClaseDocumento = doc.createAttribute("ClaseDocumento");
                    ClaseDocumento.setValue("dte");
                    
                    //elemento2.setTextContent("Contenido del elemento 2");
                    cancelacion.appendChild(elemento2);
                    elemento2.setAttributeNode(ClaseDocumento );
                    
                    
                    /*
                     <dte:DTE ID="DatosCertificados">
                    */
                    
                    Element DTE = doc.createElement("dte:DTE");
                    
                    Attr ID = doc.createAttribute("ID");
                    ID.setValue("DatosCertificados");
                    
                   /// DTE.setTextContent("Contenido del elemento 2");
                    elemento2.appendChild(DTE);
                    DTE.setAttributeNode(ID);
                    
                    /*
                    <dte:DatosEmision ID="DatosEmision">
                    */
                    Element DatosEmision = doc.createElement("dte:DatosEmision");
                    
                    Attr IDDatosEmision = doc.createAttribute("ID");
                    IDDatosEmision.setValue("DatosEmision");
                    DTE.appendChild(DatosEmision);
                    DatosEmision.setAttributeNode(IDDatosEmision);
                    
                    
                    /*
                    <dte:DatosGenerales Tipo="FACT" FechaHoraEmision="2022-06-13T18:00:00"
                    */
                    Element DatosGenerales = doc.createElement("dte:DatosGenerales");
                    Attr Tipo = doc.createAttribute("Tipo");
                    Attr FechaHoraEmision = doc.createAttribute("FechaHoraEmision");
                    Attr CodigoMoneda = doc.createAttribute("CodigoMoneda");
                    Tipo.setValue(EDNDC.getTipoDocumento());                                          //Tipo De Documento
                    FechaHoraEmision.setValue(EDNDC.getFechaHoraEmision());               //Ingresamos Fecha
                    CodigoMoneda.setValue(EDNDC.getCodigoMoneda());                                   //Tipo De Moneda
                    DatosEmision.appendChild(DatosGenerales);
                    DatosGenerales.setAttributeNode(Tipo);
                    DatosGenerales.setAttributeNode(FechaHoraEmision);
                    DatosGenerales.setAttributeNode(CodigoMoneda);
                    
                    
                    
                    
                    /*
                    <dte:Emisor NITEmisor="44653948" NombreEmisor="Nombre o Razon Social" CodigoEstablecimiento="1"
                    NombreComercial="Nombre del establecimiento comercial" AfiliacionIVA="GEN">
                    <dte:DireccionEmisor>
                        <dte:Direccion>CA GUATEMALA, GUATEMALA</dte:Direccion>
                        <dte:CodigoPostal>0100</dte:CodigoPostal>
                        <dte:Municipio>GUATEMALA</dte:Municipio>
                        <dte:Departamento>GUATEMALA</dte:Departamento>
                        <dte:Pais>GT</dte:Pais>
                    </dte:DireccionEmisor>
                </dte:Emisor>
                    */
                    Element Emisor = doc.createElement("dte:Emisor");
                    Attr NITEmisor = doc.createAttribute("NITEmisor");
                    Attr NombreEmisor = doc.createAttribute("NombreEmisor");
                    Attr CodigoEstablecimiento = doc.createAttribute("CodigoEstablecimiento");
                    Attr NombreComercial = doc.createAttribute("NombreComercial");
                    Attr AfiliacionIVA = doc.createAttribute("AfiliacionIVA");
                    NITEmisor.setValue(EDNDC.getNITEmisor());   
                    NombreEmisor.setValue(EDNDC.getNombreEmisor());
                    CodigoEstablecimiento.setValue(EDNDC.getCodigoEstablecimiento());
                    NombreComercial.setValue(EDNDC.getNombreEmisor());
                    AfiliacionIVA.setValue(EDNDC.getAfiliacionIVA());                               
                    DatosEmision.appendChild(Emisor);
                    Emisor.setAttributeNode(NITEmisor);
                    Emisor.setAttributeNode(NombreEmisor);
                    Emisor.setAttributeNode(CodigoEstablecimiento);
                    Emisor.setAttributeNode(NombreComercial);
                    Emisor.setAttributeNode(AfiliacionIVA);
                    
                    /*
                    <dte:DireccionEmisor>
                        <dte:Direccion>CA GUATEMALA, GUATEMALA</dte:Direccion>
                        <dte:CodigoPostal>0100</dte:CodigoPostal>
                        <dte:Municipio>GUATEMALA</dte:Municipio>
                        <dte:Departamento>GUATEMALA</dte:Departamento>
                        <dte:Pais>GT</dte:Pais>
                    </dte:DireccionEmisor>
                    */
                    
                    Element DireccionEmisor = doc.createElement("dte:DireccionEmisor"); 
                    Element DireccionEmisor2 = doc.createElement("dte:Direccion"); 
                    Element CodigoPostalAEmisor = doc.createElement("dte:CodigoPostal"); 
                    Element MunicipioEmisor = doc.createElement("dte:Municipio"); 
                    Element DepartamentoEmisor = doc.createElement("dte:Departamento"); 
                    Element PaisEmisor = doc.createElement("dte:Pais"); 
                    
                    DireccionEmisor2.setTextContent(EDNDC.getDireccionEmisor());
                    CodigoPostalAEmisor.setTextContent(EDNDC.getCodigoPostalEmisor());
                    MunicipioEmisor.setTextContent(EDNDC.getMunicipioEmisor());
                    DepartamentoEmisor.setTextContent(EDNDC.getDepartamentoEmisor());
                    PaisEmisor.setTextContent(EDNDC.getPaisEmisor());
                    Emisor.appendChild(DireccionEmisor);
                    DireccionEmisor.appendChild(DireccionEmisor2);
                    DireccionEmisor.appendChild(CodigoPostalAEmisor);
                    DireccionEmisor.appendChild(MunicipioEmisor);
                    DireccionEmisor.appendChild(DepartamentoEmisor);
                    DireccionEmisor.appendChild(PaisEmisor);
                    
                    
                    /*
                    <dte:Receptor NombreReceptor="DIGIFACT SERVICIOS" IDReceptor="77454820">
                    <dte:DireccionReceptor>
                        <dte:Direccion>GUATEMALA</dte:Direccion>
                        <dte:CodigoPostal>01010</dte:CodigoPostal>
                        <dte:Municipio>GUATEMALA</dte:Municipio>
                        <dte:Departamento>GUATEMALA</dte:Departamento>
                        <dte:Pais>GT</dte:Pais>
                    </dte:DireccionReceptor>
                </dte:Receptor>
                    */
                  
                    
                    Element Receptor = doc.createElement("dte:Receptor"); 
                    Element DireccionReceptor = doc.createElement("dte:DireccionReceptor"); 
                    Element DireccionReceptor2 = doc.createElement("dte:Direccion"); 
                    Element CodigoPostalReceptor2 = doc.createElement("dte:CodigoPostal"); 
                    Element MunicipioReceptor = doc.createElement("dte:Municipio"); 
                    Element DepartamentoReceptor = doc.createElement("dte:Departamento"); 
                    Element PaisReceptor = doc.createElement("dte:Pais"); 
                    
                    Attr NombreReceptor = doc.createAttribute("NombreReceptor");
                    Attr IDReceptor = doc.createAttribute("IDReceptor");
                    
                    NombreReceptor.setValue(EDNDC.getNombreReceptor());
                    IDReceptor.setValue(EDNDC.getIDReceptor());
                    DireccionReceptor2.setTextContent(EDNDC.getDireccionReceptor());
                    CodigoPostalReceptor2.setTextContent(EDNDC.getCodigoPostalReceptor());
                    MunicipioReceptor.setTextContent(EDNDC.getMunicipioReceptor());
                    DepartamentoReceptor.setTextContent(EDNDC.getDepartamentoReceptor());
                    PaisReceptor.setTextContent(EDNDC.getPaisReceptor());
                    
                    DatosEmision.appendChild(Receptor);
                    Receptor.appendChild(DireccionReceptor);
                    DireccionReceptor.appendChild(DireccionReceptor2);
                    DireccionReceptor.appendChild(CodigoPostalReceptor2);
                    DireccionReceptor.appendChild(MunicipioReceptor);
                    DireccionReceptor.appendChild(DepartamentoReceptor);
                    DireccionReceptor.appendChild(PaisReceptor);
                    
                    Receptor.setAttributeNode(NombreReceptor);
                    Receptor.setAttributeNode(IDReceptor);
                    
                    
                    /*
                    <dte:Frases>
                    <dte:Frase TipoFrase="1" CodigoEscenario="1"/>
                    
                </dte:Frases>
                    */
                    Element Frases = doc.createElement("dte:Frases"); 
                    Element Frase = doc.createElement("dte:Frase"); 
                    
                    Attr TipoFrase = doc.createAttribute("TipoFrase");
                    Attr CodigoEscenario = doc.createAttribute("CodigoEscenario");
                    TipoFrase.setValue(EDNDC.getTipoFrase());
                    CodigoEscenario.setValue(EDNDC.getCodigoEscenario());
                    
                    DatosEmision.appendChild(Frases);
                    Frases.appendChild(Frase);
                    
                    Frase.setAttributeNode(TipoFrase);
                    Frase.setAttributeNode(CodigoEscenario);
                    
                    
                    
                    /*
                    <dte:Items>
                    <dte:Item NumeroLinea="1" BienOServicio="B">
                        <dte:Cantidad>1.000</dte:Cantidad>
                        <dte:UnidadMedida>CA</dte:UnidadMedida>
                        <dte:Descripcion>Valvula</dte:Descripcion>
                        <dte:PrecioUnitario>10.00</dte:PrecioUnitario>
                        <dte:Precio>10.00</dte:Precio>
                        <dte:Descuento>0</dte:Descuento>
                        <dte:Impuestos>
                            <dte:Impuesto>
                                <dte:NombreCorto>IVA</dte:NombreCorto>
                                <dte:CodigoUnidadGravable>1</dte:CodigoUnidadGravable>
                                <dte:MontoGravable>8.92</dte:MontoGravable>
                                <dte:MontoImpuesto>1.08</dte:MontoImpuesto>
                            </dte:Impuesto>
                        </dte:Impuestos>
                        <dte:Total>10.00</dte:Total>
                    </dte:Item>
                </dte:Items>
                     */
                    Element Items = doc.createElement("dte:Items");

                    for (int i = 0; i < TablaVentas.getRowCount(); i++) {
                        Element Item = doc.createElement("dte:Item");

                        Element Cantidad = doc.createElement("dte:Cantidad");
                        Element UnidadMedida = doc.createElement("dte:UnidadMedida");
                        Element Descripcion = doc.createElement("dte:Descripcion");
                        Element PrecioUnitario = doc.createElement("dte:PrecioUnitario");
                        Element Precio = doc.createElement("dte:Precio");
                        Element Descuento = doc.createElement("dte:Descuento");

                        Element Impuestos = doc.createElement("dte:Impuestos");
                        Element Impuesto = doc.createElement("dte:Impuesto");
                        Element NombreCorto = doc.createElement("dte:NombreCorto");
                        Element CodigoUnidadGravable = doc.createElement("dte:CodigoUnidadGravable");
                        Element MontoGravable = doc.createElement("dte:MontoGravable");
                        Element MontoImpuesto = doc.createElement("dte:MontoImpuesto");

                        Element Total = doc.createElement("dte:Total");

                        Attr NumeroLinea = doc.createAttribute("NumeroLinea");
                        Attr BienOServicio = doc.createAttribute("BienOServicio");
                        NumeroLinea.setValue(String.valueOf(i + 1));
                        BienOServicio.setValue("B");

                        Cantidad.setTextContent(TablaVentas.getValueAt(i, 2).toString());
                        UnidadMedida.setTextContent("UN");
                        Descripcion.setTextContent(TablaVentas.getValueAt(i, 1).toString());
                        PrecioUnitario.setTextContent(TablaVentas.getValueAt(i, 3).toString());
                        Precio.setTextContent(TablaVentas.getValueAt(i, 4).toString());
                        Descuento.setTextContent("0.00");

                        CalcularImpuestoMontoGravable = Double.parseDouble(TablaVentas.getValueAt(i, 4).toString()) / 1.12;
                        CalcularImpuestoMontoImpuesto = Double.parseDouble(TablaVentas.getValueAt(i, 4).toString()) - CalcularImpuestoMontoGravable;
                        TotalImpuestoCalculado += CalcularImpuestoMontoImpuesto;
                    NombreCorto.setTextContent("IVA");
                    CodigoUnidadGravable.setTextContent("1");
                    MontoGravable.setTextContent(String.format("%.4f", CalcularImpuestoMontoGravable));
                    MontoImpuesto.setTextContent(String.format("%.4f", CalcularImpuestoMontoImpuesto));
                    
                    Total.setTextContent(TablaVentas.getValueAt(i, 4).toString());
                    
                    DatosEmision.appendChild(Items);
                    Items.appendChild(Item);
                    Item.appendChild(Cantidad);
                    Item.appendChild(UnidadMedida);
                    Item.appendChild(Descripcion);
                    Item.appendChild(PrecioUnitario);
                    Item.appendChild(Precio);
                    Item.appendChild(Descuento);
                    
                    Item.setAttributeNode(NumeroLinea);
                    Item.setAttributeNode(BienOServicio);
                    Item.appendChild(Impuestos);
                    Impuestos.appendChild(Impuesto);
                    Impuesto.appendChild(NombreCorto);
                    Impuesto.appendChild(CodigoUnidadGravable);
                    Impuesto.appendChild(MontoGravable);
                    Impuesto.appendChild(MontoImpuesto);
                    
                    Item.appendChild(Total);    
                    }
                    
                    /*
                    Element Cantidad = doc.createElement("dte:Cantidad"); 
                    Element UnidadMedida = doc.createElement("dte:UnidadMedida"); 
                    Element Descripcion = doc.createElement("dte:Descripcion"); 
                    Element PrecioUnitario = doc.createElement("dte:PrecioUnitario"); 
                    Element Precio = doc.createElement("dte:Precio"); 
                    Element Descuento = doc.createElement("dte:Descuento"); 
                    
                    Element Impuestos = doc.createElement("dte:Impuestos"); 
                    Element Impuesto = doc.createElement("dte:Impuesto"); 
                    Element NombreCorto = doc.createElement("dte:NombreCorto"); 
                    Element CodigoUnidadGravable = doc.createElement("dte:CodigoUnidadGravable"); 
                    Element MontoGravable = doc.createElement("dte:MontoGravable"); 
                    Element MontoImpuesto = doc.createElement("dte:MontoImpuesto"); 
                    
                    Element Total = doc.createElement("dte:Total"); 
                    
                    Attr NumeroLinea = doc.createAttribute("NumeroLinea");
                    Attr BienOServicio = doc.createAttribute("BienOServicio");
                    NumeroLinea.setValue(EDF.getNumeroLineaProducto());
                    BienOServicio.setValue(EDF.getBienOServicioProducto());
                    
                    Cantidad.setTextContent(EDF.getCantidadProducto());
                    UnidadMedida.setTextContent(EDF.getUnidadMedidaProducto());
                    Descripcion.setTextContent(EDF.getDescripcionProducto());
                    PrecioUnitario.setTextContent(EDF.getPrecioUnitarioProducto());
                    Precio.setTextContent(EDF.getPrecioProducto());
                    Descuento.setTextContent(EDF.getDescuentoProducto());
                    
                    NombreCorto.setTextContent(EDF.getNombreCortoImpuesto());
                    CodigoUnidadGravable.setTextContent(EDF.getCodigoUnidadGravableImpuesto());
                    MontoGravable.setTextContent(EDF.getMontoGravableImpuesto());
                    MontoImpuesto.setTextContent(EDF.getMontoImpuesto());
                    
                    Total.setTextContent(EDF.getTotalProducto());
                    
                    DatosEmision.appendChild(Items);
                    Items.appendChild(Item);
                    Item.appendChild(Cantidad);
                    Item.appendChild(UnidadMedida);
                    Item.appendChild(Descripcion);
                    Item.appendChild(PrecioUnitario);
                    Item.appendChild(Precio);
                    Item.appendChild(Descuento);
                    
                    Item.setAttributeNode(NumeroLinea);
                    Item.setAttributeNode(BienOServicio);
                    
                    Item.appendChild(Impuestos);
                    Impuestos.appendChild(Impuesto);
                    Impuesto.appendChild(NombreCorto);
                    Impuesto.appendChild(CodigoUnidadGravable);
                    Impuesto.appendChild(MontoGravable);
                    Impuesto.appendChild(MontoImpuesto);
                    
                    Item.appendChild(Total);    
                    }
                    */
                    
                    
                    /*
                     <dte:Totales>
                    <dte:TotalImpuestos>
                        <dte:TotalImpuesto NombreCorto="IVA" TotalMontoImpuesto="1.08"/>
                    </dte:TotalImpuestos>
                    <dte:GranTotal>10.00</dte:GranTotal>
                </dte:Totales>
                    */
                    
                    Element Totales = doc.createElement("dte:Totales"); 
                    Element TotalImpuestos = doc.createElement("dte:TotalImpuestos"); 
                    Element TotalImpuesto = doc.createElement("dte:TotalImpuesto"); 
                    Element GranTotal = doc.createElement("dte:GranTotal"); 
                    
                    Attr NombreCortoTotales = doc.createAttribute("NombreCorto");
                    Attr TotalMontoImpuestoTotales = doc.createAttribute("TotalMontoImpuesto");
                    NombreCortoTotales.setValue(EDNDC.getNombreCortoTotales());
                    TotalMontoImpuestoTotales.setValue(String.format("%.4f", TotalImpuestoCalculado));
                    GranTotal.setTextContent(EDNDC.getGranTotalTotales());
                    
                    DatosEmision.appendChild(Totales);
                    Totales.appendChild(TotalImpuestos);
                    TotalImpuestos.appendChild(TotalImpuesto);
                    Totales.appendChild(GranTotal);
                    
                    TotalImpuesto.setAttributeNode(NombreCortoTotales);
                    TotalImpuesto.setAttributeNode(TotalMontoImpuestoTotales);
                    
                    
                    /*
                    <dte:Adenda>
         <dtecomm:Informacion_COMERCIAL xmlns:dtecomm="https://www.digifact.com.gt/dtecomm" xsi:schemaLocation="https://www.digifact.com.gt/dtecomm">
           <dtecomm:InformacionAdicional Version="7.1234654163">
               <dtecomm:REFERENCIA_INTERNA>1B7IUMWYO3Basd2116</dtecomm:REFERENCIA_INTERNA>
               <dtecomm:FECHA_REFERENCIA>2021-08-15T10:40:00</dtecomm:FECHA_REFERENCIA>
               <dtecomm:VALIDAR_REFERENCIA_INTERNA>VALIDAR</dtecomm:VALIDAR_REFERENCIA_INTERNA>
            </dtecomm:InformacionAdicional>
            </dtecomm:Informacion_COMERCIAL>
         </dte:Adenda>   
                    */
                    
                    Element Complementos = doc.createElement("dte:Complementos");
                    Element Complemento = doc.createElement("dte:Complemento");
                    Element cno_ReferenciasNota = doc.createElement("cno:ReferenciasNota");
                    Element NumeroAutorizacionDocumentoOrigen = doc.createElement("NumeroAutorizacionDocumentoOrigen");
                    Element FechaEmisionDocumentoOrigen = doc.createElement("FechaEmisionDocumentoOrigen");
                    Element MotivoAjuste = doc.createElement("MotivoAjuste");
                    Element NumeroDocumentoOrigen = doc.createElement("NumeroDocumentoOrigen");
                    Element SerieDocumentoOrigen = doc.createElement("SerieDocumentoOrigen"); 
                    
                    Attr URIComplemento = doc.createAttribute("xmlns:dtecomm");
                    Attr NombreComplemento = doc.createAttribute("xsi:schemaLocation");
                    Attr xmlns_cnoComplemento = doc.createAttribute("xmlns:cno");
                    Attr xmlns_xsiComplemento = doc.createAttribute("xmlns:xsi");
                    Attr xsi_schemaLocation = doc.createAttribute("xsi:schemaLocation");
                    
                    Attr VersionReferenciasNota = doc.createAttribute("Version");
                    
                    URIComplemento.setValue("dteref");
                    NombreComplemento.setValue("NCRE");
                    xmlns_cnoComplemento.setValue("http://www.sat.gob.gt/face2/ComplementoReferenciaNota/0.1.0");
                    xmlns_xsiComplemento.setValue("http://www.w3.org/2001/XMLSchema-instance");
                    xsi_schemaLocation.setValue("http://www.sat.gob.gt/face2/ComplementoReferenciaNota/0.1.0 GT_Complemento_Referencia_Nota-0.1.0.xsd");
                    VersionReferenciasNota.setValue("0.1");
                    
                    NumeroAutorizacionDocumentoOrigen.setTextContent(EDNDC.getNumeroAutorizacionDocumentoOrigen());
                    FechaEmisionDocumentoOrigen.setTextContent(EDNDC.getFechaEmisionDocumentoOrigen());
                    MotivoAjuste.setTextContent(EDNDC.getMotivoAjuste());
                    NumeroDocumentoOrigen.setTextContent(EDNDC.getNumeroDocumentoOrigen());
                    SerieDocumentoOrigen.setTextContent(EDNDC.getSerieDocumentoOrigen());
                    
                    elemento2.appendChild(Complementos);
                    Complementos.appendChild(Complemento);
                    Complemento.appendChild(cno_ReferenciasNota);
                    cno_ReferenciasNota.appendChild(NumeroAutorizacionDocumentoOrigen);
                    cno_ReferenciasNota.appendChild(FechaEmisionDocumentoOrigen);
                    cno_ReferenciasNota.appendChild(MotivoAjuste);
                    cno_ReferenciasNota.appendChild(NumeroDocumentoOrigen);
                    cno_ReferenciasNota.appendChild(SerieDocumentoOrigen);
                    
                    Complemento.setAttributeNodeNS(URIComplemento);
                    Complemento.setAttributeNodeNS(NombreComplemento);
                    Complemento.setAttributeNode(xmlns_cnoComplemento);
                    Complemento.setAttributeNode(xmlns_xsiComplemento);
                    Complemento.setAttributeNode(xsi_schemaLocation);
                    
                    cno_ReferenciasNota.setAttributeNode(VersionReferenciasNota);
                    
                    Source source = new DOMSource(cancelacion);
                    Result resultado = new StreamResult(new File("C:\\Sistema Punto de Venta YG\\DocumentosGeneradosAutomaticamente\\GTDocumentoNotaDeCredito.xml"));

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
