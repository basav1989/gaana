package com.rayappa.basavaraj.sparshnirvana;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CuratedMusicContent extends AppCompatActivity {
    private Cursor mCursor;

    private class queryResult {
        public String mediaId;
        public String artistName;
        public String trackName;
        public String artPath;
        queryResult(Cursor result) {
            mediaId = result.getString(0);
            artistName = result.getString(1);
            trackName = result.getString(2);
            artPath = result.getString(3);
        }
    }

    ArrayList<queryResult> resultSet = new ArrayList<queryResult>();

    private class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return resultSet.size();
        }

        public ImageAdapter() {

        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            Log.v("curatedMusic", "getView");
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.griditem, viewGroup, false);
            TextView tv = (TextView) row.findViewById(R.id.gridItemText);
           // ImageView iv = (ImageView) row.findViewById(R.id.gridItemImage);
            Log.v("curatedMusic", resultSet.get(position).artistName);
            Log.v("curatedMusic", resultSet.get(position).trackName);
            Log.v("curatedMusic", resultSet.get(position).artPath);
            Log.v("curatedMusic", resultSet.get(position).mediaId);
            tv.setText(resultSet.get(position).mediaId);
            //iv.setImageBitmap(BitmapFactory.decodeFile(resultSet.get(position).artPath));
            return row;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curated_music_content);

        Log.v("curatedMusic", "oncreate");

        retrieveMetadata();
    }

    private void retrieveMetadata() {
        // acquire  contentResolver
        ContentResolver cr = getApplicationContext().getContentResolver();

        // prepare query metadata
        String[] projection = {MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA};
        mCursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
            while (mCursor.moveToNext()) {
                Log.v("grid", mCursor.getString(3));
                resultSet.add(new queryResult(mCursor));
            }
        }

        GridView contentView  = (GridView) findViewById(R.id.contentView);
        ImageAdapter ad = new ImageAdapter();
        contentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                               @Override
                                               public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                                   Log.v("curatedMusic", resultSet.get(position).mediaId);
                                                   //Log.v("curatedMusic", qr.mediaId);
                                               }
                                           });
        contentView.setAdapter(ad);
    }

}
