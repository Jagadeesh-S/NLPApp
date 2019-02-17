package com.jagadeesh.jagadeesh.nlp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class LanguageActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
   public  static String lang="ta" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);



        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select language");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.listview);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){

                   lang = "te";
                    Intent intent = new Intent(LanguageActivity.this,MainActivity.class);
                   // intent.putExtra("language","te");
                    startActivity(intent);

                }
                if(position == 1){

                    lang = "ta";
                    Intent intent = new Intent(LanguageActivity.this,MainActivity.class);
                    // intent.putExtra("language","te");
                    startActivity(intent);

                }
                if(position == 2){

                    lang = "hi";
                    Intent intent = new Intent(LanguageActivity.this,MainActivity.class);
                    // intent.putExtra("language","te");
                    startActivity(intent);

                }
                if(position == 3){

                    lang = "kn";
                    Intent intent = new Intent(LanguageActivity.this,MainActivity.class);
                    // intent.putExtra("language","te");
                    startActivity(intent);

                }
                if(position == 4){

                    lang = "ml";
                    Intent intent = new Intent(LanguageActivity.this,MainActivity.class);
                    // intent.putExtra("language","te");
                    startActivity(intent);

                }
            }
        };

        //listView.setAdapter(itemClickListener);
        listView.setOnItemClickListener(itemClickListener);


    }
}
