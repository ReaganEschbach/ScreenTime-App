package com.tryingagain;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.tryingagain.databinding.ActivityFinishedChoreScreenBinding;

public class FinishedChoreScreen extends AppCompatActivity {

    ActivityFinishedChoreScreenBinding binding;
    Dialog dialog;
    Button btnDialogCancel, btnDialogContinue;
    EditText passDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinishedChoreScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String congratsMessage = "Congratulations " + ParentInfo.getChildName() + "!";
        binding.congratsChildName.setText(congratsMessage);

        String rewardMessage = "Have your parent enter password for " + Chore.getTimeStringLayout(Chore.getSelectedChore()) + "\nof screen time.";
        binding.displayRewardedScreenTime.setText(rewardMessage);

        //popup for parent password to continue
        dialog = new Dialog(FinishedChoreScreen.this);
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
                    Intent intent = new Intent(FinishedChoreScreen.this, ParentSettingsEdit.class);
                    startActivity(intent);
                    dialog.dismiss();
                }
                else{
                    Log.d("Parent Password: ", ParentInfo.getParentPassword());
                    Log.d("Password Attempt: ", passAttempt);
                    Toast.makeText(FinishedChoreScreen.this, "Incorrect Password, Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //parent password input and validation
        binding.validatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ParentInfo.attemptPassword(binding.validatePasswordInput.getText().toString())){
                    finish();
                    Intent intent = new Intent(FinishedChoreScreen.this, Timer.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(FinishedChoreScreen.this, "Incorrect Password, try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //to parent page
        binding.toParentSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }
}