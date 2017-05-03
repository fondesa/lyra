/*
 * Copyright (c) 2017 Fondesa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fondesa.lyra.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.fondesa.lyra.annotation.SaveState;

import java.io.Serializable;

/**
 * Wrapper class of generic models that could be used in test classes.
 */
public class TestModels {
    private TestModels() {
        // private empty constructor to avoid instantiation
    }

    public static class ImplementedCharSequence implements CharSequence {
        @Override
        public int length() {
            return 5;
        }

        @Override
        public char charAt(int index) {
            return 'x';
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return "test";
        }
    }

    public static class ImplementedParcelable implements Parcelable {
        public String firstParam;
        public String secondParam;
        public int thirdParam;

        public static ImplementedParcelable getDefault() {
            return new ImplementedParcelable("first", "second", 3);
        }

        public ImplementedParcelable(String firstParam, String secondParam, int thirdParam) {
            this.firstParam = firstParam;
            this.secondParam = secondParam;
            this.thirdParam = thirdParam;
        }

        protected ImplementedParcelable(Parcel in) {
            firstParam = in.readString();
            secondParam = in.readString();
            thirdParam = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(firstParam);
            dest.writeString(secondParam);
            dest.writeInt(thirdParam);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ImplementedParcelable> CREATOR = new Creator<ImplementedParcelable>() {
            @Override
            public ImplementedParcelable createFromParcel(Parcel in) {
                return new ImplementedParcelable(in);
            }

            @Override
            public ImplementedParcelable[] newArray(int size) {
                return new ImplementedParcelable[size];
            }
        };
    }

    public static class ImplementedSerializable implements Serializable {
        private static final long serialVersionUID = -1315746751902301329L;

        public String firstParam;
        public String secondParam;
        public int thirdParam;

        public static ImplementedSerializable getDefault() {
            return new ImplementedSerializable("first", "second", 3);
        }

        public ImplementedSerializable(String firstParam, String secondParam, int thirdParam) {
            this.firstParam = firstParam;
            this.secondParam = secondParam;
            this.thirdParam = thirdParam;
        }
    }

    public static class ZeroFields {
        /* empty */
    }

    public static class ZeroAnnotatedFields {
        private int a;
        protected String b;
        public double c;
        float d;
    }

    public static class PublicAnnotatedFields {
        @SaveState
        public int a;
        @SaveState
        public String b;
    }

    public static class MixedPublicFields {
        public int a;
        @SaveState
        public String b;
    }

    public static class AllModifiersAnnotatedFields {
        private int _a;
        protected String _b;
        public double _c;
        float _d;

        @SaveState
        private int a;
        @SaveState
        protected String b;
        @SaveState
        public double c;
        @SaveState
        float d;
    }

    public static class SubClassAllModifiersAnnotatedFields extends AllModifiersAnnotatedFields {
        private int _e;
        protected String _f;
        public double _g;
        float _h;

        @SaveState
        private ImplementedParcelable e;
        @SaveState
        protected ImplementedSerializable f;
        @SaveState
        public double g;
        @SaveState
        float h;
    }

    @SuppressLint("Registered")
    public static class SaveStateActivity extends Activity {
        private int _e;
        protected String _f;
        public double _g;
        float _h;

        @SaveState
        private ImplementedParcelable e;
        @SaveState
        protected ImplementedSerializable f;
        @SaveState
        public double g;
        @SaveState
        float h;
    }

    public static class SaveStateView extends View {
        @SaveState
        protected ImplementedSerializable f;
        @SaveState
        public double g;
        @SaveState
        float h;

        public SaveStateView(Context context) {
            super(context);
        }
    }
}
