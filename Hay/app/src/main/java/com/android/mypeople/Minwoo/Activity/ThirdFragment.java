package com.android.mypeople.Minwoo.Activity;

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

import com.android.mypeople.R;

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
        tv03.setText("\n지금 바로\n시작해볼까요?");

        TextView tv03_01 = view.findViewById(R.id.tv_03_01);
        tv03_01.setText("아래의 시작하기 버튼을 눌러\nHay를 시작해주세요.");

        ImageView iv_03 = view.findViewById(R.id.iv_03);
        iv_03.setImageResource(R.drawable.intro_03);


        return view;
    }

}
