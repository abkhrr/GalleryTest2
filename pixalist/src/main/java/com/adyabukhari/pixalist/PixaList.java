package com.adyabukhari.pixalist;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.adyabukhari.pixalist.API.PixabayService;
import com.adyabukhari.pixalist.Model.PixabayImage;
import com.adyabukhari.pixalist.Model.PixabayImageList;
import com.adyabukhari.pixalist.RecyclerView.InfiniteScrollable;
import com.adyabukhari.pixalist.RecyclerView.PixabayAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PixaList extends LinearLayout {

    private List<PixabayImage> pixabayImageList;
    private PixabayAdapter pixabayAdapter;
    private InfiniteScrollable infiniteScrollable;
    private RecyclerView recyclerView;
    public String currentQuery = "CURRENT_QUERY";
    private Context context;
    public String APIKEY = "YOUR_API_KEY";
    private int PAGE;

    public PixaList(Context context) {
        super(context);
        init(context);
    }

    public PixaList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PixaList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        inflate(context, R.layout.main_layout, this);
    }

    public void StartPixabayList(){
        initList();
        loadImages(APIKEY,currentQuery);
    }

    private void initList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pixabayGallery);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this.context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        pixabayImageList = new ArrayList<>();
        pixabayAdapter = new PixabayAdapter(pixabayImageList,context,recyclerView);
        recyclerView.setAdapter(pixabayAdapter);
    }

    private void loadImages(String Api, String query) {

        Api = this.APIKEY;
        query = this.currentQuery;

        PixabayService.createPixabayService().getImageResults(APIKEY, currentQuery, 100).enqueue(new Callback<PixabayImageList>() {
            @Override
            public void onResponse(Call<PixabayImageList> call, Response<PixabayImageList> response) {
                if (response.isSuccessful()) addImagesToList(response.body());
            }

            @Override
            public void onFailure(Call<PixabayImageList> call, Throwable t) {
                Toast.makeText(context, "No Image Available!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addImagesToList(PixabayImageList response) {
        int position = pixabayImageList.size();
        pixabayImageList.addAll(response.getHits());
        pixabayAdapter.notifyItemRangeInserted(position, position + 20);
    }

}
