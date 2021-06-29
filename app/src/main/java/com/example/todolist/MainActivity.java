package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.todolist.model.MyData;
import com.example.todolist.network.ApiClient;
import com.example.todolist.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    CustomeAdapter customeAdapter;

    public static List<MyData> myDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        myDataList = new ArrayList<>();

        //make network call
        Call<List<MyData>> call = ApiClient.apiInterface().getMyDatas();

        call.enqueue(new Callback<List<MyData>>() {
            @Override
            public void onResponse(Call<List<MyData>> call, Response<List<MyData>> response) {

                if(response.isSuccessful()){

                    myDataList = response.body();

                    customeAdapter = new CustomeAdapter(response.body(), MainActivity.this);

                    gridView.setAdapter(customeAdapter);

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            //intent
                            Intent intent = new Intent();
                            intent.putExtra("title",myDataList.get(position).getTitle());
                            intent.putExtra("completed", myDataList.get(position).getCompleted());

                            startActivity(new Intent(getApplicationContext(),DetailActivity.class).putExtra("title",myDataList.get(position).getTitle()).putExtra("completed", myDataList.get(position).getCompleted()));

                        }
                    });

                }else {
                    Toast.makeText(getApplicationContext(),"An Error Occured", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<MyData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An Error Occured"+ t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public class CustomeAdapter extends BaseAdapter{
        public List<MyData> myDataList;
        public Context context;

        public CustomeAdapter(List<MyData> myDataList, Context context) {
            this.myDataList = myDataList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return myDataList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(context).inflate(R.layout.row_data, null);

            //find view
            TextView title = view.findViewById(R.id.tv_title);
            TextView completed = view.findViewById(R.id.tv_completed);

            //set data
            title.setText(myDataList.get(position).getTitle());
            completed.setText(myDataList.get(position).getCompleted());

            return view;
        }
    }
}