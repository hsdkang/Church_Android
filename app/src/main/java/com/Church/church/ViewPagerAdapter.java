package com.Church.church;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    // 프래그먼트 교체를 보여주는 처리를 위한 곳
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return Fragment_01.newInstance();
            case 1:
                return Fragment_02.newInstance();
            case 2:
                return Fragment_03.newInstance();
            case 3:
                return Fragment_04.newInstance();
            case 4:
                return Fragment_05.newInstance();
            default:
                return null;
        }
    }

    // 프래그먼트 개수 명시
    @Override
    public int getItemCount() {
        return 5;
    }



}