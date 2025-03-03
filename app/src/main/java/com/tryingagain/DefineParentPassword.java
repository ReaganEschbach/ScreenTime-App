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
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDefineParentPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String parentName = "Hello, " + sp.getString("parent","") + "!";

        TextView display = findViewById(R.id.display_parent_name);
        display.setText(parentName);


        //saving the password to SharedPreferences when the button is hit
        binding.passContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String finalPassword = binding.parentPassword.getText().toString();
                String testPassword = binding.confirmPassword.getText().toString();
                Integer screenTimeAmount= Integer.parseInt(binding.screenTimeAmount.getText().toString());

                if (finalPassword.equals(testPassword) && !finalPassword.isEmpty()) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("parentPassword", finalPassword);
                    editor.apply();

                    Toast.makeText(DefineParentPassword.this, "Password Saved Successfully", Toast.LENGTH_SHORT).show();

                    if(screenTimeAmount<1){
                        Toast.makeText(DefineParentPassword.this, "Enter a time limit", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        editor.putInt("timeLimit", screenTimeAmount);
                        editor.apply();

                        Intent next = new Intent(DefineParentPassword.this, ChoreListInitialEdit.class);
                        startActivity(next);
                    }

                }
                else{
                    if(finalPassword.isEmpty() || testPassword.isEmpty()){
                        Toast.makeText(DefineParentPassword.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(DefineParentPassword.this, "Passwords must match", Toast.LENGTH_SHORT).show();
                    }

                    //going back to login/name declaration
                    binding.backToNameDeclaration.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(DefineParentPassword.this, DeclareNames.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });




    }
}