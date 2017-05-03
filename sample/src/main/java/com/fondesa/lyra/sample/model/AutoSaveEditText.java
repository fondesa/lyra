package com.fondesa.lyra.sample.model;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.fondesa.lyra.Lyra;
import com.fondesa.lyra.annotation.SaveState;

public class AutoSaveEditText extends AppCompatEditText {
    @SaveState
    CharSequence mText;

    public AutoSaveEditText(Context context) {
        super(context);
    }

    public AutoSaveEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSaveEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        mText = getText();
        return Lyra.instance().saveState(this, super.onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Lyra.instance().restoreState(this, state));
        setText(mText);
    }
}