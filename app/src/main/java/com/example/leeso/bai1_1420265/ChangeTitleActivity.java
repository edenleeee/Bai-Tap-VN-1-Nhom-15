package com.example.leeso.bai1_1420265;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeTitleActivity extends AppCompatActivity {

    @BindView(R.id.mainET)
    EditText mainET;
    @BindView(R.id.colorExample)
    ImageView colorExample;
    @BindView(R.id.HONG)
    ImageView colorHong;
    @BindView(R.id.XANH)
    ImageView colorXanh;
    @BindView(R.id.VANG)
    ImageView colorVang;
    @BindView(R.id.DO)
    ImageView colorDo;
    @BindView(R.id.XANHLA)
    ImageView colorXanhla;
    @BindView(R.id.NAU)
    ImageView colorNau;
    @BindView(R.id.saveBtn)
    Button saveBtn;

    String whichColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_title);
        ButterKnife.bind(this);
        whichColor = "HONG";
        Intent i = getIntent();
        String main = i.getStringExtra("mainTV");
        String color = i.getStringExtra("mainColor");
        mainET.setText(main);
        if(color.equals("HONG")){
            colorExample.setBackground(getResources().getDrawable(R.color.colorAccent));
        } else if(color.equals("XANH")){
            colorExample.setBackground(getResources().getDrawable(R.color.colorPrimary));
        } else if(color.equals("VANG")){
            colorExample.setBackground(getResources().getDrawable(R.color.VANG));
        } else if(color.equals("DO")){
            colorExample.setBackground(getResources().getDrawable(R.color.Do));
        } else if(color.equals("XANHLA")){
            colorExample.setBackground(getResources().getDrawable(R.color.XanhLa));
        } else if(color.equals("NAU")){
            colorExample.setBackground(getResources().getDrawable(R.color.colorAccent));
        }


        colorHong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichColor = "HONG";
                colorExample.setBackground(colorHong.getBackground());
            }
        });
        colorXanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichColor = "XANH";
                colorExample.setBackground(colorXanh.getBackground());
            }
        });
        colorVang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichColor = "VANG";
                colorExample.setBackground(colorVang.getBackground());
            }
        });
        colorDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichColor = "DO";
                colorExample.setBackground(colorDo.getBackground());
            }
        });
        colorXanhla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichColor = "XANHLA";
                colorExample.setBackground(colorXanhla.getBackground());
            }
        });
        colorNau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whichColor = "NAU";
                colorExample.setBackground(colorNau.getBackground());
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tv = mainET.getText().toString();
                Intent i = new Intent(ChangeTitleActivity.this,MainActivity.class);
                i.putExtra("mainTV",tv);
                i.putExtra("mainColor",whichColor);
                startActivity(i);
            }
        });
    }
}
