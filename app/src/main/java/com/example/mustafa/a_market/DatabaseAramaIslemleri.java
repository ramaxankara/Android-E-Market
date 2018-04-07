package com.example.mustafa.a_market;


import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ibrahim on 07.03.2018.
 */

public class DatabaseAramaIslemleri {
    DatabaseReference okunanVeriler= FirebaseDatabase.getInstance().getReference();
    ArrayList<String> gelenSubeler= new ArrayList<String>();

    public ArrayList<String> isimIleArama( final String sehirAdi,final String arananUrun) {
        ValueEventListener dinle = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int subeSayisi = (int) dataSnapshot.child("iller").child(sehirAdi).getChildrenCount();
                for (int i = 1; i < subeSayisi; i++) {
                    int urunSayisi = (int) dataSnapshot.child("iller").child(sehirAdi).child("" + i).getChildrenCount();
                    for (int k = 1; k <urunSayisi-3; k++) {
                        UrunOzelikleri urun = new UrunOzelikleri();
                        urun = dataSnapshot.child("iller").child(sehirAdi).child("" + i).child("" + k).getValue(UrunOzelikleri.class);
                        if (urun.urunAdi.equals(arananUrun)) {
                            SubeOzelikleri sube = new SubeOzelikleri();
                            sube = dataSnapshot.child("iller").child(sehirAdi).child("" + i).getValue(SubeOzelikleri.class);
                            gelenSubeler.add(sube.subeAdi + " Şubesinde " + urun.urunMiktari + " tane " + urun.urunAdi + " bulunmaktadır");
                        }
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        okunanVeriler.addValueEventListener(dinle);
        return gelenSubeler;
    }

    public ArrayList<String> BarkodIleArama( final String sehirAdi,final String barkoNo) {
        ValueEventListener dinle = new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int subeSayisi = (int) dataSnapshot.child("iller").child(sehirAdi).getChildrenCount();
                for (int i = 1; i < subeSayisi; i++) {
                    int urunSayisi = (int) dataSnapshot.child("iller").child(sehirAdi).child("" + i).getChildrenCount();
                    for (int k = 1; k <urunSayisi-3; k++) {
                        UrunOzelikleri urun = new UrunOzelikleri();
                        urun = dataSnapshot.child("iller").child(sehirAdi).child("" + i).child("" + k).getValue(UrunOzelikleri.class);
                        if (urun.urunBarkodNo.equals(barkoNo)) {
                            SubeOzelikleri sube = new SubeOzelikleri();
                            sube = dataSnapshot.child("iller").child(sehirAdi).child("" + i).getValue(SubeOzelikleri.class);
                            gelenSubeler.add(sube.subeAdi + " Şubesinde " + urun.urunMiktari + " tane " + urun.urunAdi + " bulunmaktadır");
                        }
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        okunanVeriler.addValueEventListener(dinle);
        return gelenSubeler;
    }


}

