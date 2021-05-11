package com.example.tuan8_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListner{
    RecyclerView rcv_name;
    com.example.tuan8_bai2.CustomAdapterRecycler adt;
    List<com.example.tuan8_bai2.User> mNames;
    Button btnSave,btnAdd;
    EditText tvSearch;
    AppDatabase db;
    com.example.tuan8_bai2.UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNames = new ArrayList<>();
        rcv_name = findViewById(R.id.rcv_name);
        tvSearch = findViewById(R.id.tvSearch);
        btnAdd = findViewById(R.id.btnAdd);
//        btnAdd = findViewById(R.id.btnAdd);
//        btnRemove = findViewById(R.id.btnRemove);
//        btnAdd = findViewById(R.id.btnAdd);
//        btnSave = findViewById(R.id.btnSave);
//        btnRemove.setEnabled(false);
//        btnSave.setEnabled(false);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build();
        userDao = db.userDao();
//        userDao.insertAll(new User(8,"Hoan","Thai"));
        mNames= userDao.getAll();


        adt = new com.example.tuan8_bai2.CustomAdapterRecycler(mNames,this);
        rcv_name.setHasFixedSize(true);
        rcv_name.setAdapter(adt);
        rcv_name.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i=0;
//                String name = tvSearch.getText().toString();
//                for (User user:userDao.getAll()){
//                    i++;
//                }
//                userDao.insertAll(new User(i+10,name,""));
//                List<User> users =  userDao.getAll();
//                adt.resetList(users);
//                tvSearch.setText("");
//                btnRemove.setEnabled(false);
//                btnSave.setEnabled(false);
//            }
//        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=0;
                String name=tvSearch.getText().toString();
                for (com.example.tuan8_bai2.User user:userDao.getAll()){
                    i++;
                }
                userDao.insertAll(new com.example.tuan8_bai2.User(i++,name,""));
                List<com.example.tuan8_bai2.User> users =  userDao.getAll();
                adt.resetList(users);
                tvSearch.setText("");
            }
        });

    }

    @Override
    public void itemClicklistener(com.example.tuan8_bai2.User user) {
        tvSearch.setText(user.getFirstName()+"");
//        btnRemove.setEnabled(true);
//        btnSave.setEnabled(true);
//        tvSearch.setText(user.getFirstName().toString() + " " +user.getLastName().toString());
//        btnRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userDao.delete(userDao.findById(user.getUid()));
//                List<User> users =  userDao.getAll();
//                adt.resetList(users);
//                tvSearch.setText("");
//                btnRemove.setEnabled(false);
//                btnSave.setEnabled(false);
//            }
//        });
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user.setFirstName(tvSearch.getText().toString());
//                user.setLastName("");
//                userDao.update(user);
//                List<User> users =  userDao.getAll();
//                adt.resetList(users);
//                tvSearch.setText("");
//                btnRemove.setEnabled(false);
//                btnSave.setEnabled(false);
//            }
//        });

    }

    @Override
    public void buttonxoaClick(com.example.tuan8_bai2.User user) {
        userDao.delete(user);
        List<com.example.tuan8_bai2.User> users =  userDao.getAll();
        adt.resetList(users);
        tvSearch.setText("");

    }

    @Override
    public void buttonsuaClick(com.example.tuan8_bai2.User user) {
        String name = tvSearch.getText().toString();
        user.setFirstName(name);
        userDao.update(user);
        List<com.example.tuan8_bai2.User> users =  userDao.getAll();
        adt.resetList(users);
        tvSearch.setText("");

    }
}