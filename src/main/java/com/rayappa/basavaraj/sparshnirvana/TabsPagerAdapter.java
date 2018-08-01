package com.rayappa.basavaraj.sparshnirvana;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.rayappa.basavaraj.sparshnirvana.Spotify;
import com.rayappa.basavaraj.sparshnirvana.Soundcloud;
/**
 * Created by basavaraj.r on 2/16/2018.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch(index) {
            case 0:
                return new Spotify();
            case 1:
                return new Soundcloud();
        }
        return null;
    }

    @Override
    public int getCount() {
      return 2;
    }
}
