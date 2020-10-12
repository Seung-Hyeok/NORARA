package com.example.norara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<Item> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public recyclerAdapter(Context context, ArrayList<Item> itmes) {
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        holder.title.setText(mList.get(position).title);
        holder.add1.setText(mList.get(position).add1);
        holder.eventstartdate.setText(mList.get(position).eventstartdate);
        holder.eventenddate.setText(mList.get(position).eventenddate);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView add1;
        public TextView eventstartdate;
        public TextView eventenddate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.t_title);
            add1 = itemView.findViewById(R.id.t_add);
            eventstartdate = itemView.findViewById(R.id.t_eventstart);
            eventenddate = itemView.findViewById(R.id.t_eventend);
        }
    }
}
