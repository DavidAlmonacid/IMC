package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private double weight;
    private double height;
    private int age;
    private String gender;
    private double IMC;
    private TextView result;
    private TextView message;
    private TextView recommendation1;
    private TextView recommendation2;
    private TextView recommendation3;
    private ImageView resultImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        result = findViewById(R.id.result);
        message = findViewById(R.id.message);
        recommendation1 = findViewById(R.id.recommendation1);
        recommendation2 = findViewById(R.id.recommendation2);
        recommendation3 = findViewById(R.id.recommendation3);
        resultImage = findViewById(R.id.resultImage);

        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person");

        assert person != null;
        weight = person.getWeight();
        height = person.getHeight();
        age = person.getAge();
        gender = person.getGender();

        showIMC();
        showMessage();
        showRecommendations();
        showImage();
    }

    public void showIMC() {
        if (age < 18) {
            return;
        }

        IMC = weight / Math.pow(height, 2);

        //redondear a 2 decimales
        IMC = Math.round(IMC * 100.0) / 100.0;

        result.setText(String.valueOf(IMC));

        if (IMC >= 18.5 && IMC < 25) {
            result.setTextColor(Color.parseColor("#81B29A"));
        }
    }

    public void showMessage() {
        if (age < 18) {
            message.setText("Visita a un nutricionista para conocer tu IMC");
            return;
        }

        String[] messages = {"Bajo peso", "Peso normal", "Sobrepeso", "Obesidad"};

        if (IMC < 18.5) {
            message.setText(messages[0]);
        } else if (IMC < 25) {
            message.setText(messages[1]);
        } else if (IMC < 30) {
            message.setText(messages[2]);
        } else {
            message.setText(messages[3]);
        }
    }

    public void showRecommendations() {
        if (age < 18) {
            return;
        }

        String[] recommendations;

        if (IMC < 18.5) {
            recommendations = getResources().getStringArray(R.array.underweight_array);
        } else if (IMC < 25) {
            recommendations = getResources().getStringArray(R.array.normal_weight_array);
        } else if (IMC < 30) {
            recommendations = getResources().getStringArray(R.array.overweight_array);
        } else {
            recommendations = getResources().getStringArray(R.array.obesity_array);
        }

        recommendation1.setText(recommendations[0]);
        recommendation2.setText(recommendations[1]);
        recommendation3.setText(recommendations[2]);
    }

    public void showImage() {
        if (age < 18) {
            return;
        }

        if (IMC < 18.5) {
            if (gender.equals("Femenino")) {
                resultImage.setImageResource(R.drawable.flaco2);
            } else {
                resultImage.setImageResource(R.drawable.flaco1);
            }
        } else if (IMC < 25) {
            if (gender.equals("Femenino")) {
                resultImage.setImageResource(R.drawable.normal2);
            } else {
                resultImage.setImageResource(R.drawable.normal1);
            }
        } else if (IMC < 30) {
            if (gender.equals("Femenino")) {
                resultImage.setImageResource(R.drawable.gordo2);
            } else {
                resultImage.setImageResource(R.drawable.gordo1);
            }
        } else {
            resultImage.setImageResource(R.drawable.obeso);
        }
    }
}
