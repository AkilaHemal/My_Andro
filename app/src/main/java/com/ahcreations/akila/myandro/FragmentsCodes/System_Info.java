package com.ahcreations.akila.myandro.FragmentsCodes;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ahcreations.akila.myandro.AndroidSystemInfo;
import com.ahcreations.akila.myandro.SysInfoCustomAdapter.SysinfoCustomArrayAdapter;
import com.ahcreations.akila.myandro.R;
import com.ahcreations.akila.myandro.SysInfoCustomAdapter.SystemInfoRow;

public class System_Info extends Fragment {
    View view;

    ListView lvInfo;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frg_system_info, container, false);

        lvInfo = (ListView) view.findViewById(R.id.lvInfoList);
        LoadInfo();

        return view;
    }

    private void LoadInfo() {
        AndroidSystemInfo asi = new AndroidSystemInfo();

        SystemInfoRow infoData[] = new SystemInfoRow[]{
                new SystemInfoRow("Device : " , asi.DEVICE),
                new SystemInfoRow("Model : " , asi.MODEL),
                new SystemInfoRow("Product : " , asi.PRODUCT),
                new SystemInfoRow("Brand : " , asi.BRAND),
                new SystemInfoRow("Hardware : " , asi.HARDWARE),
                new SystemInfoRow("ID : " , asi.ID),
                new SystemInfoRow("Manufacture : " , asi.MANUFACTURER),
                new SystemInfoRow("Serial : " , asi.SERIAL),
                new SystemInfoRow("User : " , asi.USER),
                new SystemInfoRow("Host : " , asi.HOST),
                new SystemInfoRow("Device Version : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_VERSION)),
                new SystemInfoRow("UUID : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_UUID)),
                new SystemInfoRow("Type : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_TYPE)),
                new SystemInfoRow("Token : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_TOKEN)),
                new SystemInfoRow("Name : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_NAME)),
                new SystemInfoRow("Manufacture : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_MANUFACTURE)),
                new SystemInfoRow("Mac Address : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_MAC_ADDRESS)),
                new SystemInfoRow("Local Country Code : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_LOCAL_COUNTRY_CODE)),
                new SystemInfoRow("Hardware Model : " , asi.getDeviceInfo(view.getContext(), AndroidSystemInfo.Device.DEVICE_HARDWARE_MODEL)),
        };

        SysinfoCustomArrayAdapter adapter = new SysinfoCustomArrayAdapter(view.getContext(), R.layout.system_info_lv_row, infoData);
        lvInfo.setAdapter(adapter);

    }
}
