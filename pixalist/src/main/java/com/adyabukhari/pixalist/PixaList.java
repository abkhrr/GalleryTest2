package com.adyabukhari.pixalist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
    private String currentQuery;
    private Context context;
    public String APIKEY = "YOUR_API_KEY";

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

    public void StartPixabayList( String Api, String query ){
        this.APIKEY = Api;
        this.currentQuery = query;
        query = "Red Cars";
        initList();
    }

    private void initList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pixabayGallery);
        recyclerView.setHasFixedSize(true);
        pixabayImageList = new ArrayList<>();

        SnapHelper snapHelper = new LinearSnapHelper();
        if (recyclerView.getOnFlingListener() == null) {
            snapHelper.attachToRecyclerView(recyclerView);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        PixabayAdapter pixabayAdapter = new PixabayAdapter(pixabayImageList,context,recyclerView);
        recyclerView.setAdapter(pixabayAdapter);
        initInfiniteScrollListener(gridLayoutManager);
    }

    private void initInfiniteScrollListener(LinearLayoutManager mLayoutManager) {
        infiniteScrollable = new InfiniteScrollable(mLayoutManager) {
            @Override
            public void onLoadMore(int page) {
                loadImages(page, currentQuery, APIKEY);
            }
        };
        recyclerView.addOnScrollListener(infiniteScrollable);
    }

    private void loadImages(int page, String query, String Apikey) {

        Apikey = this.APIKEY;
        query = this.currentQuery;

        PixabayService.createPixabayService().getImageResults(Apikey, query, page, 1000).enqueue(new Callback<PixabayImageList>() {
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
