package com.example.firstgroupassigment;


import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class Adappter extends RecyclerView.Adapter<Adappter.ViewHolder> {

    private int [] imageId;
    private String [] capption;


    public Adappter(int[] imageId, String[] capption) {
        this.imageId = imageId;
        this.capption = capption;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        final CardView card= viewHolder.cardView;
        ImageView imageView = (ImageView) card.findViewById(R.id.image);

        Drawable dr = ContextCompat.getDrawable(card.getContext(), imageId[position]);

        imageView.setImageDrawable(dr);

        final TextView text= card.findViewById(R.id.txtName);
        text.setText(capption[position]);
        card.setId(imageId[position]);
    }
    @Override
    public int getItemCount() {
        return capption.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            // Define click listener for the ViewHolder's View
            this.cardView=cardView;
        }

    }
}
