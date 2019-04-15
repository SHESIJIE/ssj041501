package shesijie.bawei.com.ssj041501;

import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncHttpUrl {
    private static final AsyncHttpUrl httpUrl = new AsyncHttpUrl ();

    public static final AsyncHttpUrl getInstance() {
        return httpUrl;
    }
    public void PostAsync(String url, String name, String pass, final AsyncCallBack asyncCallBack){
        new AsyncTask<String,Void,String> (){

            @Override
            protected String doInBackground(String... strings) {
                return PostData(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                if (!TextUtils.isEmpty ( s )) {
                    asyncCallBack.onSuccess ( s );
                } else {
                    asyncCallBack.onError ( 540,"没有获取到数据" );
                }
            }
        }.execute ( url ,name,pass);
    }

    private String PostData(String url) {
        try {
            URL url1 = new URL ( url );
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection ();
            connection.setConnectTimeout ( 5000 );
            connection.setReadTimeout ( 5000 );
            connection.setRequestMethod ( "Post" );
            if (connection.getResponseCode () == 200) {
                InputStream inputStream = connection.getInputStream();
                String s = new String(ByteStreams.toByteArray(inputStream));
                Log.d("TAG", "getDataHttp: " + s);
                return s; }
        } catch (MalformedURLException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        return null;
    }

    public interface AsyncCallBack{
        void onSuccess(String result);
        void onError(int errorCode,String messagr);
    }
}
