package com.example.android.simplecalculator;

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
    private Button number2, number3, adder, divider, multiplier, subtracter;
    private TextView display;
    private TextView answer_display;
    private double final_answer = 0;
    private String inital_display_string = "";
    private String operator = "";
    //Various boolean checks
    private boolean values_entered = false;
    private boolean extra_operator_check = false;
    private boolean secondary_check = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog alert1 = new AlertDialog.Builder(MainActivity.this).setTitle("Important Message")
                .setMessage("This is just a fun project. We(Developers)(me :P) do not assume liability of any kind. By signing this agreement," +
                        " I(User) agrees to hold the developers entirely free from any liability")
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

//AlertDialog dialog = alert1.create();


        //Initalize the variables
        display = (TextView) findViewById(R.id.display);
        answer_display = (TextView) findViewById(R.id.answer_display);

        // adder = (Button) findViewById(R.id.adder);
        // subtracter = (Button) findViewById(R.id.subtracter);
        // multiplier = (Button) findViewById(R.id.multiplier);
        // divider = (Button) findViewById(R.id.divider);
        //number2 = (Button) findViewById(R.id.number_2);
        //number3 = (Button) findViewById(R.id.number_3);
        //display.setText(inital_display_string);
        //ArrayList<String> newArray = new ArrayList();


    }

    public void onClickNumber(View view) {
        // Button theNumber = (Button) view;
        inital_display_string += ((Button) view).getText();

        values_entered = true;
        // display.setText(inital_display_string);
        updateScreen();

    }

    public void onOperator(View view) {
        if (values_entered == false) {
            Toast.makeText(MainActivity.this, "Please enter numbers first", Toast.LENGTH_SHORT).show();

        } else {
            if (inital_display_string.contains("+") || inital_display_string.contains("-") || inital_display_string.contains("×") || inital_display_string.contains("÷")) {
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
                // tempStringArray[0]="43";
                //inital_display_string = "";
                //answer_display.setText("hai "+tempStringArray[0]);
                //updateScreen();
                // Toast.makeText(MainActivity.this,inital_display_string, Toast.LENGTH_SHORT).show();
                // Toast.makeText(MainActivity.this,tempStringArray[1], Toast.LENGTH_SHORT).show();
//new AlertDialog(this).setMessage(tempStringArray[0]).se;
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

            //answer_display.setText(String.format("%.9f",final_answer));
            inital_display_string = "Ans";
            answer_display.setText(df.format(final_answer));

//answer_display.setText(stringArray[0]);
        } catch (Exception a) {
            //display.setText("Invalid expression ");
            //answer_display.setText("");
            inital_display_string = "Invalid expression";
            updateScreen();
            OnclearAgain();

            /*values_entered = false;
            inital_display_string = "Error at on equals bad expression";
            answer_display.setText("");
            operator = "";
            final_answer = 0;
            stringArray = null;
            //Toast.makeText(MainActivity.this, "Welcome to Sibu's Simple Calculator", Toast.LENGTH_SHORT).show();
            updateScreen();*/
        }

        //double firstVar = Integer.parseInt(stringArray[0]);
        // inital_display_string =null;
        //  inital_display_string += firstVar;


        // updateScreen();

        // answer_display.setText(inital_display_string);

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
        //  } catch (Exception e) {
        //Toast.makeText(MainActivity.this,"Error bad expression",Toast.LENGTH_SHORT).show();
        //display.setText("Error bad expression");
        // answer_display.setText("");

           /* values_entered = false;
            inital_display_string = "Error bad expression";
            answer_display.setText("");
            operator = "";
            final_answer = 0;
            stringArray = null;
            //Toast.makeText(MainActivity.this, "Welcome to Sibu's Simple Calculator", Toast.LENGTH_SHORT).show();
            updateScreen();
        }*/
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
     //   Toast.makeText(MainActivity.this, "Welcome to Sibu's Simple Calculator", Toast.LENGTH_SHORT).show();
        updateScreen();

    }

    private void OnclearAgain() {
        values_entered = false;
        extra_operator_check = false;
        secondary_check = false;
        // inital_display_string = "";
        answer_display.setText("");
        operator = "";
        final_answer = 0;
        stringArray = null;
        tempStringArray = null;
        // Toast.makeText(MainActivity.this, "Welcome to Sibu's Simple Calculator", Toast.LENGTH_SHORT).show();
        updateScreen();
    }


    private void updateScreen() {
        display.setText(inital_display_string);
        //answer_display.setText(inital_display_string);

    }


}
