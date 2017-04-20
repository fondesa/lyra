-keepclassmembers class * implements com.fondesa.lyra.coder.StateCoder {
    <init>(...);
}
-keepclassmembers class ** {
    @com.fondesa.lyra.annotation.SaveState <fields>;
}