package com.example.go;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.legacy.content.WakefulBroadcastReceiver;

import android.Manifest;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.icu.util.VersionInfo;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;

import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private WebView webView;
    public String urll;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> mUploadMessages;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;
    public  int pp=0;
    private Uri mCapturedImageURI = null;
    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://rkpradheep.epizy.com";
        webView = findViewById(R.id.web);
        webView.loadUrl(url);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setGeolocationDatabasePath( MainActivity.this.getFilesDir().getPath() );

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(new VersionJSInterface(), "version");
        webView.setInitialScale(1);

        if (!hasPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
            // Permission ask
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
        } else {
// if permission is already granted than load url
            webView.loadUrl(url);

        }


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // DO NOT CALL SUPER METHOD

                handler.proceed();
            }





            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                try {

                } catch (Exception e) {
                }
                if (webView.canGoBack()) {
                    webView.goBack();
                }



                webView.loadUrl("about:blank");
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Error:\nCheck your internet connection.");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });

                alertDialog.show();
                super.onReceivedError(webView, errorCode, description, failingUrl);


            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                urll=url;
                if(url.indexOf("calc.html")!=-1)
                {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity ( intent );
                }

                if((url.indexOf("rejected.html")!=-1 ))
                {
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                    return true;
                }
                else
                {


                    if (url.indexOf("pqpq") != -1 ||url.indexOf("firebase")!=-1||url.indexOf("rkpradheep")!=-1)
                    {
                        return false;

                    } else {

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                        return true;

                    }}
            }

        });

        webView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                String fileName = URLUtil.guessFileName(url,contentDisposition,mimetype);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName);

                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();

            }
        });


        webView.setWebChromeClient(new WebChromeClient() {
            //Other methods for your WebChromeClient here, if needed..



            @Override
            public void onGeolocationPermissionsShowPrompt(final String origin,
                                                           final GeolocationPermissions.Callback callback) {
                // Always grant permission since the app itself requires location
                // permission and the user has therefore already granted it
                callback.invoke(origin, true, false);



            }

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType){
                mUploadMessage = uploadMsg;
                openImageChooser();
            }

            // For Lollipop 5.0+ Devices

            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                mUploadMessages = filePathCallback;
                openImageChooser();
                return true;
            }

            // openFileChooser for Android < 3.0

            public void openFileChooser(ValueCallback<Uri> uploadMsg){
                openFileChooser(uploadMsg, "");
            }

            //openFileChooser for other Android versions

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooser(uploadMsg, acceptType);
            }

            private void openImageChooser() {
                try {

                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                    Intent chooserIntent = Intent.createChooser(i,"");
                    startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }








            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);

            }
        });

    }
    @Override


    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILECHOOSER_RESULTCODE) {

            if (null == mUploadMessage && null == mUploadMessages) {
                return;
            }

            if (null != mUploadMessage) {
                handleUploadMessage(requestCode, resultCode, data);

            } else if (mUploadMessages != null) {
                handleUploadMessages(requestCode, resultCode, data);
            }
        }





    }

    private void handleUploadMessage(final int requestCode, final int resultCode, final Intent data) {
        Uri result = null;
        try {
            if (resultCode != RESULT_OK) {
                result = null;
            } else {
                // retrieve from the private variable if the intent is null

                result = data == null ? mCapturedImageURI : data.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mUploadMessage.onReceiveValue(result);
        mUploadMessage = null;

        // code for all versions except of Lollipop
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            result = null;

            try {
                if (resultCode != RESULT_OK) {
                    result = null;
                } else {
                    // retrieve from the private variable if the intent is null
                    result = data == null ? mCapturedImageURI : data.getData();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "activity :" + e, Toast.LENGTH_LONG).show();
            }

            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }

    } // end of code for all versions except of Lollipop






    private void handleUploadMessages(final int requestCode, final int resultCode, final Intent data) {
        Uri[] results = null;
        try {
            if (resultCode != RESULT_OK) {
                results = null;
            } else {
                if (data != null) {
                    String dataString = data.getDataString();
                    ClipData clipData = data.getClipData();
                    if (clipData != null) {
                        results = new Uri[clipData.getItemCount()];
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            ClipData.Item item = clipData.getItemAt(i);
                            results[i] = item.getUri();

                        }
                    }
                    if (dataString != null) {
                        results = new Uri[]{Uri.parse(dataString)};
                    }
                } else {
                    results = new Uri[]{mCapturedImageURI};
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mUploadMessages.onReceiveValue(results);
        mUploadMessages = null;
    }

















    @Override
    public void onBackPressed () {
        if(urll.indexOf("rkpradheep.epizy")!=-1&&urll.indexOf(".php")==-1)
        {  finishAffinity();
        }
        if (webView.canGoBack()) {
            webView.goBack();
      } else {
           super.onBackPressed();
      }

    }


    public static class VersionJSInterface {
        @JavascriptInterface
        public String getVersionName(){
            return BuildConfig.VERSION_NAME;
        }
    }


}

