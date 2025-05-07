package com.example.cst_338_project_2_group_9.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plants")
public class Plant {
    @PrimaryKey(autoGenerate = true)
    private int plantId;
    private String name;
    private String species;
    private String description;
    private String lightRequirements;
    private String wateringInstructions;
    private String fertilizerSchedule;
    private String temperatureRange;
    private String dateAdded;

    // Constructor
    public Plant(String name, String species, String description,
                 String lightRequirements, String wateringInstructions,
                 String fertilizerSchedule, String temperatureRange) {
        this.name = name;
        this.species = species;
        this.description = description;
        this.lightRequirements = lightRequirements;
        this.wateringInstructions = wateringInstructions;
        this.fertilizerSchedule = fertilizerSchedule;
        this.temperatureRange = temperatureRange;
    }

    // Getters and setters for all fields
    public int getPlantId() { return plantId; }
    public void setPlantId(int plantId) { this.plantId = plantId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLightRequirements() { return lightRequirements; }
    public void setLightRequirements(String lightRequirements) { this.lightRequirements = lightRequirements; }
    public String getWateringInstructions() { return wateringInstructions; }
    public void setWateringInstructions(String wateringInstructions) { this.wateringInstructions = wateringInstructions; }
    public String getFertilizerSchedule() { return fertilizerSchedule; }
    public void setFertilizerSchedule(String fertilizerSchedule) { this.fertilizerSchedule = fertilizerSchedule; }
    public String getTemperatureRange() { return temperatureRange; }
    public void setTemperatureRange(String temperatureRange) { this.temperatureRange = temperatureRange; }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}