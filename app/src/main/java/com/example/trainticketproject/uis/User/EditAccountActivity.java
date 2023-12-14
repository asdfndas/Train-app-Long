package com.example.trainticketproject.uis.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.Gender;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.viewmodels.UserViewModel;

import java.util.concurrent.Executors;

public class EditAccountActivity extends AppCompatActivity {
    private EditText edUsername, edFullname, edPassword, edAge, edEmail, edPhoneNumber;
    private RadioButton rbMale, rbFemale;
    private Button btnSave, btnClearInfo;
    private UserViewModel userViewModel;
    Long uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        edUsername = findViewById(R.id.edUsername3);
        edFullname = findViewById(R.id.edFullname4);
        edPassword = findViewById(R.id.edPassword4);
        edAge = findViewById(R.id.edAge5);
        edEmail = findViewById(R.id.edEmail4);
        edPhoneNumber = findViewById(R.id.edPhone4);
        rbMale = findViewById(R.id.rbMale3);
        rbFemale = findViewById(R.id.rbFemale3);
        btnSave = findViewById(R.id.btnSave3);
        btnClearInfo = findViewById(R.id.btnClearInFo3);
        rbMale.setChecked(true);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        Intent detailIntent = getIntent();
        uid = detailIntent.getLongExtra("uid", 1);

        btnSave.setOnClickListener(new View.OnClickListener() {
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

                    Executors.newSingleThreadExecutor().execute(new Runnable() {
                        @Override
                        public void run() {
                            userViewModel.updateUserById(uid,username, fullname, age, gender, email, password, phoneNumber);
                        }
                    });
                    Toast.makeText(EditAccountActivity.this, "User information updated successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        userViewModel.getUserById(uid).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null) {
                    fillUserData(user);
                }
            }
        });
    }
    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private void showInputDialog() {
        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
    }
    private void fillUserData(User user) {
        edUsername.setText(user.getUsername());
        edFullname.setText(user.getFullname());
        edPassword.setText(user.getPassword());
        edAge.setText(String.valueOf(user.getAge()));
        edEmail.setText(user.getEmail());
        edPhoneNumber.setText(user.getPhone());

        if (user.getGender() == Gender.MALE) {
            rbMale.setChecked(true);
            rbFemale.setChecked(false);
        } else {
            rbMale.setChecked(false);
            rbFemale.setChecked(true);
        }
    }


}