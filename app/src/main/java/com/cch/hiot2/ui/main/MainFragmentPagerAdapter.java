package com.cch.hiot2.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cch.hiot2.ui.main.equipment.EquipmentFragment;
import com.cch.hiot2.ui.main.message.MessageFragment;
import com.cch.hiot2.ui.main.mine.MineFragment;
import com.cch.hiot2.ui.main.scene.SceneFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch (i){
            case 0:
                fragment=new MessageFragment();
                break;
            case 1:
                fragment=new EquipmentFragment();
                break;
            case 2:
                fragment=new SceneFragment();
                break;
            case 3:
                fragment = new MineFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}

