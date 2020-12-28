package com.android.mypeople;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class JoinUsAddActivity extends AppCompatActivity {

    final static String TAG = "JoinAddActivity";
    String urlAddr = null;
    Intent intent;
    String registId,macIP,test,test2,test3;
    String uPw, uName, uTel,uPwCheck;
    EditText et_pw,et_pwcheck,et_tel,et_name;
    TextView tv_id,tv_pwcheck,tv_pw;
    Button btn_continue;
    InputMethodManager inputMethodManager ;
    LinearLayout ll_hide;
    String pwVaildation = "^.*(?=^.{8,20}$)(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
    String telVaildation = "^\\d{3}-\\d{3,4}-\\d{4}$";
    String nameVaildation = "^[a-zA-Zㄱ-ㅎ가-힣]+$";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinusadd);

        intent = getIntent();
        registId = intent.getStringExtra("et_idsend");

        Log.v(TAG, "registId : " + registId);
        macIP = intent.getStringExtra("macIP");

        Log.v(TAG, "macIP : " + macIP);
        urlAddr = "http://" + macIP + ":8080/mypeople/join.jsp?"; // 물음표 뒤로 데이터를 붙여 날아감.


        // 앞 페이지에서 받은 아이디(이메일) 띄워주기
        tv_id = findViewById(R.id.joinadd_tv_id);
        tv_id.setText(registId);

        tv_pwcheck = findViewById(R.id.joinadd_tv_pwcheck);
        tv_pw = findViewById(R.id.joinadd_tv_pw);

        // 연결
        et_pw = findViewById(R.id.joinadd_et_pw);

        et_pwcheck = findViewById(R.id.joinadd_et_pwcheck);
        et_tel = findViewById(R.id.joinadd_et_tel);
        et_name = findViewById(R.id.joinadd_et_name);

        // 입력시 자릿수 제한
        et_pw.setFilters(new InputFilter[] {new InputFilter.LengthFilter(20)});
        et_tel.setFilters(new InputFilter[] {new InputFilter.LengthFilter(13)});
        et_pwcheck.setFilters(new InputFilter[] {new InputFilter.LengthFilter(20)});
        et_name.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});


        btn_continue = findViewById(R.id.joinadd_btn_continue);
        btn_continue.setOnClickListener(onClickListener);





        ////////////////////////////////////////////////////////////////////////
        // 비밀번호 유효성검사                                                     //
        //                                                                    //
        //                                                                    //
        ////////////////////////////////////////////////////////////////////////
        //영어 대소문자가 한개이상 들어가 있는가 ?  숫자가 한개이상 들어가 있는가 ?? 특수문자가 한개이상 들어가 있는가 ?
        //영어부터 숫자 특수문자를 입력 받을것이고,  8개 이상 10개 이하 의숫자를 받아야 한다.


        et_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void afterTextChanged(Editable s) {
                test = et_pw.getText().toString().trim();
                if(test.matches(pwVaildation) && test.length() > 0 ){
                    tv_pw.setText("사용 가능합니다.");
                } else{
                    tv_pw.setText("");
                }


            }
        });






        ////////////////////////////////////////////////////////////////////////
        // 비밀번호 일치 확인 리스너                                                 //
        //                                                                    //
        //                                                                    //
        ////////////////////////////////////////////////////////////////////////
        et_pwcheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(et_pw.getText().toString().equals(et_pwcheck.getText().toString())){
                    tv_pwcheck.setText("일치합니다.");
                }
                else
                    tv_pwcheck.setText("일치하지 않습니다");
            }
        });










        ////////////////////////////////////////////////////////////////////////
        // 자동 하이픈                                                           //
        //                                                                    //
        //                                                                    //
        ////////////////////////////////////////////////////////////////////////
//        et_tel.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        et_tel.addTextChangedListener(new PhoneNumberFormattingTextWatcher());







        ////////////////////////////////////////////////////////////////////////
        // 화면터치시 자판 내려감                                                   //
        //                                                                    //
        //                                                                    //
        ////////////////////////////////////////////////////////////////////////
        ll_hide = findViewById(R.id.joinadd_ll_hide);
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);  //OS에서 지원해주는 메소드이다.
        ll_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMethodManager.hideSoftInputFromWindow(ll_hide.getWindowToken(),0);
            }
        });
    }




    ////////////////////////////////////////////////////////////////////////
    // 버튼 클릭 리스너                                                  //
    //                                                                    //
    //                                                                    //
    ////////////////////////////////////////////////////////////////////////

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            test = et_pw.getText().toString().trim();
            test2 = et_tel.getText().toString().trim();
            test3 = et_name.getText().toString().trim();
            /////////////////////////////////////
            uName = et_name.getText().toString();
            uPw = et_pw.getText().toString();
            uPwCheck = et_pwcheck.getText().toString();
            uTel = et_tel.getText().toString();
            Log.v(TAG,"Email ? " + uName);







            if(!test.matches(pwVaildation) || test.equals("") || !uPw.equals(uPwCheck)) {
                new AlertDialog.Builder(JoinUsAddActivity.this)
                        .setTitle("패스워드를 확인하세요")
                        .setPositiveButton("확인", null)
                        .show();

            }else if(!test2.matches(telVaildation) || test2.equals("")) {
                new AlertDialog.Builder(JoinUsAddActivity.this)
                        .setTitle("전화번호를 확인하세요")
                        .setPositiveButton("확인", null)
                        .show();
            }else if(!test3.matches(nameVaildation) ||test3.equals("") ){
                new AlertDialog.Builder(JoinUsAddActivity.this)
                        .setTitle("이름을 확인하세요")
                        .setPositiveButton("확인", null)
                        .show();


            } else {
            urlAddr = urlAddr + "id=" + registId + "&pw=" + uPw + "&name=" + uName + "&tel=" + uTel;
            Log.v(TAG, "registId = " + registId);
            connectInsertData();

//            // 실행이 안됨. 확인 필요
//           Toast.makeText(JoinUsAddActivity.this, registId + "님 가입을 축하합니다!", Toast.LENGTH_LONG).show();



            intent = new Intent(JoinUsAddActivity.this, LoginActivity.class);

            startActivity(intent);

            }

        }
    };









    private void connectInsertData(){
        try{
            CUDNetworkTask insetnetworkTask = new CUDNetworkTask(JoinUsAddActivity.this, urlAddr);
            insetnetworkTask.execute().get();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}