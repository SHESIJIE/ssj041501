package shesijie.bawei.com.ssj041501;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import shesijie.bawei.com.ssj041501.mvp.Constan;
import shesijie.bawei.com.ssj041501.mvp.Constan.IResigtView;
import shesijie.bawei.com.ssj041501.mvp.LoginBean;
import shesijie.bawei.com.ssj041501.mvp.Presenter;

public class MainActivity extends BaseActivity implements Constan.ILoginView,IResigtView {
    String BaseLoginURL="http://172.17.8.100/small/user/v1/login";
    String BaseRegURL="http://172.17.8.100/small/user/v1/register";
    EditText names;
    EditText pass;
    EditText checkPass;
    Button login;
    Button res;
    Gson gson;
    int mType=0;
    Presenter presenter;
    private SharedPreferences mvplogin;
    private SharedPreferences.Editor edit;
    @Override
    protected void init() {
        names = findViewById ( R.id.login_name );
        pass = findViewById ( R.id.login_pass );
        checkPass = findViewById ( R.id.login_checkPass );
        login = findViewById ( R.id.login_login );
        res = findViewById ( R.id.login_but );
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setText("立即登陆");
                checkPass.setVisibility(View.VISIBLE);
                mType=1;
            }
        });
        login.setOnClickListener(new View.OnClickListener() {

            private String name,pswd,userpswder;

            @Override
            public void onClick(View v) {
                name = names.getText ().toString ();
                pswd= pass.getText().toString();
                userpswder= checkPass.getText().toString();

                //登录
                if(mType==0){
                    if (!name.isEmpty()&&!pswd.isEmpty()){
                        presenter.getLoginModel(BaseLoginURL,name,pswd);

                    }else {
                        Toast.makeText(MainActivity.this,"dd",Toast.LENGTH_LONG).show();
                    }


                }
                //注册
                if (mType==1){
                    if (!name.isEmpty()&&!pswd.isEmpty()&&!userpswder.isEmpty()){
                        if (pswd.equals(userpswder)){
                            presenter.getResigtModel ( BaseRegURL,name,pswd );
                            login.setText("登陆");
                            checkPass.setVisibility(View.GONE);
                            mType=0;
                        }else {

                            Toast.makeText(MainActivity.this,"两次密码不一致",Toast.LENGTH_LONG).show();
                        }


                    }else {
                        Toast.makeText(MainActivity.this,"dd",Toast.LENGTH_LONG).show();
                    }



                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }

    @Override
    public void getLogin(String data) {
        gson = new Gson ();
       LoginBean loginBean = gson.fromJson(data, LoginBean.class);

        if(loginBean!=null){
            edit.putString("name",data);
            edit.commit();
        }
        loginBean.getStatus();
        Log.i("ddd", "getRegPresenter: "+loginBean.getMessage());

        if(loginBean.getStatus().equals("0000")){
            login.setText("立即注册");
            checkPass.setVisibility(View.GONE);
            mType=0;
            Toast.makeText(MainActivity.this,loginBean.getMessage().toString(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, LoginActiivty.class);
            startActivity(intent);
            finish();


        }else {
            Toast.makeText(MainActivity.this,loginBean.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void getResigt(String data) {

    }
}
