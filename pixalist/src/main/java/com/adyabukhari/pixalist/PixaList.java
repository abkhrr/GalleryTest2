package com.adyabukhari.pixalist;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

/**
 * Created by Abkhrr on 17/12/2019.
 *
 * Email: bukhariadbuk@gmail.com
 * GitHub: https://github.com/abkhrr
 *
 */

public class PixaList extends LinearLayout {

    private List<PixabayImage> pixabayImageList;
    private PixabayAdapter pixabayAdapter;
    private InfiniteScrollable infiniteScrollable;
    private RecyclerView recyclerView;
    public String currentQuery = "CURRENT_QUERY";
    private Context context;
    public String APIKEY = "YOUR_API_KEY";
    private int columns;

    // STYLE
    public final static int FILL = 0;
    public final static int TWO = 1;
    public final static int GRID = 2;

    public PixaList(Context context) {
        super(context);
        init(context);
    }

    // Set Default ATTRS

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

    // Make public void to populate image from pixabay
    // Make public to set Columns ( FILL , GRID , COLUMNS )

    public void StartPixabayList(){

        // Set Case from layout choosen

        switch (columns){
            case FILL:
                init_fill(); // STYLE FILL ( 1 COLUMNS )
                loadImages(1,APIKEY,currentQuery);
                break;

            case TWO:
                initList(); // STYLE TWO ( 2 COLUMNS )
                loadImages(1,APIKEY,currentQuery);
                break;

            default:
            case GRID:
                init_grid(); // STYLE GRID ( 3 COLUMNS )
                loadImages(1,APIKEY,currentQuery);
                break;
        }
    }

    // Set Case from layout choosen

    private void init_fill() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pixabayGallery);
        recyclerView.setHasFixedSize(true);
        pixabayImageList = new ArrayList<>();
        pixabayAdapter = new PixabayAdapter(pixabayImageList,context,recyclerView);
        recyclerView.setAdapter(pixabayAdapter);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this.context, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        initInfiniteScrollListener(mLayoutManager);
        recyclerView.addOnScrollListener(infiniteScrollable);
    }

    private void initList() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pixabayGallery);
        recyclerView.setHasFixedSize(true);
        pixabayImageList = new ArrayList<>();
        pixabayAdapter = new PixabayAdapter(pixabayImageList,context,recyclerView);
        recyclerView.setAdapter(pixabayAdapter);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this.context, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        initInfiniteScrollListener(mLayoutManager);
        recyclerView.addOnScrollListener(infiniteScrollable);
    }

    private void init_grid() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pixabayGallery);
        recyclerView.setHasFixedSize(true);
        pixabayImageList = new ArrayList<>();
        pixabayAdapter = new PixabayAdapter(pixabayImageList,context,recyclerView);
        recyclerView.setAdapter(pixabayAdapter);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this.context, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        initInfiniteScrollListener(mLayoutManager);
        recyclerView.addOnScrollListener(infiniteScrollable);

    }

    // Making RecyclerView Loads more when scroll
    private void initInfiniteScrollListener(LinearLayoutManager mLayoutManager) {
        infiniteScrollable = new InfiniteScrollable(mLayoutManager) {
            @Override
            public void onLoadMore(int page) {
                loadImages(page,APIKEY, currentQuery);
            }
        };
    }

    // Set API and call the API from and for pixabay.com

    private void loadImages(int page,String Api, String query) {

        Api = this.APIKEY;
        query = this.currentQuery;

        PixabayService.createPixabayService().getImageResults(APIKEY, currentQuery,page, 20).enqueue(new Callback<PixabayImageList>() {
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

    // Adding Image to recyclerView ( notify the recyclerView )

    private void addImagesToList(PixabayImageList response) {
        int position = pixabayImageList.size();
        pixabayImageList.addAll(response.getHits());
        pixabayAdapter.notifyItemRangeInserted(position, position + 20);
    }
}
