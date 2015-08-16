package com.clostar.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * XMLUtil class contains public methods to work with XML format 
 *
 */
public class XMLUtil{
  /**
   * Prints a xml document to an outputstream using the aEncodingScheme encoding scheme
   *
   * @param doc {@link Document} object to be printed to stream
   * @param out {@link OutputStream} object to write to
   * @param aEncodingScheme {@link String} encoding scheme to use
   */
  public static void serializetoXML(Document doc,
                                    OutputStream out,
                                    String aEncodingScheme) throws IOException {
    OutputFormat outformat = OutputFormat.createPrettyPrint();
    outformat.setEncoding(aEncodingScheme);
    XMLWriter writer = new XMLWriter(out, outformat);
    writer.write(doc);
    writer.flush();
  }

  /**
   * Loads an XML document from a specified url
   *
   * @param url {@link URL} object to load the document from
   * @return Loaded XML document of type {@link Document}
   */
  public static Document loadDocument(URL url) throws DocumentException {
    Document doc;

    //obtaining root Eleemnt
    SAXReader xmlReader = new SAXReader();
    doc    = xmlReader.read(url);

    return doc;
  }
}