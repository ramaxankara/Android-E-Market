package com.example.mustafa.a_market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.List;

/**
 Created by mustafa on 04.03.2018.
 */

public class Arama_Activity extends AppCompatActivity {

    //--- component tanımlama
    Button barkodBtn;
    ListView bulunanUrunlerLV;
    //---

    String[] gelenDizi = new String[10];


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama_s);
        //--- nesneyi xml ile bağlama
        barkodBtn = (Button) findViewById(R.id.barkodBtn);
        //----

        //--- List view işleri


        for (int i = 0; i < 10; i++)
        {
            gelenDizi[i]= "deneme" + i;
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, gelenDizi);

        bulunanUrunlerLV = (ListView) findViewById(R.id.bulunanUrunlerLV);
        bulunanUrunlerLV.setAdapter(adapter);

        //---

        //Barcode okuma Sayfası Açıldı(Ramazan)

            barkodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Arama_Activity.this,barcodeShow.class);
                startActivity(intent);

            }
        });

    }
}
