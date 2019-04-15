package shesijie.bawei.com.ssj041501;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( getlayout() );
        init();
        initData();
    }

    protected abstract void init();

    protected abstract void initData();

    protected abstract int getlayout();
}
