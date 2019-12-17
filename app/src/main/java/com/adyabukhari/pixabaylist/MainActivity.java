package com.adyabukhari.pixabaylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.adyabukhari.pixalist.PixaList;

public class MainActivity extends AppCompatActivity {

    private String API_KEY ="14658201-2fd4b3bfec2ea493e9ac398b8";
    private String query = "balls";

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
