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
        //test.setText(testBackwardChain().toString());
        pertanyaan();


    }

//    public Clause testBackwardChain()
//    {
//
//        rie.addFact(new EqualsClause("zat", "cream"));
//        rie.addFact(new EqualsClause("warna", "berwarna"));
//        rie.addFact(new EqualsClause("kegunaan", "untuk make up"));
//        rie.addFact(new EqualsClause("kegunaan", "di bibir"));
//
//        Vector<Clause> unproved_conditions= new Vector<>();
//
//        Clause conclusion=rie.infer("type", unproved_conditions);
//
//        return(conclusion);
//    }

    public void pertanyaan()
    {
        rie.getRules();
        System.out.println(rie.getRule(1).nextAntecedent());
    }


    private RuleInferenceEngine getInferenceEngine()
    {
        RuleInferenceEngine rie=new KieRuleInferenceEngine();

        Rule rule=new Rule("Lip Cream");
        rule.addAntecedent(new EqualsClause("zat", "cream"));
        rule.addAntecedent(new EqualsClause("warna", "berwarna"));
        rule.addAntecedent(new EqualsClause("kegunaan", "untuk make up"));
        rule.addAntecedent(new EqualsClause("kegunaan", "di bibir"));
        rule.setConsequent(new EqualsClause("type", "Lip Cream"));
        rie.addRule(rule);

        rule=new Rule("Mascara");
        rule.addAntecedent(new EqualsClause("zat", "cream"));
        rule.addAntecedent(new EqualsClause("warna", "berwarna"));
        rule.addAntecedent(new EqualsClause("kegunaan", "untuk make up"));
        rule.addAntecedent(new EqualsClause("kegunaan", "di bibir"));
        rule.setConsequent(new EqualsClause("type", "Mascara"));
        rie.addRule(rule);

        rule=new Rule("Makeup Remover");
        rule.addAntecedent(new EqualsClause("zat", "cream"));
        rule.addAntecedent(new EqualsClause("warna", "berwarna"));
        rule.addAntecedent(new EqualsClause("kegunaan", "untuk make up"));
        rule.addAntecedent(new EqualsClause("kegunaan", "di bibir"));
        rule.setConsequent(new EqualsClause("type", "Makeup Remover"));
        rie.addRule(rule);

        rule=new Rule("BB cream");
        rule.addAntecedent(new EqualsClause("zat", "cream"));
        rule.addAntecedent(new EqualsClause("warna", "berwarna"));
        rule.addAntecedent(new EqualsClause("kegunaan", "untuk make up"));
        rule.addAntecedent(new EqualsClause("kegunaan", "di bibir"));
        rule.setConsequent(new EqualsClause("type", "BB cream"));
        rie.addRule(rule);

        return rie;
    }
}
