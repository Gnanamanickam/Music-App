package com.gnani.project2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private ClickListener clickListener;
    private Integer image;
    private Context context;
    private ArrayList<Music> musicArray;

    // Constructor to assign the values .
    public MusicAdapter(Context context, ArrayList<Music> mArray, ClickListener listener){
        this.musicArray = mArray;
        this.clickListener = listener;
        this.context = context;
    }

    // Create the view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.songslayout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, clickListener);

        return viewHolder;
    }

    // To set the values .
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(musicArray.get(position).getSongName());
        holder.singer.setText(musicArray.get(position).getSingerName());
        holder.coverPic.setImageResource(musicArray.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return musicArray.size();
    }

//    @Nullable
//    public Music getItem(int position) {
//        return musicArray.get(position);
//    }

    // Class creates a wrapper object around a view that contains the layout for  each individual item .
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

        private final ClickListener clickListener;
        public TextView title;
        public ImageView coverPic;
        public TextView singer;
        private View itemView;
        public String url;

        public ViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            singer = (TextView) itemView.findViewById(R.id.singer);
            coverPic = (ImageView) itemView.findViewById(R.id.songImage);
            this.itemView = itemView;
            itemView.setOnCreateContextMenuListener(this); //set context menu for each list item
            this.clickListener = listener;

            itemView.setOnClickListener((View.OnClickListener) this);
        }

        // Functionality to implement on clickable
        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
            goToWebPage(musicArray.get(getAdapterPosition()).songLink);
        }

        // Method to set listener for each menu item on long click .
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.context_menu, menu);

            menu.getItem(0).setOnMenuItemClickListener(menu_option);
            menu.getItem(1).setOnMenuItemClickListener(menu_option);
            menu.getItem(2).setOnMenuItemClickListener(menu_option);

        }

        // Method to execute on long click
        private final MenuItem.OnMenuItemClickListener menu_option = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.toString().equals("Open Youtube"))
                    url = musicArray.get(getAdapterPosition()).getSongLink();
                else if(item.toString().equals("Song Information"))
                    url = musicArray.get(getAdapterPosition()).getSongDetails();
                else if(item.toString().equals("Singer Information"))
                    url = musicArray.get(getAdapterPosition()).getSingerDetails();
                goToWebPage(url);
                return true;
            }
        };

        // Go to web page method
        public void goToWebPage(String url){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            context.startActivity(intent);
        }
    }
}
