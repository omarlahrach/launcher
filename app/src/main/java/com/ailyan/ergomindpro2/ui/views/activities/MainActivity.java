package com.ailyan.ergomindpro2.ui.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ailyan.ergomindpro2.R;
import com.ailyan.ergomindpro2.ui.viewModels.SectionViewModel;
import com.ailyan.ergomindpro2.ui.views.adapters.SectionsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SectionsAdapter.ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView_sections = findViewById(R.id.recyclerView_sections);

        List<SectionViewModel> sections = new ArrayList<>();
        sections.add(new SectionViewModel(R.string.group_games, R.drawable.ic_group, R.color.green));
        sections.add(new SectionViewModel(R.string.multiplayer_games, R.drawable.ic_multiplayer, R.color.red));
        sections.add(new SectionViewModel(R.string.tv_replay, R.drawable.ic_tv_replay, R.color.amber));
        sections.add(new SectionViewModel(R.string.karaoke, R.drawable.ic_karaoke, R.color.pink));
        sections.add(new SectionViewModel(R.string.music, R.drawable.ic_music, R.color.cyan));
        sections.add(new SectionViewModel(R.string.radio, R.drawable.ic_radio, R.color.purple));
        sections.add(new SectionViewModel(R.string.visio, R.drawable.ic_visio, R.color.magenta));
        sections.add(new SectionViewModel(R.string.photos, R.drawable.ic_gallery, R.color.orange));
        sections.add(new SectionViewModel(R.string.infos, R.drawable.ic_infos, R.color.blue));

        SectionsAdapter sectionsAdapter = new SectionsAdapter(this, sections);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        sectionsAdapter.setClickListener(this);
        recyclerView_sections.setAdapter(sectionsAdapter);
        recyclerView_sections.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this, GroupGamesActivity.class);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
        }
        startActivity(intent);
    }
}