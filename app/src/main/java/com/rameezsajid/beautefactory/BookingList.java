package com.rameezsajid.beautefactory;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.annotations.Nullable;

import java.util.List;

public class BookingList extends ArrayAdapter<Booking> {

    private Activity context;

    private List<Booking> bookingList;

    public BookingList(Activity context, List<Booking> bookingList) {
        super(context, R.layout.list_layout_booking, bookingList);
        this.context = context;
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_booking, null, true);

        TextView textViewLocation = (TextView)listViewItem.findViewById(R.id.textViewLocation);
        TextView textViewDate = (TextView)listViewItem.findViewById(R.id.textViewDate);
        TextView textViewName = (TextView)listViewItem.findViewById(R.id.textViewName);
        TextView textViewPhone = (TextView)listViewItem.findViewById(R.id.textViewPhone);
        TextView textViewEmail = (TextView)listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewBusiness = (TextView)listViewItem.findViewById(R.id.textViewBusiness);

        Booking booking = bookingList.get(position);

        textViewLocation.setText(booking.getBookingLocation());
        textViewDate.setText(booking.getBookingDate());
        textViewBusiness.setText(booking.getBookingBusiness());
        textViewEmail.setText(booking.getBookingEmail());
        textViewPhone.setText(booking.getBookingPhone());
        textViewName.setText(booking.getBookingName());


        return listViewItem;

    }
}
