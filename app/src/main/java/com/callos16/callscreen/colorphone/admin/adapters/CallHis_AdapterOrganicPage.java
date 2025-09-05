package com.callos16.callscreen.colorphone.admin.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class CallHis_AdapterOrganicPage extends FragmentStateAdapter {
    public CallHis_AdapterOrganicPage(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new CallHis_ContactsFragment();
            case 1: return new CallHis_EmergencyFragment();
            case 2: return new CallListFragment();
            default: return new CallHis_ContactsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}