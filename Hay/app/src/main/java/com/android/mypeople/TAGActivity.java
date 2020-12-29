package com.android.mypeople;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TAGActivity extends AppCompatActivity {

    String urlAddr = null;
    String urlAddr1 = null;
    String tag1,tag2,tag3,tag4,tag5;
    EditText Etag1,Etag2,Etag3,Etag4,Etag5;
    Button cancelBtn,insertBtn;
    Bean_tag bean_tag=null;
    int seq;
    String macIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        Intent intent = new Intent();
        intent = getIntent();
        seq = intent.getIntExtra("uSeqno",0);
        macIP = intent.getStringExtra("macIP");

        urlAddr = "http://"+macIP+":8080/mypeople/tagActivitySelect.jsp?";

        Etag1 = findViewById(R.id.tag_edit_tagEdit1);
        Etag2 = findViewById(R.id.tag_edit_tagEdit2);
        Etag3 = findViewById(R.id.tag_edit_tagEdit3);
        Etag4 = findViewById(R.id.tag_edit_tagEdit4);
        Etag5 = findViewById(R.id.tag_edit_tagEdit5);
        findViewById(R.id.tag_insert_insertBtn).setOnClickListener(mClickListener);
        findViewById(R.id.tag__cancel_cancelBtn).setOnClickListener(mClickListener);

    }
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tag_insert_insertBtn:
                    tag1=Etag1.getText().toString();
                    tag2=Etag2.getText().toString();
                    tag3=Etag3.getText().toString();
                    tag4=Etag4.getText().toString();
                    tag5=Etag5.getText().toString();
                    if(Etag1.getText().toString().length()==0) tag1="no";
                    if(Etag2.getText().toString().length()==0) tag2="no";
                    if(Etag3.getText().toString().length()==0) tag3="no";
                    if(Etag4.getText().toString().length()==0) tag4="no";
                    if(Etag5.getText().toString().length()==0) tag5="no";

                    urlAddr1 = "http://192.168.219.100:8080/mypeople/tagActivityUpdate.jsp?tag1="+tag1+"&tag2="+tag2+"&tag3="+tag3+"&tag4="+tag4+"&tag5="+tag5+"&seq="+seq;
                    connectUpdateData();


                    break;
                case R.id.tag__cancel_cancelBtn:
                    Intent intent = new Intent(TAGActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;


            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        connectGetData();
    }
    private void connectGetData(){

        try {
            urlAddr = urlAddr+"seq="+seq;
            TAGNetworkTask tagNetworkTask = new TAGNetworkTask(TAGActivity.this, urlAddr);
            Object obj = tagNetworkTask.execute().get();
            bean_tag = (Bean_tag) obj;

            Etag1.setText(bean_tag.getTag1());
            Etag2.setText(bean_tag.getTag2());
            Etag3.setText(bean_tag.getTag3());
            Etag4.setText(bean_tag.getTag4());
            Etag5.setText(bean_tag.getTag5());
            if(bean_tag.getTag1().equals("no")) Etag1.setText("");
            if(bean_tag.getTag2().equals("no")) Etag2.setText("");
            if(bean_tag.getTag3().equals("no")) Etag3.setText("");
            if(bean_tag.getTag4().equals("no")) Etag4.setText("");
            if(bean_tag.getTag5().equals("no")) Etag5.setText("");


        }catch (Exception e){
            e.printStackTrace();
        }

    }



    private void connectUpdateData(){
        try {
            CUDNetworkTask updateTask = new CUDNetworkTask(TAGActivity.this, urlAddr1);
            updateTask.execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}