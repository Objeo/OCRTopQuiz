 package com.example.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;

    public static final int GAME_ACTIVITY_REQUEST_CODE = 21;

    private SharedPreferences mPreferences;

    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//?

        System.out.println("OnActivityResult");
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            mPreferences.edit().putInt("PREF_KEY_SCORE", score).apply();



            greetUser();
        }

    }

    private void greetUser(){
        String firstname =mPreferences.getString(PREF_KEY_FIRSTNAME, null); //création d'une variable firstname dans la méthode
        // a laquelle on attribue la valeur enregistrée dans les préférences, par défaut nulle.

        if (null != firstname){ //si la valeur est non nulle
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0); //création d'une variable score a laquelle on attribue la valeur du score enregistrée dans les préférences

            String fullText = "Welcome Back "+firstname //création d'une variabe fulltext
                    +"!\nYour last score was " + score
                    +", will you do better this time ? ";
            mGreetingText.setText(fullText);// on change la valeur de mGreetingText par celle de fulltext.
            mNameInput.setText(firstname);// on change la valeur de mNameInput par celle de firstname.
            mNameInput.setSelection(firstname.length());// ?
            mPlayButton.setEnabled(true);// on active la possibilité de cliquer sur le boutton.
        }
    }

    //La méthode onCreate() est appelée lorsque l'activité est créée.
    @Override
    protected void onCreate(Bundle savedInstanceState) {//role main java
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //la méthode setContentView() permet de déterminer quel fichier layout utiliser.
        //lors de la compilation du projet, Android Studio génère une classe Java appelée R (pour Resources), qui contient l'ensemble des identifiants de toutes les ressources du projet.

        System.out.println("MainActivity::OnCreate()");

        mUser = new User();

        mPreferences = getPreferences(MODE_PRIVATE);
        //méthode permettant de référencer un élément graphique dans le code:
        //findViewById() prend en paramètre l'identifiant de la vue qui nous intéresse, et renvoie la vue correspondante.
        // Comme pour le fichier layout, la syntaxe à utiliser pour le paramètre est spécifique : il faut préciser R.id.identifiant_de_vue.
        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_btn);
        //désactiver le bouton de démarrage de jeu au lancement de l'application:
        mPlayButton.setEnabled(false);

        greetUser();

        //Ensuite, il faut pouvoir être notifié lorsque l'utilisateur commence à saisir du texte dans le champ EditText correspondant.
        // Pour ce faire, nous allons appeler la méthode addTextChangedListener() sur l'instance d'EditText, en utilisant une implémentation d'interface sous forme de classe anonyme.
        mNameInput.addTextChangedListener(new TextWatcher()     {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            //À chaque fois que l'utilisateur saisira une lettre, la méthode onTextChanged sera appelée.
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //si au moins une lettre a été saisie, alors le bouton doit être actif
                mPlayButton.setEnabled(s.toString().length() != 0); // renvoie true false
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //Pour détecter que l'utilisateur a cliqué sur le bouton, il est nécessaire d'implémenter un listener
        //pour cela appeler la méthode setOnClickListener() sur l'objet mPlayButton, puis d'implémenter l'interface OnClickListener de la méthode View
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //La méthode onClick() est appelée chaque fois que l'utilisateur appuie sur le bouton.

                String firstname = mNameInput.getText().toString();
                mUser.setFirstName(firstname);

                mPreferences.edit().putString("PREF_KEY_FIRSTNAME", mUser.getFirstName()).apply();
                //SharedPreferences.Editor editor = preferences.edit();
                //editor.putString("firstname", mUser.getFirstName());
                //editor.apply();

                //c'est à cet endroit précis que nous allons démarrer notre nouvelle activité
                //Android propose la méthode startActivity()
                //Pour préciser quelle activité lancer, un objet spécifique est utilisé : Intent.
                // Lorsque la méthode startActivity() est appelée, l'objet interne Android ActivityManager inspecte le contenu de l'objet Intent et démarre l'activité correspondante.
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);//Le premier paramètre correspond au contexte de l'application.
                // Pour faire simple, cela correspond à l'activité appelante (car la classe Activity hérite de la classe Context).
                // Le second paramètre correspond à la classe de l'activité à démarrer.
                startActivity(gameActivityIntent);
                startActivityForResult(gameActivityIntent,GAME_ACTIVITY_REQUEST_CODE);//obtenir un resutat dans l'activité (gameActivity)

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("MainActivity::OnStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity::OnResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("MainActivity::OnPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity::OnStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity::OnDestroy()");
    }

}

