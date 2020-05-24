package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class registerActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText inputname, inputphone, inputpassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = (Button)findViewById(R.id.register_button);
        inputname = (EditText)findViewById(R.id.register_input_name);
        inputphone = (EditText)findViewById(R.id.register_input_number);
        inputpassword = (EditText)findViewById(R.id.register_input_password);
        loadingBar = new ProgressDialog(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
            private void createAccount() {
                String name = inputname.getText().toString();
                String phone = inputphone.getText().toString();
                String password = inputpassword.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(registerActivity.this, "Please enteryour name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(phone)){
                    Toast.makeText(registerActivity.this, "Please enter your Pnone Number", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(registerActivity.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    loadingBar.setTitle("Creating the account");
                    loadingBar.setMessage("Please wait, we are checking the credentials");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.setProgressStyle(loadingBar.STYLE_SPINNER);
                    loadingBar.show();
                    
                    validatePhoneNumber(name, phone, password);
                }
            }
        });

    }

    private void validatePhoneNumber(final String name, final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        ValueEventListener valueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(registerActivity.this, "inside", Toast.LENGTH_SHORT).show();
                if(!(dataSnapshot.child("Users").child(phone).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("phone", phone);
                    userdataMap.put("name", name);
                    userdataMap.put("password", password);

                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(registerActivity.this,"Congratulations, your account has been created!", Toast.LENGTH_SHORT).show();

                                        loadingBar.dismiss();

                                        Intent intent = new Intent(registerActivity.this, loginActivity.class);
                                        startActivity(intent);
                                    }  else {
                                        Toast.makeText(registerActivity.this, "This number is already have an account", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                        Toast.makeText(registerActivity.this,"Try again with a different number", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(registerActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        RootRef.addListenerForSingleValueEvent(valueEventListener);
    }
}
