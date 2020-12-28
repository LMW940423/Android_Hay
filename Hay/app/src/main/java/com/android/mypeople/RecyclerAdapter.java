package com.android.mypeople;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    //------------------Click Event------------------
//    public interface OnItemClickListener{
//        void onItemClick(View v, int position);
//    }
//
//    private OnItemClickListener mListener = null;
//
//    public void setOnItemClickListener(OnItemClickListener listener){
//        this.mListener = listener;
//    }
    //------------------Click Event------------------

    private ArrayList<Bean_friendslist> mDataset = null;
    ArrayList<String> unFilteredlist;
    ArrayList<String> filteredList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView tag_1;
        public ImageView tag_2;
        public ImageView tag_3;
        public ImageView photo;
        public TextView name;
        public TextView relation;
        public TextView comment;

        MyViewHolder(View v) {
            super(v);


            tag_1 = v.findViewById(R.id.main_ImgV_tag1);
            tag_2 = v.findViewById(R.id.main_ImgV_tag2);
            tag_3 = v.findViewById(R.id.main_ImgV_tag3);
            photo = v.findViewById(R.id.main_ImgV_Photo);
            name = v.findViewById(R.id.main_Text_Name);
            relation = v.findViewById(R.id.main_Text_Realtion);
            comment = v.findViewById(R.id.main_Text_Comment);


            //--------------------Click Event--------------------
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    int position=getAdapterPosition();//어뎁터 포지션값
//                    // 뷰홀더에서 사라지면 NO_POSITION 을 리턴
//                    if(position!=RecyclerView.NO_POSITION){
//                        if(mListener !=null){
//                            mListener.onItemClick(view,position);
//                        }
//                    }
//                }
//            });
            //---------------------Click Event---------------------

        }
    }

    // 메인 액티비티에서 받은 myDataset을 가져오
    public RecyclerAdapter(MainActivity mainActivity, int member, ArrayList<Bean_friendslist> myDataset) {
        mDataset = myDataset;
//
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_items, parent, false);
        //     반복할 xml 파일
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // 표시하는 메소드
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //데이터를 받은걸 올리기
        // Integer.toString(mDataset.get(position).getSno())
        // mDataset.get(position).getUsername()

        // if로 tag가 한개일때 두개일때 세개일때 나눠서?
        holder.tag_1.setImageResource(mDataset.get(position).getChooseTag1());
        holder.tag_2.setImageResource(mDataset.get(position).getChooseTag2());
        holder.tag_3.setImageResource(mDataset.get(position).getChooseTag3());
        holder.photo.setImageResource(mDataset.get(position).getPhoto());
        holder.name.setText(mDataset.get(position).getfName());
        holder.relation.setText(mDataset.get(position).getfRelation());
        holder.comment.setText(mDataset.get(position).getfComment());




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}