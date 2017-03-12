package com.fondesa.quicksavestate.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fondesa.quicksavestate.QuickSaveState;
import com.fondesa.quicksavestate.annotation.SaveState;
import com.fondesa.quicksavestate.sample.model.Model;
import com.fondesa.quicksavestate.sample.model.ParcelableModel;

import java.util.ArrayList;

public class MainActivity extends BaseMainActivity {
    private TextView mTextView;

    @SaveState
    private String mText;

    @SaveState
    ParcelableModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuickSaveState.instance().restoreState(this, savedInstanceState);

        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text_view);

        printInfo();

        findViewById(R.id.btn_change_value).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = (mText == null) ? "first" : null;
                mByte = (mByte == null) ? (byte) 1 : null;
                mPrimitiveByte = (mPrimitiveByte == 0) ? (byte) 1 : 0;
                ArrayList<Model> modelList = new ArrayList<>();
                modelList.add(new Model(1, "first"));
                modelList.add(new Model(2, "second"));
                modelList.add(new Model(3, "third"));

                mModelList = (mModelList == null) ? modelList : null;
                mModel = (mModel == null) ? new ParcelableModel(5, "fifth") : null;
                printInfo();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        QuickSaveState.instance().saveState(this, outState);
    }

    private void printInfo() {
        mTextView.setText(null);

        mTextView.append("String: " + mText);
        appendNl();
        mTextView.append("Primitive byte: " + mPrimitiveByte);
        appendNl();
        mTextView.append("Byte object: " + mByte);
        appendNl();

        mTextView.append("Model list: ");
        if (mModelList != null) {
            String text = "";
            for (int i = 0; i < mModelList.size(); i++) {
                Model m = mModelList.get(i);
                if (i != 0)
                    text += " - ";

                text += m.id + ": " + m.value;
            }
            mTextView.append(text);
        } else {
            mTextView.append("null");
        }

        appendNl();

        mTextView.append("Parcelable model: ");
        if (mModel != null) {
            mTextView.append(mModel.id + ": " + mModel.value);
        } else {
            mTextView.append("null");
        }
    }

    private void appendNl() {
        mTextView.append("\n");
    }
}