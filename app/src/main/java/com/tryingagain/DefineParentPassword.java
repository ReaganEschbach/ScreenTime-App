package com.tryingagain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tryingagain.databinding.ActivityDefineParentPasswordBinding;

public class DefineParentPassword extends AppCompatActivity {

    ActivityDefineParentPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDefineParentPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String parentName = "Hello, " + ParentInfo.getParentName() + "!";

        TextView display = findViewById(R.id.display_parent_name);
        display.setText(parentName);


        //saving the password to SharedPreferences when the button is hit
        binding.passContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String finalPassword = binding.parentPassword.getText().toString();
                String testPassword = binding.confirmPassword.getText().toString();
                String parentEmail= binding.defineParentEmail.getText().toString();

                if (finalPassword.equals(testPassword) && !finalPassword.isEmpty() && !parentEmail.isEmpty()) {
                    ParentInfo.setPassword(finalPassword);
                    ParentInfo.setParentEmail(parentEmail);

                    Intent intent = new Intent(DefineParentPassword.this, ChoreListInitialEdit.class);
                    finish();
                    startActivity(intent);
                }
                else{
                    if(finalPassword.isEmpty() || testPassword.isEmpty() || parentEmail.isEmpty()){
                        Toast.makeText(DefineParentPassword.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(DefineParentPassword.this, "Passwords must match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //going back to login/name declaration
        binding.backToNameDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(DefineParentPassword.this, DeclareNames.class);
                startActivity(intent);
            }
        });

    }
}