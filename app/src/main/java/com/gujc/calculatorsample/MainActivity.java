package com.gujc.calculatorsample;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    private TextView editText;
    private String operator = null;
    private String firstValue = "";
    private boolean isInit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText= findViewById(R.id.editText);

        String[] chars = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "+", "0", "=", "-"};
        LinearLayout parentLayout = (LinearLayout) editText.getParent();
        LinearLayout layout = null;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;

        for(int i=0; i<chars.length; i++) {
            if (i % 4 == 0) {
                layout = new LinearLayout(this);
                layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                layout.setOrientation(LinearLayout.HORIZONTAL);
                parentLayout.addView(layout);
            }
            Button button = new Button(this);
            button.setText(chars[i]);
            button.setLayoutParams(params);
            button.setOnClickListener(mClickListener);
            layout.addView(button);
        }
    }
    /*
     1. 숫자 입력
     2. 연산자 입력
        2.1 첫번째수(firstValue)가 없으면 입력한 숫자를 첫번째수로 지정
        2.1 연산자 보관
     3. 숫자입력
     4. 연산자, = 입력
        4.1 입력한 숫자를 두번째수(secondValue)로 지정
        4.2 연산
        4.3 = 이면 모두 초기화
        4.4 = 이 아니면 계산값은 첫번째수, 연산자 보관
     */
    Button.OnClickListener mClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            Button button = (Button) view;
            String clickValue = button.getText().toString();

            switch (clickValue) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "=":
                    if ("".equals(firstValue)) {
                        firstValue = editText.getText().toString();
                        editText.setText("");
                    } else
                    if (!"".equals(operator)) {
                        String secondValue = editText.getText().toString();
                        Integer cal = 0;
                        switch (operator) {
                            case "+": cal = Integer.parseInt(firstValue) + Integer.parseInt(secondValue); break;
                            case "-": cal = Integer.parseInt(firstValue) - Integer.parseInt(secondValue); break;
                            case "*": cal = Integer.parseInt(firstValue) * Integer.parseInt(secondValue); break;
                            case "/": cal = Integer.parseInt(firstValue) / Integer.parseInt(secondValue); break;
                        }

                        editText.setText(cal.toString());
                        firstValue = "";
                        isInit = true;

                        if ("=".equals(clickValue)) {
                            operator = "";
                            return;
                        }
                        firstValue = cal.toString();
                    }
                    operator = clickValue;

                    break ;
                default:
                    if (isInit) {
                        isInit = false;
                        editText.setText(clickValue);
                    } else {
                        editText.setText(editText.getText().toString() + clickValue);
                    }
            }

        }
    };
}