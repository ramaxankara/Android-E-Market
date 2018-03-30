package com.example.mustafa.a_market;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;



/**
 * Created by Ramazan Kara on 3.03.2018.
 */

public class barcodeShow  extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int REQUEST_CAMERA =1;
    private ZXingScannerView scannerView;
TextView listele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);
     listele=findViewById(R.id.listele);


        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1);//Kamera izni İçin en Düşük API
        {
            if (checkPermission())
            {

                Toast.makeText(barcodeShow.this,"Permission is granted",Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }

    }

    //Kamerayı Açma Metodu
    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(barcodeShow.this, CAMERA)== PackageManager.PERMISSION_GRANTED);

    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{CAMERA},REQUEST_CAMERA);
    }

    public void onRequestPermissionsResult(int requestCode,String permission[],int grantResults[])
    {
        switch (requestCode)
        {
            case   REQUEST_CAMERA :
                if(grantResults.length >0)
                {
                    boolean camereAccepted =grantResults[0] ==PackageManager.PERMISSION_GRANTED;
                    if(camereAccepted)
                    {
                        Toast.makeText(barcodeShow.this,"Permission Granted",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(barcodeShow.this,"Permission Denied",Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M)
                        {
                            if(shouldShowRequestPermissionRationale(CAMERA))
                            {
                                displayAlertMessage("You need to allow access for both permissions", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                        }
                                    }
                                });
                                return;
                            }
                        }
                    }
                }
                break;
        }

    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
        {
            if (checkPermission())
            {
                if (scannerView ==null)
                {
                    scannerView=new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else
            {
                requestPermission();
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener )
    {
        new AlertDialog.Builder(barcodeShow.this).setMessage(message).setPositiveButton("OK",listener)
                .setNegativeButton("Cancel",null)
                .create().show();
    }

    @Override
    public void handleResult(final Result result) {
        final String scanResult=result.getText();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //scannerView.resumeCameraPreview(barcodeShow.this);
                setBarkod(scanResult);

                Intent aramaSayfasiAc = new Intent(barcodeShow.this,Arama_Activity.class);
                startActivity(aramaSayfasiAc);//Arama Sayfası Açıldı
            }
        });
        builder.setNeutralButton("Visit ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
                startActivity(intent);
            }
        });
        builder.setMessage(scanResult );
        AlertDialog alert=builder.create();
        alert.show();

    }



    static String okunanBarkod = null;

    public void setBarkod(String gidecekBarkod){
        okunanBarkod = gidecekBarkod;
    }
    public String getBarkod()
    {
        return okunanBarkod;
    }

 //Okunan Barkod Return Edildi
}


