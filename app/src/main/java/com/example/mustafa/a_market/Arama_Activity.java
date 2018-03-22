package com.example.mustafa.a_market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

/**
 Created by mustafa on 04.03.2018.
 */

public class Arama_Activity extends AppCompatActivity {

    // component tanımlama
    Button barkodBtn;
    //


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama_s);

        //Barcode okuma Sayfası Açıldı(Ramazan)
            barkodBtn = (Button) findViewById(R.id.barkodBtn);
            barkodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Arama_Activity.this,barcodeShow.class);
                startActivity(intent);



            }
        });

    }



}
