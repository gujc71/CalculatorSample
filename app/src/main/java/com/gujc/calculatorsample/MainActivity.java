package com.gujc.calculatorsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    private TextView editText;
    private String operator = null;
    private String dividend = "";
    private boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText= findViewById(R.id.editText);

        for(int i=0; i<15; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            findViewById(resID).setOnClickListener(mClickListener);
        }
    }
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            Button button= (Button) view;
            String clickValue = button.getText().toString();

            if ("=".equals(clickValue)) {
                editText.setText( Calc (editText.getText().toString() ) );
            } else {
                editText.setText(editText.getText().toString() + clickValue);
            }
        }
    };

    // original code: https://gist.github.com/kor-trickster/789450
    private String Calc(String formulaStr){
        StringTokenizer st_num  = new StringTokenizer(formulaStr,"+-/* ");
        StringTokenizer st_oper = new StringTokenizer(formulaStr,"1234567890 ");

        Stack<Integer> valueStack = new Stack <Integer>();
        valueStack.push(Integer.parseInt(st_num.nextToken()));
        while(st_num.hasMoreTokens()){
            String operator = st_oper.nextToken();
            String num = st_num.nextToken();
            int a;

            if ("*".equals(operator)){
                a = valueStack.pop();
                valueStack.push( a * Integer.parseInt(num) );
            }
            else if ("/".equals(operator)){
                a = valueStack.pop();
                valueStack.push( a / Integer.parseInt(num) );
            }
            else if ("+".equals(operator)){
                valueStack.push(Integer.parseInt(num));
            }
            else if ("-".equals(operator)){
                valueStack.push(-1 * (Integer.parseInt(num)));
            }
        }

        int tot = 0;
        while(!valueStack.isEmpty()){
            tot += valueStack.pop();
        }

        return Integer.toString(tot);
    }
}
