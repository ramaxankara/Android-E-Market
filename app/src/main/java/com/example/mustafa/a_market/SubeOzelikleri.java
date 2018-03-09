package com.example.mustafa.a_market;

/**
 Created by ibrahim on 07.03.2018.
 */

public class SubeOzelikleri {
    public String subeAdi;
    public String subeAdresi;
    String enlem;
    String boylam;

    public SubeOzelikleri() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public SubeOzelikleri(String subeAdi,String subeAdresi,String enlem,String boylam) {
        this.subeAdi = subeAdi;
        this.subeAdresi=subeAdresi;
        this.enlem=enlem;
        this.boylam=boylam;

    }
}
