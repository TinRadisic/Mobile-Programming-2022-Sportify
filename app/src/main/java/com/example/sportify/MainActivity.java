package com.example.sportify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

        private EditText nameInput, surnameInput, usernameInput, passwordInput, emailInput;
        private Button registerButton;
        public static final String EXTRA_TODO_ID = "MainActivity/EXTRA_TODO_ID";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            nameInput = findViewById(R.id.name_input);
            surnameInput = findViewById(R.id.surname_input);
            usernameInput = findViewById(R.id.username_input);
            passwordInput = findViewById(R.id.password_input);
            emailInput = findViewById(R.id.email_input);
            registerButton = findViewById(R.id.register_button);

            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Users user = new Users();
                    user.setName(nameInput.getText().toString());
                    user.setSurname(surnameInput.getText().toString());
                    user.setUsername(usernameInput.getText().toString());
                    user.setPassword(passwordInput.getText().toString());
                    user.setEmail(emailInput.getText().toString());
                    if (validateInput(user)) {
                        SportifyDatabase sportifyDatabase = SportifyDatabase.getInstance(getApplicationContext());
                        UserDao userDao = sportifyDatabase.userDao();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                userDao.addUser(user);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "User registered!", Toast.LENGTH_SHORT).show();
                                        openhomeactivity();
                                    }
                                });

                            }
                        }).start();

                    } else {
                        Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_SHORT).show();
                    }

                }


            });

        }

        private boolean validateInput(Users user) {
            if (user.getName().isEmpty() || user.getSurname().isEmpty() || user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty()) {
                return false;
            }
            return true;
        }
    public void onhaveaccount(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void openhomeactivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    }
