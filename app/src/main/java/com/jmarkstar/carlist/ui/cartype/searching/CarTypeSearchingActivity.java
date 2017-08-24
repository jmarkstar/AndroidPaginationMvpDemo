package com.jmarkstar.carlist.ui.cartype.searching;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jmarkstar.carlist.R;

public class CarTypeSearchingActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CarTypeSearchingActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_type_searching);
    }
}
