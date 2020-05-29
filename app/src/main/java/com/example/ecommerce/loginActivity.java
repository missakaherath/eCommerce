package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.Models.Users;
import com.example.ecommerce.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {
    private EditText phoneInput, passwordInput;
    private Button loginInput;
    private ProgressDialog loadingBar;
    private String parentDBName = "Users";
    private CheckBox rememberMeCB;
    private TextView adminLink;
    private TextView notAdminLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupUIViews();

        Paper.init(this);
        
        loginInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });

        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginInput.setText("Login Admin");
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                parentDBName = "Admins";
            }
        });

        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginInput.setText("Login");
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                parentDBName = "Users";
            }
        });
    }

    private void loginuser() {
        String phone = phoneInput.getText().toString();
        String password = passwordInput.getText().toString();

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please enter your phone number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter your password",Toast.LENGTH_SHORT).show();
        }else {
            loadingBar.setTitle("You are loggin in");
            loadingBar.setMessage("Please wait, we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.setProgressStyle(loadingBar.STYLE_SPINNER);
            loadingBar.show();

            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final String phone, final String password) {

        if(rememberMeCB.isChecked()){
            Paper.book().write(Prevalent.userPhoneKey, phone);
            Paper.book().write(Prevalent.userPasswordKey, password);
        }

        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(parentDBName).child(phone).exists()){
                    Users userData = dataSnapshot.child(parentDBName).child(phone).getValue(Users.class);
                    if(userData.getPhone().equals(phone)){
                        if (userData.getPassword().equals(password)){
                            if(parentDBName.equals("Admins")){
                                Toast.makeText(loginActivity.this, "Welcome admin, you are logged in", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(loginActivity.this, AdminAddNewProductActivity.class);
                                startActivity(intent);
                            } else if (parentDBName.equals("Users")){
                                Toast.makeText(loginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(loginActivity.this, "Password you entered is invalid", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(loginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                } else {
                    Toast.makeText(loginActivity.this, "This number does not have an account", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setupUIViews(){
        phoneInput = (EditText)findViewById(R.id.login_input_number);
        passwordInput = (EditText)findViewById(R.id.login_input_password);
        loginInput = (Button)findViewById(R.id.login_button);
        rememberMeCB = (CheckBox)findViewById(R.id.remember_me_check);
        adminLink = (TextView)findViewById(R.id.tvImAdminLink);
        notAdminLink = (TextView)findViewById(R.id.tvImNotAdminLink);

        loadingBar = new ProgressDialog(this);
    }
}
