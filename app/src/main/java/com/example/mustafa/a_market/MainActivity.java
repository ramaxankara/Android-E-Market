package com.example.mustafa.a_market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference oku= FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // butonlar için id dizisi olusturuldu.

        int sehirButonlar []= {R.id.adanaBtn,R.id.adiyamanBtn,R.id.afyonBtn,R.id.agriBtn,R.id.amasyaBtn,R.id.ankaraBtn,R.id.antalyaBtn
                ,R.id.artvinBtn,R.id.aydinBtn,R.id.balikesirBtn ,R.id.bilecikBtn,R.id.bingolBtn,R.id.bitlisBtn,
                R.id.boluBtn,R.id.burdurBtn,R.id.bursaBtn,R.id.canakkaleBtn,R.id.cankiriBtn,R.id.corumBtn,R.id.denizliBtn,R.id.diyarbakirBtn,
                R.id.edirneBtn,R.id.elazigBtn,R.id.erzincanBtn,R.id.erzurumBtn,R.id.eskisehirBtn,R.id.gaziantepBtn,R.id.giresunBtn
                ,R.id.gumushaneBtn,R.id.hakkariBtn,R.id.hatayBtn,R.id.ispartaBtn,R.id.mersinBtn,R.id.istanbulBtn,R.id.izmirBtn
                ,R.id.karsBtn,R.id.kastamonuBtn,
                R.id.kayseriBtn,R.id.kirklareliBtn,R.id.kirsehirBtn,R.id.kocaeliBtn,R.id.konyaBtn,R.id.kutahyaBtn,R.id.malatyaBtn
                ,R.id.manisaBtn,R.id.marasBtn,R.id.mardinBtn,
                R.id.muglaBtn,R.id.musBtn,R.id.nevsehirBtn,
                R.id.nigdeBtn,R.id.orduBtn,R.id.rizeBtn,R.id.sakaryaBtn,R.id.samsunBtn,R.id.siirtBtn,R.id.sinopBtn,R.id.sivasBtn,
                R.id.tekirdagBtn,R.id.tokatBtn,R.id.trabzonBtn,
                R.id.tunceliBtn,R.id.urfaBtn,R.id.usakBtn,
                R.id.vanBtn,R.id.yozgatBtn,R.id.zonguldakBtn,R.id.aksarayBtn,R.id.bayburtBtn,R.id.karamanBtn,R.id.kirikkaleBtn,R.id.batmanBtn
                ,R.id.sirnakBtn,R.id.bartinBtn,
                R.id.ardahanBtn,R.id.igdirBtn,
                R.id.yalovaBtn,R.id.karabukBtn,R.id.kilisBtn,R.id.osmaniyeBtn,R.id.duzceBtn};


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


        Button butonlar []= new Button[81];// for ile 81 tane buton olusturup click olayını ayarlama
        int i;
        String temp = null;

        for ( i = 0; i < 81; i++){

            temp = sehirButonIsimleri[i];   // buton ismini sehirButonIsimleri dizisinden cekip kullanmak için gecici degisken

            butonlar[i] = (Button) findViewById(sehirButonlar[i]); // her buton için id dizisinden buton idlerini cektik

            final String finalTest = temp;  // final ara degisken gerekiyormus

            butonlar[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setIsim(finalTest);


                    Intent aramaSayfasıAc = new Intent(MainActivity.this, Arama_Activity.class);
                    startActivity(aramaSayfasıAc);


                }

            });
        }


    }


    static String gonder = null;

    public void setIsim(String gidecekIsim){
        gonder = gidecekIsim;
    }
    public String getIsim()
    {
        return gonder;
    }


}
