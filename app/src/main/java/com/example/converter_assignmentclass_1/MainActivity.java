package com.example.converter_assignmentclass_1;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName;
    private Button btnConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }

    private void initViews() {
        etUserName = findViewById(R.id.et_user_name);
        btnConvert = findViewById(R.id.btn_convert);
    }

    private void initListeners() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(etUserName.getText().toString());
            }
        });
    }

    private void showCustomDialog(final String userName) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);

        final TextView tvResult = dialog.findViewById(R.id.tv_result);
        final RadioButton rbUpperCase = dialog.findViewById(R.id.rb_upper_case);
        final RadioButton rbLowerCase = dialog.findViewById(R.id.rb_lower_case);
        final RadioButton rbProperCase = dialog.findViewById(R.id.rb_proper_case);
        final CheckBox cbReverseText = dialog.findViewById(R.id.cb_reverse_text);
        Button btnFinish = dialog.findViewById(R.id.btn_finish);

        // Method to update the TextView based on the selected radio button and checkbox
        View.OnClickListener updateText = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultText = userName;

                if (rbUpperCase.isChecked()) {
                    resultText = resultText.toUpperCase();
                } else if (rbLowerCase.isChecked()) {
                    resultText = resultText.toLowerCase();
                } else if (rbProperCase.isChecked()) {
                    resultText = properCase(resultText);
                }

                if (cbReverseText.isChecked()) {
                    resultText = new StringBuilder(resultText).reverse().toString();
                }

                tvResult.setText(resultText);
            }
        };

        rbUpperCase.setOnClickListener(updateText);
        rbLowerCase.setOnClickListener(updateText);
        rbProperCase.setOnClickListener(updateText);
        cbReverseText.setOnClickListener(updateText);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // Utility function to convert text to proper case
    private String properCase(String text) {
        String[] words = text.split(" ");
        StringBuilder properCaseText = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                properCaseText.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return properCaseText.toString().trim();
    }
}