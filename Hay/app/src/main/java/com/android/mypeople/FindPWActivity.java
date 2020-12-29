package com.android.mypeople;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FindPWActivity extends AppCompatActivity {

    EditText editFindId;
    TextView tv;
    String userid;
    String urlAddrFindPwCount = null;
    String urlAddrPwUpdate = null;
    String macIP;

    int findPwCount=0;
    int getFindPwCount=0;

    SendMail sendMail = new SendMail();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);

        Intent intent = new Intent();
        intent = getIntent();
        macIP = intent.getStringExtra("macIP");


        editFindId = findViewById(R.id.findpw_et_id);
        tv = findViewById(R.id.findpw_tv_idcheck);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build()

        );

        findViewById(R.id.findpw_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userid = editFindId.getText().toString();
                urlAddrFindPwCount = "http://"+macIP+":8080/mypeople/findUserPw.jsp?userid="+userid;
                Log.v("여기","urlAddrFindPwCount : " + urlAddrFindPwCount);
                getFindPwCount =  getFindCount();
                // 아이디가 없으면
                if(getFindCount()==0){

                    tv.setVisibility(View.VISIBLE);

                }else{

                    tv.setVisibility(View.INVISIBLE);
                    SendMail mailServer = new SendMail();
                    Log.v("여기","sendMail");
                    //-----------------------------------메일보내기-----------------------------------
                    try {
                        Log.v("여기","sendMailtry");
                        mailServer.sendSecurityCode(getApplicationContext(), userid);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //-----------------------------------메일보내기-----------------------------------

                    Log.v("여기","mailServer");
                    Log.v("여기",sendMail.pwCode);
                    urlAddrPwUpdate = "http://"+macIP+":8080/mypeople/finduserPwUpdate_email.jsp?userid="+userid+"&userpw="+sendMail.pwCode+"";

                    Log.v("여기",urlAddrPwUpdate);
                    updateUserPw();
                    Intent intent1 = new Intent(FindPWActivity.this,LoginActivity.class);
                    startActivity(intent1);

                }
            }
        });

    }

    private int getFindCount(){
        try {
            NetworkTask_jiseok networkTask_jiseok = new NetworkTask_jiseok(FindPWActivity.this,urlAddrFindPwCount,"findPwCount");
            Object obj = networkTask_jiseok.execute().get();

            findPwCount = (int) obj;
            Log.v("여기","FindPWActivity_getFindCount_findPwCount : " + findPwCount);

        }catch (Exception e){
            e.printStackTrace();
        }
        return findPwCount;

    }
    private void updateUserPw(){
        try {
            CUDNetworkTask updateTask = new CUDNetworkTask(FindPWActivity.this, urlAddrPwUpdate);
            updateTask.execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}