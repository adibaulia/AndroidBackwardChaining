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

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getInferenceEngine();

        test = (TextView) findViewById(R.id.test);
        test.setText(testBackwardChain().toString());


    }

    public Clause testBackwardChain()
    {
        RuleInferenceEngine rie=getInferenceEngine();
        rie.addFact(new EqualsClause("num_wheels", "4"));
        rie.addFact(new EqualsClause("motor", "yes"));
        rie.addFact(new EqualsClause("num_doors", "3"));
        rie.addFact(new EqualsClause("size", "medium"));

        System.out.println("Infer: vehicle");

        Vector<Clause> unproved_conditions= new Vector<>();

        Clause conclusion=rie.infer("vehicle", unproved_conditions);

        return(conclusion);
    }

    private RuleInferenceEngine getInferenceEngine()
    {
        RuleInferenceEngine rie=new KieRuleInferenceEngine();

        Rule rule=new Rule("Miceral Water");
        rule.addAntecedent(new EqualsClause("zat", "cair"));
        rule.addAntecedent(new EqualsClause("warna", "transparan"));
        rule.addAntecedent(new EqualsClause("sifat", "tidak membuat iritasi"));
        rule.addAntecedent(new EqualsClause("kandungan", "mengandung coconut oil"));
        rule.setConsequent(new EqualsClause("type", "Miceral Water"));
        rie.addRule(rule);

        rule=new Rule("Lip Cream");
        rule.addAntecedent(new EqualsClause("zat", "cream"));
        rule.addAntecedent(new EqualsClause("warna", "berwarna"));
        rule.addAntecedent(new EqualsClause("kegunaan", "untuk make up"));
        rule.addAntecedent(new EqualsClause("kegunaan", "di bibir"));
        rule.setConsequent(new EqualsClause("type", "Lip Cream"));
        rie.addRule(rule);

        rule=new Rule("Lip Cream");
        rule.addAntecedent(new EqualsClause("zat", "cream"));
        rule.addAntecedent(new EqualsClause("warna", "berwarna"));
        rule.addAntecedent(new EqualsClause("kegunaan", "untuk make up"));
        rule.addAntecedent(new EqualsClause("kegunaan", "di bibir"));
        rule.setConsequent(new EqualsClause("type", "Lip Cream"));
        rie.addRule(rule);

        rule=new Rule("Lip Cream");
        rule.addAntecedent(new EqualsClause("zat", "cream"));
        rule.addAntecedent(new EqualsClause("warna", "berwarna"));
        rule.addAntecedent(new EqualsClause("kegunaan", "untuk make up"));
        rule.addAntecedent(new EqualsClause("kegunaan", "di bibir"));
        rule.setConsequent(new EqualsClause("type", "Lip Cream"));
        rie.addRule(rule);

        return rie;
    }
}
