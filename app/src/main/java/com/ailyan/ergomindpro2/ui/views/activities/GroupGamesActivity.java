package com.ailyan.ergomindpro2.ui.views.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ailyan.ergomindpro2.R;
import com.ailyan.ergomindpro2.ui.viewModels.AppViewModel;
import com.ailyan.ergomindpro2.ui.views.adapters.AppsAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroupGamesActivity extends AppCompatActivity implements AppsAdapter.ItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_games);

        RecyclerView recyclerView_apps = findViewById(R.id.recyclerView_apps);

        List<AppViewModel> apps = new ArrayList<>();
        apps.add(new AppViewModel(R.string.quiz, R.drawable.ic_quiz, R.color.green));
        apps.add(new AppViewModel(R.string.intrus, R.drawable.ic_intrus, R.color.red));

        AppsAdapter appsAdapter = new AppsAdapter(this, apps);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
        appsAdapter.setClickListener(this);
        recyclerView_apps.setAdapter(appsAdapter);
        recyclerView_apps.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onItemClick(View view, int position) {
        PackageManager packageManager = getPackageManager();
        String appPackage = "com.ailyan.";
        switch (position) {
            case 0:
                appPackage += "quizz";
                break;
            case 1:
                appPackage += "intrus";
                break;
        }
        Intent intent = packageManager.getLaunchIntentForPackage(appPackage);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
    }
}