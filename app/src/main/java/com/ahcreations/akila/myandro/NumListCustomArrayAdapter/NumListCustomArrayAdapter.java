package com.ahcreations.akila.myandro.NumListCustomArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahcreations.akila.myandro.R;
import com.ahcreations.akila.myandro.SysInfoCustomAdapter.SystemInfoRow;

import java.util.AbstractList;
import java.util.ArrayList;

public class NumListCustomArrayAdapter extends ArrayAdapter<CustomArrayList> {

    Context context;
    int layoutResourceId;
    ArrayList<CustomArrayList> data = null;

    public NumListCustomArrayAdapter(Context context, int resource, ArrayList<CustomArrayList> data) {
        super(context, resource, data);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        InfoHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new InfoHolder();
            holder.tvUserName = (TextView) row.findViewById(R.id.tvUserNumber);
            holder.ivUserImage = (ImageView) row.findViewById(R.id.imUserImage);

            row.setTag(holder);
        } else {
            holder = (InfoHolder) row.getTag();
        }
        CustomArrayList temp = data.get(position);
        holder.tvUserName.setText(temp._name);
        holder.ivUserImage.setImageResource(R.drawable.app_icon);

        return row;
    }

    static class InfoHolder {
        TextView tvUserName;
        ImageView ivUserImage;
    }
}
