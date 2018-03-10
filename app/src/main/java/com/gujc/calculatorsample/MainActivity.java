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

        button0 = findViewById(R.id.button0);

        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                editText.setText( button0.getText());
            }
        });

        Button button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Button a= (Button) view;
                editText.setText(a.getText());
            }
        });
    }
}
