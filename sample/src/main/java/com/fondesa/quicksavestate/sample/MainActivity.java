package com.fondesa.quicksavestate.sample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fondesa.quicksavestate.SaveState;
import com.fondesa.quicksavestate.StateSerDes;

public class MainActivity extends AppCompatActivity {
    private static final String FIRST_VALUE = "first";
    private static final String SECOND_VALUE = "second";

    public TextView mTextView;

    @SaveState
    private String mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.text_view);

        mTextView.setText(mText);

        findViewById(R.id.btn_change_value).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mText = (mText == null || mText.equals(SECOND_VALUE)) ? FIRST_VALUE : SECOND_VALUE;
                mText = (mText == null) ? FIRST_VALUE : null;
                mTextView.setText(mText);
            }
        });
    }

//    public static class RandomStateSerDes implements StateSerDes<String> {
//        @Override
//        public void serialize(@NonNull Bundle state, @NonNull String key, @NonNull String value) {
//            state.putString(key + ".string", value);
//        }
//
//        @Override
//        public String deserialize(@NonNull Bundle state, @NonNull String fieldName) {
//            return state.getString(fieldName + ".string");
//        }
//    }
}