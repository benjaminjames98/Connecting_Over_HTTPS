package tech.bencloud.connecting_over_https;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static EditText urlET;
    private static TextView pageContentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlET = findViewById(R.id.urlEditText);
        pageContentTV = findViewById(R.id.pageContentTextView);
    }

    public void onClickHandler(View view) {
        String urlString = urlET.getText().toString();

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if ((nInfo != null) && nInfo.isConnected())
            new DownloadAsyncTask().execute(urlString);
        else pageContentTV.setText(R.string.no_network_msg);
    }

    public static void setPageContent(String pageContent) {
        System.out.println("Got: " + pageContent);
        pageContentTV.setText(pageContent);
        pageContentTV.invalidate();
    }

}
