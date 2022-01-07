package com.Church.church;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Button img1, img2, img3, img4, img5;
    Animation fh_01, fh_02, fh_03, fh_04, fh_05;
    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 화면 회전 금지

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("그리스도와의 연합");

        fh_01 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeh_01);
        fh_02 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeh_02);
        fh_03 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeh_03);
        fh_04 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeh_04);
        fh_05 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeh_05);

        img1 = findViewById(R.id.button1);
        img2 = findViewById(R.id.button2);
        img3 = findViewById(R.id.button3);
        img4 = findViewById(R.id.button4);
        img5 = findViewById(R.id.button5);

        img1.startAnimation(fh_01);
        img2.startAnimation(fh_02);
        img3.startAnimation(fh_03);
        img4.startAnimation(fh_04);
        img5.startAnimation(fh_05);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(IntroduceActivity.class);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(Gospel_01_01Activity.class);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(NewsActivity.class);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(EventActivity.class);
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentActivity(CommunityActivity.class);
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
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        } else {
            ActivityCompat.finishAffinity(this);
            moveTaskToBack(true); // 태스크를 백그라운드로 이동
            finishAndRemoveTask(); // 액티비티 종료 + 태스크 리스트에서 지우기
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
            toast.cancel();
        }
    }
}