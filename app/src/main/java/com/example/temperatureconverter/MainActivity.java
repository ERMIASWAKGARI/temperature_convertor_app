package com.example.temperatureconverter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputEditText;
    private TextView celsiusTextView, fahrenheitTextView, kelvinTextView;
    private Spinner unitSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        celsiusTextView = findViewById(R.id.celsiusTextView);
        fahrenheitTextView = findViewById(R.id.fahrenheitTextView);
        kelvinTextView = findViewById(R.id.kelvinTextView);
        unitSpinner = findViewById(R.id.unitSpinner);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);
        unitSpinner.setAdapter(adapter);

        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateConversion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    public void convertTemperature(View view) {
        updateConversion();
    }

    @SuppressLint("DefaultLocale")
    private void updateConversion() {
        String inputText = inputEditText.getText().toString();

        if (!inputText.isEmpty()) {
            double inputTemperature = Double.parseDouble(inputText);
            int selectedUnitPosition = unitSpinner.getSelectedItemPosition();

            double celsius = 0, fahrenheit = 0, kelvin = 0;

            switch (selectedUnitPosition) {
                case 0: // Celsius
                    celsius = inputTemperature;
                    fahrenheit = celsiusToFahrenheit(celsius);
                    kelvin = celsiusToKelvin(celsius);
                    break;
                case 1: // Fahrenheit
                    fahrenheit = inputTemperature;
                    celsius = fahrenheitToCelsius(fahrenheit);
                    kelvin = celsiusToKelvin(celsius);
                    break;
                case 2: // Kelvin
                    kelvin = inputTemperature;
                    celsius = kelvinToCelsius(kelvin);
                    fahrenheit = celsiusToFahrenheit(celsius);
                    break;
            }

            celsiusTextView.setText(String.format("%.2f°C", celsius));
            fahrenheitTextView.setText(String.format("%.2f°F", fahrenheit));
            kelvinTextView.setText(String.format("%.2fK", kelvin));
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double celsiusToKelvin(double celsius) {
        return fahrenheitToCelsius(celsius) + 273.15;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
}
