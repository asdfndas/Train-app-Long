package com.example.trainticketproject.uis.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.uis.Train.TrainActivity;
import com.example.trainticketproject.viewmodels.UserViewModel;

import java.util.Locale;
import java.util.concurrent.Executors;

public class UserLogInActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btnLogin, btnClear, btnCreate;
    UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);

        edUsername = findViewById(R.id.edUser);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnClear = findViewById(R.id.btnClear);
        btnCreate = findViewById(R.id.btnCreateUser);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSignUpActitviy();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }

    private void loginUser() {
        String username = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            LiveData<User> loggedUser = userViewModel.getLoginUser(username, password);
            Observer<User> observer = new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user != null && user.getUid() != null) {
                        long uid = user.getUid();
                        String username = user.getUsername();
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(UserLogInActivity.this, TrainActivity.class);
                                intent.putExtra("uid", uid);
                                intent.putExtra("username", username);
                                Log.d("UserSignInActivity", "uid: " + uid + " username: " + username);
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(UserLogInActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            };
            loggedUser.observe(UserLogInActivity.this, observer);
            getLifecycle().addObserver(new LifecycleObserver() {
                @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                public void onDestroy() {
                    loggedUser.removeObserver(observer);
                }
            });
        } else {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
        }
    }


    private void toSignUpActitviy() {
        Intent signUpIntent = new Intent(this, UserSignUpActivity.class);
        startActivity(signUpIntent);
    }

    private void clearFields() {
        edUsername.setText("");
        edPassword.setText("");
    }
}