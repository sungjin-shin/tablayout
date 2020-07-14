package com.example.tablayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment3 extends Fragment {

    ListView listview = null;
    Context mContext;
    View mView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment3 newInstance(String param1, String param2) {
        fragment3 fragment = new fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getContext();
        mView= inflater.inflate(R.layout.activity_sub_02, container, false);

        final ArrayList<MusicItem> items = new ArrayList<>();

        //final ArrayList<ListViewItem> items = new ArrayList<>();
        final MusicItemAdapter adapter;

        // Adapter 생성
        adapter = new MusicItemAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) mView.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        AssetManager assetManager = mContext.getAssets();
        try {
            InputStream is = assetManager.open("data.json");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line + "\n");
                line = reader.readLine();
            }

            String jsonData = buffer.toString();
            JSONArray jsonArray = new JSONArray(jsonData);
            ArrayList<Integer> myImageList = new ArrayList<>();
            myImageList.add(R.drawable.blueming);
            myImageList.add(R.drawable.four);
            myImageList.add(R.drawable.return1);
            myImageList.add(R.drawable.snow);
            myImageList.add(R.drawable.cold);


            String s = "";

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jo = jsonArray.getJSONObject(i);


                String name = jo.getString("info1");
                String msg = jo.getString("info2");
                adapter.addItem(ContextCompat.getDrawable(mContext, myImageList.get(i)), name, msg);
                items.add(new MusicItem((ContextCompat.getDrawable(mContext, myImageList.get(i))), name, msg));
                //JSONObject flag=jo.getJSONObject("flag");
                //int aa= flag.getInt("aa");
                //int bb= flag.getInt("bb");

                //s += name+" : "+msg+"==>"+aa+","+bb+"\n";
            }
            //tv.setText(s);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        EditText editTextFilter = (EditText) mView.findViewById(R.id.editTextFilter);
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString();
                if (filterText.length() > 0) {
                    listview.setFilterText(filterText);
                } else {
                    listview.clearTextFilter();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(mContext, MusicItemClicked.class);
                intent.putExtra("info1", items.get(position).getInfo1());
                intent.putExtra("info2", items.get(position).getInfo2());

                Bitmap sendBitmap=null;
                System.out.println(position);




                switch(position){
                    case 0:
                        sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blueming);
                        break;
                    case 1:
                        sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.four);
                        break;
                    case 2:
                        sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.return1);
                        break;
                    case 3:
                        sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snow);
                        break;
                    case 4:
                        sendBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.cold);
                        break;
                    default:
                        break;
                }


                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                sendBitmap.compress(Bitmap.CompressFormat.PNG, 98, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);

                startActivity(intent);
            }
        });

        return mView;
    }
}