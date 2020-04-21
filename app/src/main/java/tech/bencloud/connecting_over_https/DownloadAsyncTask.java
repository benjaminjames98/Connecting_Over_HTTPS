package tech.bencloud.connecting_over_https;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadAsyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        try {
            return downloadURL(urls[0]);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        MainActivity.setPageContent(result);
    }

    public String readInputStream(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    private String downloadURL(String theURL) throws IOException {
        InputStream is = null;
        int maxContentLength = 500;

        try {
            URL url = new URL(theURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(15000);
            con.setReadTimeout(10000);
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            int response = con.getResponseCode();
            System.out.println("Web page response is: " + response);

            is = con.getInputStream();
            return readInputStream(is, maxContentLength);
        } finally {
            if (is != null) is.close();
        }
    }


}
