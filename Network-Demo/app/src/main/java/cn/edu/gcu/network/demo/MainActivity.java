package cn.edu.gcu.network.demo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    private EditText webUrlText;
    private Handler  handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ipBtn = (Button) findViewById(R.id.btn_check_ip);
        Button webviewBtn = (Button) findViewById(R.id.btn_open_webview);
        webUrlText = (EditText) findViewById(R.id.web_url);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                Toast.makeText(getApplicationContext(),message.getData().getString("msg"),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        ipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String domain = webUrlText.getText().toString();
                CheckNetwork thread = new CheckNetwork(domain,handler);
                thread.start();
            }
        });
        webviewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                WebView web = (WebView) findViewById(R.id.web_view);
                String domain = webUrlText.getText().toString();
                if(!domain.startsWith("http")){
                    domain = "http://"+domain;
                }
                web.loadUrl(domain);
            }

        });

    }

    private class CheckNetwork extends Thread {

        private String domain;
        private Handler handler;

        public CheckNetwork(String domain, Handler handler) {
            this.domain = domain;
            this.handler = handler;
        }

        @Override
        public void run() {
            super.run();
            Message message = new Message();
            try {
                InetAddress address = InetAddress.getByName(domain);
                String str = domain + ":" + address.getHostAddress().toString();
                Bundle bundle = new Bundle();
                bundle.putString("msg", str);
                message.setData(bundle);
            } catch (Exception e) {
                Log.e("Main Activity", "Exception", e);
                String str = "Can not find the address of " + domain;
                Bundle bundle = new Bundle();
                bundle.putString("msg", str);
                message.setData(bundle);
            }
            handler.sendMessage(message);
        }
    }

}
