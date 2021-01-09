package com.rameezsajid.beautefactory;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TrainingFragment extends Fragment {

    TextView textViewPrev, textViewNext;
    ViewPager viewPager;

    ListView listViewTraining;

    List<Training> trainingList;

    FirebaseDatabase rootmode;
    DatabaseReference reference, bookingReference;

    public TrainingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        viewPager = getActivity().findViewById(R.id.viewPager);

        textViewPrev = view.findViewById(R.id.tvPrev);

        textViewNext = view.findViewById(R.id.tvNext);

        textViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        textViewPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });


        listViewTraining = (ListView) view.findViewById(R.id.listViewTraining);

        trainingList = new ArrayList<>();

        rootmode = FirebaseDatabase.getInstance();
        reference = rootmode.getInstance().getReference("training");
        bookingReference = rootmode.getInstance().getReference("booking");

        listViewTraining.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Training training = trainingList.get(i);

                bookDialogBox(training.getTrainingId(), training.getTrainingLocation());
            }
        });

        return view;
    }

    private void bookDialogBox(final String trainingID, final String trainingLocation){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_box_book, null);
        dialogBuilder.setView(dialogView);

        final Button buttonCancel = (Button) dialogView.findViewById(R.id.btnClose);
        final Button buttonBook = (Button) dialogView.findViewById(R.id.btnBook);

        final EditText editTextLocation = (EditText) dialogView.findViewById(R.id.editTextLocationBook);
        final EditText editTextDate = (EditText) dialogView.findViewById(R.id.editTextDateBook);
        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextNameBook);
        final EditText editTextPhone = (EditText) dialogView.findViewById(R.id.editTextPhoneBook);
        final EditText editTextEmail = (EditText) dialogView.findViewById(R.id.editTextEmailBook);
        final EditText editTextBusiness = (EditText) dialogView.findViewById(R.id.editTextBusinessBook);

        dialogBuilder.setTitle("Book " + trainingLocation);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


        Query query = reference.child(trainingID);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                editTextLocation.setText(dataSnapshot.child("trainingLocation").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query query2 = reference.child(trainingID);

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                editTextDate.setText(dataSnapshot.child("trainingDate").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = editTextLocation.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String business = editTextBusiness.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone)){
                    String id = bookingReference.push().getKey();

                    Booking booking = new Booking(id, location, date, name, phone, email, business);
                    bookingReference.child(id).setValue(booking);

                    Toast.makeText(getContext(), "Successfully Booked\n We Will Contact You Shortly To Confirm Booking", Toast.LENGTH_LONG).show();
                    alertDialog.dismiss();

                }else {
                    Toast.makeText(getContext(), "Fill All Fields", Toast.LENGTH_LONG).show();

                }
            }
        });

    }



    @Override
    public void onStart() {

        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trainingList.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Training training = data.getValue(Training.class);

                    trainingList.add(training);
                }
                TrainingList adapter = new TrainingList(getActivity(), trainingList);
                listViewTraining.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
