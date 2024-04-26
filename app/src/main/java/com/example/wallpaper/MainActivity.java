package com.example.wallpaper;

import android.app.WallpaperManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private final int[] imageResources = {
            R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,R.drawable.image11,R.drawable.image12,R.drawable.image13,R.drawable.image14,R.drawable.image15,R.drawable.image16,R.drawable.image17,R.drawable.image18,R.drawable.image19,R.drawable.image20
    };
    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        setImage(imageResources[currentImageIndex]);

        final Button setWallpaperButton = findViewById(R.id.setWallpaperButton);
        setWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper();
            }
        });

        final Button previousButton = findViewById(R.id.button2);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousImage();
            }
        });

        final Button nextButton = findViewById(R.id.button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextImage();
            }
        });

        // Display image1 for 5 seconds and then switch to image2
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setImage(imageResources[++currentImageIndex]); // Switch to image2
            }
        }, 2000); // 5000 milliseconds = 5 seconds
    }

    private void setImage(int resourceId) {
        imageView.setImageResource(resourceId);
    }

    private void setWallpaper() {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
            wallpaperManager.setResource(imageResources[currentImageIndex]);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void showNextImage() {
        if (currentImageIndex < imageResources.length - 1) {
            setImage(imageResources[++currentImageIndex]);
        }
    }

    private void showPreviousImage() {
        if (currentImageIndex > 0) {
            setImage(imageResources[--currentImageIndex]);
        }
    }
}
