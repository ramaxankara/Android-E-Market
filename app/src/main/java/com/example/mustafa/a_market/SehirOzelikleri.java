package com.example.mustafa.a_market;

/**
 Created by ibrahim on 07.03.2018.
 */

public class SehirOzelikleri {
    public String sehirAdi;

    public SehirOzelikleri() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public SehirOzelikleri(String sehirAdi) {
        this.sehirAdi = sehirAdi;

    }
}
