package queque.cabe.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import queque.cabe.R;
import queque.cabe.model.HomeViewPagerData;
import queque.cabe.utilities.PicassoLoader;

/**
 * Created by Willy Laurents on 3/9/2017.
 */

public class HomeViewPagerAdapter extends PagerAdapter{
    private Context context;
    private List<HomeViewPagerData> data;
    private LayoutInflater layoutInflater;
    public HomeViewPagerAdapter(Context context,List<HomeViewPagerData> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position){
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.viewpager_home,container,false);
        ImageView imageView = (ImageView)item_view.findViewById(R.id.img);
        TextView tv_foodName = (TextView)item_view.findViewById(R.id.tv_foodName);
        TextView tv_outletName = (TextView)item_view.findViewById(R.id.tv_outletName);
        TextView tv_discountPrice = (TextView)item_view.findViewById(R.id.tv_discountPrice);
        TextView tv_price = (TextView)item_view.findViewById(R.id.tv_price);
        if(data.get(position).getImgUrl() != null) {
            PicassoLoader.loadImageSuccess(context,data.get(position).getImgUrl(),imageView);
        }

        tv_foodName.setText(data.get(position).getFoodName());
        tv_outletName.setText(data.get(position).getStoreName());
        tv_discountPrice.setText(data.get(position).getDiscountPrice());
        tv_price.setText(data.get(position).getPrice());
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
