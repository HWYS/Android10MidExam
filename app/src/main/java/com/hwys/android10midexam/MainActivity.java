package com.hwys.android10midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hwys.android10midexam.db.PostDatabase;
import com.hwys.android10midexam.db.model.UserModel;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText tetUserName, tetPassword;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tetUserName = findViewById(R.id.etUserName);
        tetPassword = findViewById(R.id.etPassword);

        preferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        if(preferences.getInt("u_id", 0)>0){
            startActivity(new Intent(this, PostActivity.class));
            finish();
        }
    }

    public void goToRegister(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }

    public void logIn(View view) {
        UserModel model = new PostDatabase(MainActivity.this).getLogInUser(tetUserName.getText().toString(), tetPassword.getText().toString());
        if(model!= null && model.getU_id()>0){

            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("u_id", model.getU_id());
            editor.apply();
            Toast.makeText(MainActivity.this, "User Name and Password do not match or User does no exists", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, PostActivity.class));
            finish();
        }
        else
            Toast.makeText(MainActivity.this, "User Name and Password do not match or User does no exists", Toast.LENGTH_LONG).show();
    }
}
