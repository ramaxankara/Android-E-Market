package com.example.mustafa.a_market;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 Created by mustafa on 04.03.2018.
 */

public class Arama_Activity extends AppCompatActivity {
    TextView listele;
    String arananUrun="",urununBulunduguSubeler="",sehirAdi;
    Boolean urunVarmı=false;
    Button isimileArama,barkodNoileArama;
    MainActivity mainNesne = new MainActivity();
    ArrayList<String> urunler = new ArrayList<String>();

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
                     urunler.add(urun.urunAdi);

                 }

             }


             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         };
         oku.addValueEventListener(dinle);

     }

    DatabaseReference oku= FirebaseDatabase.getInstance().getReference();




    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama);


         listele=findViewById(R.id.listele);
         sehirAdi = mainNesne.getIsim().substring(0,mainNesne.getIsim().length()-3);
             isimileArama = (Button) findViewById(R.id.isimlearamabtn);
             barkodNoileArama=findViewById(R.id.barkodnoilearamabtn);



        final AutoCompleteTextView sirala =findViewById(R.id.txt);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
        android.R.layout.simple_spinner_dropdown_item, urunler);
        sirala.setAdapter(adapter);



            //Ürünün ismine göre şube arama
        isimileArama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arananUrun=sirala.getText().toString();
                    //girilenUrun.getText().toString();
                    ValueEventListener dinle=new ValueEventListener()
                    {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)
                        {


                            for (DataSnapshot postSnapshot: dataSnapshot.getChildren())
                            {

                                int subeSayisi= (int) dataSnapshot.child("iller").child(sehirAdi).getChildrenCount();
                                for(int i=1;i<subeSayisi;i++)
                                {
                                    int urunSayisi=(int)dataSnapshot.child("iller").child(sehirAdi).child(""+i).getChildrenCount();
                                    for (int k=1;k<urunSayisi-3;k++)
                                    {
                                        UrunOzelikleri urun=new UrunOzelikleri();
                                        urun=dataSnapshot.child("iller").child(sehirAdi).child(""+i).child(""+k).getValue(UrunOzelikleri.class);
                                        if(urun.urunAdi.equals(arananUrun)){
                                            urunVarmı=true;
                                            SubeOzelikleri sube=new SubeOzelikleri();
                                            sube=dataSnapshot.child("iller").child(sehirAdi).child(""+i).getValue(SubeOzelikleri.class);
                                              urununBulunduguSubeler+=sube.subeAdi+" Şubesinde "+urun.urunMiktari+" tane "+urun.urunAdi+" bulunmaktadır"+"\n";
                                        }

                                    }

                                }
                   break;
                            }
                            if(urunVarmı){
                                listele.setText(urununBulunduguSubeler);
                                urunVarmı=false;
                                urununBulunduguSubeler="";

                            }
                            else {
                                listele.setText("ürün bulunamadı");
                                urununBulunduguSubeler="";
                            }


                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    oku.addValueEventListener(dinle);
                }
            });



            //Barcode okuma Sayfası Açıldı(Ramazan)
            Button ramazanButton = (Button) findViewById(R.id.ramazanBtn);
            ramazanButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                  //  DatabaseEklemeIslemleri ekle=new DatabaseEklemeIslemleri();
                    //  ekle.Veriekle();
                   Intent intent=new Intent(Arama_Activity.this,barcodeShow.class);
                   startActivity(intent);



                }
            });

// barkod ile arama
        barkodNoileArama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arananUrun=sirala.getText().toString();

                ValueEventListener dinle=new ValueEventListener()
                {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {


                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren())
                        {

                            int subeSayisi= (int) dataSnapshot.child("iller").child(sehirAdi).getChildrenCount();
                            for(int i=1;i<subeSayisi;i++)
                            {
                                int urunSayisi=(int)dataSnapshot.child("iller").child(sehirAdi).child(""+i).getChildrenCount();
                                for (int k=1;k<urunSayisi-3;k++)
                                {
                                    UrunOzelikleri urun=new UrunOzelikleri();
                                    urun=dataSnapshot.child("iller").child(sehirAdi).child(""+i).child(""+k).getValue(UrunOzelikleri.class);
                                    if(urun.urunBarkodNo.equals(arananUrun)){
                                        urunVarmı=true;
                                        SubeOzelikleri sube=new SubeOzelikleri();
                                        sube=dataSnapshot.child("iller").child(sehirAdi).child(""+i).getValue(SubeOzelikleri.class);
                                        urununBulunduguSubeler+=sube.subeAdi+" Şubesinde "+urun.urunMiktari+" tane "+urun.urunAdi+" bulunmaktadır"+"\n";
                                    }

                                }

                            }
                            break;
                        }
                        if(urunVarmı){
                            listele.setText(urununBulunduguSubeler);
                            urunVarmı=false;
                            urununBulunduguSubeler="";

                        }
                        else {
                            listele.setText("ürün bulunamadı");
                            urununBulunduguSubeler="";
                        }


                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                oku.addValueEventListener(dinle);
            }
        });

        }



}

