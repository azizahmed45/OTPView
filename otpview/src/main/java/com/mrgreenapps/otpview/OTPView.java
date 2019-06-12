package com.mrgreenapps.otpview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OTPView extends LinearLayout {

    Context context;


    int mOtpLength;

    ColorStateList mBoxDefaultColor;
    ColorStateList mBoxErrorColor;
    ColorStateList mBoxAcceptedColor;

    ColorStateList mTextColor;

    int mBoxWidth;
    int mBoxHeight;

    int mTextSize;


    List<EditText> pinBoxList;

    public OTPView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OTPView, 0, 0);

        try{
            mOtpLength = typedArray.getInt(R.styleable.OTPView_otp_length, 4);

            mBoxDefaultColor = typedArray.getColorStateList(R.styleable.OTPView_otp_box_default_color);
            mBoxErrorColor = typedArray.getColorStateList(R.styleable.OTPView_otp_box_error_color);
            mBoxAcceptedColor = typedArray.getColorStateList(R.styleable.OTPView_otp_box_accepted_color);

            mTextColor = typedArray.getColorStateList(R.styleable.OTPView_otp_text_color);

            mBoxWidth = typedArray.getDimensionPixelSize(R.styleable.OTPView_otp_box_width, getResources().getDimensionPixelSize(R.dimen.box_default_width));
            mBoxHeight = typedArray.getDimensionPixelSize(R.styleable.OTPView_otp_box_height, getResources().getDimensionPixelSize(R.dimen.box_default_height));

            mTextSize = typedArray.getDimensionPixelSize(R.styleable.OTPView_otp_text_size, getResources().getDimensionPixelSize(R.dimen.default_text_size));

        } finally {
            typedArray.recycle();
        }

        setWeightSum(mOtpLength);

        pinBoxList = new ArrayList<>();
        for(int i = 0; i < mOtpLength; i++){

            EditText pinBox = new EditText(context);
            pinBox.setText(String.valueOf(i));

            pinBox.setTextSize(mTextSize);
            pinBox.setTextColor(mTextColor.getDefaultColor());

            pinBox.setInputType(InputType.TYPE_CLASS_NUMBER);

            pinBox.setFilters(new InputFilter[] {new InputFilter.LengthFilter(1)});

            pinBox.setLayoutParams(new LayoutParams(mBoxWidth, mBoxHeight));


            pinBox.setBackgroundColor(mBoxDefaultColor.getDefaultColor());

            pinBoxList.add(pinBox);

            addView(pinBox);
        }



//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//
//        TextView textView = (TextView) inflater.inflate(R.layout.text_view, null, false);
//        textView.setTextSize(55);
//
//        addView(textView);

    }

}
