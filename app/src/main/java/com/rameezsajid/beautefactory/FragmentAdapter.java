package com.rameezsajid.beautefactory;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new AboutFragment();
            case 1:
                return new TrainingFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
