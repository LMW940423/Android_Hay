package com.android.mypeople.HyunA.NetworkTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.mypeople.Share.Bean.Bean_user;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CUDNetworkTask_Hyeona extends AsyncTask<Integer, String, Object> {

    /*공용이므로 항상 수정사항 알려주시기 바랍니다.*/

    final static String TAG = "CUDNetworkTask";
    Context context = null;
    String mAddr = null;
    ProgressDialog progressDialog = null;
    String where = null;
    ArrayList<Bean_user> user_info;

    public CUDNetworkTask_Hyeona(Context context, String mAddr, String where) {
        this.context = context;
        this.mAddr = mAddr;
        this.where = where;
        Log.v(TAG,"Start : " + mAddr);
    }

    @Override
    protected void onPreExecute() {
        Log.v(TAG, "onPreExecute()");
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Create/Update/Delete");
        progressDialog.setMessage("Working...");
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(Integer... integers) {
        Log.v(TAG, "doInBackground()");

        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        String result = null;

        try {
            URL url = new URL(mAddr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(10000);

            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);

                while (true){
                    String strline = bufferedReader.readLine();
                    if(strline == null) break;
                    stringBuffer.append(strline + "\n");
                }

                if(where.equals("select")){
                    //parserSelect(stringBuffer.toString());
                }else{
                    result = parserAction(stringBuffer.toString());
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader != null) bufferedReader.close();
                if(inputStreamReader != null) inputStreamReader.close();
                if(inputStream != null) inputStream.close();

            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

        if(where.equals("select")){
            return null;
        }else{
            return result;
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Log.v(TAG, "onProgressUpdate()");
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Object o) {
        Log.v(TAG, "onPostExecute()");
        super.onPostExecute(o);
        progressDialog.dismiss();

    }

    @Override
    protected void onCancelled() {
        Log.v(TAG, "onCancelled");
        super.onCancelled();
    }

    private String parserAction(String s){
        Log.v(TAG,"Parser()");
        String returnValue = null;

        try {
            Log.v(TAG, s);

            JSONObject jsonObject = new JSONObject(s);
            returnValue = jsonObject.getString("result");
            Log.v(TAG, returnValue);

        }catch (Exception e){
            e.printStackTrace();
        }
        return returnValue;
    }




//    private void parserSelect(String s){
//        //Log.v("aaaaaa","parser()");
//        try {
//            JSONObject jsonObject = new JSONObject(s);
//            JSONArray jsonArray = new JSONArray(jsonObject.getString("user_info"));
//            user_info.clear();
//
//            for (int i=0; i<jsonArray.length(); i++){
//                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
//                int uSeqno = jsonObject1.getInt("uSeqno");
//                String uId = jsonObject1.getString("uId");
//                Log.v("aaaaaa","aaaaaa"+uId);
//                String uPw = jsonObject1.getString("uPw");
//                String uName = jsonObject1.getString("uName");
//                String uTel = jsonObject1.getString("uTel");
//                Log.v("aaaaaa","aaaaaa");
//                Timestamp uInsertDate = Timestamp.valueOf(jsonObject1.getString("uInsertDate"));
//                Timestamp uDeleteDate = Timestamp.valueOf(jsonObject1.getString("uDeleteDate"));
//
//                Log.v("aaaaaa","aaaaaa");
//                Bean_user bean_user = new Bean_user(uSeqno, uId, uPw, uName, uTel, uInsertDate, uDeleteDate);
//
//                Log.v("aaaaaa","aaaaaa");
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }



} // ----------