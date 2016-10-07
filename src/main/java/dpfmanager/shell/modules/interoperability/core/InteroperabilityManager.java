package dpfmanager.shell.modules.interoperability.core;

import dpfmanager.shell.core.DPFManagerProperties;
import dpfmanager.shell.core.config.BasicConfig;
import dpfmanager.shell.core.context.DpfContext;
import dpfmanager.shell.modules.messages.messages.LogMessage;

import org.apache.logging.log4j.Level;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Adrià Llorens on 05/10/2016.
 */
public class InteroperabilityManager {

  private DpfContext context;
  private ResourceBundle bundle;

  public InteroperabilityManager(DpfContext context, ResourceBundle bundle) {
    this.context = context;
    this.bundle = bundle;
  }

  /**
   * Saves the conformance checkers to file
   */
  public boolean writeChanges(List<Conformance> conformances) {
    try {
      String xmlFileOld = DPFManagerProperties.getConformancesConfig();
      String xmlFileNew = DPFManagerProperties.getConformancesConfig() + ".new";
      Document doc = getXML(conformances);

      // Write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

      File newFile = new File(xmlFileNew);
      StreamResult result = new StreamResult(newFile);
      transformer.transform(new DOMSource(doc), result);

      // All OK
      File oldFile = new File(xmlFileOld);
      oldFile.delete();
      return newFile.renameTo(oldFile);
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public Document getXML(List<Conformance> conformances) {
    try {
      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.newDocument();
      Element listElement = doc.createElementNS("http://www.preforma-project/interoperability", "tns:ListOutput");
      listElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
      listElement.setAttribute("xmlns:schemaLocation", "http://www.preforma-project/interoperability preformainteroperability.xsd");
      doc.appendChild(listElement);
      Element conformanceCheckers = doc.createElement("ConformanceCheckers");
      listElement.appendChild(conformanceCheckers);

      // Individual conformances
      for (Conformance conformance : conformances) {
        Document conformanceDoc = conformance.toXML();
        if (conformanceDoc != null) {
          Node node = doc.importNode(conformanceDoc.getDocumentElement(), true);
          conformanceCheckers.appendChild(node);
        }
      }

      return doc;
    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getString(List<Conformance> conformances) {
    try {
      // Get XML Document
      Document doc = getXML(conformances);

      // Write the content into String
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

      StringWriter writer = new StringWriter();
      transformer.transform(new DOMSource(doc), new StreamResult(writer));
      String output = writer.getBuffer().toString();

      return output;
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * Read the conformance checkers from configuration file
   */
  public List<Conformance> loadFromFile() {
    List<Conformance> conformances = new ArrayList<>();
    try {
      String path = DPFManagerProperties.getConformancesConfig();
      File fXmlFile = new File(path);
      if (fXmlFile.exists()) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        conformances.addAll(readConformanceCheckers(doc));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conformances;
  }

  /**
   * Load the built in conformance checkers
   */
  public List<Conformance> loadFromBuiltIn() {
    List<Conformance> conformances = new ArrayList<>();
    try {
      String xml = DPFManagerProperties.getBuiltInDefinition();
      boolean needWrite = false;
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
      conformances =  readConformanceCheckers(doc);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conformances;
  }

  private List<Conformance> readConformanceCheckers(Document doc) {
    List<Conformance> conformances = new ArrayList<>();
    try {
      // Read the conformance checkers
      NodeList nList = doc.getDocumentElement().getElementsByTagName("conformanceChecker");
      for (int i = 0; i < nList.getLength(); i++) {
        Node conformanceNode = nList.item(i);
        Conformance conformance = new Conformance();
        conformance.fromXML(conformanceNode);
        conformances.add(conformance);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conformances;
  }

}
