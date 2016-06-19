package com.ahcreations.akila.myandro.FragmentsCodes;

import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ahcreations.akila.myandro.AndroidSystemInfo;
import com.ahcreations.akila.myandro.SysInfoCustomAdapter.SysinfoCustomArrayAdapter;
import com.ahcreations.akila.myandro.SysInfoCustomAdapter.SystemInfoRow;
import com.ahcreations.akila.myandro.R;

public class RAM extends Fragment {
    View view;

    ListView lvInfo;
    ImageView ivUsedSize, ivFreeSize;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frg_ram, container, false);

        lvInfo = (ListView) view.findViewById(R.id.lvInfoList);
        ivUsedSize = (ImageView) view.findViewById(R.id.ivUsedSize);
        ivFreeSize = (ImageView) view.findViewById(R.id.ivFreeSize);

        LoadInfo();

        return view;
    }

    private void LoadInfo() {
        AndroidSystemInfo asi = new AndroidSystemInfo();

        SystemInfoRow infoData[] = new SystemInfoRow[]{
                new SystemInfoRow("FREE_MEMORY : ", asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_FREE_MEMORY)),
                new SystemInfoRow("TOTAL_MEMORY : ", asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_TOTAL_MEMORY)),
                new SystemInfoRow("USED_MEMORY : ", asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_USED_MEMORY)),
        };

        SysinfoCustomArrayAdapter adapter = new SysinfoCustomArrayAdapter(view.getContext(), R.layout.system_info_lv_row, infoData);
        lvInfo.setAdapter(adapter);

        double pre = 100.0 - (Double.valueOf(asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_USED_MEMORY)) / Double.valueOf(asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_TOTAL_MEMORY))) * 100;
        ivUsedSize.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, (int) pre));
        ivFreeSize.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,100 - (int) pre));

    }
}
