package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private EditText weightInput;
    private EditText heightInput;
    private EditText ageInput;
    private Spinner genderInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInput = findViewById(R.id.weight);
        heightInput = findViewById(R.id.height);
        ageInput = findViewById(R.id.age);
        genderInput = findViewById(R.id.gender);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array,
                R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_item);

        genderInput.setAdapter(adapter);
        genderInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                parent.getItemAtPosition(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Button calculateButton = findViewById(R.id.calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sendData(v);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendData(View view) {
        double weight = Double.parseDouble(weightInput.getText().toString());
        double height = Double.parseDouble(heightInput.getText().toString());
        int age = Integer.parseInt(ageInput.getText().toString());
        String gender = genderInput.getSelectedItem().toString();

        Person person = new Person(weight, height, age, gender);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("person", person);
        startActivity(intent);
    }
}
