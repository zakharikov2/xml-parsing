package com.zakharikov.testtask;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class XmlUtil {

    public static SortedSet<String> getDocumentTypes(Document xmlDocument) {
        TreeSet<String> docTypes = new TreeSet<>();
        NodeList docListElements = xmlDocument.getDocumentElement().getElementsByTagName("par_list");
        for (int i = 0; i < docListElements.getLength(); i++) {
            Node parListElement = docListElements.item(i);
            NamedNodeMap attrMap = parListElement.getAttributes();
            Node valueAttr = attrMap.getNamedItem("value");
            if (parListElement.getParentNode().getAttributes().getNamedItem("name").getNodeValue().equals("ВИД_ДОК")) {
                docTypes.add(valueAttr.getNodeValue());
            }
        }
        return docTypes;
    }

    public static void printCitizenshipAttrs(Document xmlDocument) {
        NodeList parameters = xmlDocument.getDocumentElement().getElementsByTagName("par");
        for (int i = 0; i < parameters.getLength(); i++) {
            Node par = parameters.item(i);
            NamedNodeMap attrMap = par.getAttributes();
            Node valueAttr = attrMap.getNamedItem("name");
            if (valueAttr.getNodeValue().equals("ГРАЖДАНСТВО")) {
                for (int j = 0; j < attrMap.getLength(); j++) {
                    Node attr = attrMap.item(j);
                    System.out.println(attr.getNodeName() + "   " + attr.getNodeValue());
                }
            }
        }
    }

    public static Document getXmlDocument(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        return document;
    }
}
