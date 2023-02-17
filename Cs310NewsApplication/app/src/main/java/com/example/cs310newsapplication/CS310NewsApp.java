package com.example.cs310newsapplication;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CS310NewsApp extends Application {
    ExecutorService srv = Executors.newCachedThreadPool();

}
