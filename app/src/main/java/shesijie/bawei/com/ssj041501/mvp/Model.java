package shesijie.bawei.com.ssj041501.mvp;

import shesijie.bawei.com.ssj041501.AsyncHttpUrl;

public class Model implements Constan.ILoginModel,Constan.IResigtModel {
    @Override
    public void login(String urls , String username , String pswd , final Constan.ILoginModel.CallBack callBack) {
        AsyncHttpUrl.getInstance ().PostAsync ( urls , username , pswd , new AsyncHttpUrl.AsyncCallBack () {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess ( result );
            }

            @Override
            public void onError(int errorCode , String messagr) {

            }
        } );
    }

    @Override
    public void resigt(String urls , String username , String pswd , final Constan.ILoginModel.CallBack callBack) {
        AsyncHttpUrl.getInstance ().PostAsync ( urls , username , pswd , new AsyncHttpUrl.AsyncCallBack () {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess ( result );
            }

            @Override
            public void onError(int errorCode , String messagr) {

            }
        } );
    }
}
