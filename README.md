# RxActivity
RxJava2를 이용한 startActivityForResult(), requestPermissions()
-
- 라이브러리를 통해 코드 간편화 시킬수 있으며 RxJava2 형태로 구현할 수 있다.

### [1] 사용법
* RxActivity.from(context).activityForResult(intent)
* RxActivity.from(context).request(permissions)
<pre>
<code>   
    RxActivity.from(context)                           // 사용 세팅
            .activityForResult(intent)                 // intent Setting-> return Single<'T'>
            .observeOn(AndroidSchedulers.mainThread()) // UI Thread
            .subscribe(/*result -> */);                // result.getResultCode() : int resultCode;
                                                       // result.getData() : Intent data;

--------------------------------------------------------------------------------------------------

    RxActivity.from(context)                           // 사용 세팅
            .request(permissions)                      // permissions[]:array-> return Single<'T'>
            .observeOn(AndroidSchedulers.mainThread()) // UI Thread
            .subscribe(/*result -> */);                // result.isGranted() : boolean isGranted
                                                       // result.getPermission() : String permission
</code>
</pre>

### [2] Project Gradle
<pre>
<code>   
    buildscript {
        maven { url "https://srpark.bintray.com/maven" }
    }
</code>
</pre>

### [3] Dependencies
<pre>
<code>   
    // Rx
    implementation 'io.reactivex.rxjava2:rxjava:2.2.10'
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'srpark.rxactivity2:rxactivity:2.0.3'
</code>
</pre>
