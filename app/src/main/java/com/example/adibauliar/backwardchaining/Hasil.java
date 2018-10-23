package com.example.adibauliar.backwardchaining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Hasil extends AppCompatActivity {

    TextView viewHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        Bundle bundle = getIntent().getExtras();
        String hasil = bundle.getString("hasil");

        viewHasil = findViewById(R.id.hasil);
        viewHasil.setText(hasil);



    }

    public void restart (View v){
        Intent restart = new Intent(Hasil.this, MainActivity.class);
        startActivity(restart);
    }

    public void exit (View v){
        finishAffinity();
    }
}
