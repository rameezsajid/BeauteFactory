package com.rameezsajid.beautefactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewBookingActivity extends AppCompatActivity {

    FirebaseDatabase rootmode;
    DatabaseReference reference;

    ListView listViewBooking;

    List<Booking> bookingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);

        listViewBooking = (ListView) findViewById(R.id.listViewBooking);

        bookingList = new ArrayList<>();

        rootmode = FirebaseDatabase.getInstance();
        reference = rootmode.getInstance().getReference("booking");
    }

    @Override
    public void onStart() {

        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookingList.clear();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Booking booking = data.getValue(Booking.class);

                    bookingList.add(booking);
                }
                BookingList adapter = new BookingList(ViewBookingActivity.this, bookingList);
                listViewBooking.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
