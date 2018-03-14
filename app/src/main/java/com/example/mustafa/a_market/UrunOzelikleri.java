package com.example.mustafa.a_market;

/**
  Created by ibrahim on 07.03.2018.
 */

public class UrunOzelikleri {
    public String urunAdi;
    public String urunBarkodNo;
    public String urunMiktari;
    public String urunFiyati;


    public UrunOzelikleri() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UrunOzelikleri(String urunAdi,String urunBarkodNo,String urunMiktari,String urunFiyati) {
        this.urunAdi = urunAdi;
        this.urunBarkodNo=urunBarkodNo;
        this.urunFiyati=urunFiyati;
        this.urunMiktari=urunMiktari;

    }
}
