package com.example.mustafa.a_market;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ibrahim on 07.03.2018.
 */

public class DatabaseAramaIslemleri {
    DatabaseReference oku= FirebaseDatabase.getInstance().getReference();
    String arananUrun="";
    Boolean varmı=false;
    String urununBulunduguSubeler="";
    String sehirAdi;
    String urunAdi;

    public String IsimIleUrunArama(String sehirAdi, final String urunAdi)
    {


        ValueEventListener dinle=new ValueEventListener()
        {

            public void deneme(){


            }

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                urununBulunduguSubeler="mustafa";



                for (DataSnapshot postSnapshot: dataSnapshot.getChildren())
                {

                    int subeSayisi= (int) dataSnapshot.child("iller").child("adana").getChildrenCount();
                    for(int i=1;i<subeSayisi;i++)
                    {
                        int urunSayisi=(int)dataSnapshot.child("iller").child("adana").child(""+i).getChildrenCount();
                        for (int k=1;k<urunSayisi-1;k++)
                        {
                            UrunOzelikleri urun=new UrunOzelikleri();
                            urun=dataSnapshot.child("iller").child("adana").child(""+i).child(""+k).getValue(UrunOzelikleri.class);
                            if(urun.urunAdi.equalsIgnoreCase("centro1-")){
                                urununBulunduguSubeler+=dataSnapshot.child("iller").child("adana").child(""+i).getKey()+"\n";
                            }
                            else
                            {
                                //urununBulunduguSubeler="urun yok";
                            }
                        }
                    }

                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        oku.addValueEventListener(dinle);
        return urununBulunduguSubeler;
    }
}
