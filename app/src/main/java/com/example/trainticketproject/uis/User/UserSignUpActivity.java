package com.example.trainticketproject.uis.User;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.trainticketproject.R;
import com.example.trainticketproject.databases.UserRoomDatabase;
import com.example.trainticketproject.models.Gender;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.viewmodels.UserViewModel;

import java.util.concurrent.Executors;

public class UserSignUpActivity extends AppCompatActivity {
    private EditText edUsername, edFullname, edPassword, edAge, edEmail, edPhoneNumber;
    private RadioButton rbMale, rbFemale;
    private Button btnSignup, btnClearInfo;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        edUsername = findViewById(R.id.edUsername);
        edFullname = findViewById(R.id.edFullname);
        edPassword = findViewById(R.id.edPassword);
        edAge = findViewById(R.id.edAge);
        edEmail = findViewById(R.id.edEmail);
        edPhoneNumber = findViewById(R.id.edPhone);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnSignup = findViewById(R.id.btnSignup);
        btnClearInfo = findViewById(R.id.btnClearInFo);
        rbMale.setChecked(true);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString().trim();
                String fullname = edFullname.getText().toString().trim();
                String strAge = edAge.getText().toString().trim();
                int age = TextUtils.isEmpty(strAge) ? 30 : Integer.parseInt(strAge);
                String email = edEmail.getText().toString().trim();
                String phoneNumber = edPhoneNumber.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                Gender gender = rbMale.isChecked() ? Gender.MALE : Gender.FEMALE;

                if (isEmpty(edUsername) || isEmpty(edFullname) || isEmpty(edPassword) || isEmpty(edAge) || isEmpty(edEmail) || isEmpty(edPhoneNumber)) {
                    showInputDialog();
                } else {
                    LiveData<Integer> countLiveData = userViewModel.getCountWithUsername(username);
                    countLiveData.observe(UserSignUpActivity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(Integer count) {
                            if (count == 0) {
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        User newUser = new User(username, fullname, age, gender, email, password, phoneNumber);
                                        userViewModel.insert(newUser);

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(UserSignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(UserSignUpActivity.this, UserLogInActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        });
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(UserSignUpActivity.this, "Username already exists. Please choose another one.", Toast.LENGTH_SHORT).show();
                                        edUsername.requestFocus();
                                    }
                                });
                            }
                            countLiveData.removeObserver(this);
                        }
                    });
                }
            }
        });

        btnClearInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUsername.setText("");
                edFullname.setText("");
                edPassword.setText("");
                edAge.setText("");
                edEmail.setText("");
                edPhoneNumber.setText("");
                rbMale.setChecked(true);
            }
        });

    }

    private boolean isEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText());
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Input Required");
        builder.setMessage("Please enter all fields");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText focusedEditText = findFirstEmptyEditText();
                if (focusedEditText != null) {
                    focusedEditText.requestFocus();
                }
            }
        });
        builder.show();
    }

    private EditText findFirstEmptyEditText() {
        if (isEmpty(edUsername)) {
            return edUsername;
        } else if (isEmpty(edFullname)) {
            return edFullname;
        } else if (isEmpty(edPassword)) {
            return edPassword;
        } else if (isEmpty(edAge)) {
            return edAge;
        } else if (isEmpty(edEmail)) {
            return edEmail;
        } else if (isEmpty(edPhoneNumber)) {
            return edPhoneNumber;
        }
        return null;
    }
}
