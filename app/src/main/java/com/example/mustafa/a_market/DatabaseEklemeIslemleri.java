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

    public void UrunTablosunaUrunEkleme(String urun_Id,String urunAdi,String urunBarkodNo,String urunMiktari,String urunFiyati)
    {
        veritabani= FirebaseDatabase.getInstance().getReference().child("urunler");
        UrunOzelikleri urunSinifi=new UrunOzelikleri(urunAdi,urunMiktari,urunFiyati,urunBarkodNo);

        veritabani.child(urun_Id).setValue(urunSinifi);

    }



    public void Veriekle()
    {

        String sehirButonIsimleri []= {"adanaBtn","adiyamanBtn","afyonBtn","agriBtn","amasyaBtn","ankaraBtn","antalyaBtn","artvinBtn","aydinBtn","balikesirBtn"
                ,"bilecikBtn","bingolBtn","bitlisBtn",
                "boluBtn","burdurBtn","bursaBtn","canakkaleBtn","cankiriBtn","corumBtn","denizliBtn","diyarbakirBtn","edirneBtn","elazigBtn", "erzincanBtn","erzurumBtn",
                "eskisehirBtn","gaziantepBtn","giresunBtn","gumushaneBtn","hakkariBtn","hatayBtn","ispartaBtn","mersinBtn","istanbulBtn","izmirBtn"
                ,"karsBtn","kastamonuBtn",
                "kayseriBtn","kirklareliBtn","kirsehirBtn","kocaeliBtn","konyaBtn","kutahyaBtn","malatyaBtn","manisaBtn","marasBtn","mardinBtn",
                "muglaBtn","musBtn","nevsehirBtn",
                "nigdeBtn","orduBtn","rizeBtn","sakaryaBtn","samsunBtn","siirtBtn","sinopBtn","sivasBtn","tekirdagBtn","tokatBtn","trabzonBtn",
                "tunceliBtn","urfaBtn","usakBtn",
                "vanBtn","yozgatBtn","zonguldakBtn","aksarayBtn","bayburtBtn","karamanBtn","kirikkaleBtn","batmanBtn","sirnakBtn","bartinBtn",
                "ardahanBtn","igdirBtn",
                "yalovaBtn","karabukBtn","kilisBtn","osmaniyeBtn","duzceBtn"};




        DatabaseEklemeIslemleri ekle=new DatabaseEklemeIslemleri();

//sehir ekleme
        for(int i=0;i<sehirButonIsimleri.length;i++){

            String  sehir=sehirButonIsimleri[i].substring(0,sehirButonIsimleri[i].length()-3);
            SehirEkle(sehir,sehir);
        }

//sube ekleme
        for(int i=0;i<sehirButonIsimleri.length;i++){
            for(int j=1;j<5;j++){
                String  sehir=sehirButonIsimleri[i].substring(0,sehirButonIsimleri[i].length()-3);
                SubeEkle(sehir,""+j,"A10"+j,"çaydaçıra mh no="+j+"-","4592","7845");
            }

        }

        //  urun ekleme
        for(int i=0;i<sehirButonIsimleri.length;i++){
            for(int j=1;j<5;j++){
                for(int t=1;t<4;t++){
                    String  sehir=sehirButonIsimleri[i].substring(0,sehirButonIsimleri[i].length()-3);
                    UrunEkle(sehir,""+j,""+t,"centro"+t+"-",""+(10-j-t),"457"+i+""+j+""+t,""+j);

                }
            }

        }


        //urunler taablosuna urun ekleme
        for(int i=0;i<5;i++){
          UrunTablosunaUrunEkleme(""+i,"centro"+i+"-","145248","45","5");
        }
    }
}
