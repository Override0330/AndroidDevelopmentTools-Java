package com.override0330.android.redrock.myrecyclerviewutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        myRecyclerViewAdapter.setInit(new MyRecyclerViewAdapter.initViewHolder() {
            @Override
            public void init(MyRecyclerViewAdapter.ViewHolder viewHolder, int position) {
                viewHolder.setButtonText()
            }
        });
    }
}
