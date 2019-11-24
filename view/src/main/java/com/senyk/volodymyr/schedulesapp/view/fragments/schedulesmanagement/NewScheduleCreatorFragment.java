package com.senyk.volodymyr.schedulesapp.view.fragments.schedulesmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.senyk.volodymyr.schedulesapp.R;
import com.senyk.volodymyr.schedulesapp.view.fragments.base.BaseFragment;

public class NewScheduleCreatorFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_schedule_creator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.screen_title)).setText(R.string.new_schedule_creator_screen_title);
        ((ImageView) view.findViewById(R.id.back_button)).setImageResource(R.drawable.ic_cancel);
        ((ImageView) view.findViewById(R.id.next_button)).setImageResource(R.drawable.ic_disabled_confirm);
    }
}
