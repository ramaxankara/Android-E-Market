package com.example.mustafa.a_market;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 Created by mustafa on 04.03.2018.
 */

public class Arama_Activity extends AppCompatActivity {
       DatabaseAramaIslemleri getir=new DatabaseAramaIslemleri();
    //--- component tanımlama
    Button barkodBtn,aramaBtn;
    ListView bulunanUrunlerLV;
    String sehirAdi="",arananUrun,barkodNo="";
    Boolean urunVarmı;
    ArrayList<String> gelenSubeler= new ArrayList<String>();
    ArrayList<String> listelenecekUrunler = new ArrayList<String>();
    DatabaseReference okunanVeriler= FirebaseDatabase.getInstance().getReference();
    ArrayAdapter sube,veri;


    //---




    public Arama_Activity()
    {

        ValueEventListener dinle=new ValueEventListener()
        {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                int urunsayisi= (int) dataSnapshot.child("urunler").getChildrenCount();
                for(int i=0;i<urunsayisi;i++)
                {
                    UrunOzelikleri urun=new UrunOzelikleri();
                    urun=dataSnapshot.child("urunler").child(""+i).getValue(UrunOzelikleri.class);
                    listelenecekUrunler.add(urun.urunAdi);

                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        okunanVeriler.addValueEventListener(dinle);

    }



    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama_s);
        MainActivity mainNesne = new MainActivity();
        final barcodeShow barkod=new barcodeShow();

        sehirAdi=mainNesne.getIsim().substring(0,mainNesne.getIsim().length()-3);





        //--- nesneyi xml ile bağlama


      final   AutoCompleteTextView aramaTxt =findViewById(R.id.aramaTxt);
        barkodBtn = (Button) findViewById(R.id.barkodBtn);
        aramaBtn=findViewById(R.id.aramaBtn);

        //----

        //--- List view işleri


       veri = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, listelenecekUrunler);
        aramaTxt.setAdapter(veri);

           // aramaTxt.setText(barkodNo);

        //---
              barkodNo=barkod.getBarkod();

            gelenSubeler = getir.BarkodIleArama(sehirAdi, barkodNo);


        EkranaYazmakIcinHazırla();
        bulunanUrunlerLV = (ListView) findViewById(R.id.bulunanUrunlerLV);
        bulunanUrunlerLV.setAdapter(sube);



        aramaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arananUrun = aramaTxt.getText().toString();
                gelenSubeler = getir.isimIleArama(sehirAdi, arananUrun);


                EkranaYazmakIcinHazırla();
                bulunanUrunlerLV = (ListView) findViewById(R.id.bulunanUrunlerLV);
                bulunanUrunlerLV.setAdapter(sube);
            }

        });

    bulunanUrunlerLV = (ListView) findViewById(R.id.bulunanUrunlerLV);
        bulunanUrunlerLV.setAdapter(sube);



        //Barcode okuma Sayfası Açıldı(Ramazan)

            barkodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Arama_Activity.this,barcodeShow.class);
                startActivity(intent);

            }
        });

    }
    public void EkranaYazmakIcinHazırla(){
        sube = new ArrayAdapter<String>(this, R.layout.activity_listview,gelenSubeler);
    }
}
