package com.example.assignment3networking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class LoremSpaceRepository {

    public LoremSpaceRepository() {
    }

    // Interface is a "contract" where the implementation agrees to make the method in certain way.
    interface IPicture {
        void Callback(Bitmap bitmap);
    }

    public void GetPicture(String _url, IPicture iPicture) {
        // Networking needs to be done in a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(_url);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    InputStream inputStream = httpsURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    // Doing the UI update in separate thread:
                    MySingleton.getInstance().postRunnable(new Runnable() {
                      @Override
                        public void run() {
                            iPicture.Callback(bitmap);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
