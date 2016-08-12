# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/davidzou/Android/sdk/tools/proguard/proguard-android.txt
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

#Coroutines
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}


#For intrepid classes
-dontwarn io.intrepid.koroutines.**

#From Moshi Readme
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keep @com.squareup.moshi.JsonQualifier interface *
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

#Retrofit
-dontwarn retrofit2.Platform$Java8

#Bouncy
-dontwarn org.bouncycastle.**
-dontwarn scala.**
-dontwarn com.google.common.util.concurrent.**
-dontwarn com.fasterxml.jackson.databind.**

#Disable Notes
-dontnote okhttp3.**
-dontnote kotlin.**
-dontnote io.fabric.**
-dontnote google.gson.**
-dontnote com.google.**
-dontnote com.crashlytics.**
-dontnote com.squareup.moshi.**
-dontnote io.reactivex.**
-dontnote io.intrepid.**