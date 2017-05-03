package com.fondesa.lyra.sample;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fondesa.lyra.Lyra;
import com.fondesa.lyra.annotation.SaveState;
import com.fondesa.lyra.coder.gson.base.DefaultGsonCoder;
import com.fondesa.lyra.sample.model.AutoSaveEditText;
import com.fondesa.lyra.sample.model.Model;
import com.fondesa.lyra.sample.model.ParcelableModel;

import java.util.ArrayList;

public class MainActivity extends BaseMainActivity {
    private TextView mTextView;

    @SaveState(DefaultGsonCoder.class)
    private String mText;

    @SaveState
    ParcelableModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        int defaultPadding = getResources().getDimensionPixelSize(R.dimen.default_inner_padding);
        root.setPadding(defaultPadding, defaultPadding, defaultPadding, defaultPadding);

        mTextView = new TextView(this);
        root.addView(mTextView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        Button button = new Button(this);
        button.setText(R.string.change_value);
        button.setOnClickListener(new View.OnClickListener() {
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
        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        root.addView(button, buttonParams);

        AutoSaveEditText autoSaveEditText = new AutoSaveEditText(this);
        autoSaveEditText.setId(R.id.auto_save_edit_text);
        root.addView(autoSaveEditText, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(root, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Lyra.instance().restoreState(this, savedInstanceState);
        }

        printInfo();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            Lyra.instance().saveState(this, outState);
        }
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