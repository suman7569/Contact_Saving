package com.appdevelopersumankr.contact_saving;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ViewContact extends AppCompatActivity {


    TextView textViewName, textViewPhoneNumber;
    AppCompatButton buttonCall;

    String strName, strNumber;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_view_contact );


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewName = findViewById(R.id.textViewName);
        textViewPhoneNumber = findViewById(R.id.textViewNumber);
        buttonCall = findViewById(R.id.buttonCall);

        if(getIntent() == null){
            finish();
        }else{
            strName = getIntent().getStringExtra("name");
            strNumber = getIntent().getStringExtra("number");
            id = getIntent().getLongExtra("id",-1);
            textViewName.setText(strName);
            textViewPhoneNumber.setText(strNumber);
        }

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }
        });
    }

    private void call(){
        if(checkPermission()){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData( Uri.parse("tel:"+strNumber));
            startActivity(callIntent);
        }
    }

    private boolean checkPermission(){
        if(ActivityCompat.checkSelfPermission(ViewContact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ViewContact.this,new String[]{Manifest.permission.CALL_PHONE},123);
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}