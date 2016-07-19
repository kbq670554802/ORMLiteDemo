package com.kbq.ormlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private Button btnUserAdd,btnUserDelete,btnUserQueryAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUserAdd = (Button) findViewById(R.id.btn_user_add);
        btnUserDelete = (Button) findViewById(R.id.btn_user_delete);
        btnUserQueryAll = (Button) findViewById(R.id.btn_user_queryAll);
        btnUserAdd.setOnClickListener(this);
        btnUserDelete.setOnClickListener(this);
        btnUserQueryAll.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_user_add:
                User user = new User("柯葆青", "AAAAA");
                int ret = UserDao.getInstance(this).add(user);
                Log.i(TAG, "添加："+ret);
                break;
            case R.id.btn_user_delete:
                Collection<Integer> ids = new ArrayList<>();
                ids.add(5);
                ids.add(7);
                ids.add(8);
                int retDelete = UserDao.getInstance(this).deleteIds(ids);
                Log.i(TAG, "删除："+retDelete);
                break;
            case R.id.btn_user_queryAll:
                List<User> users = UserDao.getInstance(this).queryAll();
                if(users!=null) {
                    Log.i(TAG, "总记录数：" + users.size());
                }
                break;
        }
    }
}
