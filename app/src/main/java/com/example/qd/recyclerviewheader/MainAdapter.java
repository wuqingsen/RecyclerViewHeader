package com.example.qd.recyclerviewheader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.utils.Utils;

import java.util.List;

/**
 * MainAdapter
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<String> mDatas;
    private Context mContext;
    private final int type_zero = 0;
    private final int type_one = 1;

    public MainAdapter(Context mContext, List<String> mDatas) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main_header, parent, false);
            return new ZeroViewHolder(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case type_zero:
                break;
            default:
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                ViewGroup.LayoutParams params1 = myViewHolder.iv_icon.getLayoutParams();
                if (position % 2 == 0) {
                    params1.height = 600;
                    params1.width = Utils.getScreenWidth(mContext) / 2;
                    myViewHolder.iv_icon.setLayoutParams(params1);
                    myViewHolder.iv_icon.setBackgroundResource(R.drawable.icon_bg1);
                }else {
                    params1.height = 700;
                    params1.width = Utils.getScreenWidth(mContext) / 2;
                    myViewHolder.iv_icon.setLayoutParams(params1);
                    myViewHolder.iv_icon.setBackgroundResource(R.drawable.icon_bg2);
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return type_zero;
        } else {
            return type_one;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ZeroViewHolder extends RecyclerView.ViewHolder {

        public ZeroViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
