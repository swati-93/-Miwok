package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create an array of words
        ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("tutti", "one", R.drawable.number_one, R.raw.number_one));
        words.add(new Words("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        words.add(new Words("tolookosu", "three", R.drawable.number_three, R.raw.number_three));
        words.add(new Words("oyyisa", "four", R.drawable.number_four, R.raw.number_four));
        words.add(new Words("massokka", "five", R.drawable.number_five, R.raw.number_five));
        words.add(new Words("temmokka", "six", R.drawable.number_six, R.raw.number_six));
        words.add(new Words("tenekaku", "seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Words("kawinta", "eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Words("wo'e", "nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Words("na'aacha", "ten", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();
              //  Toast.makeText(NumbersActivity.this, "List item clicked", Toast.LENGTH_SHORT).show();
                 Words localword = (Words)adapterView.getItemAtPosition(i);
                int id = localword.getMusicResourceId();
                mediaPlayer = MediaPlayer.create(NumbersActivity.this,id);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        });

    }

    private void releaseMediaPlayer(){

        // If mediaplayer is not null it must be playing an audio file

        if(mediaPlayer != null){

            // Release the resource, we no longer need it

            mediaPlayer.release();

        }

        // Set the media player back to null. For our code, we've decided that
        // setting the media player to null is an easy way to tell that the media player
        // is not configured to play an audio file at the moment.

        mediaPlayer = null;

    }

}
