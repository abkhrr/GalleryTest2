package com.adyabukhari.pixalist;

import android.content.Context;
import android.content.res.TypedArray;
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
    private int columns;

    public final static int FILL = 0;     // 宫格布局
    public final static int TWO = 1;     // 全填充布局
    public final static int GRID = 2;     // 全填充布局

    public PixaList(Context context) {
        super(context);
        init(context);
    }

    public PixaList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PixaList);
        this.columns = typedArray.getInt(R.styleable.PixaList_columns, GRID);
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

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pixabayGallery);
        recyclerView.setHasFixedSize(true);
        pixabayImageList = new ArrayList<>();
        pixabayAdapter = new PixabayAdapter(pixabayImageList,context,recyclerView);
        recyclerView.setAdapter(pixabayAdapter);

        switch (columns){
            case FILL:
                init_fill();
                loadImages(APIKEY,currentQuery);
                break;

            case TWO:
                initList();
                loadImages(APIKEY,currentQuery);
                break;

            default:
            case GRID:
                init_grid();
                loadImages(APIKEY,currentQuery);
                break;
        }
    }

    private void init_fill() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(this.context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void initList() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(this.context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private void init_grid() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(this.context, 3);
        recyclerView.setLayoutManager(mLayoutManager);
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
