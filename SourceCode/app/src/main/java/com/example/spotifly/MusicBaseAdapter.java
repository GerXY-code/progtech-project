package com.example.spotifly;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MusicBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<String[]> musicList;

    LayoutInflater inflater;

    public MusicBaseAdapter(Context ctx, ArrayList<String[]> musics){
        this.context = ctx;
        this.musicList = musics;
        inflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txtView = (TextView)convertView.findViewById(R.id.textView);
        txtView.setText(musicList.get(0)[position]);
        return convertView;
    }
}
