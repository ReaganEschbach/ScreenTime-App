package com.tryingagain;

import android.content.Context;
import android.content.Intent;
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


    ArrayList<String> choreList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    EditText choreText, timeText;
    Button addButton, removeButton, continueButton;
    ListView lv;
    ActivityChoreListInitialEditBinding binding;

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

        String message = "What are " + ParentInfo.getChildName() + "'s chores?";
        TextView display = binding.initialChoreText;
        display.setText(message);

        adapter = new ArrayAdapter<>(ChoreListInitialEdit.this, android.R.layout.simple_list_item_multiple_choice, choreList);

        //removing item from list (successful)
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecker = lv.getCheckedItemPositions();
                int count = lv.getCount();
                //if there is only one item
                if(count==1 && Chore.getChoreListSize()==1){
                    adapter.remove(choreList.get(0));
                    Chore.clearList();
                }
                //if there are multiple items in the list
                else {
                    for (int item = count-1; item >=0; item--) {
                        if (positionChecker.get(item)) {
                            adapter.remove(choreList.get(item)); //removes from string array
                            Chore.removeFromChores(choreList.get(item));//removes from static array
                        }
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
                //if both fields are fulfilled
                else{
                    int integer = Integer.parseInt(timeText.getText().toString());
                    Chore newItem = new Chore(choreText.getText().toString(), integer);
                    choreList.add(Chore.formatStringForView(newItem)); //actual adding action
                    timeText.setText("");
                    adapter.notifyDataSetChanged();

                    //adding to Chore class list
                    Chore.addToChores(new Chore(choreText.getText().toString(), integer)); //uses the parsed int from the first line in else segment
                    choreText.setText("");
                }
            }
        });

        lv.setAdapter(adapter);


        //continue to next activity
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ChoreListInitialEdit.this, ChildScreen.class);
                startActivity(intent);
            }
        });
    }

}
