package com.android.mypeople;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ThirdFragment extends Fragment {

    private String title;
    private int page;
    Button button;

    public static ThirdFragment newInstance(int page, String title) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page",page);
        bundle.putString("title",title);
        fragment.setArguments(bundle);


        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        page =getArguments().getInt("page");

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = null;
        view = inflater.inflate(R.layout.fragment_third,container,false);

        TextView tv03 = view.findViewById(R.id.tv_03);
        tv03.setText("\n여기엔뭐하지\n아이디어좀줘라");

        TextView tv03_01 = view.findViewById(R.id.tv_03_01);
        tv03_01.setText("주소록 등록시 사진 업로드로 리스트에서\n친구를 사진으로 구별 할 수 있습니다.");

        ImageView iv_03 = view.findViewById(R.id.iv_03);
        iv_03.setImageResource(R.drawable.intro_02);


        return view;
    }

}
