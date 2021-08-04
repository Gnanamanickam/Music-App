package com.gnani.project2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private String[] songName;
    private String[] songLink;
    private String[] singerName;
    private String[] SingerDetails;
    private String[] SongDetails;
    private ArrayList<Integer> image = new ArrayList<>(
            Arrays.asList(R.drawable.waka_waka,R.drawable.shape_of_you,R.drawable.taki_taki,R.drawable.sorry,
                    R.drawable.no_tears_left_to_cry, R.drawable.blank_space,R.drawable.senorita,R.drawable.new_rules,
                    R.drawable.without_me,R.drawable.diamonds,R.drawable.we_dont_talk_anymore,R.drawable.dont_let_me_down,
                    R.drawable.faded,R.drawable.see_you_again,R.drawable.let_her_go,R.drawable.all_of_me,R.drawable.sugar,
                    R.drawable.work_from_home,R.drawable.uptown_funk,R.drawable.just_the_way_you_are,R.drawable.sunflower,
                    R.drawable.alone,R.drawable.lean_on,R.drawable.cheap_thrills,R.drawable.rockabye,R.drawable.cant_stop_the_feeling,
                    R.drawable.happy,R.drawable.cheerleader,R.drawable.call_me_maybe,R.drawable.roar,R.drawable.lovely,R.drawable.stressed_out));
    RecyclerView rView;
    MusicAdapter mAdapter;
    private ArrayList<Music> AllDetails  = new ArrayList<>();
    private int SelectedView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rView = (RecyclerView) findViewById(R.id.recyclerview);

        songName = getResources().getStringArray(R.array.songName);
        songLink = getResources().getStringArray(R.array.songLink);
        singerName = getResources().getStringArray(R.array.singerName);
        SingerDetails = getResources().getStringArray(R.array.SingerDetails);
        SongDetails = getResources().getStringArray(R.array.SongDetails);

        for (int i = 0; i < image.size(); i++) {
            AllDetails.add(new Music( songName[i], songLink[i], singerName[i], SingerDetails[i], SongDetails[i], image.get(i))); // Add all the details in a 2D array .
        }


        ClickListener listener = (view,position)->{
                //listener to access the information on the view
        };
        rView.setLayoutManager(new LinearLayoutManager(this)); // For view
        rView.setHasFixedSize(true);
        mAdapter = new MusicAdapter(this, AllDetails , listener);
        rView.setAdapter(mAdapter);
    }

    // Method to create options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu); // Create the options menu given in the XML file .
        return true;
    }

    // Method to change the view between grid and list .
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SelectedView = item.getItemId(); // Getting the selected view id and saving it in selected view , so that we can use it on screen rotation .
        if(item.getItemId() == R.id.list)
        {
            rView.setLayoutManager(new LinearLayoutManager(this)); // Set to list view .
        }
        else if(item.getItemId() == R.id.grid)
        {
            rView.setLayoutManager(new GridLayoutManager(this, 2)); // Set to grid view .
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("SelectedView", SelectedView); // Saving the current view state .

    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SelectedView = savedInstanceState.getInt("SelectedView");
        if(SelectedView == R.id.grid)
        {
            rView.setLayoutManager(new GridLayoutManager(this, 2)); // Setting back to grid view on screen rotate . By default it is list view .
        }
    }

}