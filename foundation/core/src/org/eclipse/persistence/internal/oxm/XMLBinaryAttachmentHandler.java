/*******************************************************************************
 * Copyright (c) 1998, 2007 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/
package org.eclipse.persistence.internal.oxm;

import javax.activation.DataHandler;

import org.eclipse.persistence.internal.sessions.AbstractSession;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.oxm.XMLConstants;
import org.eclipse.persistence.oxm.attachment.XMLAttachmentUnmarshaller;
import org.eclipse.persistence.oxm.mappings.XMLBinaryDataMapping;
import org.eclipse.persistence.oxm.mappings.XMLBinaryDataCollectionMapping;
import org.eclipse.persistence.oxm.mappings.converters.XMLConverter;
import org.eclipse.persistence.oxm.record.UnmarshalRecord;
import org.eclipse.persistence.mappings.DatabaseMapping;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * INTERNAL:
 * @author  mmacivor
 */

public class XMLBinaryAttachmentHandler implements ContentHandler {
    UnmarshalRecord record;
    DatabaseMapping mapping;
    String c_id = null;
    Converter converter;
    NodeValue nodeValue;
    boolean isCollection = false;

    public XMLBinaryAttachmentHandler(UnmarshalRecord unmarshalRecord, NodeValue nodeValue, XMLBinaryDataMapping mapping) {
        record = unmarshalRecord;
        this.mapping = mapping;
        this.nodeValue = nodeValue;
        converter = mapping.getConverter();
    }
    
    public XMLBinaryAttachmentHandler(UnmarshalRecord unmarshalRecord, NodeValue nodeValue, XMLBinaryDataCollectionMapping mapping) {
        record = unmarshalRecord;
        this.mapping = mapping;
        converter = mapping.getValueConverter();
        this.nodeValue = nodeValue;
        isCollection = true;
    }
    public void startPrefixMapping(String prefix, String URI) {
   
    }
    
    public void ignorableWhitespace(char[] chars, int offset, int length) {
        
    }
    
    public void characters(char[] characters, int offset, int length) {
        
    }
    
    public void endDocument() {
        
    }
    
    public void startDocument() {
        
    }
    
    public void skippedEntity(String entity) {
        
    }
    
    public void setDocumentLocator(Locator locator) {
        
    }
    
    public void endPrefixMapping(String prefix) {
        
    }
    
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if(namespaceURI.equals(XMLConstants.XOP_URL) && (localName.equals("Include") || qName.equals("Include"))) {
            this.c_id = atts.getValue("", "href");
        } else {
            //Return control to the UnmarshalRecord
            record.getXMLReader().setContentHandler(record);
            record.startElement(namespaceURI, localName, qName, atts);
        }
    }
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if(namespaceURI.equals(XMLConstants.XOP_URL) && (localName.equals("Include") || qName.equals("Include"))) {
            //Get the attachment and set it in the object.
            XMLAttachmentUnmarshaller attachmentUnmarshaller = record.getUnmarshaller().getAttachmentUnmarshaller();
            Object data = attachmentUnmarshaller.getAttachmentAsByteArray(this.c_id);
            if(this.converter != null) {
                Converter converter = this.converter;
                if(converter instanceof XMLConverter) {
                    data = ((XMLConverter)converter).convertDataValueToObjectValue(data, record.getSession(), record.getUnmarshaller());
                } else {
                    data = converter.convertDataValueToObjectValue(data, record.getSession());
                }
            }
            data = XMLBinaryDataHelper.getXMLBinaryDataHelper().convertObject(data, mapping.getAttributeClassification());
            //check for collection case
            if(isCollection) {
                Object container = record.getContainerInstance((XMLBinaryDataCollectionMappingNodeValue)nodeValue);
                ((XMLBinaryDataCollectionMapping)mapping).getContainerPolicy().addInto(data, container, (AbstractSession)record.getSession());
            } else {
                mapping.setAttributeValueInObject(record.getCurrentObject(), data);
            }
            /*if(mapping.getAttributeClassification() == ClassConstants.ABYTE || mapping.getAttributeClassification() == ClassConstants.APBYTE) {
                byte[] attachment = attachmentUnmarshaller.getAttachmentAsByteArray(this.c_id);
                mapping.setAttributeValueInObject(record.getObject(), mapping.getAttributeValue(attachment, record.getSession()));
            } else {
                DataHandler data = attachmentUnmarshaller.getAttachmentAsDataHandler(c_id);
                Object objectValue = getObjectValueFromDataHandler(data, mapping.getAttributeClassification());
            }*/
            //Return control to the UnmarshalRecord
            record.getXMLReader().setContentHandler(record);
        }
        else {
            record.getXMLReader().setContentHandler(record);
            record.endElement(namespaceURI, localName, qName);
        }
    }
    public void processingInstruction(String target, String data) throws SAXException {
    }
   
    public String getCID() {
        return this.c_id;
    }
    
    public Object getObjectValueFromDataHandler(DataHandler handler, Class cls) {
        return XMLBinaryDataHelper.getXMLBinaryDataHelper().convertObject(handler, cls);
    }
    
}
