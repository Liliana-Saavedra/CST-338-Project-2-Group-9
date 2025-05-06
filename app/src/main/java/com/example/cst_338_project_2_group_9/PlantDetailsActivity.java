package com.example.cst_338_project_2_group_9;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PlantDetailsActivity extends AppCompatActivity {
    private TextView plantName, careInstructions;
    private String plantType;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details2);

        // Initialize all views
        plantName = findViewById(R.id.plantName);
        careInstructions = findViewById(R.id.careInstructions);
        backButton = findViewById(R.id.backButton); // Add this line

        // Get plant type from intent
        plantType = getIntent().getStringExtra("PLANT_TYPE");

        displayCareGuide();

        backButton.setOnClickListener(v -> {
            finish();
        });
    }

    private void displayCareGuide() {
        switch (plantType) {
            case "succulent":
                plantName.setText("Succulent Care");
                careInstructions.setText(getString(R.string.succulent_care));
                break;

            case "tree":
                plantName.setText("Tree Care");
                careInstructions.setText(getString(R.string.tree_care));
                break;

            case "bush":
                plantName.setText("Bush Care");
                careInstructions.setText(getString(R.string.bush_care));
                break;

            case "grass":
                plantName.setText("Grass Care");
                careInstructions.setText(getString(R.string.grass_care));
                break;
        }
    }
}