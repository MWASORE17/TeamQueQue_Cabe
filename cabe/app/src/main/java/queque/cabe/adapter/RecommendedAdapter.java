package queque.cabe.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import queque.cabe.R;
import queque.cabe.model.RecommendedData;
import queque.cabe.utilities.PicassoLoader;

/**
 * Created by willy on 3/9/2017.
 */

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.SingleItemRowHolder> {
    private List<RecommendedData> data;
    private Context mContext;
    public RecommendedAdapter(Context context, List<RecommendedData> data) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recommended, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        holder.tv_outletName.setText(data.get(i).getOutletName());
        holder.tv_outletAddress.setText(data.get(i).getOutletAddress());
        PicassoLoader.loadImageSuccess(mContext,data.get(i).getImgUrl(),holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return (null != data ? data.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {
        protected TextView tv_outletAddress;
        protected TextView tv_outletName;
        protected ImageView iv_image;


        public SingleItemRowHolder(View view) {
            super(view);
            this.tv_outletAddress = (TextView) view.findViewById(R.id.tv_outletAddress);
            this.tv_outletName = (TextView) view.findViewById(R.id.tv_outletName);
            this.iv_image = (ImageView) view.findViewById(R.id.iv_image);
        }

    }
}
