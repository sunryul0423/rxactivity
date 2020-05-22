# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\dev_tool\android_sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontshrink # 사용하지 않는 메소드 유지
-keepparameternames
-dontskipnonpubliclibraryclasses

# 클래스 중 public static과 public은 난독화하지 않음
-keep class srpark.rxactivity2.** {
    public static <fields>;
    public static <methods>;
    public <methods>;
}

-keepclassmembers class * {
    public static <fields>;
    #public *;
}

-keep interface * {
  <methods>;
  <fields>;
}

-keepattributes SourceFile, LineNumberTable, *Annotation*
#소스 파일 변수 명 바꾸는 옵션
-renamesourcefileattribute SourceFile
-ignorewarnings
#지정해서 워닝 무시할 때
#-dontwarn 패키지명.**