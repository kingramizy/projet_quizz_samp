/*
 *
 *  *
 *  *  * Copyright (c) 2015. Petetin Cédric.
 *  *  * Master 1 - SAMP
 *  *  * Quizz/Questionnaires avec Android
 *  *
 *
 */

package ufr.m1.quizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import ufr.m1.quizz.Adapter.ListeSaisieReponseAdapter;
import ufr.m1.quizz.SQLite.Database;
import ufr.m1.quizz.Stockage.SujetItem;

public class AjoutQuestionActivity extends AppCompatActivity {

    private EditText questionSaisie;
    private Spinner spin_listSujet;
    private ListView lv_reponsesSaisie;

    private ListeSaisieReponseAdapter lvSaisieAdapter;
    private ArrayList<String> reponses;

    private Database mydb;
    private ArrayList<SujetItem> listeSujet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //garder le clavier fermer
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mydb = new Database(this);
        listeSujet = new ArrayList<>();
        mydb.getListeSujets(listeSujet);

        questionSaisie = (EditText)findViewById(R.id.edt_question_enoncer_saisie);
        spin_listSujet = (Spinner)findViewById(R.id.spinner_liste_sujet);
        lv_reponsesSaisie = (ListView)findViewById(R.id.lv_saisie_reponse);


        ArrayAdapter<SujetItem> adapter = new ArrayAdapter<SujetItem>(this, android.R.layout.simple_spinner_item,listeSujet);
        spin_listSujet.setAdapter(adapter);

        reponses = new ArrayList<>();
        reponses.add("");
        lvSaisieAdapter = new ListeSaisieReponseAdapter(reponses, this);
        lv_reponsesSaisie.setAdapter(lvSaisieAdapter);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    public void ajoutReponse(){
        reponses = lvSaisieAdapter.getReponses();
        if (!reponses.get(reponses.size()-1).isEmpty()){
            reponses.add("");
        }

        lvSaisieAdapter.notifyDataSetChanged();
    }

}