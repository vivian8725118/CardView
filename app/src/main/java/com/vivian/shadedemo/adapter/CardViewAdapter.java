package com.vivian.shadedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vivian.shadedemo.R;
import com.vivian.shadedemo.entity.Card;
import com.vivian.shadedemo.widget.CardView;

import java.util.List;

/**
 * *          _       _
 * *   __   _(_)_   _(_) __ _ _ __
 * *   \ \ / / \ \ / / |/ _` | '_ \
 * *    \ V /| |\ V /| | (_| | | | |
 * *     \_/ |_| \_/ |_|\__,_|_| |_|
 * <p>
 * Created by vivian on 2016/12/1.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    List<Card> mData;
    Context mContext;
    int[] colors = {0xff01a3a1, 0xff91bbeb, 0xff01b1bf, 0xffff9d62, 0xff2d3867, 0xffee697e};//颜色组

    public void setData(List<Card> list) {
        this.mData = list;
    }

    public CardViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new CardView(mContext);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.content.setText(mData.get(position).getContent());
        holder.comment.setText(mData.get(position).getComment());
        holder.comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mData.get(holder.getAdapterPosition()).setComment(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //设置颜色变化
        ((CardView) holder.itemView).changeTheme(colors[position % 6]);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout top;
        ImageView center;
        EditText comment;
        TextView content;

        ViewHolder(View view) {
            super(view);
            top = (RelativeLayout) view.findViewById(R.id.top);
            center = (ImageView) view.findViewById(R.id.center);
            comment = (EditText) view.findViewById(R.id.comment);
            content = (TextView) view.findViewById(R.id.content);
        }
    }
}
