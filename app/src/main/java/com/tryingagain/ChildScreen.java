package com.tryingagain;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tryingagain.databinding.ActivityChildScreenBinding;

import java.util.ArrayList;

public class ChildScreen extends AppCompatActivity {

    Button parentSettings, btnDialogCancel, btnDialogContinue;
    ListView lv;
    ArrayAdapter<String> adapter;
    TextView helloChild;
    EditText passDialog;
    SharedPreferences sp;
    ActivityChildScreenBinding binding;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChildScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        helloChild = binding.helloChildName;
        helloChild.setText("Hello " + ParentInfo.getChildName() + "!");

        parentSettings = binding.toParentSettings;

        //popup for parent password to continue
        dialog = new Dialog(ChildScreen.this);
        dialog.setContentView(R.layout.parent_password_required);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.round_rectangle_background));
        dialog.setCancelable(false);
            //pop up views
        passDialog = dialog.findViewById(R.id.dialog_verification);
        btnDialogCancel = dialog.findViewById(R.id.pop_up_cancel);
        btnDialogContinue = dialog.findViewById(R.id.pop_up_continue);
            //pop up button instructions
        btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnDialogContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passAttempt = passDialog.getText().toString();
                if(ParentInfo.attemptPassword(passAttempt)){
                    finish();
                    Intent intent = new Intent(ChildScreen.this, ParentSettingsEdit.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
                else{
                    Log.d("Parent Password: ", ParentInfo.getParentPassword());
                    Log.d("Password Attempt: ", passAttempt);
                    Toast.makeText(ChildScreen.this, "Incorrect Password, Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //pop up before parent settings
        parentSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        //ListView stuff (successful) (needs activity for chore completion)
            //getting data from Chore class
        ArrayList<String> choreList = new ArrayList<>();
        for(int i=0;i<Chore.getChoreListSize();i++){
            choreList.add(Chore.getChoreFromList(i).getName());
        }
            //adding data to the ListView
        adapter = new ArrayAdapter<>(ChildScreen.this, android.R.layout.simple_list_item_1, choreList);
        lv = findViewById(R.id.display_child_chores);
        lv.setAdapter(adapter);

            //when item is clicked
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Chore selected = Chore.getChoreFromList(position);
                Chore.setSelectedChore(selected);

                finish();
                Intent intent = new Intent(ChildScreen.this, FinishedChoreScreen.class);
                startActivity(intent);
            }
        });

    }
}