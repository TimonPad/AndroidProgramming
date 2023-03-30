package com.example.assignment3networking;


import android.os.Handler;
import android.os.Looper;

public class MySingleton {
    private static MySingleton mySingleton;
    private Handler handler;
    private LoremSpaceRepository loremSpaceRepository;

    private MySingleton() {
        // Get the handle for the main thread for UI operations
        handler = new Handler(Looper.getMainLooper());
        loremSpaceRepository = new LoremSpaceRepository();
    }

    public LoremSpaceRepository getLoremSpaceRepository() {
        return loremSpaceRepository;
    }

    public void postRunnable(Runnable runnable) {
        handler.post(runnable);
    }

    // Always get the same instance of the same class
    public static MySingleton getInstance() {
        if (mySingleton == null) {
            synchronized (MySingleton.class) {
                if (mySingleton == null) {
                    mySingleton = new MySingleton();
                }
            }
        }
        return mySingleton;
    }
}
