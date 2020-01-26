package com.mrgreenapps.otpview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OTPView extends LinearLayout {

    private static final String TAG = "OTPView";

    Context context;

    int mOtpLength;

    ColorStateList mBoxDefaultColor;
    ColorStateList mBoxErrorColor;
    ColorStateList mBoxAcceptedColor;

    ColorStateList mTextColor;

    int mBoxWidth;
    int mBoxHeight;

    int mBoxHorizontalPadding;

    int mGap;

    int mTextSize;

    List<EditText> pinBoxList;

    EditText mInput;

    public OTPView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(
                        attrs,
                        R.styleable.OTPView,
                        0,
                        0
                );

        try {
            mOtpLength = typedArray.getInt(R.styleable.OTPView_otp_length, 4);

            mBoxDefaultColor = typedArray.getColorStateList(R.styleable.OTPView_otp_box_default_color);
            mBoxErrorColor = typedArray.getColorStateList(R.styleable.OTPView_otp_box_error_color);
            mBoxAcceptedColor = typedArray.getColorStateList(R.styleable.OTPView_otp_box_accepted_color);

            mTextColor = typedArray.getColorStateList(R.styleable.OTPView_otp_text_color);

            mBoxWidth = typedArray.getDimensionPixelSize(R.styleable.OTPView_otp_box_width, LayoutParams.WRAP_CONTENT);
            mBoxHeight = typedArray.getDimensionPixelSize(R.styleable.OTPView_otp_box_height, LayoutParams.WRAP_CONTENT);

            mBoxHorizontalPadding = typedArray.getDimensionPixelSize(R.styleable.OTPView_otp_box_horizontal_padding, getResources().getDimensionPixelSize(R.dimen.default_box_horizontal_padding));

            mTextSize = typedArray.getDimensionPixelSize(R.styleable.OTPView_otp_text_size, getResources().getDimensionPixelSize(R.dimen.default_text_size));

            mGap = typedArray.getDimensionPixelSize(R.styleable.OTPView_otp_box_gap, getResources().getDimensionPixelSize(R.dimen.default_box_gap));

        } finally {
            typedArray.recycle();
        }

        drawBoxes();
        addTextWatcherForIterate();

    }

    private void drawBoxes() {
        pinBoxList = new ArrayList<>();
        for (int i = 0; i < mOtpLength; i++) {

            EditText pinBox = new EditText(context);
            pinBox.setText(String.valueOf(i));

            pinBox.setMinimumWidth(mBoxWidth);
            pinBox.setTextSize(mTextSize);

            if (mTextColor != null) pinBox.setTextColor(mTextColor.getDefaultColor());

            pinBox.setInputType(InputType.TYPE_CLASS_NUMBER);

            pinBox.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});


            LayoutParams p = new LayoutParams(mBoxWidth, mBoxHeight);

            p.setMargins(mGap, 0, mGap, 0);

            pinBox.setLayoutParams(p);
            pinBox.setGravity(Gravity.CENTER);

            pinBox.setPadding(mBoxHorizontalPadding, 0, mBoxHorizontalPadding, 0);


            if (mBoxDefaultColor != null) {
                pinBox.setBackgroundColor(mBoxDefaultColor.getDefaultColor());
            }

            pinBoxList.add(pinBox);

            addView(pinBox);
        }
    }

    private void addTextWatcherForIterate() {
        for (int i = 0; i < pinBoxList.size() - 1; i++) {
            final int nextPinBoxIndex = i + 1;
            pinBoxList.get(i).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0) {
                        pinBoxList.get(nextPinBoxIndex).requestFocus();
                    }
                }
            });
        }
    }

    public String getOTPPin(){
        String OTPPin = "";
        for(EditText pinbox: pinBoxList){
            OTPPin += pinbox.getText().toString();
        }
        return OTPPin;
    }

}
