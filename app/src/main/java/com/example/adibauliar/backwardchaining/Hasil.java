package com.example.adibauliar.backwardchaining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Hasil extends AppCompatActivity {

    TextView viewHasil;
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        Bundle bundle = getIntent().getExtras();
        String hasil = bundle.getString("hasil");

        gambar = findViewById(R.id.gambar);
        switch (hasil){
            case "Tipe kosmetik = Makeup Remover":
                gambar.setImageResource(R.drawable.makeup_remover);
                break;
            case "Tipe kosmetik = Lipcream":
                gambar.setImageResource(R.drawable.lipcream);
                break;
            case "Tipe kosmetik = Maskara":
                gambar.setImageResource(R.drawable.maskara);
                break;
            case "Tipe kosmetik = Eyeshadow":
                gambar.setImageResource(R.drawable.eyeshadow);
                break;

        }

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
