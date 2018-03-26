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
        barkodNo=barkod.getBarkod();
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
        sube = new ArrayAdapter<String>(this,
                R.layout.activity_listview, gelenSubeler);

        aramaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aramaTxt.getText().length()==0){
                    aramaTxt.setText(barkodNo);
                }


                arananUrun=aramaTxt.getText().toString();

                gelenSubeler.clear();


                ValueEventListener dinle=new ValueEventListener()
                {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {



                            int subeSayisi= (int) dataSnapshot.child("iller").child(sehirAdi).getChildrenCount();
                            for(int i=1;i<subeSayisi;i++)
                            {
                                int urunSayisi=(int)dataSnapshot.child("iller").child(sehirAdi).child(""+i).getChildrenCount();
                                for (int k=1;k<urunSayisi-1;k++)
                                {
                                    UrunOzelikleri urun=new UrunOzelikleri();
                                    urun=dataSnapshot.child("iller").child(sehirAdi).child(""+i).child(""+k).getValue(UrunOzelikleri.class);
                                    if(arananUrun.charAt(0)<60)
                                    {

                                        if(urun.urunBarkodNo.equals(arananUrun)){
                                            urunVarmı=true;
                                            SubeOzelikleri sube=new SubeOzelikleri();
                                            sube=dataSnapshot.child("iller").child(sehirAdi).child(""+i).getValue(SubeOzelikleri.class);
                                            gelenSubeler.add(sube.subeAdi+" Şubesinde "+urun.urunMiktari+" tane "+urun.urunAdi+" bulunmaktadır");
                                        }
                                    }
                                    else
                                    {
                                        if(urun.urunAdi.equals(arananUrun)){
                                            urunVarmı=true;
                                            SubeOzelikleri sube=new SubeOzelikleri();
                                            sube=dataSnapshot.child("iller").child(sehirAdi).child(""+i).getValue(SubeOzelikleri.class);
                                            gelenSubeler.add(sube.subeAdi+" Şubesinde "+urun.urunMiktari+" tane "+urun.urunAdi+" bulunmaktadır");
                                        }
                                    }


                                }

                            }


                        if(urunVarmı)
                        {
                            bulunanUrunlerLV = (ListView) findViewById(R.id.bulunanUrunlerLV);
                            bulunanUrunlerLV.setAdapter(sube);




                        }
                        else {
                            aramaTxt.setText("ürün bulunamadı");

                        }


                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                okunanVeriler.addValueEventListener(dinle);
            }
        });



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
