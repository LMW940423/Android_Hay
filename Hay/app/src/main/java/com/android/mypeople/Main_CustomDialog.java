package com.android.mypeople;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main_CustomDialog extends Dialog {

    private int seq;
    private String ipurl;
    private String urlAddr;
    private Context context;

    Button btn_Call = null;
    Button btn_Delete = null;
    Button btn_Detail = null;

    public Main_CustomDialog(Context context, int seq, String ipurl) {
        super(context);
        this.context = context;
        this.seq = seq;
        this.ipurl = ipurl;


    }


}