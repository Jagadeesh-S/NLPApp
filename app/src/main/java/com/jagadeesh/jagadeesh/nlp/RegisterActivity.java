package com.jagadeesh.jagadeesh.nlp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private MaterialEditText username,email,password;
    private Button button;
    FirebaseAuth auth;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        button = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = username.getText().toString();
                String e = email.getText().toString();
                String p = password.getText().toString();
                if(TextUtils.isEmpty(u)||TextUtils.isEmpty(e)||TextUtils.isEmpty(p))
                    Toast.makeText(RegisterActivity.this,"All fields are required",Toast.LENGTH_SHORT).show();
                else if(p.length()<6) Toast.makeText(RegisterActivity.this,"Password must be atleast 6 characters",Toast.LENGTH_SHORT).show();
                else
                    register(u,e,p);
            //    register(username,email,password);
            }
        });
    }
    void register(final String username, String email, String password){
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                String userid = firebaseUser.getUid();
                                reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("id",userid);
                                hashMap.put("username",username);
                                hashMap.put("imageURL","default");
                                reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(RegisterActivity.this,"Cant register with this email",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    });
    }
}
