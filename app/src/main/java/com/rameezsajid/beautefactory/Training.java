package com.rameezsajid.beautefactory;

public class Training {

    String trainingId;
    String trainingLocation;
    String trainingDate;

    public Training() {
    }

    public Training(String trainingId, String trainingLocation, String trainingDate) {
        this.trainingId = trainingId;
        this.trainingLocation = trainingLocation;
        this.trainingDate = trainingDate;
    }

    public String getTrainingId() {
        return trainingId;
    }

    public String getTrainingLocation() {
        return trainingLocation;
    }

    public String getTrainingDate() {
        return trainingDate;
    }
}
