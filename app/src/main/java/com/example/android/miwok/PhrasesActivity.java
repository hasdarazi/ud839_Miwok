/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create an arraylist of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word ("Where are you going?","ela ayan enta zahib" ,R.raw.phrase_where_are_you_going));
        words.add(new Word ("What is your name?","ma howa esmok" ,R.raw.phrase_what_is_your_name));
        words.add(new Word ("My name is...","Esmi howa.."  ,R.raw.phrase_my_name_is));
        words.add(new Word ("How are you feeling?","ma howa sho'orok"  ,R.raw.phrase_how_are_you_feeling));
        words.add(new Word ("I’m feeling good.","Ash'or anni mortah" ,R.raw.phrase_im_feeling_good));
        words.add(new Word ("Are you coming?","hal sa ta'ti" ,R.raw.phrase_are_you_coming));
        words.add(new Word ("Yes, I’m coming.","na'am  ana Qadim" ,R.raw.phrase_yes_im_coming));
        words.add(new Word ("Let’s go.","da'na nazhab" ,R.raw.phrase_lets_go));
        words.add(new Word ("Come here.","ta'ala ela hona" ,R.raw.phrase_come_here));

        /* Verify the contents of the array by printing out each arraylist element to the logs
        Log.v("NumbersActivity", "Word at index 0: " + words.get(0));
        Log.v("NumbersActivity", "Word at index 1: " + words.get(1));
        Log.v("NumbersActivity", "Word at index 2: " + words.get(2));
        Log.v("NumbersActivity", "Word at index 3: " + words.get(3));
        Log.v("NumbersActivity", "Word at index 4: " + words.get(4));
        Log.v("NumbersActivity", "Word at index 5: " + words.get(5));
        Log.v("NumbersActivity", "Word at index 6: " + words.get(6));
        Log.v("NumbersActivity", "Word at index 7: " + words.get(7));
        Log.v("NumbersActivity", "Word at index 8: " + words.get(8));
        Log.v("NumbersActivity", "Word at index 9: " + words.get(9));
        */





        // Keep looping until we've reached the end of the list (which means keep looping
        // as long as the current index position is less than the length of the list)
        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

    // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            // Get the {@link Word} object at the given position the user clicked on
            Word word = words.get(position);

            // Create and setup the {@link MediaPlayer} for the audio resource associated
            // with the current word
            mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());

            // Start the audio file
            mMediaPlayer.start();


            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //Toast.makeText(PhrasesActivity.this, "I'm Finished", Toast.LENGTH_SHORT).show();
                    // to release the media player
                    mediaPlayer.release();
                    mMediaPlayer = null;
                }
            });
        }
        });
    }
}
