package com.example.norara;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";
    public String dataKey = "GJqkuUGCkeIfQcR9XAbsy6FcXt1Udf1TP%2BLWMEo%2BthYn22SdX2reEs0raWrOSUKLEit12kzwJSe3a%2B3cDEMKrQ%3D%3D";
    private String requestUrl;
    ArrayList<Item> list = null;
    Item festival = null;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }
    @SuppressLint("StaticFieldLeak")
    public class MyAsyncTask extends AsyncTask<String, Void, String>{

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?serviceKey=GJqkuUGCkeIfQcR9XAbsy6FcXt1Udf1TP%2BLWMEo%2BthYn22SdX2reEs0raWrOSUKLEit12kzwJSe3a%2B3cDEMKrQ%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&arrange=P&listYN=Y&areaCode=&sigunguCode=&eventStartDate=20170901&eventEndDate=&modifiedtime=&" + dataKey+ "&cat1=A02";
            try{
                boolean f_title = false;
                boolean f_add1 = false;
                boolean f_eventstartdate = false;
                boolean f_eventenddate = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, StandardCharsets.UTF_8));

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item") & festival != null) {
                                list.add(festival);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("item")) {
                                festival = new Item();
                            }
                            if (parser.getName().equals("title")) f_title = true;
                            if (parser.getName().equals("add1")) f_add1 = true;
                            if (parser.getName().equals("eventstartdate")) f_eventstartdate = true;
                            if (parser.getName().equals("eventenddate")) f_eventenddate = true;
                            break;
                        case XmlPullParser.TEXT:
                            if (f_title) {
                                festival.setTitle(parser.getText());
                                f_title = false;
                            } else if (f_add1) {
                                festival.setAdd1(parser.getText());
                                f_add1 = false;
                            } else if (f_eventstartdate) {
                                festival.setEventstartdate(parser.getText());
                                f_eventstartdate = false;
                            } else if (f_eventenddate) {
                                festival.setEventenddate(parser.getText());
                                f_eventenddate = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }


                }catch (Exception e){
                e.printStackTrace();

            }

            return null;
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);

            recyclerAdapter adapter = new recyclerAdapter(getApplicationContext(),list);
            recyclerView.setAdapter(adapter);


        }

    }



}