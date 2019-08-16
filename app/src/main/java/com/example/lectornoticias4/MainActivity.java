package com.example.lectornoticias4;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

    ArrayList<Item> data = null;
    ItemAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<Item>();
        CargaXMLAsincrona hilo = new CargaXMLAsincrona();
        hilo.execute("https://www.efe.com/efe/noticias/espana/1/rss");
    }


    public class CargaXMLAsincrona extends AsyncTask<String, Integer, Boolean> {


        @Override
        protected Boolean doInBackground(String... arg0)
        {
            ParserDOM parserDOM = new ParserDOM();
            data = parserDOM.parsear(arg0[0].toString());

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            Log.v("SAX", "Se ha completado el proceso de parseo");
            adapter = new ItemAdapter(MainActivity.this, data);
            setListAdapter(adapter);
        }

    }

}
