package com.example.adibauliar.backwardchaining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.cschen1205.ess.engine.Clause;
import com.github.cschen1205.ess.engine.EqualsClause;
import com.github.cschen1205.ess.engine.KieRuleInferenceEngine;
import com.github.cschen1205.ess.engine.LessClause;
import com.github.cschen1205.ess.engine.Rule;
import com.github.cschen1205.ess.engine.RuleInferenceEngine;

import java.util.Scanner;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    TextView test;
    RuleInferenceEngine rie=getInferenceEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getInferenceEngine();
        test = (TextView) findViewById(R.id.test);
        test.setText(testBackwardChain());
    }



    public String testBackwardChain()
    {

        rie.addFact(new EqualsClause("Warna","Lebih dari 1 warna"));
        rie.addFact(new EqualsClause("Lokasi pengaplikasian","Wajah"));
        rie.addFact(new EqualsClause("Sifat zat","Cair"));
        rie.addFact(new EqualsClause("Pengaplikasian","Spons, Brush"));
        rie.addFact(new EqualsClause("Kegunaan","Meratakan Warna Kulit"));
        rie.addFact(new EqualsClause("Kandungan","UV protection"));

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
        rule.addAntecedent(new EqualsClause("Sifat zat", "Cair"));
        rule.addAntecedent(new EqualsClause("Pengaplikasian", "Spons, Brush"));
        rule.addAntecedent(new EqualsClause("Kegunaan", "Meratakan Warna Kulit"));
        rule.addAntecedent(new EqualsClause("Kandungan", "UV protection"));
        rule.setConsequent(new EqualsClause("Tipe kosmetik", "BBcream"));
        rie.addRule(rule);

        return rie;
    }
}
