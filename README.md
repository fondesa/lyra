<img src="https://raw.githubusercontent.com/Fondesa/Lyra/master/images/lyra_logo.png" height="196">

[![Build Status](https://travis-ci.org/Fondesa/Lyra.svg?branch=master)](https://travis-ci.org/Fondesa/Lyra)

Lyra is an open source library for Android that simplify the save and the restore of Android components' state.
Lyra supports automatic save/restore, base coders to serialize/deserialize values in `Bundle`, caching and retrieval of fields. Lyra includes a flexible API that allows developers to customize any aforementioned behavior.

By default, Lyra uses an internal serializer/deserializer for fields, but also provides a utility library to add serialization/deserialization capabilities of [Gson][1].

Integration
------

You can download the [latest JAR][2] or grab it from ```jcenter()``` or ```mavenCentral()```.
You can optionally use the dependency `lyra-coder-gson` if you want to include the [Gson][1] coder.

### Gradle ###

```gradle
dependencies {
    compile 'com.github.fondesa:lyra:1.0.0-rc1'
    // Use this dependency if you want to include the Gson coder.
    compile 'com.github.fondesa:lyra-coder-gson:1.0.0-rc1'
}
```

### Maven ###

```xml
<dependency>
  <groupId>com.github.fondesa</groupId>
  <artifactId>lyra</artifactId>
  <version>1.0.0-rc1</version>
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

Compatibility
------

**Android SDK**: Lyra requires a minimum API level of 9.

[1]: https://github.com/google/gson
[2]: https://repo1.maven.org/maven2/com/github/fondesa/lyra/1.0.0-rc1/lyra-1.0.0-rc1-sources.jar
