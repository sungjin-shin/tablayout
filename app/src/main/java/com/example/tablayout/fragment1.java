package com.example.tablayout;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment1 extends Fragment {

    Context mContext;
    View mView;
    private List<Customer> list;
    private ListView listView;
    private EditText editSearch;
    private ListViewAdapter_mod adapter;
    private ArrayList<Customer> arraylist;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment1 newInstance(String param1, String param2) {
        fragment1 fragment = new fragment1();
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
        mContext = getContext();
        mView= inflater.inflate(R.layout.activity_main, container, false);
        //mView.findViewById(R.id.editTextFilter);
        //mContext.getAssets();

        editSearch = (EditText) mView.findViewById(R.id.editTextFilter);
        listView = (ListView) mView.findViewById(R.id.listview1);

        list = new ArrayList<Customer>();

        AssetManager assetManager = mContext.getAssets();

        ////////////json 파일의 정보를 불러와서 파싱하는 과정///////////
        try {
            InputStream is= assetManager.open("data1.json");
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            String line= reader.readLine();
            while (line!=null){
                buffer.append(line+"\n");
                line=reader.readLine();
            }

            String jsonData= buffer.toString();
            JSONArray jsonArray= new JSONArray(jsonData);
            ArrayList<Integer> myImageList = new ArrayList<>();
            myImageList.add(R.drawable.image1);
            myImageList.add(R.drawable.image2);
            myImageList.add(R.drawable.image3);
            myImageList.add(R.drawable.image4);
            myImageList.add(R.drawable.image5);
            myImageList.add(R.drawable.image6);
            myImageList.add(R.drawable.image7);
            myImageList.add(R.drawable.image8);
            myImageList.add(R.drawable.image9);

            String s="";

            for(int i=0; i<jsonArray.length();i++){
                JSONObject jo=jsonArray.getJSONObject(i);

                String name= jo.getString("name");
                String msg= jo.getString("msg");
                String gender = jo.getString("gender");
                list.add(new Customer(name, msg, gender, ContextCompat.getDrawable(mContext, myImageList.get(i))));
                //items.add(new Customer(name, msg, gender));
                //JSONObject flag=jo.getJSONObject("flag");
                //int aa= flag.getInt("aa");
                //int bb= flag.getInt("bb");

                //s += name+" : "+msg+"==>"+aa+","+bb+"\n";
            }
            //tv.setText(s);

        } catch (IOException e) {e.printStackTrace();} catch (JSONException e) {e.printStackTrace();}

        ///////////arraylist 선언하여 list의 내용 복사(search를 하기 위함)///////
        arraylist = new ArrayList<Customer>();
        arraylist.addAll(list);

        adapter = new ListViewAdapter_mod(list, mContext);

        listView.setAdapter(adapter);

        //////////////검색창에 검색어 입력 후 인지/////////////
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        /////////////리스트의 한 요소 터치////////////////
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(mContext, CustomersClicked.class);
                intent.putExtra("name", list.get(position).getName());
                intent.putExtra("gender", list.get(position).getGender());
                intent.putExtra("phone", list.get(position).getPhone());
                startActivity(intent);
            }
        });

        //////////////길게 누르면 삭제///////////////
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                list.remove(i);
                arraylist.remove(i);
                adapter.notifyDataSetChanged();

                Toast.makeText(mContext, i+1+"번째 전화번호가 삭제되었습니다", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        Button addButton = (Button) mView.findViewById(R.id.addPhone);
        addButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
//                int count;
////                count = adapter.getCount();

                //adapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.anonymous), "신성진", "010-9238-7609");
                list.add(new Customer("신성진", "010-9238-7609", "남자", ContextCompat.getDrawable(mContext, R.drawable.anonymous)));
                arraylist.add(new Customer("신성진", "010-9238-7609", "남자", ContextCompat.getDrawable(mContext, R.drawable.anonymous)));
                //addList.add("LIST"+Integer.toString(count+1));

                adapter.notifyDataSetChanged();
                //Toast.makeText(getApplicationContext(), "와우", Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return mView;
    }

    public void search(String charText){
        list.clear();

        if(charText.length()==0){
            list.addAll(arraylist);
        }

        else
        {
            for(int i = 0;i<arraylist.size();i++){
                if (arraylist.get(i).getName().toLowerCase().contains(charText)||arraylist.get(i).getPhone().toLowerCase().contains(charText)){
                    list.add(arraylist.get(i));
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}