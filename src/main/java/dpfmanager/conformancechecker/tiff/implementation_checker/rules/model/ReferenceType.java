//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.11 at 06:42:42 PM CEST 
//


package dpfmanager.conformancechecker.tiff.implementation_checker.rules.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for referenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="referenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="document" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="section" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="paragraph" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="pages" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "referenceType", propOrder = {
    "document",
    "section",
    "paragraph",
    "page",
    "pages"
})
public class ReferenceType {

    protected Object document;
    protected Object section;
    protected String paragraph;
    protected String page;
    protected String pages;

    /**
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDocument(Object value) {
        this.document = value;
    }

    /**
     * Gets the value of the section property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSection() {
        return section;
    }

    /**
     * Sets the value of the section property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSection(Object value) {
        this.section = value;
    }

    /**
     * Gets the value of the paragraph property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParagraph() {
        return paragraph;
    }

    /**
     * Sets the value of the paragraph property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParagraph(String value) {
        this.paragraph = value;
    }

    /**
     * Gets the value of the page property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPage(String value) {
        this.page = value;
    }

    /**
     * Gets the value of the pages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPages() {
        return pages;
    }

    /**
     * Sets the value of the pages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPages(String value) {
        this.pages = value;
    }

}
