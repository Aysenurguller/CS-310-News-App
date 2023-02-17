package com.example.cs310newsapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewsRepository {
    public  void getAllData(ExecutorService srv, Handler uiHandler){
        srv.execute(()->{
            try{
                URL url = new URL("http://10.3.0.14:8080/newsapp/getall");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line="";
                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }


                JSONObject response = new JSONObject(buffer.toString());
                JSONArray arr = response.getJSONArray("items");


                List<News> data = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    News newObj = new News(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("date"),
                            current.getString("image"),
                            current.getString("categoryName"));
                    data.add(newObj);

                    Message msg = new Message();
                    msg.obj = data;
                    uiHandler.sendMessage(msg);

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });
    }
    public  void getDataById(ExecutorService srv, Handler uiHandler, int id){
        srv.execute(()->{
            try{
                URL url = new URL("http://10.3.0.14:8080/newsapp/getnewsbyid/"+String.valueOf(id));
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line="";
                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject response = new JSONObject(buffer.toString());
                JSONArray arr = response.getJSONArray("items");
                JSONObject current = arr.getJSONObject(0);



                News newObj = new News(current.getInt("id"),
                        current.getString("title"),
                        current.getString("text"),
                        current.getString("date"),
                        current.getString("image"),
                        current.getString("categoryName"));


                Message msg = new Message();
                msg.obj = newObj;
                uiHandler.sendMessage(msg);





            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

    }

    public void getNewsByCategory(ExecutorService srv, Handler uiHandler, int id)
    {
        srv.execute(()->{
            try{
                URL url = new URL("http://10.3.0.14:8080/newsapp/getbycategoryid/"+String.valueOf(id));
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line="";
                while((line=reader.readLine())!=null){

                    buffer.append(line);

                }

                JSONObject response = new JSONObject(buffer.toString());
                JSONArray arr = response.getJSONArray("items");


                List<News> data = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject current = arr.getJSONObject(i);

                    News newObj = new News(current.getInt("id"),
                            current.getString("title"),
                            current.getString("text"),
                            current.getString("date"),
                            current.getString("image"),
                            current.getString("categoryName"));
                    data.add(newObj);

                    Message msg = new Message();
                    msg.obj = data;
                    uiHandler.sendMessage(msg);

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        });

    }
    public void downloadImage(ExecutorService srv, android.os.Handler uiHandler, String path) {
        srv.execute(() -> {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream());

                Message msg = new Message();
                msg.obj = bitmap;
                uiHandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
