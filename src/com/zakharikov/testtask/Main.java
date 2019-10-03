package com.zakharikov.testtask;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class Main {

    public static String path = "resources/order.xml";
    public static File xmlFile = new File(path);

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        Collection<String> docTypes = XmlUtil.getDocumentTypes(XmlUtil.getXmlDocument(xmlFile));

        for (String s : docTypes) {
            System.out.println(s);
        }

        System.out.println();

        XmlUtil.printCitizenshipAttrs(XmlUtil.getXmlDocument(xmlFile));

        DbConnection.createTable();
        DbConnection.populateTable(docTypes);
    }
}
