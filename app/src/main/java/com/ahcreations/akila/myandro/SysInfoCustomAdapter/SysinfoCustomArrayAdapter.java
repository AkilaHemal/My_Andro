package com.ahcreations.akila.myandro.SysInfoCustomAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ahcreations.akila.myandro.R;

public class SysinfoCustomArrayAdapter extends ArrayAdapter<SystemInfoRow> {

    Context context;
    int layoutResourceId;
    SystemInfoRow data[] = null;

    public SysinfoCustomArrayAdapter(Context contxt, int resource, SystemInfoRow[] data) {
        super(contxt, resource, data);

        this.layoutResourceId = resource;
        this.context = contxt;
        this.data = data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        InfoHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new InfoHolder();
            holder.tvTitle = (TextView)row.findViewById(R.id.tvTitle);
            holder.tvValue = (TextView)row.findViewById(R.id.tvValue);

            row.setTag(holder);
        }
        else
        {
            holder = (InfoHolder)row.getTag();
        }

        SystemInfoRow weather = data[position];
        holder.tvTitle.setText("     " + weather.Title);
        holder.tvValue.setText(weather.Value + "     ");

        return row;
    }

    static class InfoHolder
    {
        TextView tvTitle;
        TextView tvValue;
    }
}
