package com.imaginer.tes_ayomakan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.cschen1205.ess.engine.EqualsClause;
import com.github.cschen1205.ess.engine.KieRuleInferenceEngine;
import com.github.cschen1205.ess.engine.Rule;
import com.github.cschen1205.ess.engine.RuleInferenceEngine;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView pertanyaan, hasil;
    LinearLayout btn;
    Button no, yes;
    String [] p;
    String [] p3;
    String [] m;
    private long backPressedTime;
    int indexP;
    int indexM;
    RuleInferenceEngine rie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pertanyaan = (TextView) findViewById(R.id.pertanyaan);
        no = (Button) findViewById(R.id.no);
        yes = (Button) findViewById(R.id.yes);
        hasil = (TextView) findViewById(R.id.hasil);
        btn = (LinearLayout) findViewById(R.id.btn);
        p = new String[]{"Apakah Makanan FastFood?", "Apakah Makanan Restoran?" , "Apakah memiliki menu Rawon?", "Apakah memiliki menu Seafood?"};

        m = new String[]{};
        rie=getInferenceEngine();
        pertanyaan.setText(p[0]);
        indexP = 0;
        no.setOnClickListener(this);
        yes.setOnClickListener(this);

//        testForwardChain();
    }
    private RuleInferenceEngine getInferenceEngine()
    {
        RuleInferenceEngine rie=new KieRuleInferenceEngine();

        Rule rule=new Rule("Rawon Setan");
        rule.addAntecedent(new EqualsClause("fast_food", "slow food"));
        rule.addAntecedent(new EqualsClause("restoran", "restoran"));
        rule.addAntecedent(new EqualsClause("menu", "rawon"));
        rule.setConsequent(new EqualsClause("Rekomendasi", "Rawon Setan" +
                " " +
                "Jl. Embong Malang"));
        rie.addRule(rule);

        rule=new Rule("Ayam Goreng Ny. Suharti");
        rule.addAntecedent(new EqualsClause("fast_food", "slow food"));
        rule.addAntecedent(new EqualsClause("restoran", "restoran"));
        rule.addAntecedent(new EqualsClause("menu", "ayam kremes"));
        rule.setConsequent(new EqualsClause("Rekomendasi", "Ayam Goreng Ny. Suharti" +
                " " +
                "Jl. Sulawesi"));
        rie.addRule(rule);

        rule=new Rule("KFC");
        rule.addAntecedent(new EqualsClause("fast_food", "fast food"));
        rule.setConsequent(new EqualsClause("Rekomendasi", "KFC" +
                " " +
                "Jl. Basuki Rahmat"));
        rie.addRule(rule);

        rule=new Rule("Seafood Genteng");
        rule.addAntecedent(new EqualsClause("fast_food", "slow food"));
        rule.addAntecedent(new EqualsClause("restoran", "kaki lima"));
        rule.addAntecedent(new EqualsClause("menu", "seafood"));
        rule.setConsequent(new EqualsClause("Rekomendasi", "Seafood Genteng" +
                " " +
                "Jl. Pasar Genteng"));
        rie.addRule(rule);

        rule=new Rule("Rawon Budhe");
        rule.addAntecedent(new EqualsClause("fast_food", "slow food"));
        rule.addAntecedent(new EqualsClause("restoran", "kaki lima"));
        rule.addAntecedent(new EqualsClause("menu", "rawon"));
        rule.setConsequent(new EqualsClause("Rekomendasi", "Rawon Budhe" +
                " " +
                "Jl. FST"));
        rie.addRule(rule);


        rule=new Rule("Kremes Mulyos");
        rule.addAntecedent(new EqualsClause("fast_food", "slow food"));
        rule.addAntecedent(new EqualsClause("restoran", "kaki lima"));
        rule.addAntecedent(new EqualsClause("menu", "ayam kremes"));
        rule.setConsequent(new EqualsClause("Rekomendasi", "Penyetan Mulyos" +
                " " +
                "Jl. Mulyosari"));
        rie.addRule(rule);

        rule=new Rule("Seafood Genteng");
        rule.addAntecedent(new EqualsClause("fast_food", "slow food"));
        rule.addAntecedent(new EqualsClause("restoran", "kaki lima"));
        rule.addAntecedent(new EqualsClause("menu", "seafood"));
        rule.setConsequent(new EqualsClause("Rekomendasi", "Seafood Genteng" +
                " " +
                "Jl. Pasar Genteng"));
        rie.addRule(rule);

        rule=new Rule("Penyetan");
        rule.addAntecedent(new EqualsClause("fast_food", "slow food"));
        rule.addAntecedent(new EqualsClause("restoran", "kaki lima"));
        rule.addAntecedent(new EqualsClause("menu", "penyetan"));
        rule.setConsequent(new EqualsClause("Rekomendasi", "Penyetan Bang Ali" +
                " " +
                "Jl. Dukuh Kupang"));
        rie.addRule(rule);


        return rie;
    }

    public void testForwardChain()
    {

        rie.addFact(new EqualsClause("internasional", "internasional"));
        rie.addFact(new EqualsClause("restoran", "kaki lima"));
        rie.addFact(new EqualsClause("menu", ""));

        rie.infer(); //forward chain

//        res.setText(String.valueOf(rie.getFacts()));
    }

    public void changePertanyaan(int index, int jwb){


//        if (index > 2){
//            pertanyaan.setText(p[index+1]);
//            int indexM = index - 3;
//            if (jwb == 1){
//                switch (indexM){
//                    case 0:
//                        rie.addFact(new EqualsClause("menu", "rawon"));
//                    case 1:
//                        rie.addFact(new EqualsClause("menu", "fried chicken"));
//                        break;
//                    case 2:
//                        rie.addFact(new EqualsClause("menu", "seafood"));
//                        break;
//                }
//            }
//        } else {
//            if (index == 3) {
////                pertanyaan.setVisibility(View.GONE);
////                btn.setVisibility(View.GONE);
//                pertanyaan.setText(p[3]);
//                if (jwb == 0){
//                    rie.addFact(new EqualsClause("menu", "penyetan"));
//                    rie.infer();
//                    hasil.setText(String.valueOf(rie.getFacts()));
//                } else {
//                    rie.addFact(new EqualsClause("menu", "seafood"));
//                    rie.infer();
//                    hasil.setText(String.valueOf(rie.getFacts()));
//                }
//            } else {
//                Log.e("index", String.valueOf(index));
//                if (index == 3){
//                    pertanyaan.setText(p[index]);
//                } else {
//                }
        pertanyaan.setText(p[index + 1]);
        switch (index){
                    case 0:
                        if (jwb == 0){
                            rie.addFact(new EqualsClause("fast_food", "slow food"));
//                            indexP+=2;
                        } else {
                            pertanyaan.setVisibility(View.GONE);
                            btn.setVisibility(View.GONE);
                            rie.addFact(new EqualsClause("fast_food", "fast food"));
                            rie.infer();
                            hasil.setText(String.valueOf(rie.getFacts()));
                        }
                        break;
                    case 1:
                        if (jwb == 0){
                            rie.addFact(new EqualsClause("restoran", "kaki lima"));
                        } else {
                            rie.addFact(new EqualsClause("restoran", "restoran"));
                        }
                        break;
                    case 2:
                        if (jwb == 0){
                            pertanyaan.setVisibility(View.GONE);
                            btn.setVisibility(View.GONE);
                            rie.addFact(new EqualsClause("menu", "ayam kremes"));
                            rie.infer();
                            hasil.setText(String.valueOf(rie.getFacts()));
                        } else {
                            pertanyaan.setVisibility(View.GONE);
                            btn.setVisibility(View.GONE);
                            rie.addFact(new EqualsClause("menu", "rawon"));
                            rie.infer();
                            hasil.setText(String.valueOf(rie.getFacts()));
                        }
                        break;
                    case 3:
                        if (jwb == 0){
                            rie.addFact(new EqualsClause("menu", "penyetan"));
                            rie.infer();
                            pertanyaan.setVisibility(View.GONE);
                            btn.setVisibility(View.GONE);
                            hasil.setText(String.valueOf(rie.getFacts()));
                        } else {
                            rie.addFact(new EqualsClause("menu", "seafood"));
                            rie.infer();
                            pertanyaan.setVisibility(View.GONE);
                            btn.setVisibility(View.GONE);
                            hasil.setText(String.valueOf(rie.getFacts()));
                        }
                        break;
                }
//            }

//        }
        indexP++;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.no:
                changePertanyaan(indexP, 0);
                break;
            case R.id.yes:
                changePertanyaan(indexP, 1);
                break;
        }
    } @Override
public void onBackPressed() {


    if(backPressedTime + 1000 > System.currentTimeMillis()){
        super.onBackPressed();
        return;
    }else{
        Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
    }
    backPressedTime = System.currentTimeMillis();
}
}
