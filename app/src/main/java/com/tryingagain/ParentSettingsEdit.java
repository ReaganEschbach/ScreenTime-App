package com.tryingagain;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.tryingagain.databinding.ActivityParentSettingsEditBinding;

import java.util.ArrayList;
import java.util.Objects;

public class ParentSettingsEdit extends AppCompatActivity {

    Button backToChildScreen, forgotPassword, addChore, removeChore;
    EditText name, time;
    ListView lv;
    ArrayAdapter<String> adapter;
    ActivityParentSettingsEditBinding binding;
    ArrayList<String> choreNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParentSettingsEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //buttons
        backToChildScreen = binding.backToChildScreen;
        forgotPassword = binding.forgotPassword;
        addChore = binding.parentSettingsAdd;
        removeChore = binding.parentSettingsRemove;

        //EditText Chore info
        name = findViewById(R.id.parent_settings_add_name);
        time = findViewById(R.id.parent_settings_add_time);

        //initializing listview
        for(int i=0;i<Chore.getChoreListSize();i++){
            choreNames.add(Objects.requireNonNull(Chore.getChoreFromList(i)).getName());
        }
        lv = binding.editChoreParentScreen;
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        adapter = new ArrayAdapter<>(ParentSettingsEdit.this, android.R.layout.simple_list_item_multiple_choice, choreNames);
        lv.setAdapter(adapter);

        //add button
        addChore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if either name or time field is empty
                if(name.getText().toString().isEmpty() || time.getText().toString().isEmpty()){
                    Toast.makeText(ParentSettingsEdit.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    choreNames.add(name.getText().toString());//adding to listview
                    int integer = Integer.parseInt(time.getText().toString());
                    Chore.addToChores(new Chore(name.getText().toString(), integer));//updating static chore list

                    adapter.notifyDataSetChanged();
                    time.setText("");
                    name.setText("");
                }
            }
        });
        //remove button
        removeChore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray positionChecker = lv.getCheckedItemPositions();
                int count = lv.getCount();

                //if there is only one item checked
                if(count==1 && Chore.getChoreListSize()==1){
                    adapter.remove(choreNames.get(0));
                    Chore.clearList();
                }

                //if there are multiple items checked
                else{
                    for(int item = count-1; item>=0;item--){
                        if(positionChecker.get(item)){
                            adapter.remove(choreNames.get(item));
                            Chore.removeFromChores(choreNames.get(item));
                        }
                    }
                }
            }
        });

        backToChildScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ParentSettingsEdit.this, ChildScreen.class);
                startActivity(intent);
            }
        });
    }
}