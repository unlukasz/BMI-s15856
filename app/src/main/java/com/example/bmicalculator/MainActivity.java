package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText height, weight;
        TextView value, description;
        Button count;

        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);

        value = (TextView) findViewById(R.id.value);
        description = (TextView) findViewById(R.id.description);

        count = (Button) findViewById(R.id.count);

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String h1 = height.getText().toString();
                String w1 = weight.getText().toString();
                boolean numericH = true;
                boolean numericW = true;

                if(w1.equals("") || w1 == null){
                    weight.setError("Podaj poprawną wagę");
                    weight.requestFocus();
                    return;
                }
                if(h1.equals("") || h1 == null) {
                    height.setError("Podaj poprawny wzrost");
                    height.requestFocus();
                    return;
                }
                try {
                    float h2 = Float.parseFloat(h1) / 100;
                }catch (NumberFormatException e){
                    numericH = false;
                }
                try {
                    float w2 = Float.parseFloat(w1);
                }catch (NumberFormatException e){
                    numericW = false;
                }
                if(numericH == true && numericW == true){
                    float h2 = Float.parseFloat(h1) / 100;
                    float w2 = Float.parseFloat(w1);
                    float bmiValue = BMI(w2,h2);

                    value.setText("BMI = " + String.format("%.02f", bmiValue));
                    description.setText(BMICases(bmiValue));
                }else{
                    if(numericW == false){
                        weight.setError("Podaj poprawną wagę");
                        weight.requestFocus();
                        return;
                    }
                    if(numericH == false){
                        height.setError("Podaj poprawny wzrost");
                        height.requestFocus();
                        return;
                    }
                }
            }
        });
    }
    public float BMI(float weight, float height){
        return weight / (height * height);
    }
    public String BMICases(float Score){
        if(Score < 16){
            return "Wygłodzenie";
        }else if(Score < 17){
            return "Wychudzenie";
        }else if(Score < 18.5){
            return "Niedowaga";
        }else if(Score < 25){
            return "Wartość prawidłowa";
        }else if(Score < 30){
            return "Nadwaga";
        }else if(Score < 35){
            return "1 Stopień otyłości";
        }else if(Score < 40){
            return "2 Stopień otyłości";
        }else{
            return "Otyłość skrajna";
        }
    }
}