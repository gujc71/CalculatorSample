package com.gujc.calculatorsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    /*
     1. 숫자 입력
     2. 연산자 입력
        2.1 피젯수(dividend)가 없으면 입력한 숫자를 피젯수로 지정
        2.1 연산자 보관
     3. 숫자입력
     4. 연산자, = 입력
        4.1 입력한 숫자를 젯수(divisor)로 지정
        4.2 연산
        4.3 = 이면 모두 초기화
        4.4 = 이 아니면 계산값은 피젯수, 연산자 보관
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
                    if ("".equals(dividend)) {
                        dividend = editText.getText().toString();
                        editText.setText("");
                    } else
                    if (!"".equals(operator)) {
                        String divisor = editText.getText().toString();
                        Integer cal = 0;
                        switch (operator) {
                            case "+": cal = Integer.parseInt(dividend) + Integer.parseInt(divisor); break;
                            case "-": cal = Integer.parseInt(dividend) - Integer.parseInt(divisor); break;
                            case "*": cal = Integer.parseInt(dividend) * Integer.parseInt(divisor); break;
                            case "/": cal = Integer.parseInt(dividend) / Integer.parseInt(divisor); break;
                        }

                        editText.setText(cal.toString());
                        dividend = "";
                        isInit = true;

                        if ("=".equals(clickValue)) {
                            operator = "";
                            return;
                        }
                        dividend = cal.toString();
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
