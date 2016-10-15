package com.sam_chordas.android.stockhawk.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sam_chordas.android.stockhawk.R;

import java.util.ArrayList;

/**
 * Created by daman on 15/10/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private ArrayList<String> title;
    private ArrayList<String> source_url;
    private ArrayList<String> description;
    private Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txttitle, txtdes;
        public CardView cardView;

        public MyViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            txttitle = (TextView) v.findViewById(R.id.headline);
            txtdes = (TextView) v.findViewById(R.id.description);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NewsAdapter(Context context, ArrayList<String> title, ArrayList<String> description,
                       ArrayList<String> source_url) {
        this.title = title;
        this.source_url = source_url;
        this.description = description;
        this.mContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        MyViewHolder holder;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news, parent, false);
        holder = new MyViewHolder(v);
        holder.cardView.setTag(holder);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {
            holder.txttitle.setText(title.get(position));
            holder.txtdes.setText(description.get(position));
            final String url = source_url.get(position);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    mContext.startActivity(i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return title.size();
    }
}
