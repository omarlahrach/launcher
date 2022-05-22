package com.ailyan.ergomindpro2.ui.viewModels;

import androidx.lifecycle.ViewModel;

public class SectionViewModel extends ViewModel {
    public int title;
    public int icon;
    public int color;

    public SectionViewModel(int title, int icon, int color) {
        this.title = title;
        this.icon = icon;
        this.color = color;
    }
}
