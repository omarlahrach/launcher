package com.ailyan.ergomindpro2.ui.views.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ailyan.ergomindpro2.R;
import com.ailyan.ergomindpro2.databinding.ActivitySectionsBinding;
import com.ailyan.ergomindpro2.ui.viewModels.SectionViewModel;
import com.ailyan.ergomindpro2.ui.views.adapters.SectionsAdapter;
import com.ailyan.ergomindpro2.utilities.Section;

import java.util.ArrayList;
import java.util.List;

public class SectionsActivity extends AppCompatActivity implements SectionsAdapter.ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySectionsBinding binding = ActivitySectionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final RecyclerView recyclerViewSections = binding.sections;

        List<SectionViewModel> sections = new ArrayList<>();
        sections.add(new SectionViewModel(R.string.collective_games, R.drawable.ic_group, R.color.green));
        sections.add(new SectionViewModel(R.string.multiplayer_games, R.drawable.ic_multiplayer, R.color.red));
        sections.add(new SectionViewModel(R.string.tv_replay, R.drawable.ic_tv_replay, R.color.amber));
        sections.add(new SectionViewModel(R.string.karaoke, R.drawable.ic_karaoke, R.color.pink));
        sections.add(new SectionViewModel(R.string.music, R.drawable.ic_music, R.color.cyan));
        sections.add(new SectionViewModel(R.string.radio, R.drawable.ic_radio, R.color.purple));
        sections.add(new SectionViewModel(R.string.visio, R.drawable.ic_visio, R.color.magenta));
        sections.add(new SectionViewModel(R.string.photos, R.drawable.ic_gallery, R.color.orange));
        sections.add(new SectionViewModel(R.string.infos, R.drawable.ic_infos, R.color.blue));

        int rows, cols;
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rows = 3;
            cols = 3;
        } else {
            rows = 4;
            cols = 2;
        }

        SectionsAdapter sectionsAdapter = new SectionsAdapter(this, sections, rows);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, cols);
        sectionsAdapter.setClickListener(this);
        recyclerViewSections.setAdapter(sectionsAdapter);
        recyclerViewSections.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onSectionClick(View view, Section section) {
        switch (section) {
            case MUSIC:
                String spotifyPackage = "com.spotify.music";
                Intent spotifyIntent = getPackageManager().getLaunchIntentForPackage(spotifyPackage);
                startActivity(spotifyIntent);
                break;
            case PHOTOS:
                Uri galleryUri = Uri.parse("content://media/internal/images/media");
                Intent galleryIntent = new Intent(Intent.ACTION_VIEW, galleryUri);
                startActivity(galleryIntent);
                break;
            case RADIO:
                String radioPackage = "com.ailyan.radio";
                Intent radioIntent = getPackageManager().getLaunchIntentForPackage(radioPackage);
                startActivity(radioIntent);
                break;
            case TV_REPLAY:
                String tvReplayPackage = "com.ailyan.tvReplay";
                Intent tvReplayIntent = getPackageManager().getLaunchIntentForPackage(tvReplayPackage);
                startActivity(tvReplayIntent);
                break;
            case VISIO:
                String visioPackage = "com.ailyan.lotto";
                Intent visioIntent = getPackageManager().getLaunchIntentForPackage(visioPackage);
                startActivity(visioIntent);
                break;
            default:
                Intent intent = new Intent(this, AppsActivity.class);
                intent.putExtra("section", section);
                startActivity(intent);
                break;
        }
    }
}