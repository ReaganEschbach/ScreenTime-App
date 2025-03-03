package com.tryingagain;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tryingagain.databinding.ActivityChoreListInitialEditBinding;

import java.util.ArrayList;
import com.tryingagain.Chore;

public class ChoreListInitialEdit extends AppCompatActivity {

    ArrayList<Chore> choreList = new ArrayList<>();
    ArrayAdapter<Chore> adapter;
    EditText choreText, timeText;
    Button addButton, removeButton, continueButton;
    ListView lv;
    ActivityChoreListInitialEditBinding binding;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityChoreListInitialEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //buttons
        addButton = binding.addButton;
        removeButton = binding.removeButton;
        choreText = binding.choreToAdd;
        timeText = binding.timeInSeconds;
        lv = binding.choreList;
        continueButton = binding.continueButton;

        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); //allows multiple items to be selected at once

        sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String message = "What are " + sp.getString("child", "") + "'s chores?";
        TextView display = binding.initialChoreText;
        display.setText(message);

        adapter = new ArrayAdapter<>(ChoreListInitialEdit.this, android.R.layout.simple_list_item_multiple_choice, choreList);

        //removing item from list
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecker = lv.getCheckedItemPositions();
                int count = lv.getCount();
                for(int item = count-1; item>=0; item--){
                    if(positionChecker.get(item)){
                        adapter.remove(choreList.get(item));

                        Toast.makeText(ChoreListInitialEdit.this, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                positionChecker.clear();
                adapter.notifyDataSetChanged();
            }
        });

        //add item (successful)
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if either of the EditText boxes are empty
                if (choreText.getText().toString().isEmpty()) {
                    Toast.makeText(ChoreListInitialEdit.this, "Item field is mandatory", Toast.LENGTH_SHORT).show();
                }
                else if(timeText.getText().toString().isEmpty()){
                    Toast.makeText(ChoreListInitialEdit.this, "Time field is mandatory", Toast.LENGTH_SHORT).show();
                }
                //checks to see if time is input in numbers only
                else if(!(TextUtils.isDigitsOnly(timeText.getText()))){
                    Toast.makeText(ChoreListInitialEdit.this, "Time must be input in seconds", Toast.LENGTH_SHORT).show();
                }
                else{
                    int integer = timeText.getAutofillType();
                    Chore newChore = new Chore(choreText.getText().toString(), integer);
                    choreList.add(newChore);
                    choreText.setText("");
                    timeText.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        lv.setAdapter(adapter);
    }

}
