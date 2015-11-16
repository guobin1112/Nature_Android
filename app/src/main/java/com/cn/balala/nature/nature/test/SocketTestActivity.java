package com.cn.balala.nature.nature.test;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cn.balala.nature.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class SocketTestActivity extends AppCompatActivity {

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket("http://121.42.156.36:3700");
        } catch (URISyntaxException e) {
        }
    }

    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_test);

        mSocket.on("test", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showSimpleDialog();
                    }
                });
            }
        });
        mSocket.connect();

        btnTest = (Button) findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSocket.emit("test", new Object());
            }
        });

    }

    private void showSimpleDialog() {
        new AlertDialog.Builder(SocketTestActivity.this)
                .setTitle("测试")
                .setMessage("Socket测试成功！")
                .setPositiveButton("确认", null)
                .show();
    }

}
