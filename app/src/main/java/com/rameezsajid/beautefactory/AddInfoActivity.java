package com.rameezsajid.beautefactory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddInfoActivity extends AppCompatActivity {

    EditText editTextLocation;
    EditText editTextDate;
    Button buttonAdd, buttonView;

    FirebaseDatabase rootmode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        rootmode = FirebaseDatabase.getInstance();
        reference = rootmode.getInstance().getReference("training");

        //databaseTraining = FirebaseDatabase.getInstance().getReference("training");

        //databaseTraining = FirebaseDatabase.getInstance().getReference();

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");


        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTraining();
            }

        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddInfoActivity.this, ViewBookingActivity.class));
            }
        });

    }

    private void addTraining(){

        String location = editTextLocation.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();

        if (!TextUtils.isEmpty(location) && !TextUtils.isEmpty(date)){

            String id = reference.push().getKey();

            Training training = new Training(id, location, date);

            reference.child(id).setValue(training);

            Toast.makeText(AddInfoActivity.this, "Property Added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddInfoActivity.this, MainActivity.class));

        } else {
            Toast.makeText(AddInfoActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
        }

    }

}
