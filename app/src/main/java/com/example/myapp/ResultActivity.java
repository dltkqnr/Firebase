package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    private static final String TAG = "ResultActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int price,sale1,sale2,sale3;
        DecimalFormat formatter = new DecimalFormat("###,###");

        Intent intent = getIntent();
        Item item = (Item) intent.getSerializableExtra("send_Item");

        price = txtChangePrice(item.getPrice()); // , 없애고 int 형으로
        sale1 = txtChangePrice(item.getSale1());
        sale2 = txtChangePrice(item.getSale2());
        sale3 = txtChangePrice(item.getSale3());

        TextView txtModel = findViewById(R.id.txtModel);
        TextView txtPrice = findViewById(R.id.txtPrice);
        TextView txtSale1 = findViewById(R.id.txtSale1);
        TextView txtSale2 = findViewById(R.id.txtSale2);
        TextView txtSale3 = findViewById(R.id.txtSale3);
        TextView txtResult = findViewById(R.id.txtResult);


        Log.d(TAG , price + " " + sale1+""+sale2+""+sale3);

        txtModel.setText(txtChange(item.getModel()));
        txtPrice.setText(txtChange(item.getPrice()));
        txtSale1.setText(txtChange(item.getSale1()));
        txtSale2.setText(txtChange(item.getSale2()));
        txtSale3.setText(txtChange(item.getSale3()));



        txtResult.setText(formatter.format(price +sale1 +sale2 +sale3));

        Log.d(TAG , "Price : " + item.getPrice());



    }
    public static String txtChange(String str){
        if (str.equals("")){
            str = "X";
        }
        return str;
    }

    public int txtChangePrice(String str){
        if (str.equals(""))
            return 0;
        else
            return Integer.parseInt(str.replace(",",""));

    }
}