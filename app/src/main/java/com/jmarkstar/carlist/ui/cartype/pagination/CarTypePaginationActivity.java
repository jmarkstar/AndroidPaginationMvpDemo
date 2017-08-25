package com.jmarkstar.carlist.ui.cartype.pagination;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist.ui.cartype.pagination.manutafturer.SelectManufacturerActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarTypePaginationActivity extends AppCompatActivity {

    private String mSelectedManufacturerId;

    public static void start(Context context) {
        Intent starter = new Intent(context, CarTypePaginationActivity.class);
        context.startActivity(starter);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_type_pagination);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_manufacturer) public void onSelectManufacturerOption(){
        SelectManufacturerActivity.start(this, mSelectedManufacturerId);
    }
}
