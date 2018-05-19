<img src="https://raw.githubusercontent.com/Fondesa/Lyra/master/images/lyra_logo.png" height="196">

[![Build Status](https://travis-ci.org/Fondesa/Lyra.svg?branch=master)](https://travis-ci.org/Fondesa/Lyra)

Lyra is an open source library for Android that simplifies the save and the restore of Android components' state.
Lyra supports automatic save/restore, base coders to serialize/deserialize values in `Bundle`, caching and retrieval of fields. Lyra includes a flexible API that allows developers to customize any aforementioned behavior.

By default, Lyra uses an internal serializer/deserializer for fields, but also provides a utility library to add serialization/deserialization capabilities of [Gson][1].

Integration
------

You can download a jar from GitHub's [releases page][2] or grab it from ```jcenter()``` or ```mavenCentral()```.
You can optionally use the dependency `lyra-coder-gson` if you want to include the [Gson][1] coder.

### Gradle ###

```gradle
dependencies {
    compile 'com.github.fondesa:lyra:1.0.1'
    // Use this dependency if you want to include the Gson coder.
    compile 'com.github.fondesa:lyra-coder-gson:1.0.1'
}
```

### Maven ###

```xml
<dependency>
  <groupId>com.github.fondesa</groupId>
  <artifactId>lyra</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

ProGuard
--------

If you are using ProGuard, you need to include the following lines to your proguard configuration file.

```pro
-keepclassmembers class * implements com.fondesa.lyra.coder.StateCoder {
    <init>(...);
}
-keepclassmembers class ** {
    @com.fondesa.lyra.annotation.SaveState <fields>;
}
```

Usage
------

You have to initialize the Lyra instance in your `Application`.
You can use the short version with only required options:

```java
@Override
public void onCreate() {
    super.onCreate();
    // Pass the Application's Context to the instance.
    Lyra.with(this).build();
}
```

Or the full version to customize each component:

```java
@Override
public void onCreate() {
    super.onCreate();
    // Create the builder and pass the Application's Context.
    Lyra.Builder builder = Lyra.with(this)
            // Optional, the default is: DefaultCoderRetriever.
            .coderRetriever(new CustomCoderRetriver())
            // Optional, the default is: DefaultFieldsRetriever.
            .fieldsRetriever(new CustomFieldsRetriever());
    
    // Automatic save state is available only above api 14.
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
        // Optional, the default will not save the state of the Activities automatically.
        builder.autoSaveActivities();
    }
    // Build the instance.
    builder.build();
}
```

You have to annotate the fields that you want to save with the annotation `@SaveState` and, if needed, you have to call the methods `saveState()` and `restoreState()`:

```java
public class MainActivity extends Activity {
    @SaveState
    private int mCount;

    @SaveState
    ParcelableModel mModel;

    // If you want to use a custom coder, you can specify the class.
    @SaveState(CustomStringCoder.class)
    private String mText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Necessary only if you aren't in an Activity or you 
        haven't specified to auto save Activities in the Lyra instance. */
        Lyra.instance().restoreState(this, savedInstanceState);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /* Necessary only if you aren't in an Activity or you 
        haven't specified to auto save Activities in the Lyra instance. */
        Lyra.instance().saveState(this, outState);
    }
}
```

The save/restore of the state is supported also in a custom `View`. For example:

```java
public class AutoSaveEditText extends AppCompatEditText {
    @SaveState
    CharSequence mText;

    @Override
    public Parcelable onSaveInstanceState() {
        return Lyra.instance().saveState(this, super.onSaveInstanceState());
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Lyra.instance().restoreState(this, state));
    }
}
```

As shown above, you can create your own custom `StateCoder`. For example, this coder will save/restore a `String` in `Base64`:

```java
public class CustomStringCoder implements StateCoder<String> {
    @Override
    public void serialize(@NonNull Bundle state, @NonNull String key, @NonNull String fieldValue) {
        try {
            byte[] data = fieldValue.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
            state.putString(key, base64);
        } catch (UnsupportedEncodingException ignored) {}
    }

    @Override
    public String deserialize(@NonNull Bundle state, @NonNull String key) {
        String base64 = state.getString(key);
        if (base64 == null)
            return null;
            
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException ignored) {
            return null;
        }
    }
}
```

Compatibility
------

**Android SDK**: Lyra requires a minimum API level of 9.


[1]: https://github.com/google/gson
[2]: https://github.com/Fondesa/Lyra/releases
