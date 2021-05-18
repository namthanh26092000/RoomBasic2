package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.AdapterViewHolder> {

    private Context context;
    private List<Place> list;

    public CustomAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Place> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        Place place = list.get(position);
        if(place == null)
            return;
        holder.placeName.setText(place.getPlaceName());

        MainActivity main = new MainActivity();

        holder.placeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("name", place.getPlaceName().trim());
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(place);
                setData(list);
                notifyDataSetChanged();
                main.editText.setText("");
                //  main.notif();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = main.editText.getText().toString().trim();
                if (value.isEmpty())
                    MainActivity.editText.setError("Vui lòng nhập thông tin cần sửa!");
                else{
                    // place.setPlaceName(value);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView placeName;
        private ImageView delete, edit;
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.placeName);
            delete = itemView.findViewById(R.id.btnDelete);
            edit = itemView.findViewById(R.id.btnEdit);
        }
    }
}