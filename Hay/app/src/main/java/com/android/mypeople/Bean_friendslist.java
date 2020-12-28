package com.android.mypeople;

import android.media.Image;
import android.widget.ImageView;

public class Bean_friendslist {

    /*공용이므로 항상 수정사항 알려주시기 바랍니다.*/

    //Field
    int fSeqno;
    int user_uSeqno;
    String fName;
    String fTel;
    String fAddress;
    String fRelation;
    String fComment;
    String fImage;
    String fImageReal;
    int fTag1;
    int fTag2;
    int fTag3;
    int fTag4;
    int fTag5;
    int chooseTag1;
    int chooseTag2;
    int chooseTag3;
    int photo;

    //Constructor
    public Bean_friendslist(int fSeqno, int user_uSeqno, String fName, String fTel, String fAddress, String fRelation, String fComment, String fImage, String fImageReal, int fTag1, int fTag2, int fTag3, int fTag4, int fTag5) {
        this.fSeqno = fSeqno;
        this.user_uSeqno = user_uSeqno;
        this.fName = fName;
        this.fTel = fTel;
        this.fAddress = fAddress;
        this.fRelation = fRelation;
        this.fComment = fComment;
        this.fImage = fImage;
        this.fImageReal = fImageReal;
        this.fTag1 = fTag1;
        this.fTag2 = fTag2;
        this.fTag3 = fTag3;
        this.fTag4 = fTag4;
        this.fTag5 = fTag5;
    }

    public Bean_friendslist(String fName, String fRelation, String fComment, int chooseTag1, int chooseTag2, int chooseTag3, int photo) {
        this.fName = fName;
        this.fRelation = fRelation;
        this.fComment = fComment;
        this.chooseTag1 = chooseTag1;
        this.chooseTag2 = chooseTag2;
        this.chooseTag3 = chooseTag3;
        this.photo = photo;
    }

    public Bean_friendslist(String fName, String fRelation, String fComment, int chooseTag1, int photo) {
        this.fName = fName;
        this.fRelation = fRelation;
        this.fComment = fComment;
        this.chooseTag1 = chooseTag1;
        this.photo = photo;
    }

    public Bean_friendslist() {
    }

    public Bean_friendslist(int fSeqno, String fName, String fTel, String fAddress, String fRelation, String fComment, String fImage, String fImageReal, int fTag1, int fTag2, int fTag3, int fTag4, int fTag5) {
        this.fSeqno = fSeqno;
        this.user_uSeqno = user_uSeqno;
        this.fName = fName;
        this.fTel = fTel;
        this.fAddress = fAddress;
        this.fRelation = fRelation;
        this.fComment = fComment;
        this.fImage = fImage;
        this.fImageReal = fImageReal;
        this.fTag1 = fTag1;
        this.fTag2 = fTag2;
        this.fTag3 = fTag3;
        this.fTag4 = fTag4;
        this.fTag5 = fTag5;
    }

    //Getters and Setters
    public int getfSeqno() {
        return fSeqno;
    }

    public void setfSeqno(int fSeqno) {
        this.fSeqno = fSeqno;
    }

    public int getUser_uSeqno() {
        return user_uSeqno;
    }

    public void setUser_uSeqno(int user_uSeqno) {
        this.user_uSeqno = user_uSeqno;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfTel() {
        return fTel;
    }

    public void setfTel(String fTel) {
        this.fTel = fTel;
    }

    public String getfAddress() {
        return fAddress;
    }

    public void setfAddress(String fAddress) {
        this.fAddress = fAddress;
    }

    public String getfRelation() {
        return fRelation;
    }

    public void setfRelation(String fRelation) {
        this.fRelation = fRelation;
    }

    public String getfComment() {
        return fComment;
    }

    public void setfComment(String fComment) {
        this.fComment = fComment;
    }

    public String getfImage() {
        return fImage;
    }

    public void setfImage(String fImage) {
        this.fImage = fImage;
    }

    public String getfImageReal() {
        return fImageReal;
    }

    public void setfImageReal(String fImageReal) {
        this.fImageReal = fImageReal;
    }

    public int getfTag1() {
        return fTag1;
    }

    public void setfTag1(int fTag1) {
        this.fTag1 = fTag1;
    }

    public int getfTag2() {
        return fTag2;
    }

    public void setfTag2(int fTag2) {
        this.fTag2 = fTag2;
    }

    public int getfTag3() {
        return fTag3;
    }

    public void setfTag3(int fTag3) {
        this.fTag3 = fTag3;
    }

    public int getfTag4() {
        return fTag4;
    }

    public void setfTag4(int fTag4) {
        this.fTag4 = fTag4;
    }

    public int getfTag5() {
        return fTag5;
    }

    public void setfTag5(int fTag5) {
        this.fTag5 = fTag5;
    }

    public int getChooseTag1() {
        return chooseTag1;
    }

    public void setChooseTag1(int chooseTag1) {
        this.chooseTag1 = chooseTag1;
    }

    public int getChooseTag2() {
        return chooseTag2;
    }

    public void setChooseTag2(int chooseTag2) {
        this.chooseTag2 = chooseTag2;
    }

    public int getChooseTag3() {
        return chooseTag3;
    }

    public void setChooseTag3(int chooseTag3) {
        this.chooseTag3 = chooseTag3;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
