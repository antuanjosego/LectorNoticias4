package com.example.lectornoticias4;

import java.util.List;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter
{
    static class ViewHolder
    {
        TextView tvFecha_;
        TextView tvTitulo;
    }

    private static final String TAG = "CustomAdapter";
    private static int convertViewCounter = 0;

    private List<Item> data;
    private LayoutInflater inflater = null;

    public ItemAdapter(Context c, List<Item> d)
    {
        Log.v(TAG, "Constructing CustomAdapter");

        this.data = d;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount()
    {
        Log.v(TAG, "in getCount()");
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        Log.v(TAG, "in getItem() for position" + position);
        return data.get(position);
    }


    @Override
    public long getItemId(int position)
    {
        Log.v(TAG, "in getItemID() for position" + position);
        return position;
    }

    @Override
    public int getViewTypeCount()
    {
        Log.v(TAG, "in getViewTypeCount()");
        return 1;
    }

    @Override
    public int getItemViewType(int position)
    {
        Log.v(TAG, "in getItemViewType() for position" + position);
        return 0;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        Log.v(TAG, "in getView for position " + position + ", convertView is " + ((convertView == null) ?"null" : "being recycled"));


        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.row_item, null);

            convertViewCounter++;
            Log.v(TAG, convertViewCounter + " convertViews have been created");

            holder = new ViewHolder();

            holder.tvFecha_ = (TextView)convertView.findViewById(R.id.tvFecha);
            holder.tvTitulo = (TextView)convertView.findViewById(R.id.tvTitulo);

            convertView.setTag(holder);
        }
        else

            holder = (ViewHolder)convertView.getTag();
        Item d = (Item)getItem(position);

        holder.tvFecha_.setText(data.get(position).getPubDate());
        holder.tvTitulo.setText(data.get(position).getTitle());

        return convertView;

    }






}
