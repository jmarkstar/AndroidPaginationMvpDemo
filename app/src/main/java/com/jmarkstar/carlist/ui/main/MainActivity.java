package com.jmarkstar.carlist.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist.ui.cartype.pagination.CarTypePaginationActivity;
import com.jmarkstar.carlist.ui.cartype.searching.CarTypeSearchingActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_go_pagination) public void onGoPaginationScreen(){
        CarTypePaginationActivity.start(this);
    }

    @OnClick(R.id.btn_go_searching) public void onGoSearchingScreen(){
        CarTypeSearchingActivity.start(this);
    }
}
