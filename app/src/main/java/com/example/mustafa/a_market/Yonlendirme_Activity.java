package com.example.mustafa.a_market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Yonlendirme_Activity extends AppCompatActivity {

    ImageButton urunBulBtn,subelerBtn,iletisimBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yonlendirme);

        urunBulBtn = (ImageButton)(findViewById(R.id.urunBulBtn));
        subelerBtn = (ImageButton)(findViewById(R.id.subelerBtn));
        iletisimBtn = (ImageButton)(findViewById(R.id.iletisimBtn));


        urunBulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aramaAc = new Intent(new Intent(Yonlendirme_Activity.this,Arama_Activity.class));
                startActivity(aramaAc);
            }
        });

        subelerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent magazaAc = new Intent(new Intent(Yonlendirme_Activity.this,webViewActivity.class));
                startActivity(magazaAc);
            }
        });

        iletisimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent aramaAc = new Intent(new Intent(Yonlendirme_Activity.this,Arama_Activity.class));
                //startActivity(aramaAc);
            }
        });

    }
}
