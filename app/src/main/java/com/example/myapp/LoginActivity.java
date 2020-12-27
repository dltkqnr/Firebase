package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    //현재 유저 불러오기
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //초기화

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.Button_login).setOnClickListener(onClickListener);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.Button_login:
                    signIn();
                    Log.e("리스너", "회원가입");
                    break;
            }
        }
    };

    private void signIn() {

        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();

        if (email.length()>0 && password.length()>0) {
            //로그인 되는 과정 (로딩바)
            final RelativeLayout loderLayout = findViewById(R.id.loderLayout);
            loderLayout.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //로딩바 꺼짐
                            loderLayout.setVisibility(View.GONE);

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인 성공했습니다.");
                                startMainActivity();
                                //UI
                            } else {
                                // If sign in fails, display a message to the user.
                                if (task.getException() != null)
                                    startToast(task.getException().toString());
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                startToast("로그인 실패");
                                //UI
                                // ...
                            }

                            // ...
                        }
                    });
        }
        else{
            startToast("이메일 또는 패스워드를 입력해주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void startMainActivity() {
        Intent intent = new Intent(this , MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 로그인 후 메인화면으로 갈 시 뒤로 가기를 누르면 다시 로그인화면으로 가지않게
        startActivity(intent);
    }
}
