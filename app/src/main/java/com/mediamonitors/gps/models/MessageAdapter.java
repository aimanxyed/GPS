package com.mediamonitors.gps.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mediamonitors.gps.R;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<UserModel> {

    Context context;
    List<UserModel>  arrayListUser;


    public MessageAdapter(@NonNull Context context,  List<UserModel>  arrayListUser) {
        super(context, R.layout.custom_list_item,arrayListUser);
        this.context=context;
        this.arrayListUser=arrayListUser;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null, true);
        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.txt_name);
        tvID.setText(arrayListUser.get(position).getId());
        tvName.setText(arrayListUser.get(position).getName());
        return view;
    }
}
