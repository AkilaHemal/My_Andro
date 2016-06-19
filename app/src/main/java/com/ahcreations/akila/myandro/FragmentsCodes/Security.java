package com.ahcreations.akila.myandro.FragmentsCodes;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ahcreations.akila.myandro.DataBaseFiles.DataBaseHelper;
import com.ahcreations.akila.myandro.MainActivity;
import com.ahcreations.akila.myandro.NumListCustomArrayAdapter.CustomArrayList;
import com.ahcreations.akila.myandro.NumListCustomArrayAdapter.NumListCustomArrayAdapter;
import com.ahcreations.akila.myandro.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractList;
import java.util.ArrayList;

public class Security extends Fragment {

    static View view;

    EditText etPassword;

    static ListView lvMags;
    LinearLayout llPassword, llMsgList;

    static ArrayList<String> NumberList = new ArrayList<>();
    static ArrayList<String> NameList = new ArrayList<>();
    static AbstractList<Bitmap> ImageList = new ArrayList<>();

    ArrayList<String> MagsList = new ArrayList<>();
    static ArrayAdapter NumbersAdapter;
    static ArrayAdapter MsgAdapter;
    static NumListCustomArrayAdapter adapter;
    static DataBaseHelper mydb;

    static ArrayList<CustomArrayList> abc = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.frg_security, container, false);

        etPassword = (EditText) view.findViewById(R.id.etPassword);
        lvMags = (ListView) view.findViewById(R.id.listView);
        llPassword = (LinearLayout) view.findViewById(R.id.llPasswordFrame);
        llMsgList = (LinearLayout) view.findViewById(R.id.listFrame);

        mydb = new DataBaseHelper(view.getContext());

        LoadNumberList();
        NumberListClick();

        etPasswordOnTextChangeListener();

        llMsgList.setVisibility(View.INVISIBLE);

        return view;
    }

    public static void BackToNumberList() {
        if (lvMags.getAdapter() == MsgAdapter) {
            LoadNumberList();
        } else if (lvMags.getAdapter() == NumbersAdapter) {
        }
    }

    private void NumberListClick() {
        lvMags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lvMags.getAdapter() == adapter) {
                    String SelectedItem = NumberList.get(position);
                    LoadMsgFrom(SelectedItem);
                }
            }
        });


    }

    private void etPasswordOnTextChangeListener() {
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) {
                    if (etPassword.getText().toString().equals("1371")) {
                        etPassword.setText("");
                        llMsgList.setVisibility(View.VISIBLE);
                        llPassword.setVisibility(View.INVISIBLE);
                    } else {
                        Toast.makeText(view.getContext(), "Try Again", Toast.LENGTH_SHORT).show();
                        etPassword.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void LoadMsgFrom(String number) {
        MagsList.clear();
        Cursor cus = mydb.getMsgList(number);
        while (cus.moveToNext()) {
            MagsList.add(cus.getString(0));
        }
        MsgAdapter = new ArrayAdapter<>(view.getContext(), R.layout.mag_list_row, MagsList);
        lvMags.setAdapter(MsgAdapter);

        MainActivity.assessingMsgList = true;
    }


    public static void LoadNumberList() {
        NumberList.clear();
        NameList.clear();
        abc.clear();



        Cursor cus = mydb.getNumberList();
        while (cus.moveToNext()) {
            NumberList.add(cus.getString(0));
            NameList.add(getContactName(view.getContext(), cus.getString(0)));

            //ImageList.add(retrieveContactPhoto(view.getContext(), cus.getString(0)));
            //ImageList.add(BitmapFactory.decodeResource(view.getResources(),R.drawable.app_icon));
            CustomArrayList a = new CustomArrayList(getContactName(view.getContext(), cus.getString(0)),"hjgj");
            abc.add(a);
        }


        adapter = new NumListCustomArrayAdapter(view.getContext(), R.layout.num_list_row, abc);
        lvMags.setAdapter(adapter);

        MainActivity.assessingMsgList = false;
    }

    public static Bitmap retrieveContactPhoto(Context context, String number) {
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

        Cursor cursor =
                contentResolver.query(
                        uri,
                        projection,
                        null,
                        null,
                        null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
            }
            cursor.close();
        }

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.app_icon);

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactId)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
            }

            assert inputStream != null;
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return photo;
    }

    public static String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        } else {
            contactName = phoneNumber;
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return contactName;
    }


}
