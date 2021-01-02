package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    ArrayList<Item> item_arr = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth 로그인 확인
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            startSignUpActivity();
        }

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this ,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //구분선 추가가
       DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        findViewById(R.id.button_dim).setOnClickListener(onClickListener);
        findViewById(R.id.button_dimcook).setOnClickListener(onClickListener);
        findViewById(R.id.button_refg).setOnClickListener(onClickListener);
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener);

    }


    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.logoutButton:
                    FirebaseAuth.getInstance().signOut();
                    startSignUpActivity();
                    break;
                case R.id.button_dim:
                    loadCollection("dimchae");

                    break;

                case R.id.button_dimcook:
                    loadCollection("dimchae_cook");

                    break;

                case R.id.button_refg:
                    loadCollection("refrigerator");

                    break;
            }
        }
    };

    private void loadCollection(final String name) {
        item_arr.clear(); //초기화
        //로딩 시작
        final RelativeLayout loderLayout = findViewById(R.id.loderLayout);
        loderLayout.setVisibility(View.VISIBLE);

        db.collection(name) // 컬렉션의 모든 데이터 불러오기
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //문서 불러오기

                                DocumentReference docRef = db.collection(name).document(document.getId()); //id를 통해서
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                        startToast("확인");
                                        Item item = documentSnapshot.toObject(Item.class); // item 객체로 만듦
                                        item_arr.add(item);

                                        adapter = new MyAdapter(getApplicationContext(), item_arr);

                                        ((MyAdapter) adapter).setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(MyAdapter.ViewHolder holder, View view, int position) {
                                                Item item = ((MyAdapter) adapter).getItem(position);
//                                                Toast.makeText(getApplicationContext(), item.getModel()+" clicked", Toast.LENGTH_SHORT).show();
                                                // 다른액티비티로 이동
                                                startResultActivity(item);

                                            }
                                        });

                                        recyclerView.setAdapter(adapter); //어댑터에 적용
                                    }
                                });
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        loderLayout.setVisibility(View.GONE); //로딩 종료

                    }
                });

    }


    // 사인업 액티비티로 전환
    private void startSignUpActivity() {
        Intent intent = new Intent(this , SignUpActivity.class);
        startActivity(intent);
    }
    private void startResultActivity(Item item) {
        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
        intent.putExtra("send_Item" , item);
        startActivity(intent);
    }

}
