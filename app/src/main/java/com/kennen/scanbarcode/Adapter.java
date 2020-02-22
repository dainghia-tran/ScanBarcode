package com.kennen.scanbarcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Adapter extends FragmentStatePagerAdapter
{
    private String[] listTile = {"Quét mã", "Cập nhật sản phẩm", "Danh sách sản phẩm"};
    private ScanFragment scanFragment;
    private UpdateDBFragment updateDBFragment;
    private ShowInformation showInformation;

    public Adapter(@NonNull FragmentManager fm)
    {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        scanFragment = new ScanFragment();
        updateDBFragment = new UpdateDBFragment();
        showInformation = new ShowInformation();
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        if (position == 0)
            return scanFragment;
        else if(position == 1)
            return updateDBFragment;
        else
            return showInformation;
    }

    @Override
    public int getCount()
    {
        return listTile.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return listTile[position];
    }
}
