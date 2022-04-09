package com.javarush.task.task33.task3309;

import org.w3c.dom.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/*
Комментарий внутри xml
*/

public class Solution {

    public static String toXmlWithComment(Object obj, String tagName, String comment) throws ParserConfigurationException, JAXBException, TransformerException, UnsupportedEncodingException {

        Document document  = getDocument(obj);

        Node rootNode = document.getFirstChild();


        if(rootNode.hasChildNodes()){
            goToDeep(rootNode,document,tagName,comment);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        writeXml(document,outputStream);


        StringWriter stringWriter = new StringWriter();
        stringWriter.append(outputStream.toString());
        return stringWriter.toString();
    }


    private static Document getDocument(Object obj) throws ParserConfigurationException, JAXBException {

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.newDocument();

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        marshaller.marshal(obj,document);
        return document;

    }


    public static void goToDeep(Node rootNode, Document document, String tagName, String comment){
        NodeList nodeList = rootNode.getChildNodes();

        Node node;
        for (int i = 0; i < nodeList.getLength(); i++) {

            node = nodeList.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE) {

                if (node.getFirstChild().getNodeType() == Node.TEXT_NODE){
                    if (node.getTextContent().matches(".*[\"'<>&].*")) {
                        Node section = document.createCDATASection(node.getTextContent());
                        node.setTextContent("");
                        node.appendChild(section);
                    }
                }

                if (node.getNodeName().equals(tagName)) {
                    Comment comm = document.createComment(comment);
                    rootNode.insertBefore(comm, node);
                    i++;
                }

            }

            if (node.hasChildNodes()) {
                goToDeep(node, document, tagName, comment);
            }

        }
    }



    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException, UnsupportedEncodingException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // The default add many empty new line, not sure why?
        // https://mkyong.com/java/pretty-print-xml-with-java-dom-and-xslt/
        // Transformer transformer = transformerFactory.newTransformer();

        // add a xslt to remove the extra newlines
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

    public static void main(String[] args) throws Exception {
        AnExample obj = new AnExample();
        System.out.println(toXmlWithComment(obj, "needCDATA", "comment"));
    }

    @XmlType(name = "anExample")
    @XmlRootElement
    public static class AnExample {
        @XmlElement(name = "needCDATA", type = String.class)
        public String[] needCDATA = new String[]{"<needCDATA><![CDATA[need CDATA because of < <>& and >]]></needCDATA>", ""};

        public List<String> characters = new ArrayList<>();
    }


}
