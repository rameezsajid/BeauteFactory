package com.rameezsajid.beautefactory;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;

import com.google.firebase.database.annotations.Nullable;

import java.util.List;

public class TrainingList extends ArrayAdapter<Training> {

    private Activity context;
    private List<Training> trainingList;

    public TrainingList(Activity context, List<Training> trainingList) {
        super(context, R.layout.list_layout, trainingList);
        this.context = context;
        this.trainingList = trainingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewLocation = (TextView)listViewItem.findViewById(R.id.textViewLocation);
        TextView textViewDate = (TextView)listViewItem.findViewById(R.id.textViewDate);

        Training training = trainingList.get(position);

        textViewLocation.setText(training.getTrainingLocation());
        textViewDate.setText(training.getTrainingDate());

        return listViewItem;

    }
}
