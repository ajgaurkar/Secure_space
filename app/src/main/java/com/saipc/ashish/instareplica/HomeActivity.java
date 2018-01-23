package com.saipc.ashish.instareplica;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ashish.instareplica.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView imageList;
    CustomListAdapter customListAdapter;
    ArrayList temp_list = new ArrayList();
    ArrayList post_url_list = new ArrayList();
    SessionManager manager;
    private String restResponseString;
    private String fetch_profile_url;
    String accessToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        manager = new SessionManager(getApplicationContext());

        if (!manager.isLoggedIn()) {
            Intent login_intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login_intent);
        }

        imageList = (ListView) findViewById(R.id.lstView);
        imageList.setDividerHeight(5);
//        imageList.setItemsCanFocus(true);
//        imageList.setFocusable(false);
//        imageList.setFocusableInTouchMode(false);
//        imageList.setClickable(false);

//        populate_list();

        imageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                System.out.print("position : " + position);

            }
        });

//        String user_id = SessionManager.KEY_ACCESS_TOKEN;
//        user_id = user_id.substring(0, user_id.indexOf("."));
//
//        fetch_profile_url = "https://api.instagram.com/v1/users/" + user_id + "/media/recent/?access_token=" + SessionManager.KEY_ACCESS_TOKEN;
////            String fetch_profile_url = "https://api.instagram.com/v1/users/512588310/media/recent/?access_token=512588310.c45197d.7b9fdb6968af494293425fdce5ff33f0";

        accessToken = manager.getSpecificBabyDetail(SessionManager.KEY_ACCESS_TOKEN);
        System.out.println("Access token from shared pref " + accessToken);

        new Rest_call().execute();

    }

    private void populate_list() {
        ImageObject img1 = new ImageObject();
        img1.setName("Vivek");
        img1.setUrl("https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/18949487_817252311765092_4011757053717512192_n.jpg");
        img1.setCaption("Caption for Vivek");
        img1.setHashtags("#VivekPost");

        temp_list.add("com 1");
        temp_list.add("comm 2");

        System.out.println("temp_list : " + temp_list);

        img1.setComment_list(temp_list);
//        temp_list.clear();
        img1.setLiked(true);

        ImageObject img2 = new ImageObject();
        img2.setName("Abhijeet");
        img2.setUrl("https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/19933319_110981812885582_6359640091882684416_n.jpg");
        img2.setCaption("Caption for Abhijeet");
        img2.setHashtags("#AbhijeetPost");
        img2.setComment_list(temp_list);

        img2.setLiked(false);

        ImageObject img3 = new ImageObject();
        img3.setName("Ketan");
        img3.setUrl("https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20067370_114459255863344_3887307936774488064_n.jpg");
        img3.setCaption("Caption for Ketan");
        img3.setHashtags("#KetanPost");
        img3.setComment_list(temp_list);
        img3.setLiked(false);

        ImageObject img4 = new ImageObject();
        img4.setName("Koninika");
        img4.setUrl("https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20065521_106162250047171_8398106595053010944_n.jpg");
        img4.setCaption("Caption for Koninika");
        img4.setHashtags("#KoninikaPost");
        img4.setComment_list(temp_list);
        img4.setLiked(false);

        ImageObject img5 = new ImageObject();
        img5.setName("Aniket");
        img5.setUrl("https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20065467_1932762120306201_5338133883474935808_n.jpg");
        img5.setCaption("Caption for Aniket");
        img5.setHashtags("#AniketPost");
        img5.setComment_list(temp_list);
        img5.setLiked(false);

        ImageObject img6 = new ImageObject();
        img6.setName("Siddhant");
        img6.setUrl("https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/19985805_1455270814552692_3742638221176078336_n.jpg");
        img6.setCaption("Caption for Siddhant");
        img6.setHashtags("#SiddhantPost");
        img6.setComment_list(temp_list);
        img6.setLiked(false);

        ArrayList<ImageObject> objectArrayList = new ArrayList<>();

        objectArrayList.add(img1);
        objectArrayList.add(img2);
        objectArrayList.add(img3);
        objectArrayList.add(img4);
        objectArrayList.add(img5);
        objectArrayList.add(img6);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        customListAdapter = new CustomListAdapter(objectArrayList, this, width * 90 / 100);
        imageList.setAdapter(customListAdapter);
    }

    private class Rest_call extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {


//            String fetch_profile_url = "https://api.instagram.com/v1/users/512588310/media/recent/?access_token=512588310.c45197d.7b9fdb6968af494293425fdce5ff33f0";
            String fetch_profile_url = "https://api.instagram.com/v1/users/512588310/media/recent/?access_token=" + accessToken;
            Log.d("fetch_profile_url: ", "> " + fetch_profile_url);

            ServiceHandler sh = new ServiceHandler();
            restResponseString = sh.makeServiceCall(fetch_profile_url, ServiceHandler.GET);
            Log.d("Response: ", "> " + restResponseString);


            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            parse_json(restResponseString);

        }
    }

    private void parse_json(String restResponseString) {

        System.out.println(" IN PARSE JASON");

        JSONObject mainObject = null;
        try {
            restResponseString = "{\"pagination\": {}, \"data\": [{\"id\": \"1560625436576069407_512588310\", \"user\": \n" +
                    "{\"id\": \"512588310\", \"full_name\": \"Ajinkya Gaurkar\", \"profile_picture\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/19226958_123469111574814_2507406756422877184_a.jpg\", \"username\": \"ajgaurkar\"}, \"images\": {\"thumbnail\": {\"width\": 150, \"height\": 150, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/18949487_817252311765092_4011757053717512192_n.jpg\"}, \"low_resolution\": {\"width\": 320, \"height\": 320, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/18949487_817252311765092_4011757053717512192_n.jpg\"}, \"standard_resolution\": {\"width\": 640, \"height\": 640, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/18949487_817252311765092_4011757053717512192_n.jpg\"}}, \"created_time\": \"1500261071\", \"caption\": {\"id\": \"17863006054174657\", \"text\": \"Daytwo Daytime Sunday funday #niagara #family \\ud83d\\ude0d\\ud83d\\ude0d\", \"created_time\": \"1500261071\", \"from\": {\"id\": \"512588310\", \"full_name\": \"Ajinkya Gaurkar\", \"profile_picture\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/19226958_123469111574814_2507406756422877184_a.jpg\", \"username\": \"ajgaurkar\"}}, \"user_has_liked\": false, \"likes\": {\"count\": 33}, \"tags\": [\"niagara\", \"family\"], \"filter\": \"Normal\", \"comments\": {\"count\": 0}, \"type\": \"carousel\", \"link\": \"https://www.instagram.com/p/BWodJDmjjcfaQSd7WjEfid_QTBvZj_qGAlmYJw0/\", \"location\": {\"latitude\": 43.0943, \"longitude\": -79.0377, \"name\": \"Niagara Falls, New York\", \"id\": 324081739}, \"attribution\": null, \"users_in_photo\": [], \"carousel_media\": [{\"images\": {\"thumbnail\": {\"width\": 150, \"height\": 150, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/18949487_817252311765092_4011757053717512192_n.jpg\"}, \"low_resolution\": {\"width\": 320, \"height\": 320, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/18949487_817252311765092_4011757053717512192_n.jpg\"}, \"standard_resolution\": {\"width\": 640, \"height\": 640, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/18949487_817252311765092_4011757053717512192_n.jpg\"}}, \"users_in_photo\": [], \"type\": \"image\"}, {\"images\": {\"thumbnail\": {\"width\": 150, \"height\": 150, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20067370_114459255863344_3887307936774488064_n.jpg\"}, \"low_resolution\": {\"width\": 320, \"height\": 320, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/20067370_114459255863344_3887307936774488064_n.jpg\"}, \"standard_resolution\": {\"width\": 640, \"height\": 640, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20067370_114459255863344_3887307936774488064_n.jpg\"}}, \"users_in_photo\": [], \"type\": \"image\"}, {\"images\": {\"thumbnail\": {\"width\": 150, \"height\": 150, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20065521_106162250047171_8398106595053010944_n.jpg\"}, \"low_resolution\": {\"width\": 320, \"height\": 320, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/20065521_106162250047171_8398106595053010944_n.jpg\"}, \"standard_resolution\": {\"width\": 640, \"height\": 640, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20065521_106162250047171_8398106595053010944_n.jpg\"}}, \"users_in_photo\": [], \"type\": \"image\"}, {\"images\": {\"thumbnail\": {\"width\": 150, \"height\": 150, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/20065467_1932762120306201_5338133883474935808_n.jpg\"}, \"low_resolution\": {\"width\": 320, \"height\": 320, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/20065467_1932762120306201_5338133883474935808_n.jpg\"}, \"standard_resolution\": {\"width\": 640, \"height\": 640, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/20065467_1932762120306201_5338133883474935808_n.jpg\"}}, \"users_in_photo\": [], \"type\": \"image\"}, {\"images\": {\"thumbnail\": {\"width\": 150, \"height\": 150, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/19985805_1455270814552692_3742638221176078336_n.jpg\"}, \"low_resolution\": {\"width\": 320, \"height\": 320, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/19985805_1455270814552692_3742638221176078336_n.jpg\"}, \"standard_resolution\": {\"width\": 640, \"height\": 640, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/19985805_1455270814552692_3742638221176078336_n.jpg\"}}, \"users_in_photo\": [], \"type\": \"image\"}]},\n" +
                    " {\"id\": \"1448119907991437620_512588310\", \"user\": {\"id\": \"512588310\", \"full_name\": \"Ajinkya Gaurkar\", \"profile_picture\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/19226958_123469111574814_2507406756422877184_a.jpg\", \"username\": \"ajgaurkar\"}, \"images\": {\"thumbnail\": {\"width\": 150, \"height\": 150, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s150x150/e35/c135.0.810.810/16583789_387074898325086_7949675192890949632_n.jpg\"}, \"low_resolution\": {\"width\": 320, \"height\": 240, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s320x320/e35/16583789_387074898325086_7949675192890949632_n.jpg\"}, \"standard_resolution\": {\"width\": 640, \"height\": 480, \"url\": \"https://scontent.cdninstagram.com/t51.2885-15/s640x640/sh0.08/e35/16583789_387074898325086_7949675192890949632_n.jpg\"}}, \"created_time\": \"1486849367\", \"caption\": {\"id\": \"17872186375002694\", \"text\": \"#navalheritage #downtownbaltimore#freezingcold\", \"created_time\": \"1486849367\", \"from\": {\"id\": \"512588310\", \"full_name\": \"Ajinkya Gaurkar\", \"profile_picture\": \"https://scontent.cdninstagram.com/t51.2885-19/s150x150/19226958_123469111574814_2507406756422877184_a.jpg\", \"username\": \"ajgaurkar\"}}, \"user_has_liked\": false, \"likes\": {\"count\": 13}, \"tags\": [\"freezingcold\", \"navalheritage\", \"downtownbaltimore\"], \"filter\": \"Normal\", \"comments\": {\"count\": 1}, \"type\": \"image\", \"link\": \"https://www.instagram.com/p/BQYwWJEAEE0KPPKRwa19PJRYjsqc4JVC-fd9DA0/\", \"location\": null, \"attribution\": null, \"users_in_photo\": []}], \"meta\": {\"code\": 200}}";

            mainObject = new JSONObject(restResponseString);
            JSONArray jsonArray = mainObject.getJSONArray("data");

            System.out.println(" jsonArray jsonArray " + jsonArray);

            for (int j = 0; j < jsonArray.length(); j++) {
                JSONObject posts_object = jsonArray.getJSONObject(j);
                System.out.println(" posts_object  " + posts_object);

                JSONArray media_array = posts_object.getJSONArray("carousel_media");
                System.out.println(" media_array " + media_array);

                for (int i = 0; i < media_array.length(); i++) {
                    JSONObject carousel_media = media_array.getJSONObject(i);
                    System.out.println(" carousel_media " + carousel_media);

                    JSONObject img_media = carousel_media.getJSONObject("images");
                    System.out.println(" img_media " + img_media);

                    JSONObject std_res_obj = img_media.getJSONObject("standard_resolution");
                    String url = std_res_obj.getString("url");
                    System.out.println(" carousel_media url " + url);
                }
            }
//            String uniName = uniObject.getJsonString("name");
//            String uniURL = uniObject.getJsonString("url");

//            JSONObject oneObject = mainObject.getJSONObject("1");
//            String id = oneObject.getJsonString("id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        populate_list();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        //condition to enable/disable options depending on current page
        getMenuInflater().inflate(R.menu.secu_space_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options_logout:
                manager.logoutUser();

                delete_app_data();

                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void delete_app_data() {

        try {
            // clearing app data
            String packageName = getApplicationContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear " + packageName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
