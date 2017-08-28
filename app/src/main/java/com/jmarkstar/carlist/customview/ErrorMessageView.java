package com.jmarkstar.carlist.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jmarkstar.carlist.R;

/**
 * Created by jmarkstar on 26/08/2017.
 */
public class ErrorMessageView extends LinearLayout {

    private TextView mTvTitle;
    private TextView mTvCause;
    private Button mBtnRetry;

    public ErrorMessageView(Context context) {
        super(context);
        init();
    }

    public ErrorMessageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        setAttrs(context,attrs);
    }

    public ErrorMessageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttrs(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ErrorMessageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
        setAttrs(context,attrs);
    }

    private void init(){
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER_VERTICAL);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_error_message, this, true);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);

        mTvTitle = findViewById(R.id.tv_error_title);
        mTvCause = findViewById(R.id.tv_error_cause);
        mBtnRetry = findViewById(R.id.btn_error_retry);
    }

    private void setAttrs(Context context, AttributeSet attrs) {
        TypedArray arg = context.obtainStyledAttributes(attrs, R.styleable.ErrorMessageView, 0, 0);
        int titleRs = arg.getResourceId(R.styleable.ErrorMessageView_error_title, R.string.error_generic_title);
        int causeRs = arg.getResourceId(R.styleable.ErrorMessageView_error_cause, R.string.error_generic_cause);
        mTvTitle.setText(getContext().getString(titleRs));
        mTvCause.setText(getContext().getString(causeRs));
    }

    public void setTitle(String title){
        mTvTitle.setText(title);
    }

    public void setCause(String cause){
        mTvCause.setText(cause);
    }

    public void onRetry(View.OnClickListener onRetry){
        mBtnRetry.setOnClickListener(onRetry);
    }

}
