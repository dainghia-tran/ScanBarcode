package com.kennen.scanbarcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity
{
    private ViewPager viewPager;
    public static IntentIntegrator intentIntegrator;
    public static DatabaseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHandler(this);

        initView();
        Toast.makeText(this, "Ứng dụng được tạo bởi:\nTrần Đại Nghĩa - FIT - HCMUS", Toast.LENGTH_LONG).show();
    }

    private void initView()
    {
        viewPager = (ViewPager)findViewById(R.id.vp_scan);
        viewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tl_scan);
        tabLayout.setupWithViewPager(viewPager);
    }

}
