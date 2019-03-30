package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    //Resource id for the background color for this list of words
    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.

        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
            // Check if the existing view is being reused, otherwise inflate the view
            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_item, parent, false);
            }

            // Get the {@link} object located at this position in the list
            final Word currentposition = getItem(position);

            //playes sound recording on list item click from word adapter
/*
                if(currentposition.getAudiResourceId() != 0) {
                    listItemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MediaPlayer wordsayer2 = MediaPlayer.create(getContext(), currentposition.getAudiResourceId());
                            wordsayer2.start();

                        }
                    });
            }
*/

            // Find the TextView in the list_item.xml layout with the ID version_name
            TextView nameTextView = (TextView) listItemView.findViewById(R.id.text2);
            // Get the version name from the current AndroidFlavor object and
            // set this text on the name TextView
            nameTextView.setText(currentposition.getDefaultTranslation());

            // Find the TextView in the list_item.xml layout with the ID version_number
            TextView numberTextView = (TextView) listItemView.findViewById(R.id.text1);
            // Get the version number from the current AndroidFlavor object and
            // set this text on the number TextView
            numberTextView.setText(currentposition.getMiwokTranslation());

           // Find the ImageView in the list_item.xml layout with the ID list_item_icon
           ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_image1);
           // Get the image resource ID from the current AndroidFlavor object and
           // set the image to iconView
            if(currentposition.getImageResourceId() != 0) {
                iconView.setImageResource(currentposition.getImageResourceId());
                iconView.setVisibility(View.VISIBLE);
            } else {
                iconView.setImageResource(R.drawable.family_younger_brother);
                iconView.setVisibility(View.GONE);
            }
            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);



            // Return the whole list item layout (containing 2 TextViews and an ImageView)
            // so that it can be shown in the ListView
            return listItemView;
      //  return super.getView(position, convertView, parent);
    }
}
