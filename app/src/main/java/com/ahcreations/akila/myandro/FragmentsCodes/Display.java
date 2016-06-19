package com.ahcreations.akila.myandro.FragmentsCodes;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ahcreations.akila.myandro.AndroidSystemInfo;
import com.ahcreations.akila.myandro.SysInfoCustomAdapter.SysinfoCustomArrayAdapter;
import com.ahcreations.akila.myandro.SysInfoCustomAdapter.SystemInfoRow;
import com.ahcreations.akila.myandro.R;

public class Display extends Fragment {

    View view;

    TextView tvHeight, tvWidth,tvDiagonal,tvDisplayModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_dispaly, container, false);

        tvHeight = (TextView) view.findViewById(R.id.tvHeight);
        tvWidth = (TextView) view.findViewById(R.id.tvWidth);
        tvDiagonal = (TextView) view.findViewById(R.id.tvDiagonal);
        tvDisplayModel = (TextView) view.findViewById(R.id.tvDispalyModel);

        LoadInfo();

        return view;
    }

    private void LoadInfo() {
        AndroidSystemInfo asi = new AndroidSystemInfo();

        tvDisplayModel.setText("Display Model - " + asi.DISPLAY);
        tvWidth.setText(round(Double.valueOf(asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_WIDTH_IN_INCH)),2) + " Inch");
        tvHeight.setText(round(Double.valueOf(asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_HEIGHT_IN_INCH)), 2) + " Inch");
        tvDiagonal.setText(round(Double.valueOf(asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_IN_INCH)), 2) + " Inch");
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
