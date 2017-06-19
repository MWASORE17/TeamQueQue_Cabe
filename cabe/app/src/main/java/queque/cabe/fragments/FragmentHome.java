package queque.cabe.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import queque.cabe.R;
import queque.cabe.adapter.HomeViewPagerAdapter;
import queque.cabe.adapter.RecommendedAdapter;
import queque.cabe.model.HomeViewPagerData;
import queque.cabe.model.RecommendedData;

/**
 * Created by Willy Laurents on 3/8/2017.
 */

public class FragmentHome extends Fragment {
    @BindView(R.id.viewPager) ViewPager viewPager;
    @BindView(R.id.recycleView) RecyclerView recycleView;
    private HomeViewPagerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_home, container, false);
        ButterKnife.bind(this,rootView);



        List<HomeViewPagerData> list = new ArrayList<HomeViewPagerData>();
        HomeViewPagerData homeViewPagerData = new HomeViewPagerData();
        homeViewPagerData.setImgUrl("http://restaurant.business.brookes.ac.uk/images/slideshow/restaurant.jpg");
        homeViewPagerData.setFoodName("naa");
        homeViewPagerData.setStoreName("naazcv");
        homeViewPagerData.setPrice("Rp 10.000");
        homeViewPagerData.setDiscountPrice("Rp 10.000");
        list.add(homeViewPagerData);

        homeViewPagerData = new HomeViewPagerData();
        homeViewPagerData.setImgUrl("http://hcevisuals.com/wp-content/uploads/2015/02/Food-photography-Cafe-Bar-Sandwich.jpg");
        homeViewPagerData.setFoodName("naa");
        homeViewPagerData.setStoreName("naazcv");
        homeViewPagerData.setPrice("Rp 10.000");
        homeViewPagerData.setDiscountPrice("Rp 10.000");
        list.add(homeViewPagerData);


        adapter = new HomeViewPagerAdapter(getActivity(),list);
        viewPager.setAdapter(adapter);

        setRecycleView();

        return rootView;
    }

    private void setRecycleView(){
        GridLayoutManager gLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        recycleView.setLayoutManager(gLayoutManager);
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setHasFixedSize(true);
//        recycleView.clearFocus();

        List<RecommendedData>list = new ArrayList<>();
        RecommendedData recommendedData = new RecommendedData();
        recommendedData.setImgUrl("http://notable.ca/wp-content/uploads/2015/07/resto.jpg");
        recommendedData.setOutletAddress("Jl.Cemara");
        recommendedData.setOutletName("Cemara Biru");
        list.add(recommendedData);

        recommendedData = new RecommendedData();
        recommendedData.setImgUrl("https://cdn.theculturetrip.com/wp-content/uploads/2016/02/restaurant-939435_960_720.jpg");
        recommendedData.setOutletAddress("Jl.Biru");
        recommendedData.setOutletName("Biruan");
        list.add(recommendedData);

        recommendedData = new RecommendedData();
        recommendedData.setImgUrl("https://static1.squarespace.com/static/51ab58f4e4b0361e5f3ed294/51ab58f4e4b0361e5f3ed29a/51ab80bee4b0058e26cfcb1f/1370194240299/Benchmark_Restaurant_Dining_Room_Photographed_by_Evan_Sung.jpg");
        recommendedData.setOutletAddress("Jl.Eropa");
        recommendedData.setOutletName("Asia");
        list.add(recommendedData);

        recommendedData = new RecommendedData();
        recommendedData.setImgUrl("http://everything-pr.com/wp-content/uploads/2015/08/Restaurant.jpg");
        recommendedData.setOutletAddress("Jl.Everything");
        recommendedData.setOutletName("Has Changes");
        list.add(recommendedData);

        RecommendedAdapter recommendedAdapter = new RecommendedAdapter(getActivity(),list);
        recycleView.setAdapter(recommendedAdapter);
    }
}
