package com.android.mypeople;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    //
    private RecyclerView listView = null;
    private RecyclerView.LayoutManager layoutManager = null;
    private ArrayList<Bean_friendslist> data = null;
    private ArrayList<Bean_tag> tags = null;
    private RecyclerAdapter adapter = null;

    String TAG = "MainActivity";
    BottomAppBar bab;
    boolean isCenter=true;
    Spinner spinner_field = null;
    Class_LMW class_lmw = new Class_LMW();
    LinearLayout linearLayout = null;
    EditText searchText = null;
    Button addBtn = null;
    String macIP;
    String urlAddr = null;
    String urlAddr2 = null;
    CoordinatorLayout outLayout = null;
    int spinnerItmeNum = -1;
    String putExtraSpNum = null;
    ImageView searchBtn = null;
    String where = null;
    String getSearchText = null;
    int userSeqno = 0;
    String action = null;
    String putExtraText = null;
    int dataSize = 0;
    TextView tv_Count = null;

    RadioButton radioBtn_Name = null;
    RadioButton radioBtn_New = null;
    RadioButton radioBtn_Tag = null;
    RadioGroup radioGroup = null;

    // 스피너 (태그 선택 안했을 경우 이미지 필요)
    int tag[] = {R.drawable.tagspinner, R.drawable.main_spinner_tag1, R.drawable.main_spinner_tag2, R.drawable.main_spinner_tag3, R.drawable.main_spinner_tag4, R.drawable.main_spinner_tag5};
    String tagName[] = {"TAG", "TAG1", "TAG2", "TAG3", "TAG4", "TAG5"};
    Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.main_Edit_SearchText);
        tv_Count = findViewById(R.id.main_Text_Count);

        // 값 받기
        Intent intent = getIntent();
        userSeqno = intent.getIntExtra("uSeqno", 0);
        Log.v(TAG, "userSeqno : " + userSeqno);

        // 스피너
        spinner = findViewById(R.id.main_Spinner_tag);

        MainSpinnerAdapter spinnerAdapter = new MainSpinnerAdapter(getApplicationContext(), tag, tagName);
        spinner.setAdapter(spinnerAdapter);
        spinnerItmeNum = spinner.getSelectedItemPosition();

        // 연결 (검색 내용, 정렬순에 따라 jsp 바꿔주기)
        macIP = intent.getStringExtra("macIP");
        urlAddr2 = "http://" + macIP + ":8080/mypeople/tag_select_Tagname.jsp?user_uSeqno=" + userSeqno;
        // 인텐트 받은 액션여부로 나누어 urlAddr 설정하기
        action = intent.getStringExtra("action");

        switch (action){
            case "Show_List":
                where = "select"; // 리스트 불러오는 처음은 select
                urlAddr = "http://" + macIP + ":8080/mypeople/friendslist_Select_All.jsp?user_uSeqno=" + userSeqno;
                Log.v(TAG, "Show_List");
                break;
            case "Search_NoTag": // 태그없이 검색
                putExtraText = intent.getStringExtra("searchText");
                if (putExtraText == null) {
                    putExtraText = "";
                }
                where = "search";
                urlAddr = "http://" + macIP + ":8080/mypeople/friendslist_Search_SearchText.jsp?user_uSeqno=" + userSeqno + "&searchText=" + putExtraText;
                Log.v(TAG, "Search_With_NoTag");
                break;
            case "Search_With_Tag": // 태그 고르고 검색
                putExtraText = intent.getStringExtra("searchText");
                putExtraSpNum = intent.getStringExtra("spinnerPosition");

                if (putExtraText == null) {
                    putExtraText = "";
                }
                where = "search_with_tag";
                urlAddr = "http://" + macIP + ":8080/mypeople/friendslist_Search_With_Tag.jsp?user_uSeqno=" + userSeqno + "&searchText=" + putExtraText + "&fTag=fTag" + putExtraSpNum;
                Log.v(TAG, "Search_With_NoTag");
                break;
        }

        Log.v(TAG, "검색값 : " + getSearchText);
        Log.v(TAG, "spinnerItemNum : " + spinnerItmeNum);
        searchBtn = findViewById(R.id.main_ImgBtn_SearchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerItmeNum = spinner.getSelectedItemPosition();
                getSearchText = searchText.getText().toString();

                switch (spinnerItmeNum){
                    case 0: // 태그 선택없이 검색
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        intent.putExtra("uSeqno", userSeqno);
                        intent.putExtra("action", "Search_NoTag");
                        intent.putExtra("searchText", getSearchText);
                        intent.putExtra("macIP", macIP);
                        startActivity(intent);
                        break;
                    default: // 태그를 선택 후 검색
                        intent = new Intent(MainActivity.this, MainActivity.class);
                        intent.putExtra("macIP", macIP);
                        intent.putExtra("uSeqno", userSeqno);
                        intent.putExtra("action", "Search_With_Tag");
                        intent.putExtra("searchText", getSearchText);
                        intent.putExtra("spinnerPosition", Integer.toString(spinnerItmeNum));

                        Log.v(TAG, "유저넘버 : " + userSeqno);
                        Log.v(TAG, "검색어 : " + getSearchText);
                        Log.v(TAG, "태그 : " + spinnerItmeNum);
                        startActivity(intent);
                }
            }
        });
        Log.v(TAG, "urlAddr : " + urlAddr);

        // 정렬

        //라디오 버튼 설정
        radioBtn_Name = findViewById(R.id.main_Radio_Name);
        radioBtn_New = findViewById(R.id.main_Radio_New);
        radioBtn_Tag = findViewById(R.id.main_Radio_Tag);

        radioBtn_Tag.setOnClickListener(radioButtonClickListener);
        radioBtn_New.setOnClickListener(radioButtonClickListener);
        radioBtn_Tag.setOnClickListener(radioButtonClickListener);
        //라디오 그룹 설정
        radioGroup = findViewById(R.id.main_RadioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);

        // 리사이클러뷰 규격 만들기
        listView = findViewById(R.id.listView_Friends);
        listView.setHasFixedSize(true);

        // Context는 Activity
        adapter = new RecyclerAdapter(MainActivity.this, R.layout.activity_main, data);
        listView.setAdapter(adapter);

        // 레이아웃 매니저 만들기
        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);

        // 툴바 생성
        Toolbar toolbar = (Toolbar)findViewById(R.id.main_toolbar); // 상단 툴바
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        // 검색창 키보드 액션
        searchText.setOnKeyListener(new View.OnKeyListener() { // 엔터 키 눌렀을 경우!(onClick 메소드 사용해서 검색버튼 누르기)
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    finish();
                    return true;
                }
                return false;
            }
        });

        // 홈버튼 액션을 위한 선언
        bab=findViewById(R.id.bab);

        // 배경 선택 시 키보드 내리기 위한 선언
        outLayout = findViewById(R.id.main_coordinator_outer);

        outLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(searchText.getText().toString() != null){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        // 친구 추가 버튼 액션
        addBtn =  findViewById(R.id.main_Btn_AddFriend);
        addBtn.setOnClickListener(onClickListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume urlAddr : " + urlAddr);
        connectGetData();

        // 리스트 클릭 이벤트

        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(MainActivity.this, Main_CustomDialog.class);
                // 뭐가 필요하실지 몰라서 다 보냅니다..
                intent.putExtra("macIP", macIP);
                intent.putExtra("uSeqno", userSeqno);
                intent.putExtra("fSeqno", data.get(position).getfSeqno());
                intent.putExtra("fName", data.get(position).getfName());
                intent.putExtra("fRelation", data.get(position).getfRelation());
                intent.putExtra("fTel", data.get(position).getfTel());
                intent.putExtra("fImage", data.get(position).getfImage());
                intent.putExtra("fImageReal", data.get(position).getfImageReal());
                intent.putExtra("fTag1", data.get(position).getfTag1());
                intent.putExtra("fTag2", data.get(position).getfTag2());
                intent.putExtra("fTag3", data.get(position).getfTag3());
                intent.putExtra("fTag4", data.get(position).getfTag4());
                intent.putExtra("fTag5", data.get(position).getfTag5());
                intent.putExtra("fComment", data.get(position).getfComment());
                intent.putExtra("fAddress", data.get(position).getfAddress());
                intent.putExtra("fEmail", data.get(position).getfEmail());

                Log.v(TAG,"macIP : " + macIP);
                Log.v(TAG, "클릭한 사람 seqno : " + data.get(position).getfSeqno());
                Log.v(TAG, "이름 : " + data.get(position).getfName());
                Log.v(TAG, "관계 : " + data.get(position).getfRelation());
                Log.v(TAG, "이미지 : " + data.get(position).getfImage());
                Log.v(TAG, "실제이미지 : " + data.get(position).getfImageReal());
                Log.v(TAG, "tag1 : " + data.get(position).getfTag1());
                Log.v(TAG, "tag2 : " + data.get(position).getfTag2());
                Log.v(TAG, "tag3 : " + data.get(position).getfTag3());
                Log.v(TAG, "tag4 : " + data.get(position).getfTag4());
                Log.v(TAG, "tag5 : " + data.get(position).getfTag5());
                Log.v(TAG, "코멘트 : " + data.get(position).getfComment());
                Log.v(TAG, "주소 : " + data.get(position).getfAddress());

                startActivity(intent);
            }
        });

        Log.v(TAG, "onResume()");
        Log.v(TAG, "onResume getPosition : " + spinnerItmeNum);

    }

    private void connectGetData(){
        try {
            ///////////////////////////////////////////////////////////////////////////////////////
            // Date : 2020.12.25
            //
            // Description:
            //  - NetworkTask의 생성자 추가 : where <- "select"
            //
            ///////////////////////////////////////////////////////////////////////////////////////
            getSearchText = searchText.getText().toString();
            Log.v(TAG, "connectGetData getSearchText : " + getSearchText);
            ListNetworkTask listNetworkTask= new ListNetworkTask(MainActivity.this, urlAddr, where);


                SelectTagNameTask selectTagNameTask = new SelectTagNameTask(MainActivity.this, urlAddr2, "select");
                Object obj1 = selectTagNameTask.execute().get();
                tags = (ArrayList<Bean_tag>) obj1;
                tagName[1] = tags.get(0).getTag1();
                tagName[2] = tags.get(0).getTag2();
                tagName[3] = tags.get(0).getTag3();
                tagName[4] = tags.get(0).getTag4();
                tagName[5] = tags.get(0).getTag5();


            ///////////////////////////////////////////////////////////////////////////////////////
            Object obj = listNetworkTask.execute().get();
            data = (ArrayList<Bean_friendslist>) obj;

            Log.v(TAG, "data.size() : " + data.size());

            adapter = new RecyclerAdapter(MainActivity.this, R.layout.main_recycler_items, data);
            listView.setAdapter(adapter);

            // 리스트 개수 파악
            Intent intent = getIntent();
            String get = intent.getStringExtra("searchText");
            if(action.equals("Show_List")){ // 전체 개수 보존하기 위함
                dataSize = data.size();
                Log.v(TAG, "dataSize = " + dataSize);
                tv_Count.setText("친구 : " + dataSize + "명");

            }else { // 그외의 액션 시 검색결과로 보여줌
                dataSize = data.size();
                Log.v(TAG, "dataSize = " + dataSize);
                tv_Count.setText("검색결과 : " + dataSize + "명");
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }



//        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
//            Intent intent = null;
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                intent = new Intent(SelectAllActivity.this, UpdateActivity.class);
//                intent.putExtra("code", members.get(i).getCode());
//                intent.putExtra("name", members.get(i).getName());
//                intent.putExtra("dept", members.get(i).getDept());
//                intent.putExtra("phone", members.get(i).getPhone());
//                intent.putExtra("macIP" , macIP);
//                startActivity(intent);
//            }
//        };

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()){
                case R.id.main_Btn_AddFriend:
                    intent = new Intent(MainActivity.this, DetailViewActivity.class);
                    intent.putExtra("uSeqno", userSeqno);
                    intent.putExtra("macIP", macIP);
                    startActivity(intent);
            }
        }
    };

    ////////////////////////////////////////////////////////////
    //                                                        //
    //                                                        //
    //                       구분용 주석                       //
    //                                                        //
    //                                                        //
    ////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 툴바 햄버거 메뉴

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // 메뉴 아이템 액션
        Intent intent = null;
        switch (item.getItemId()){
            case R.id.menu_MyPage: // 마이페이지
                intent = new Intent(MainActivity.this, MyPageActivity.class);
                intent.putExtra("uSeqno", userSeqno);
                intent.putExtra("macIP", macIP);
                startActivity(intent);

                // 아이디값 넘기기?
                break;
            case R.id.menu_ManageTag: // 태그수정
                intent = new Intent(MainActivity.this, TAGActivity.class);
                intent.putExtra("uSeqno", userSeqno);
                intent.putExtra("macIP", macIP);
                startActivity(intent);
                // 아이디값 넘기기?
                break;
            case R.id.menu_Logout: // 로그아웃 시 선택 가능
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("로그아웃");
                builder.setMessage("로그아웃 하시겠습니까?");
                builder.setNegativeButton("아니오", null);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() { // 예를 눌렀을 경우 로그인 창으로 이동
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        // 아이디값 넘기기?
                    }
                });

                builder.create().show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickFab(View view) { // 하단 앱바 홈버튼 클릭시
        isCenter= !isCenter;

        if(isCenter) bab.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        else bab.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("macIP", macIP);
        intent.putExtra("uSeqno", userSeqno);
        intent.putExtra("action", "Show_List");
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.v(TAG, "버튼 클릭 : " + checkedId);

            switch (checkedId){
                case 2131231000: // 가나다순 (ASC)
                    Comparator<Bean_friendslist> solt_Name = new Comparator<Bean_friendslist>() {
                        @Override
                        public int compare(Bean_friendslist o1, Bean_friendslist o2) {
                            return o1.getfName().compareTo(o2.getfName()) ;
                        }
                    };
                    Collections.sort(data, solt_Name);

                    adapter.notifyDataSetChanged();
                    break;

                case 2131231001: // 최신순 (DESC)
                    Comparator<Bean_friendslist> solt_new = new Comparator<Bean_friendslist>() {
                        @Override
                        public int compare(Bean_friendslist o1, Bean_friendslist o2) {
                            return (o2.getfSeqno() - o1.getfSeqno());
                        }
                    };
                    Collections.sort(data, solt_new) ;
                    adapter.notifyDataSetChanged() ;
                    break;

                case 2131231002: // 태그순 (ASC)
                    Comparator<Bean_friendslist> solt_Tag = new Comparator<Bean_friendslist>() {
                        @Override
                        public int compare(Bean_friendslist o1, Bean_friendslist o2) {
                            int ret = 0;

                            if(o1.getfTag1() < o2.getfTag1()){ // 처음 값 비교
                                ret = -1;
                            }else if(o1.getfTag1() == o2.getfTag1()){ // 같으면 다음값 비교
                                ret = o1.getfName().compareTo(o2.getfName()); // 1번째 이름 비교
                                if(o1.getfTag2() < o2.getfTag2()){ // 두번째 값 비교
                                    ret = -1;
                                }else if(o1.getfTag2() == o2.getfTag2()){ // 같으면 다음값
                                    ret = o1.getfName().compareTo(o2.getfName()); // 2번째 이름 비교
                                    if(o1.getfTag3() < o2.getfTag3()){ // 세번째 값 비교
                                        ret = -1;
                                    }else if(o1.getfTag3() == o2.getfTag3()){ // 같으면 다음값
                                        ret = o1.getfName().compareTo(o2.getfName()); // 3번째 이름 비교
                                        if(o1.getfTag4() < o2.getfTag4()){ // 네번째 값 비교
                                            ret = -1;
                                        }else if(o1.getfTag4() == o2.getfTag4()){ // 같으면 다음값
                                            ret = o1.getfName().compareTo(o2.getfName()); // 4번째 이름 비교
                                            if(o1.getfTag5() < o2.getfTag5()){ // 마지막 값 비교
                                                ret = -1;
                                            }else if(o1.getfTag5() == o2.getfTag5()){ //  같으면 다음값
                                                ret = o1.getfName().compareTo(o2.getfName()); // 5번째 이름 비교
                                            }else{
                                                ret = 1;
                                            }
                                        }else{ // 전자가 작으면 앞으로
                                            ret = 1;
                                        }
                                    }else{ // 전자가 작으면 앞으로
                                        ret = 1;
                                    }
                                }else{ // 전자가 작으면 앞으로
                                    ret = 1;
                                }
                            }else{ // 전자가 작으면 앞으로
                                ret = 1;
                            }
                            Log.v(TAG, "o1.getfTag1 : " + o1.getfTag1());
                            Log.v(TAG, "o2.getfTag2 : " + o2.getfTag2());

                            return ret;
                        }
                    };
                    Collections.sort(data, solt_Tag) ;
                    adapter.notifyDataSetChanged() ;
            }
        }
    };
}