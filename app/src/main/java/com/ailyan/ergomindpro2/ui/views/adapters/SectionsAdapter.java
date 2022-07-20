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
import com.ailyan.ergomindpro2.ui.viewModels.SectionViewModel;
import com.ailyan.ergomindpro2.utilities.Section;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class SectionsAdapter extends RecyclerView.Adapter<SectionsAdapter.ViewHolder> {

    private final Context context;
    private final List<SectionViewModel> sections;
    private final LayoutInflater mInflater;
    private SectionsAdapter.ItemClickListener mClickListener;
    private final int rows;

    public SectionsAdapter(Context context, List<SectionViewModel> sections, int rows) {
        this.mInflater = LayoutInflater.from(context);
        this.sections = sections;
        this.context = context;
        this.rows = rows;
    }

    @NonNull
    @Override
    public SectionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_section, parent, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        ViewGroup.MarginLayoutParams lpv = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.height = parent.getMeasuredHeight() / rows - lpv.topMargin * 2;
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionsAdapter.ViewHolder holder, int position) {
        SectionViewModel section = sections.get(position);
        holder.textView_section.setText(section.title);
        holder.imageView_section.setImageDrawable(AppCompatResources.getDrawable(context, section.icon));
        holder.cardView_section.setCardBackgroundColor(context.getColor(section.color));
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MaterialCardView cardView_section;
        TextView textView_section;
        ImageView imageView_section;

        ViewHolder(View itemView) {
            super(itemView);
            cardView_section = itemView.findViewById(R.id.cardView_section);
            textView_section = itemView.findViewById(R.id.textView_section);
            imageView_section = itemView.findViewById(R.id.imageView_section);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Section section;
            switch (getAdapterPosition()) {
                case 0:
                    section = Section.COLLECTIVE_GAMES;
                    break;
                case 1:
                    section = Section.MULTIPLAYER_GAMES;
                    break;
                case 2:
                    section = Section.TV_REPLAY;
                    break;
                case 3:
                    section = Section.KARAOKE;
                    break;
                case 4:
                    section = Section.MUSIC;
                    break;
                case 5:
                    section = Section.RADIO;
                    break;
                case 6:
                    section = Section.VISIO;
                    break;
                case 7:
                    section = Section.PHOTOS;
                    break;
                case 8:
                    section = Section.INFO;
                    break;
                default:
                    section = null;
                    break;
            }
            if (mClickListener != null) mClickListener.onSectionClick(view, section);
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onSectionClick(View view, Section section);
    }
}
