package rs.ac.metropolitan.rentparking_mapbox.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import rs.ac.metropolitan.rentparking_mapbox.R;
import rs.ac.metropolitan.rentparking_mapbox.entity.data.dto.BookingDTO;

public class CardAdapter extends ArrayAdapter<BookingDTO> {

    public CardAdapter(Context context) {
        super(context, R.layout.card_item);
    }

    @SuppressLint("SetTextI18n")
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
            // associate the holder with the view for later lookup
            v.setTag(holder);
        }
        else {
            // view already exists, get the holder instance from the view
            holder = (ViewHolder) v.getTag();
        }
        BookingDTO bookingDTO = getItem(position);

//        holder.image.setImage;
        Glide.with(getContext()).load(bookingDTO.getParking().getParkingPic()).into(holder.image);
        holder.name.setText(bookingDTO.getParking().getName());
        holder.price.setText("$" + bookingDTO.getParking().getPrice().toString());

        return v;
    }

    static class ViewHolder {
        ImageView image;
        TextView price;
        TextView name;

        ViewHolder() {
        }
    }
}
