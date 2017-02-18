package com.fondesa.quicksavestate.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.fondesa.quicksavestate.SaveState;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private TextView mArrayTextView;


    @SaveState
    private String mText;

    @SaveState
    Byte mByte;

    @SaveState
    byte mPrimitiveByte;

    @SaveState
    ArrayList<CustomModel> mIntegerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text_view);
        mArrayTextView = (TextView) findViewById(R.id.array_text_view);

        printInfo();

        findViewById(R.id.btn_change_value).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = (mText == null) ? "first" : null;
                mByte = (mByte == null) ? (byte) 1 : null;
                mPrimitiveByte = (mPrimitiveByte == 0) ? (byte) 1 : 0;
                ArrayList<CustomModel> integers = new ArrayList<>();
                integers.add(new CustomModel(1, "first"));
                integers.add(new CustomModel(2, "second"));
                integers.add(new CustomModel(3, "third"));
                mIntegerArray = (mIntegerArray == null) ? integers : null;

                printInfo();
            }
        });
    }

    private void printInfo() {
        mTextView.setText(mText + " " + mByte + " " + mPrimitiveByte);
        if (mIntegerArray != null) {
            String text = "";
            for (CustomModel m : mIntegerArray) {
                text += m.id + ": " + m.value + "\n";
            }
            mArrayTextView.setText(text);
        } else {
            mArrayTextView.setText(null);
        }
    }
}