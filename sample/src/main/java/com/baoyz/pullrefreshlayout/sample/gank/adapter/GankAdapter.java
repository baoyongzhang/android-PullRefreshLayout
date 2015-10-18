/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 baoyongzhang <baoyz94@gmail.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.baoyz.pullrefreshlayout.sample.gank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baoyz.pullrefreshlayout.sample.R;
import com.baoyz.pullrefreshlayout.sample.gank.model.GankItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baoyz on 15/1/14.
 */
public class GankAdapter extends RecyclerView.Adapter<GankAdapter.ViewHolder> {

    private int mLastPosition = -1;
    private final List<GankItem> mItems;

    public GankAdapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed, viewGroup, false));
    }

    public void addItems(List<GankItem> items) {
        if (items == null || items.size() <= 0)
            return;
        mItems.addAll(items);
    }

    public void clear() {
        mItems.clear();
    }

    @Override
    public void onBindViewHolder(GankAdapter.ViewHolder holder, int position) {
        if (position > mLastPosition) {
            mLastPosition = position;
            startItemAnimation(holder, position);
        }
        GankItem item = mItems.get(position);
        Picasso.with(holder.mView.getContext()).load(item.getUrl()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    protected void startItemAnimation(final GankAdapter.ViewHolder holder, int position) {
        holder.mView.post(new Runnable() {
            @Override
            public void run() {
                holder.mView.setTranslationY(holder.mView.getMeasuredHeight() / 2);
                holder.mView.setAlpha(0);
                holder.mView.animate().translationY(0).alpha(1).setDuration(500).start();
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View mView;
        public ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
