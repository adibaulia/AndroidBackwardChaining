package com.example.adibauliar.backwardchaining;

import android.content.Intent;
import android.os.Handler;
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

    TextView pertanyaan;
    RuleInferenceEngine rie=getInferenceEngine();
    Button buttonBenar, buttonSalah;
    Boolean answered;
    String [] p;
    int indexP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_facts);
        pertanyaan = findViewById(R.id.pertanyaan);
        buttonBenar = findViewById(R.id.benar);
        buttonSalah = findViewById(R.id.salah);
        p = new String[]{"Apakah Zat Berwarna Transparan?", "Apakah alat tersebut digunakan dibagian bibir?" , "Apakah alat tersebut memperlentik bulu mata?"};
        rie=getInferenceEngine();
        pertanyaan.setText(p[0]);
        indexP = 0;
        answered = false;
    }

    public void pertanyaan(int index, boolean jawab){
        switch (index){
            case 0:
                if(jawab){
                    rie.addFact(new EqualsClause("Warna","Transparan"));
                    this.hasil();
                }else{
                    rie.addFact(new EqualsClause("Warna","Berwarna"));
                }
                break;
            case 1:
                if(jawab){
                    rie.addFact(new EqualsClause("Lokasi pengaplikasian","Bibir"));
                    rie.addFact(new EqualsClause("Kegunaan","Memberi warna pada bibir"));
                    this.hasil();
                }else{
                    rie.addFact(new EqualsClause("Lokasi pengaplikasian","Mata"));
                }
                break;
            case 2:
                if(jawab){
                    rie.addFact(new EqualsClause("Kegunaan","Memperlentik bulu mata"));
                    this.hasil();
                }else{
                    rie.addFact(new EqualsClause("Kegunaan","Tidak memperlentik bulu mata"));
                    this.hasil();
                }
        }
        if((index != 2) && (answered == false)){
            pertanyaan.setText(p[index+1]);
        }
        indexP++;

    }

    public void hasil(){
        answered = true;
        Vector<Clause> unproved_conditions= new Vector<>();
        Clause conclusion=rie.infer("Tipe kosmetik", unproved_conditions);
        String hasil = conclusion.toString();
        Intent intent = new Intent(ChooseFacts.this, Hasil.class);
        intent.putExtra("hasil", hasil);
        startActivity(intent);

    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.benar:
                pertanyaan(indexP, true);

                break;
            case R.id.salah:
                pertanyaan(indexP, false);
                break;
        }
    }



    private RuleInferenceEngine getInferenceEngine()
    {
        RuleInferenceEngine rie=new KieRuleInferenceEngine();

        Rule rule=new Rule("Makeup Remover");
        rule.addAntecedent(new EqualsClause("Warna", "Transparan"));
        rule.setConsequent(new EqualsClause("Tipe kosmetik", "Makeup Remover"));
        rie.addRule(rule);

        rule=new Rule("Lipcream");
        rule.addAntecedent(new EqualsClause("Warna", "Berwarna"));
        rule.addAntecedent(new EqualsClause("Lokasi pengaplikasian", "Bibir"));
        rule.addAntecedent(new EqualsClause("Kegunaan", "Memberi warna pada bibir"));
        rule.setConsequent(new EqualsClause("Tipe kosmetik", "Lipcream"));
        rie.addRule(rule);

        rule=new Rule("Maskara");
        rule.addAntecedent(new EqualsClause("Warna", "Berwarna"));
        rule.addAntecedent(new EqualsClause("Lokasi pengaplikasian", "Mata"));
        rule.addAntecedent(new EqualsClause("Kegunaan", "Memperlentik bulu mata"));
        rule.setConsequent(new EqualsClause("Tipe kosmetik", "Maskara"));
        rie.addRule(rule);

        rule=new Rule("Eyeshadow");
        rule.addAntecedent(new EqualsClause("Warna", "Berwarna"));
        rule.addAntecedent(new EqualsClause("Lokasi pengaplikasian", "Mata"));
        rule.addAntecedent(new EqualsClause("Kegunaan", "Tidak memperlentik bulu mata"));
        rule.setConsequent(new EqualsClause("Tipe kosmetik", "Eyeshadow"));
        rie.addRule(rule);

        return rie;
    }
}
