package com.ll.uicomponentexample.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ll.uicomponentexample.R;

import java.util.ArrayList;

/**
 * ProjectName:    UIComponentExample
 * Package:        com.ll.uicomponentexample.example.adapter
 * ClassName:      MaxWidthRecyclerViewAdapter
 * Author:         dev-gxy
 * CreateDate:     2021/4/20 15:20
 * Description:
 */
public class MaxWidthRecyclerViewAdapter extends RecyclerView.Adapter<MaxWidthRecyclerViewAdapter.MaxWidthRecyclerViewHolder> {

    private ArrayList<String> mData;
    private Context mContext;

    public MaxWidthRecyclerViewAdapter(Context context, @Nullable ArrayList<String> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public MaxWidthRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_max_width, parent, false);
        return new MaxWidthRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaxWidthRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class MaxWidthRecyclerViewHolder extends RecyclerView.ViewHolder {


        public MaxWidthRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
