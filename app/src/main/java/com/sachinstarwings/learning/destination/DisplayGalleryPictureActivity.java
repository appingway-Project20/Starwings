package com.sachinstarwings.learning.destination;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DisplayGalleryPictureActivity extends AppCompatActivity {
    ImageView galleryImageIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_gallery_picture);

        galleryImageIv = (ImageView)findViewById(R.id.gallery_image_view);
        Intent intent = getIntent();
        if(intent.hasExtra(Intent.EXTRA_TEXT)){
            String imageLink = intent.getStringExtra(Intent.EXTRA_TEXT);
            imageLink = "http://starwingslearningdestination.com/php/web_api/" + imageLink;
            Picasso.with(this)
                    .load(imageLink)
                    .placeholder(R.drawable.ic_picture_gallery)
                    .error(R.drawable.ic_error_triangle)
                    .resize(320,240)
                    .centerCrop()
                    .into(galleryImageIv);
        }
    }
}
