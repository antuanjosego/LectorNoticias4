package com.example.lectornoticias4;

import android.content.ClipData;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public class ManejadorSAX extends DefaultHandler{

    private ArrayList<Item> items;

    private Item itemTemporal;

    private StringBuilder texto;


    @Override
    public void startDocument() throws SAXException
    {
        super.startDocument();;
        texto = new StringBuilder();
        items = new ArrayList<Item>();
    }

    @Override
    public void startElement(String uri, String nombreLocal, String nombre, Attributes atributos) throws SAXException
    {
        super.startElement(uri, nombreLocal, nombre, atributos);

        if(nombreLocal.equals("item"))
        {
            itemTemporal = new Item();
        }
    }

    @Override
    public void endElement(String uri, String nombreLocal, String nombre) throws SAXException
    {
        super.endElement(uri, nombreLocal, nombre);

        if(this.itemTemporal != null)
        {
            if(nombreLocal.equals("title"))
            {
                itemTemporal.setTitle(texto.toString());
            }
            else if(nombreLocal.equals("link"))
            {
                itemTemporal.setLink(texto.toString());
            }
            else if(nombreLocal.equals("pubDate"))
            {
                itemTemporal.setPubDate(texto.toString());
            }
            else if(nombreLocal.equals("category"))
            {
                itemTemporal.setCategory(texto.toString());
            }
            else if(nombreLocal.equals("guid"))
            {
                itemTemporal.setGuid(texto.toString());
            }
            else if(nombreLocal.equals("item"))
            {
                items.add(itemTemporal);
            }
            texto.setLength(0);
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        super.characters(ch, start, length);

        if(this.itemTemporal != null)
            texto.append(ch, start, length);
    }


    public ArrayList<Item> getItems()
    {
        return items;
    }


}
