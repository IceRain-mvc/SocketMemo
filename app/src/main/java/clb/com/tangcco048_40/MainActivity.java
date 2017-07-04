package clb.com.tangcco048_40;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView2);
    }

    public void sendMessage(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //1:连接到服务器
                try {
                    Socket socket = new Socket("192.168.1.167",9001);

                    //输出流
                    OutputStream outputStream = socket.getOutputStream();

                    outputStream.write("嘿嘿".getBytes("utf-8"));
                    outputStream.flush();

                    //接收服务器发送的消息
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    final String result = reader.readLine();
                    //handler  异步  .post Rxjava
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.append("\r\n" + result);
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }
                //2:发送消息
            }
        }).start();
    }
}
