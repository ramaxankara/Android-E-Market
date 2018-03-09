package com.example.mustafa.a_market;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 Created by ibrahim on 07.03.2018.
 */

public class DatabaseEklemeIslemleri {
    DatabaseReference veritabani;

    public void SehirEkle(String sehir_Id,String sehirAdi ){

        veritabani= FirebaseDatabase.getInstance().getReference().child("iller");
        SehirOzelikleri sehirSinifi=new SehirOzelikleri(sehirAdi);
        veritabani.child(sehir_Id).setValue(sehirSinifi);

    }


    public void SubeEkle(String sehir_Id,String sube_Id,String subeAdi,String subeAdresi,String enlem,String boylam)
    {
        veritabani= FirebaseDatabase.getInstance().getReference().child("iller").child(sehir_Id);
        SubeOzelikleri subeSinifi=new SubeOzelikleri(subeAdi,subeAdresi,enlem,boylam);

        veritabani.child(sube_Id).setValue(subeSinifi);

    }

    public void UrunEkle(String sehir_Id,String sube_Id,String urun_Id,String urunAdi,String urunFiyati,String urunMiktari,String urunBarkodNo)
    {
        veritabani= FirebaseDatabase.getInstance().getReference().child("iller").child(sehir_Id).child(sube_Id);
        UrunOzelikleri urunSinifi=new UrunOzelikleri(urunAdi,urunMiktari,urunFiyati,urunBarkodNo);

        veritabani.child(urun_Id).setValue(urunSinifi);
    }

    public void UrunTablosunaUrunEkleme(String urunAdi,String urunBarkodNo,String urunMiktari,String urunFiyati)
    {
        veritabani= FirebaseDatabase.getInstance().getReference().child("urunler");
        UrunOzelikleri urunSinifi=new UrunOzelikleri(urunAdi,urunMiktari,urunFiyati,urunBarkodNo);

        veritabani.child(urunAdi).setValue(urunSinifi);

    }
}
