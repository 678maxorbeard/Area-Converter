package com.example.areaconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputArea;
    private Spinner inputUnit;
    private TextView outputArea;

    private static final String[] UNITS = {"Square Meters", "Square Kilometers", "Acres", "Hectares"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputArea = findViewById(R.id.input_area);
        inputUnit = findViewById(R.id.input_unit);
        outputArea = findViewById(R.id.output_area);
        Button convertButton = findViewById(R.id.convert_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UNITS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputUnit.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertArea();
            }
        });
    }

    private void convertArea() {
        String inputValue = inputArea.getText().toString();
        if (inputValue.isEmpty()) {
            outputArea.setText("Please enter a value.");
            return;
        }

        double area = Double.parseDouble(inputValue);
        String selectedUnit = inputUnit.getSelectedItem().toString();
        double convertedArea;

        switch (selectedUnit) {
            case "Square Meters":
                convertedArea = area;
                break;
            case "Square Kilometers":
                convertedArea = area / 1_000_000;
                break;
            case "Acres":
                convertedArea = area * 0.000247105;
                break;
            case "Hectares":
                convertedArea = area / 10_000;
                break;
            default:
                convertedArea = area;
                break;
        }

        outputArea.setText(String.format("Converted Area: %.4f", convertedArea));
    }
}
