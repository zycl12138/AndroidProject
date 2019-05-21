package com.example.administrator.m123;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import tool.SendGet;

import static tool.StringDeal.StringDeal;

public class MainActivity extends AppCompatActivity{
    ImageButton myhome;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        myhome=(ImageButton)findViewById(R.id.myhome);
        myhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Myhome.class);
                startActivity(intent);
            }
        });
    }
    private void send(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = SendGet.SendGet("/QueryServlet/vedio01", "vid=1");
                    txt.setText(s);
                    String[] ss = StringDeal(s);
                    for(int i = 0;i < ss.length;i++){
                        System.out.println(ss[i]);
                        txt.append(ss[i]);
                    }
                } catch (Exception e) {
                    //txt.append("出错！");
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                try {
                    String s = SendGet.SendGet("/QueryServlet/collect", "id=1");
                    txt.setText(s);
                } catch (IOException e) {
                    txt.setText("错误");
                    //e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }*/
}
