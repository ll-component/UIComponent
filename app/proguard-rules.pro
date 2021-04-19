# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# glide 代码混淆
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# ARouter 混淆
# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
-keep class com.google.gson.examples.android.model.** {*;}
-keep class com.google.gson.** {*;}
-dontwarn com.bumptech.glide.**
#bugly
-keep class * extends java.lang.annotation.Annotation { *; }
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep  class androidx.core.app.CoreComponentFactory {*;}
-keep public class com.tencent.** { *; }

-dontoptimize
-dontpreverify

-keep class androidx.core.** { *; }
-keep class com.scwang.smart.refresh.** { *; }
-keepclasseswithmembernames class * {
  native <methods>;
}
# 保持Parcelable不被混淆
-keep class * implements Android.os.Parcelable {
   public static final Android.os.Parcelable$Creator *;
 }
 #保持所有实现 Serializable 接口的类成员
 -keepclassmembers class * implements java.io.Serializable {
     static final long serialVersionUID;
     private static final java.io.ObjectStreamField[] serialPersistentFields;
     private void writeObject(java.io.ObjectOutputStream);
     private void readObject(java.io.ObjectInputStream);
     java.lang.Object writeReplace();
     java.lang.Object readResolve();
 }
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class com.zui.** {*;}
-keep class com.miui.** {*;}
-keep class com.heytap.** {*;}
-keep class a.** {*;}
-keep class com.vivo.** {*;}
-keep public class com.ll.uicomponent.R$*{
public static final int *;
}
-keep public class com.ll.uicomponentexample.R$*{
public static final int *;
}
# 腾讯x5webview混淆
-dontwarn dalvik.**

#-keep class com.chad.library.adapter.base.viewholder.BaseDataBindingHolder {*;}
-keep public class * extends com.chad.library.adapter.base.viewholder.BaseViewHolder{
<init>(...);
}
-keepclassmembers class **$** extends com.chad.library.adapter.base.viewholder.BaseViewHolder {
<init>(...);
}