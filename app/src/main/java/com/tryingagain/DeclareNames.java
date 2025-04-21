 package com.tryingagain;

import static com.tryingagain.ParentInfo.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tryingagain.databinding.ActivityMainBinding;

 public class DeclareNames extends AppCompatActivity {

    ActivityMainBinding binding;
    EditText parentName, childName;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        continueButton = findViewById(R.id.continue_button);

        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pname = binding.parentName.getText().toString();
                String cname = binding.childName.getText().toString();

                if(!pname.isEmpty() && !cname.isEmpty()){
                    setChildName(cname);
                    setParentName(pname);

                Toast.makeText(DeclareNames.this, "User info successfully saved", Toast.LENGTH_SHORT).show();

                finish();
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