package com.Church.church;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Gospel_03_01Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<VideoList> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    Button btn_gospel_01, btn_gospel_02, btn_gospel_03;
    Button btn_gospel_03_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gospel_03_01);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 화면 회전 금지

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("말씀");

        recyclerView = findViewById(R.id.recyclerView01); // 아이디 연결
        recyclerView.setHasFixedSize(true); // 리사이클 기능 성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // Video 객체 담을 어레이 리스트 (어댑터 쪽으로 전송할)
        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // 리싸이클러에 어댑터 연결
        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이타베이스 연동
        databaseReference = database.getReference("VideoList_03_01"); // DB테이블 연결
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear(); // 기존 배열리스트 초기화(방지차원)
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    VideoList videoList = snapshot.getValue(VideoList.class); //만들었던 VideoList 객체에다 파이어베이스로부터 데이터를 담는다
                    arrayList.add(videoList); // 담은 데이터를 배열에 넣고 recyclerView에 보낼 준비

                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침 (갱신을 해야 반영됨)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        btn_gospel_01 = findViewById(R.id.btn_gospel_01);
        btn_gospel_02 = findViewById(R.id.btn_gospel_02);
        btn_gospel_03 = findViewById(R.id.btn_gospel_03);
        btn_gospel_03_01 = findViewById(R.id.btn_gospel_03_01);

        btn_gospel_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(Gospel_01_01Activity.class);
            }
        });
        btn_gospel_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(Gospel_02_01Activity.class);
            }
        });

        btn_gospel_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(Gospel_03_01Activity.class);
            }
        });
        btn_gospel_03_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(Gospel_03_01Activity.class);
            }
        });

    }
    private void intentActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAffinity(this);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }
}