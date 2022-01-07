package com.Church.church;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mFirebaseAuth;     // 파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd, mEtPwdcheck, mEtname, mEtphone;      // 회원가입 입력필드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 화면 회전 금지

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("회원가입");

        mFirebaseAuth = FirebaseAuth.getInstance(); // 인스턴스 초기화
        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtPwdcheck = findViewById(R.id.et_pwdcheck);
        mEtname = findViewById(R.id.et_name);
        mEtphone = findViewById(R.id.et_phone);
        Button mBtnRegister = findViewById(R.id.btn_register_complete);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strName = mEtname.getText().toString().trim();
                String strPhone = mEtphone.getText().toString().trim();
                String strEmail = mEtEmail.getText().toString().trim(); // trim() : 앞뒤 양쪽 공백제거
                String strPwd = mEtPwd.getText().toString().trim();
                String strPwdcheck = mEtPwdcheck.getText().toString().trim();

                if (strEmail.length() > 0 && strPwd.length() > 0 && strPwdcheck.length() > 0
                        && strPhone.length() > 0 && strName.length() > 0) {
                    if (strPwd.equals(strPwdcheck)) {
                        mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd)
                                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

                                            String uid = firebaseUser.getUid();
                                            String email = firebaseUser.getEmail();
                                            String name = strName;
                                            String phone = strPhone;
                                            String per = "false";

                                            HashMap<Object, String> result = new HashMap<>();
                                            result.put("uid", uid);
                                            result.put("email", email);
                                            result.put("name", name);
                                            result.put("phone", phone);
                                            result.put("per", per);

                                            mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                                            mDatabaseRef.child("Early_Church").child("UserAccount").child(uid).setValue(result);

                                            intentActivity(LoginActivity.class);
                                            finish();
                                            Toast.makeText(RegisterActivity.this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "회원가입에 실패했습니다. (아이디중복)",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intentActivity (Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

}