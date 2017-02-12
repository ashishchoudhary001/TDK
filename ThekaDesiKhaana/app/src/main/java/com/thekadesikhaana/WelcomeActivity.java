package com.thekadesikhaana;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.thekadesikhaana.MenuFragment.ARG_MENU;

/*
 * Created by ashishchoudhary on 05/02/17.
 */
public class WelcomeActivity extends Activity {

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        WelcomeActivity.DownloadMenuTask downloadMenuTask = new WelcomeActivity.DownloadMenuTask();
        downloadMenuTask.execute();
    }

    private class DownloadMenuTask extends AsyncTask<String,Void,String> {

        OkHttpClient client;
        Response response = null;

        @Override
        protected String doInBackground(String... params) {
            try {
                client  = new OkHttpClient();
                response = run("http://107.23.59.43:8787/thekadesi/menu/today");
                return response.body().string();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String responseString) {
            super.onPostExecute(responseString);

            try {

//                System.out.println(responseString);

                JSONObject jsonObject = new JSONObject(responseString);

                JSONObject bengaliJson = (JSONObject) jsonObject.get("Bengali");

                System.out.println(bengaliJson.get("name"));

                JSONArray bengaliMenuItems = (JSONArray) bengaliJson.get("menuItems");

                for(int i=0; i<bengaliMenuItems.length(); i++){
                    JSONObject jsonObj  = bengaliMenuItems.getJSONObject(i);
                    System.out.println(jsonObj.getString("items"));
                    System.out.println(jsonObj.getString("urlMobile"));

                    WelcomeActivity.DownloadMenuImageTask menuImageTask = new WelcomeActivity.DownloadMenuImageTask();
                    menuImageTask.execute(jsonObj.getString("urlMobile"));
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        Response run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                response = client.newCall(request).execute();
                return response;
            } catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }



    private class DownloadMenuImageTask extends AsyncTask<String,Void,Bitmap> {


        OkHttpClient client;
        Response response = null;

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                client  = new OkHttpClient();
                return run(params[0]);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            System.out.println("Downloaded bitmap");

            MenuParcelable menuParcelable = new MenuParcelable();

            ArrayList<MenuItemModel> data = menuParcelable.menuItem;

            try {
                for (int i = 0; i < 10; i++) {
                    data.add(new MenuItemModel("rice, chapati, daal, gulab jamun, curd, salad, juice, mutton, biryani, chai", false, i, MenuItemModel.convert(BitmapFactory.decodeResource(getResources(),
                            R.drawable.food_one))));
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }


            bundle = new Bundle();
            bundle.putParcelable(ARG_MENU,menuParcelable);

            Intent intent = new Intent(getApplicationContext(), MenuViewPagerActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

        Bitmap run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Bitmap bitmap = null;

            try {
                response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    try {
                        bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                }
                return bitmap;
            } catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }
    }
}
