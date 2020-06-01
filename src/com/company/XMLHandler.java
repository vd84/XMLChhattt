package com.company;


import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.StringReader;


public class XMLHandler {
    private final XMLOutputter xmlOutputter;
    private final XMLStandards xmlStandards;
    private SAXBuilder saxBuilder;

    public XMLHandler() {
        this.xmlStandards = new XMLStandards();
        Format format = Format.getCompactFormat();
        format.setLineSeparator("");
        this.xmlOutputter = new XMLOutputter(format);
    }

    public Document createXMLDoc(String inputHomepage, String inputName, String inputEmail, String inputMessage) {

        //Skapa alla element
        DocType docType = new DocType("message", "1//PW//Example//123", "https://atlas.dsv.su.se/~pierre/i/05_ass/ip1/2/2.1.3/message.dtd");

        Element message = new Element("message");
        Element header = new Element("header");
        Element protocol = new Element("protocol");
        Element type = new Element("type");
        Element version = new Element("version");
        Element command = new Element("command");
        Element id = new Element("id");
        Element name = new Element("name");
        Element email = new Element("email");
        Element homepage = new Element("homepage");
        Element host = new Element("host");
        Element body = new Element("body");

        type.addContent(xmlStandards.getType());
        version.addContent(xmlStandards.getVersion());
        command.addContent("MESS");
        name.addContent(inputName);
        email.addContent(inputEmail);
        homepage.addContent(inputHomepage);
        host.addContent("unknown");
        body.addContent(inputMessage);


        //Lägg in rätt element i rätt element
        message.addContent(header);
        message.addContent(body);

        header.addContent(protocol);
        header.addContent(id);

        protocol.addContent(type);
        protocol.addContent(version);
        protocol.addContent(command);


        id.addContent(name);
        id.addContent(email);
        id.addContent(homepage);
        id.addContent(host);


        return new Document(message, docType);


    }

    public String covertXMLToString(Document XMLDocument) {
        return this.xmlOutputter.outputString(XMLDocument);
    }

    public Document convertStringToXML(String xmlString) {
        Document document = null;
        try {
            document = this.saxBuilder.build(new StringReader(xmlString));

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


}
