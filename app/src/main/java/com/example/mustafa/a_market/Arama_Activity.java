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

    DatabaseAramaIslemleri arama=new DatabaseAramaIslemleri(); //Arama sınıfı nesnesi
    DatabaseEklemeIslemleri ekle=new DatabaseEklemeIslemleri();//Ekleme sınıfı nesnesi

    //--- component tanımlama
    Button aramaBtn;
    ImageButton barkodBtn;
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
       listelenecekUrunler=arama.ListelenecekUrunler();//listelenecek urunleri diziye alma
    }


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama_s);

        MainActivity mainNesne = new MainActivity(); //main sınıfının nesnesi
        barcodeShow barkod=new barcodeShow(); //barkod  sınıfının nesnesi

        sehirAdi=mainNesne.getIsim().substring(0,mainNesne.getIsim().length()-3);

        //--- nesneyi xml ile bağlama


        barkodBtn = (ImageButton) findViewById(R.id.barkodBtn);
        aramaBtn=findViewById(R.id.aramaBtn);

        //----

        //--- List view işleri

        final   AutoCompleteTextView aramaTxt =findViewById(R.id.aramaTxt);
       veri = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item, listelenecekUrunler);
        aramaTxt.setAdapter(veri);

           // aramaTxt.setText(barkodNo);

        //---
              barkodNo=barkod.getBarkod();
                    gelenSubeler.clear();
                    gelenSubeler = arama.BarkodIleArama(sehirAdi, barkodNo);


                        EkranaYazmakIcinHazırla();
                        bulunanUrunlerLV = (ListView) findViewById(R.id.bulunanUrunlerLV);
                        bulunanUrunlerLV.setAdapter(sube);





        aramaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arananUrun = aramaTxt.getText().toString();
                gelenSubeler.clear();
                gelenSubeler = arama.isimIleArama(sehirAdi, arananUrun);
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
