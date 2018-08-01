package com.rayappa.basavaraj.sparshnirvana;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BackendClip extends FragmentActivity implements Soundcloud.OnFragmentInteractionListener, Spotify.OnFragmentInteractionListener {
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private android.app.ActionBar actionBar;
    Logger logger = Logger.getLogger("foo");
    // Tab titles
    private String[] tabs = { "Spotify", "Soundcloud" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backend_clip);

        // Initialization
        viewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
    }

    private void setupTabs() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        logger.log(Level.FINER, uri.getFragment());
    }

}
