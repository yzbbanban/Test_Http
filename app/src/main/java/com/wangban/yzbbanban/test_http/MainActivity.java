package com.wangban.yzbbanban.test_http;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //    @ViewInject(R.id.et_compNo)
    private EditText etCompNo;
    //    @ViewInject(R.id.et_userID)
    private EditText etUserID;
    //    @ViewInject(R.id.et_userPwd)
    private EditText etUserPwd;
    //    @ViewInject(R.id.et_checkKey)
    private EditText etCheckKey;
    //    @ViewInject(R.id.btn_login)
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        initView();
        setListeners();
    }

    private void initView() {
        etCompNo = (EditText) findViewById(R.id.et_compNo);
        etUserID = (EditText) findViewById(R.id.et_userID);
        etUserPwd = (EditText) findViewById(R.id.et_userPwd);
        etCheckKey = (EditText) findViewById(R.id.et_checkKey);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }

    private void setListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String compNo = etCompNo.getText().toString().trim();
                String userID = etUserID.getText().toString();
                String userPwd = etUserPwd.getText().toString();
                String checkKey = etCheckKey.getText().toString();
                LogInfo logInfo = new LogInfo();
                logInfo.setCompNo(compNo);
                logInfo.setUserID(userID);
                logInfo.setUserPwd(userPwd);
                logInfo.setCheckKey(checkKey);
                sendPost(logInfo);
            }
        });
    }

    private void sendPost(final LogInfo logInfo) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                String path = "http://fleet01.elocation.com.cn/webservice/ws_blogin.asmx/loginCheck%20";
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setRequestProperty("Content-Length", "length");

                    //CompNo= string &UserID= string &UserPwd= string &CheckKey= string
                    OutputStream os = conn.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
                    StringBuffer sb = new StringBuffer();
                    sb.append("CompNo=" + logInfo.getCompNo());
                    sb.append("&UserID=" + logInfo.getUserID());
                    sb.append("&UserPwd=" + logInfo.getUserPwd());
                    sb.append("&CheckKey=" + logInfo.getCheckKey());
                    osw.write(sb.toString());
                    osw.flush();
                    osw.close();

                    InputStream is = conn.getInputStream();
                    BufferedReader reader1 = new BufferedReader(new InputStreamReader(is, "utf-8"));
                    StringBuffer s = new StringBuffer();
                    String line = "";
                    while ((line = reader1.readLine()) != null) {
                        s.append(line);
                    }
                    return s.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                Log.i("supergirl", "onPostExecute: "+s);
            }
        }.execute();
    }
}
