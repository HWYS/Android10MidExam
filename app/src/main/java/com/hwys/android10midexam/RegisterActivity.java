package com.hwys.android10midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hwys.android10midexam.db.PostDatabase;
import com.hwys.android10midexam.db.model.UserModel;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText tetUserName, tetPassword, tetConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tetUserName = findViewById(R.id.etUserName);
        tetPassword = findViewById(R.id.etPassword);
        tetConfirmPassword = findViewById(R.id.etConfirmPassword);
    }

    public void register(View view) {
        if(!(tetPassword.getText().toString()).equals(tetConfirmPassword.getText().toString()) ){
            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
            return;
        }
        if(new PostDatabase(RegisterActivity.this).register(new UserModel(tetUserName.getText().toString(), tetPassword.getText().toString()))){
            Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_LONG).show();
            finish();
        }
        else
            Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_LONG).show();
    }
}
