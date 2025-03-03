 package com.tryingagain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tryingagain.databinding.ActivityMainBinding;

 public class DeclareNames extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedPreferences sp;
    EditText parentName, childName;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        parentName = findViewById(R.id.parent_name);
        childName = findViewById(R.id.child_name);
        continueButton = findViewById(R.id.continue_button);
        //SharedPreferences
        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);

        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pname = binding.parentName.getText().toString();
                String cname = binding.childName.getText().toString();

                if(!pname.isEmpty() && !cname.isEmpty()){
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("parent", pname);
                editor.putString("child", cname);
                editor.apply();
                Toast.makeText(DeclareNames.this, "User info successfully saved", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DeclareNames.this, DefineParentPassword.class);
                startActivity(intent);
                }
                else{
                    Toast.makeText(DeclareNames.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}