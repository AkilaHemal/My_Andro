package com.ahcreations.akila.myandro.FragmentsCodes;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ahcreations.akila.myandro.AndroidSystemInfo;
import com.ahcreations.akila.myandro.SysInfoCustomAdapter.SysinfoCustomArrayAdapter;
import com.ahcreations.akila.myandro.SysInfoCustomAdapter.SystemInfoRow;
import com.ahcreations.akila.myandro.R;

public class Processor extends Fragment {

    View view;

    ListView lvInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_dispaly, container, false);

        lvInfo = (ListView) view.findViewById(R.id.lvInfoList);
        LoadInfo();

        return view;
    }

    private void LoadInfo() {
        AndroidSystemInfo asi = new AndroidSystemInfo();

        SystemInfoRow infoData[] = new SystemInfoRow[]{
                new SystemInfoRow("CPU ABI : ", asi.CPU_ABI),
                new SystemInfoRow("CPU ABI2 : ", asi.CPU_ABI2),
                new SystemInfoRow("No. of Processors : ", asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_NUMBER_OF_PROCESSORS)),
                new SystemInfoRow("Total cpu idle : ", asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_TOTAL_CPU_IDLE)),
                new SystemInfoRow("total cpu usage : ", asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_TOTAL_CPU_USAGE)),
                new SystemInfoRow("total cpu usage by sys : ", asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_TOTAL_CPU_USAGE_SYSTEM)),
                new SystemInfoRow("total cpu usage by user : ", asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_TOTAL_CPU_USAGE_USER)),

        };

        SysinfoCustomArrayAdapter adapter = new SysinfoCustomArrayAdapter(view.getContext(), R.layout.system_info_lv_row, infoData);
        lvInfo.setAdapter(adapter);

    }
}
