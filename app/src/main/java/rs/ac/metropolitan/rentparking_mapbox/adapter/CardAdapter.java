package rs.ac.metropolitan.rentparking_mapbox.adapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.Calendar;

import rs.ac.metropolitan.rentparking_mapbox.R;
import rs.ac.metropolitan.rentparking_mapbox.entity.data.domen.BookingStatus;
import rs.ac.metropolitan.rentparking_mapbox.entity.data.dto.BookingDTO;
import timber.log.Timber;

public class CardAdapter extends ArrayAdapter<BookingDTO> {

    public CardAdapter(Context context) {
        super(context, R.layout.card_item);
    }

    @SuppressLint({"SetTextI18n", "InflateParams"})
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder; // to reference the child views for later actions

        if (v == null) {
            LayoutInflater vi =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.card_item, null);
            // cache view fields into the holder
            holder = new ViewHolder();
            holder.name = (TextView) v.findViewById(R.id.name);
            holder.price = (TextView) v.findViewById(R.id.price);
            holder.image = (ImageView) v.findViewById(R.id.image);
            holder.buy = (Button) v.findViewById(R.id.buy);
            holder.status = (Button) v.findViewById(R.id.status);
            // associate the holder with the view for later lookup
            v.setTag(holder);
        } else {
            // view already exists, get the holder instance from the view
            holder = (ViewHolder) v.getTag();
        }
        BookingDTO bookingDTO = getItem(position);

//        holder.image.setImage;
        Glide.with(getContext()).load(bookingDTO.getParking().getParkingPic()).into(holder.image);
        holder.name.setText(bookingDTO.getParking().getName());
        holder.price.setText("$" + bookingDTO.getParking().getPrice().toString());
        if (bookingDTO.getBooking() != null) {
            if (bookingDTO.getBooking().getBookingStatus() == BookingStatus.RESERVED) {
                holder.buy.setEnabled(false);
            }
        }

        holder.status.setText(bookingDTO.getBooking() != null ? bookingDTO.getBooking().getBookingStatus().toString() : "FREE");

        View.OnClickListener corkyListener = v1 -> {
            System.out.println(bookingDTO);
            startDateTime(bookingDTO);
        };

        holder.buy.setOnClickListener(corkyListener);

        return v;
    }

    static class ViewHolder {
        ImageView image;
        TextView price;
        TextView name;
        Button buy;
        Button status;

        ViewHolder() {
        }
    }


    private void startDateTime(BookingDTO bookingDTO) {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this.getContext());


        TimePickerDialog timePickerDialog = new TimePickerDialog(this.getContext(), (timePicker, hour, minute) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.HOUR, hour);
            calendar1.set(Calendar.MINUTE, minute);
            endDateTime(bookingDTO);
        }, HOUR, MINUTE, is24HourFormat);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(), (datePicker, year, month, date) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.YEAR, year);
            calendar1.set(Calendar.MONTH, month);
            calendar1.set(Calendar.DATE, date);
            timePickerDialog.show();
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();

    }

    private void endDateTime(BookingDTO bookingDTO) {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this.getContext());


        TimePickerDialog timePickerDialog = new TimePickerDialog(this.getContext(), (timePicker, hour, minute) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.HOUR, hour);
            calendar1.set(Calendar.MINUTE, minute);
        }, HOUR, MINUTE, is24HourFormat);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(), (datePicker, year, month, date) -> {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.set(Calendar.YEAR, year);
            calendar1.set(Calendar.MONTH, month);
            calendar1.set(Calendar.DATE, date);
            timePickerDialog.show();
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();

    }

}
