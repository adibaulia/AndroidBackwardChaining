package com.example.adibauliar.backwardchaining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.cschen1205.ess.engine.Clause;
import com.github.cschen1205.ess.engine.EqualsClause;
import com.github.cschen1205.ess.engine.KieRuleInferenceEngine;
import com.github.cschen1205.ess.engine.LessClause;
import com.github.cschen1205.ess.engine.Rule;
import com.github.cschen1205.ess.engine.RuleInferenceEngine;

import com.github.cschen1205.ess.engine.RuleInferenceEngine;

import java.sql.SQLOutput;
import java.util.Vector;


public class ChooseFacts extends AppCompatActivity {

    TextView textView, test;
    RuleInferenceEngine rie=getInferenceEngine();
    RadioGroup warna, bagian, zat, pengaplikasian, kegunaan, kandungan;
    RadioButton selectedWarna, selectedBagian, selectedZat, selectedPengaplikasian, selectedKegunaan, selectedKandungan;
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_facts);
        warna = findViewById(R.id.warna);
        bagian = findViewById(R.id.bagian);
        zat = findViewById(R.id.zat);
        pengaplikasian = findViewById(R.id.pengaplikasian);
        kegunaan = findViewById(R.id.kegunaan);
        kandungan = findViewById(R.id.kandungan);

        apply = findViewById(R.id.button_apply);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    selectedWarna = findViewById(warna.getCheckedRadioButtonId());
                    selectedBagian = findViewById(bagian.getCheckedRadioButtonId());
                    selectedZat = findViewById(zat.getCheckedRadioButtonId());
                    selectedPengaplikasian = findViewById(pengaplikasian.getCheckedRadioButtonId());
                    selectedKegunaan = findViewById(kegunaan.getCheckedRadioButtonId());
                    selectedKandungan = findViewById(kandungan.getCheckedRadioButtonId());
                    String valueWarna = selectedWarna.getText().toString();
                    String valueBagian = selectedBagian.getText().toString();
                    String valueZat = selectedZat.getText().toString();
                    String valuePengaplikasian = selectedPengaplikasian.getText().toString();
                    String valueKegunaan = selectedKegunaan.getText().toString();
                    String valueKandungan = selectedKandungan.getText().toString();

                    Intent hasil = new Intent(ChooseFacts.this, Hasil.class);
                    hasil.putExtra("hasil", testBackwardChain(valueWarna, valueBagian, valueZat, valuePengaplikasian, valueKegunaan, valueKandungan));
                    startActivity(hasil);

                    //Toast.makeText(ChooseFacts.this, testBackwardChain(valueWarna, valueBagian, valueZat, valuePengaplikasian, valueKegunaan, valueKandungan), Toast.LENGTH_SHORT).show();

                    warna.clearCheck();
                    bagian.clearCheck();
                    zat.clearCheck();
                    pengaplikasian.clearCheck();
                    kegunaan.clearCheck();
                    kandungan.clearCheck();
                }
                catch (Exception e){
                    Toast.makeText(ChooseFacts.this, "Pilih semua variabel yang ada", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public String testBackwardChain(String warna, String bagian, String zat, String pengaplikasian, String kegunaan, String kandungan)
    {

        rie.addFact(new EqualsClause("Warna",warna));
        rie.addFact(new EqualsClause("Lokasi pengaplikasian",bagian));
        rie.addFact(new EqualsClause("Sifat zat",zat));
        rie.addFact(new EqualsClause("Pengaplikasian",pengaplikasian));
        rie.addFact(new EqualsClause("Kegunaan",kegunaan));
        rie.addFact(new EqualsClause("Kandungan",kandungan));

        Vector<Clause> unproved_conditions= new Vector<>();

        Clause conclusion=rie.infer("Tipe kosmetik", unproved_conditions);

        String hasil = "";

        if(conclusion==null){
            hasil = "Hasil Tidak ditemukan";
        }else{
            hasil = conclusion.toString();
        }
        return(hasil);

    }

//    public void pertanyaan()
//    {
//        rie.getRules();
//        System.out.println(rie.getRule(1).nextAntecedent());
//    }


    private RuleInferenceEngine getInferenceEngine()
    {
        RuleInferenceEngine rie=new KieRuleInferenceEngine();

        Rule rule=new Rule("Mascara");
        rule.addAntecedent(new EqualsClause("Warna", "Lebih dari 1 warna"));
        rule.addAntecedent(new EqualsClause("Lokasi pengaplikasian", "Mata"));
        rule.addAntecedent(new EqualsClause("Sifat zat", "Creamy"));
        rule.addAntecedent(new EqualsClause("Pengaplikasian", "Menggunakan Spoolie"));
        rule.addAntecedent(new EqualsClause("Kegunaan", "Memperindah bagian mata"));
        rule.addAntecedent(new EqualsClause("Kandungan", "Collagen"));
        rule.setConsequent(new EqualsClause("Tipe kosmetik", "Mascara"));
        rie.addRule(rule);

        rule=new Rule("Lipcream");
        rule.addAntecedent(new EqualsClause("Warna", "Lebih dari 1 warna"));
        rule.addAntecedent(new EqualsClause("Lokasi pengaplikasian", "Bibir"));
        rule.addAntecedent(new EqualsClause("Sifat zat", "Creamy"));
        rule.addAntecedent(new EqualsClause("Pengaplikasian", "Brush aplikator"));
        rule.addAntecedent(new EqualsClause("Kegunaan", "Memberi warna pada bibir"));
        rule.addAntecedent(new EqualsClause("Kandungan", "Olive Oil"));
        rule.setConsequent(new EqualsClause("Tipe kosmetik", "Lipcream"));
        rie.addRule(rule);

        rule=new Rule("Makeup Remover");
        rule.addAntecedent(new EqualsClause("Warna", "Transparan"));
        rule.addAntecedent(new EqualsClause("Lokasi pengaplikasian", "Wajah"));
        rule.addAntecedent(new EqualsClause("Sifat zat", "Cair"));
        rule.addAntecedent(new EqualsClause("Pengaplikasian", "Kapas"));
        rule.addAntecedent(new EqualsClause("Kegunaan", "Membersihkan wajah/sisa makeup"));
        rule.addAntecedent(new EqualsClause("Kandungan", "Alkohol / Coconut Oil"));
        rule.setConsequent(new EqualsClause("Tipe kosmetik", "Makeup Remover"));
        rie.addRule(rule);

        rule=new Rule("BBcream");
        rule.addAntecedent(new EqualsClause("Warna", "Lebih dari 1 warna"));
        rule.addAntecedent(new EqualsClause("Lokasi pengaplikasian", "Wajah"));
        rule.addAntecedent(new EqualsClause("Sifat zat", "Creamy"));
        rule.addAntecedent(new EqualsClause("Pengaplikasian", "Spons, Brush"));
        rule.addAntecedent(new EqualsClause("Kegunaan", "Meratakan Warna Kulit"));
        rule.addAntecedent(new EqualsClause("Kandungan", "UV protection"));
        rule.setConsequent(new EqualsClause("Tipe kosmetik", "BBcream"));
        rie.addRule(rule);

        return rie;
    }
}
