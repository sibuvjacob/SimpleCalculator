package com.apprightstudio.android.simplecalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static String[] stringArray = new String[3];
    private static String[] tempStringArray = new String[3];
 //   private Button number2, number3, adder, divider, multiplier, subtracter;
    private TextView display;
    private TextView answer_display;
    private double final_answer = 0;
    private String inital_display_string = "";
    private String operator = "";
    //Various boolean checks for incorrect inputs
    private boolean values_entered = false;
    private boolean extra_operator_check = false;
    private boolean secondary_check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog alert1 = new AlertDialog.Builder(MainActivity.this).setTitle("Important Message")
                .setMessage("I understand this app is currently in beta and as such that there will be bugs")
                .setNegativeButton("Disagree", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int answer) {
                        finish();
                        System.exit(0);
                    }
                })
                .setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int answer) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

        //Initalize the variables
        display = (TextView) findViewById(R.id.display);
        answer_display = (TextView) findViewById(R.id.answer_display);
    }

    public void onClickNumber(View view) {
        inital_display_string += ((Button) view).getText();
        values_entered = true;
        updateScreen();

    }

    public void onOperator(View view) {
        if (values_entered == false) {
            Toast.makeText(MainActivity.this, "Please enter numbers first", Toast.LENGTH_SHORT).show();

        } else {
            if (inital_display_string.contains("+") || inital_display_string.contains("-")
                    || inital_display_string.contains("×") || inital_display_string.contains("÷")) {
                extra_operator_check = true;
                if (extra_operator_check == true) {
                    Toast.makeText(MainActivity.this, "Only one operator allowed at a time", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            inital_display_string += ((Button) view).getText();
            updateScreen();
        }

    }

    public void onEquals(View view) {
        if (values_entered == false) {
            return;
        }
        try {
            if (secondary_check == true) {
                if (inital_display_string.contains("+")) {
                    tempStringArray = inital_display_string.split("\\+");
                    operator = "+";
                }
                if (inital_display_string.contains("-")) {
                    tempStringArray = inital_display_string.split("\\-");
                    operator = "-";
                }
                if (inital_display_string.contains("×")) {
                    tempStringArray = inital_display_string.split("\\×");
                    operator = "x";
                }
                if (inital_display_string.contains("÷")) {
                    tempStringArray = inital_display_string.split("\\÷");
                    operator = "÷";
                }
                secondary_check = true;
                final_answer = calculator(stringArray[0], tempStringArray[1], operator);
                stringArray[0] = String.valueOf(final_answer);

            } else if (secondary_check == false) {
                if (inital_display_string.contains("+")) {
                    stringArray = inital_display_string.split("\\+");
                    operator = "+";
                }
                if (inital_display_string.contains("-")) {
                    stringArray = inital_display_string.split("\\-");
                    operator = "-";
                }
                if (inital_display_string.contains("×")) {
                    stringArray = inital_display_string.split("\\×");
                    operator = "x";
                }
                if (inital_display_string.contains("÷")) {
                    stringArray = inital_display_string.split("\\÷");
                    operator = "÷";
                }


                final_answer = calculator(stringArray[0], stringArray[1], operator);
                stringArray[0] = String.valueOf(final_answer);
                secondary_check = true;

            }
            DecimalFormat df = new DecimalFormat("#.###########");
            inital_display_string = "Ans";
            answer_display.setText(df.format(final_answer));

        } catch (Exception a) {

            inital_display_string = "Invalid expression";
            updateScreen();
            OnclearAgain();
        }

    }

    private double calculator(String a, String b, String operator) {
        // try {
        if (operator == "+") {
            return Double.parseDouble(a) + Double.parseDouble(b);
        } else if (operator == "-") {
            return Double.parseDouble(a) - Double.parseDouble(b);

        } else if (operator == "÷") {
            return Double.parseDouble(a) / Double.parseDouble(b);

        } else if (operator == "x") {
            return Double.parseDouble(a) * Double.parseDouble(b);

        }
        return 0;

    }

    public void onClear(View view) {
        values_entered = false;
        secondary_check = false;
        inital_display_string = "";
        answer_display.setText("");
        operator = "";
        final_answer = 0;
        stringArray = null;
        updateScreen();

    }

    private void OnclearAgain() {
        values_entered = false;
        extra_operator_check = false;
        secondary_check = false;
        answer_display.setText("");
        operator = "";
        final_answer = 0;
        stringArray = null;
        tempStringArray = null;
        updateScreen();
    }

    private void updateScreen() {
        display.setText(inital_display_string);

    }


}
