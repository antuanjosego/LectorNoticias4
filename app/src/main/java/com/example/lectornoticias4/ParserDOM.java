package com.example.lectornoticias4;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParserDOM
{

    private URL url;
    private InputStream is;

    public ArrayList<Item> parsear(String urlFuente)
    {
        try
        {
            url = new URL(urlFuente);
        }
        catch (MalformedURLException url_ex)
        {
            throw new RuntimeException(url_ex);
        }

        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();

        ArrayList<Item> items = new ArrayList<Item>();

        try
        {
            is = url.openConnection().getInputStream();
        }
        catch (IOException iex)
        {
            throw new RuntimeException(iex);
        }


        try
        {
            Document doc = fabrica.newDocumentBuilder().parse(is);

            Element padre = doc.getDocumentElement();

            NodeList hijos = padre.getElementsByTagName("item");

            for(int i = 0; i < hijos.getLength(); i++)
            {
                Node hijoActual = hijos.item(i);
                Item item = new Item();
                NodeList atributosHijo = hijoActual.getChildNodes();

                for (int j = 0; j < atributosHijo.getLength(); j++)
                {
                    Node atributoActual = atributosHijo.item(j);
                    String etiqueta = atributoActual.getNodeName();

                    if(etiqueta.equals("title"))
                    {
                        String contenido = getTextoEtiqueta(atributoActual);
                        item.setTitle(contenido);
                    }
                    else if (etiqueta.equals("category"))
                    {
                        String contenido = getTextoEtiqueta(atributoActual);
                        item.setCategory(contenido);
                    }
                    else if(etiqueta.equals("link"))
                    {
                        String contenido = getTextoEtiqueta(atributoActual);
                        item.setLink(contenido);
                    }
                    else if(etiqueta.equals("pubDate"))
                    {
                        String contenido = getTextoEtiqueta(atributoActual);
                        item.setPubDate(contenido);
                    }
                    else if(etiqueta.equals("guid"))
                    {
                        String contenido = getTextoEtiqueta(atributoActual);
                        item.setGuid(contenido);
                    }
                }

                items.add(item);
            }

        }catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

        return items;

    }




    private String getTextoEtiqueta(Node atributoActual)
    {
        StringBuilder caracteres = new StringBuilder();
        NodeList caracter = atributoActual.getChildNodes();

        for(int k = 0; k < caracter.getLength(); k++)
        {
            caracteres.append(caracter.item(k).getNodeValue());
        }

        return caracteres.toString();
    }











}
