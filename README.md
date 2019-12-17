# PixaList
A library that use for displaying List of pixabay Image's

#### NOTE : IGNORE UPGRADE TO 3.7.81 ( KEEP USING Version .1.1.1 )


# Usage
If you dont have API_KEY, Please get API_KEY from [https://pixabay.com/api/docs/].



* ## Gradle ( Module App )
```gradle
implementation 'com.github.abkhrr:PixaList:1.1.1'
```

* ## NOTE: Important
This library needs dataBinding on projects
You have to enable dataBinding to true to get the image displayed
```gradle
android{
     dataBinding {
        enabled = true
    }
}
```

* ## Gradle ( Module App )
```gradle
implementation 'com.github.abkhrr:PixaList:1.1.1'
implementation 'androidx.recyclerview:recyclerview:1.1.0'
```
You have to Implements RecyclerView To projects.

* ## Gradle ( Project App )
### Add maven ( jitpack )
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

## 1. Adding Layout

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <com.adyabukhari.pixalist.PixaList
        android:id="@+id/MypixaList"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:columns="FILL" />

</RelativeLayout>
```
Use RelativeLayout instead.
You can change the columns style by defines the " app:columns=' ' "
There are three type of columns :
* FILL ( One Columns )
* TWO ( Two Columns )
* GRID ( Three Columns )

## 2. Defines in activity

After defines the layout you can defines in your activity :

```java



private String API_KEY = "14658201-2fd4b3bfec2ea493e9ac398b8"; // NOTE : YOU CAN PUT ON Values/String. THIS IS JUST EXAMPLE
private String query = "red cars"; // Defines type of images query
 
PixaList pixaList = (PixaList) findViewById(R.id.MypixaList);
        pixaList.APIKEY = 'YOUR_API_KEY';
        pixaList.currentQuery = 'query';
        pixaList.StartPixabayList();
```

#### You can put the APIKEY and query in String ( values/string ) or directly in your activity.

## 3. Manifest
```xml
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
```
Since this library call an api, you have to define permission ( internet and network_state ) to get Images List
