package com.example.lectornoticias4;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{

    private String title;
    private String link;
    private String pubDate;
    private String category;
    private String guid;

    public Item(String titulo, String enlace, String fecha, String categoria, String guid_)
    {
        title = titulo;
        link = enlace;
        pubDate = fecha;
        category = categoria;
        guid = guid_;
    }

    public Item(Parcel entrada)
    {
        title = entrada.readString();
        pubDate = entrada.readString();
    }

    public Item()
    {

    }

    public String getTitle()
    {
        return title;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public String getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(String pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getGuid()
    {
        return guid;
    }

    public void setGuid(String guid)
    {
        this.guid = guid;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(getTitle());
        dest.writeString(getPubDate());
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>()
    {
        public Item createFromParcel(Parcel entrada)
        {
            return new Item(entrada);
        }

        public Item[] newArray(int size)
        {
            return new Item[size];
        }
    };
}