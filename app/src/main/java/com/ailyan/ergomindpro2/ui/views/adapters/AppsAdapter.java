package com.ailyan.ergomindpro2.ui.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ailyan.ergomindpro2.R;
import com.ailyan.ergomindpro2.ui.viewModels.AppViewModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private final Context context;
    private final List<AppViewModel> apps;
    private final LayoutInflater mInflater;
    private AppsAdapter.ItemClickListener mClickListener;

    public AppsAdapter(Context context, List<AppViewModel> apps) {
        this.mInflater = LayoutInflater.from(context);
        this.apps = apps;
        this.context = context;
    }

    @NonNull
    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_app, parent, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        ViewGroup.MarginLayoutParams lpv = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.height = parent.getMeasuredHeight() / 4 - lpv.topMargin * 2;
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppsAdapter.ViewHolder holder, int position) {
        AppViewModel app = apps.get(position);
        holder.textView_app.setText(app.title);
        holder.imageView_app.setImageDrawable(AppCompatResources.getDrawable(context, app.icon));
        holder.cardView_app.setCardBackgroundColor(context.getColor(app.color));
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MaterialCardView cardView_app;
        TextView textView_app;
        ImageView imageView_app;

        ViewHolder(View itemView) {
            super(itemView);
            cardView_app = itemView.findViewById(R.id.cardView_app);
            textView_app = itemView.findViewById(R.id.textView_app);
            imageView_app = itemView.findViewById(R.id.imageView_app);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
