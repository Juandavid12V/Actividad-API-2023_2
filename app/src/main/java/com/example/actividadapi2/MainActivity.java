package com.example.actividadapi2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                  final EditText usernameEditText = findViewById(R.id.usernameEditText);
                final EditText passwordEditText = findViewById(R.id.passwordEditText);
                  Button loginButton = findViewById(R.id.loginButton);

                  loginButton.setOnClickListener(new View.OnClickListener() {
                         @Override
                          public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                intent.putExtra("USERNAME", usernameEditText.getText().toString());
                                intent.putExtra("PASSWORD", passwordEditText.getText().toString());
                                startActivity(intent);
                        }
                });
        }
}