package com.ailyan.ergomindpro2.ui.views.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ailyan.ergomindpro2.R;
import com.ailyan.ergomindpro2.databinding.ActivityAppsBinding;
import com.ailyan.ergomindpro2.ui.viewModels.AppViewModel;
import com.ailyan.ergomindpro2.ui.views.adapters.AppsAdapter;
import com.ailyan.ergomindpro2.utilities.Section;
import com.ailyan.ergomindpro2.utilities.Shared;

import java.util.ArrayList;
import java.util.List;

public class AppsActivity extends AppCompatActivity implements AppsAdapter.ItemClickListener {
    private Section section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAppsBinding binding = ActivityAppsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView textViewAppsTitle = binding.title;
        RecyclerView recyclerViewApps = binding.apps;
        List<AppViewModel> apps = new ArrayList<>();

        Intent intent = getIntent();
        section = (Section) intent.getSerializableExtra("section");
        switch (section) {
            case COLLECTIVE_GAMES:
                textViewAppsTitle.setText(R.string.collective_games);
                apps.add(new AppViewModel(R.string.quiz, R.drawable.ic_quiz, R.color.amber));
                apps.add(new AppViewModel(R.string.flag, R.drawable.ic_flag, R.color.green));
                apps.add(new AppViewModel(R.string.intruder, R.drawable.ic_intruder, R.color.red));
                apps.add(new AppViewModel(R.string.anagram, R.drawable.ic_anagram, R.color.blue));
                break;
            case MULTIPLAYER_GAMES:
                textViewAppsTitle.setText(R.string.multiplayer_games);
                apps.add(new AppViewModel(R.string.lotto, R.drawable.ic_lotto, R.color.magenta));
                apps.add(new AppViewModel(R.string.bingo, R.drawable.ic_bingo, R.color.green));
                break;
        }

        int rows, cols;
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rows = 2;
            cols = 4;
        } else {
            rows = 5;
            cols = 3;
        }

        AppsAdapter appsAdapter = new AppsAdapter(this, apps, rows);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, cols);
        appsAdapter.setClickListener(this);
        recyclerViewApps.setAdapter(appsAdapter);
        recyclerViewApps.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onAppClick(int position) {
        String appPackage = "com.";
        String action = "launch";
        switch (section) {
            case COLLECTIVE_GAMES:
                switch (position) {
                    case 0:
                        appPackage += "ailyan.quizz";
                        action = "send";
                        break;
                    case 1:
                        appPackage += "ailyan.drapeau";
                        break;
                    case 2:
                        appPackage += "ailyan.intrus";
                        action = "send";
                        break;
                    case 3:
                        appPackage += "ailyan.anagram";
                        break;
                }
                break;
            case MULTIPLAYER_GAMES:
                switch (position) {
                    case 0:
                        appPackage += "ailyan.lotto";
                        break;
                    case 1:
                        appPackage += "ailyan.bingo";
                        break;
                }
        }

        if (action.equals("launch")) {
            Intent intent = getPackageManager().getLaunchIntentForPackage(appPackage);
            startActivity(intent);
        } else {
            Shared shared = Shared.getInstance(this);
            String username = (String) shared.get(String.class, "username");
            String password = (String) shared.get(String.class, "password");

            Intent sendIntent = new Intent();
            sendIntent.setPackage(appPackage);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, username + "&" + password);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }
}