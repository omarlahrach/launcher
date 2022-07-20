package com.ailyan.ergomindpro2.ui.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ailyan.ergomindpro2.databinding.FragmentZoneBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ZoneFragment extends Fragment {
    private FragmentZoneBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentZoneBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView textViewDate = binding.date;
        final TextView textViewTime = binding.time;

        Date zone = Calendar.getInstance().getTime();
        String currentDate = new SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault()).format(zone);
        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(zone);

        textViewDate.setText(currentDate);
        textViewTime.setText(currentTime);
    }
}