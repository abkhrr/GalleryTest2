package com.adyabukhari.pixabaylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.adyabukhari.pixalist.PixaList;

public class MainActivity extends AppCompatActivity {

    private String API_KEY ="14646935-c08f547623e6f679a2c28d594";
    private String query = "Red Cars";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PixaList pixaList = (PixaList) findViewById(R.id.MypixaList);
        pixaList.APIKEY = API_KEY;
        pixaList.currentQuery = query;
        pixaList.StartPixabayList();
    }
}
