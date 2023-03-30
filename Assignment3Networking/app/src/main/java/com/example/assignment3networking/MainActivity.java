package com.example.assignment3networking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OwnAdapter.OnNoteListener {
    private Button getPicture;
    private OwnAdapter ownAdapter;
    private RecyclerView recyclerView;
    ArrayList<Bitmap> bmp_images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPicture = findViewById(R.id.getPicButton);
        recyclerView = findViewById(R.id.recyclerId);

        bmp_images = new ArrayList<>();
        ownAdapter = new OwnAdapter(bmp_images, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ownAdapter);

        getPicture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addNewItem();
            }
        });
    }

    private void addNewItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose what picture you want:");
        builder.setItems(new CharSequence[] {"Faces", "Furniture", "Pizza"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int position) { switch (position) {
                        case 0:
                            Toast.makeText(MainActivity.this, "Faces", Toast.LENGTH_SHORT).show();
                            addPictureMap();
                            ownAdapter.notifyDataSetChanged();

                            break;
                        case 1:
                            Toast.makeText(MainActivity.this, "Furniture", Toast.LENGTH_SHORT).show();
                            //listA.add("Image number " + (1+ listA.size()));
                            ownAdapter.notifyDataSetChanged();
                            break;
                        case 2:
                            Toast.makeText(MainActivity.this, "Pizza", Toast.LENGTH_SHORT).show();
                            //listA.add("Image number " + (1+ listA.size()));
                            ownAdapter.notifyDataSetChanged();
                            break;
                    }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void addPictureMap(){
        MySingleton.getInstance()
                .getLoremSpaceRepository()
                .GetPicture("https://api.lorem.space/image/album?w=70&h=70", new LoremSpaceRepository.IPicture() {
                    @Override
                    public void Callback(Bitmap bitmap) {
                        bmp_images.add(bitmap);
                    }
                });
    }

    @Override
    public void onNoteClick(int position) {
    }
}