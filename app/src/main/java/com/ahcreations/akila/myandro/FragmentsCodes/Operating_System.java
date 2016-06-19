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

public class Operating_System extends Fragment{

    View view;

    ListView lvInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_os, container, false);

        lvInfo = (ListView) view.findViewById(R.id.lvInfoList);
        LoadInfo();

        return view;
    }

    private void LoadInfo() {
        AndroidSystemInfo asi = new AndroidSystemInfo();

        SystemInfoRow infoData[] = new SystemInfoRow[]{
                new SystemInfoRow("Model : " , asi.OSNAME),
                new SystemInfoRow("Size : " , asi.OSVERSION),
                new SystemInfoRow("Release : " , asi.RELEASE),
                new SystemInfoRow("SYSTEM_VERSION : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_SYSTEM_VERSION)),
        };

        SysinfoCustomArrayAdapter adapter = new SysinfoCustomArrayAdapter(view.getContext(), R.layout.system_info_lv_row, infoData);
        lvInfo.setAdapter(adapter);

    }
}
