package com.example.spotifly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutionException;

public class LandingPageActivity extends AppCompatActivity {

    Button to_music_library_btn,finalize_playlist_creation;
    FloatingActionButton get_playlist_view_btn;

    EditText playlist_name_editor;

    View add_playlist_view;

    String playlistName = "", musicName;

    Integer currentMusicID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);
        to_music_library_btn = (Button)findViewById(R.id.to_musics_btn);
        get_playlist_view_btn = (FloatingActionButton)findViewById(R.id.get_playlist_view_btn);
        finalize_playlist_creation = (Button)findViewById(R.id.finalize_playlist_btn);
        add_playlist_view = (View)findViewById(R.id.add_playlist_view);
        finalize_playlist_creation.setVisibility(View.INVISIBLE);
        add_playlist_view.setVisibility(View.INVISIBLE);
        goToTheMusicLibrary();
        getPlaylistView();
        finalizePlaylistCreation();

    }

    public void goToTheMusicLibrary(){

        to_music_library_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPageActivity.this, HomePageActivity.class));

            }
        });
    }
    public void getPlaylistView(){
        playlist_name_editor = (EditText) findViewById(R.id.playlist_name_input);
        playlist_name_editor.setVisibility(View.INVISIBLE);
        get_playlist_view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlist_name_editor.setVisibility(View.VISIBLE);
                finalize_playlist_creation.setVisibility(View.VISIBLE);
                get_playlist_view_btn.setVisibility(View.INVISIBLE);

            }
        });
    }
    public void finalizePlaylistCreation(){
        finalize_playlist_creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playlistName = playlist_name_editor.getText().toString();
                Playlist p = new Playlist();
                PlaylistCommand createPlaylsit = new PlaylistCommand(p,PlaylistAction.CreatePlaylist,currentMusicID, playlistName);
                try {
                    createPlaylsit.call();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
