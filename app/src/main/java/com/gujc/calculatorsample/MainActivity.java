package com.gujc.calculatorsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView editText;
    private Button button0;

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
            Button a= (Button) view;
            editText.setText(editText.getText().toString() + a.getText().toString());
        }
    };

}
