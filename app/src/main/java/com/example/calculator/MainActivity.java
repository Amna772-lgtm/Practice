package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result_tv, solution_tv;
    MaterialButton btnC, btnOpen, btnClose;
    MaterialButton btnDivide, btnMultiply, btnAdd, btnMinus, btnEquals;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    MaterialButton btnAC, btnDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv=findViewById(R.id.result);
        solution_tv=findViewById(R.id.sol);

        assignID(btnC, R.id.button_c);
        assignID(btnAC, R.id.button_AC);
        assignID(btnOpen, R.id.open_bracket);
        assignID(btnClose, R.id.close_bracket);
        assignID(btn0, R.id.button_0);
        assignID(btn1, R.id.button_1);
        assignID(btn2, R.id.button_2);
        assignID(btn3, R.id.button_3);
        assignID(btn4, R.id.button_4);
        assignID(btn5, R.id.button_5);
        assignID(btn6, R.id.button_6);
        assignID(btn7, R.id.button_7);
        assignID(btn8, R.id.button_8);
        assignID(btn9, R.id.button_9);
        assignID(btnMultiply, R.id.multiply);
        assignID(btnMinus, R.id.minus);
        assignID(btnAdd, R.id.plus);
        assignID(btnDivide, R.id.divide);
        assignID(btnDot, R.id.button_dot);
        assignID(btnEquals, R.id.equal);
    }

    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String data=solution_tv.getText().toString();

        if(buttonText.equals("AC"))
        {
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            solution_tv.setText(result_tv.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            data = data.substring(0,data.length()-1);
        }
        else
        {
            data = data + buttonText;
        }

        solution_tv.setText(data);

        String finalResult = getResult(data);
        if(!finalResult.equals("Err"))
        {
            result_tv.setText(finalResult);
        }
    }

    String getResult(String data)
    {
        try
        {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch(Exception e)
        {
            return "Err";
        }
    }
}