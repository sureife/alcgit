package com.alc.alcgit.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sureife on 10/03/2017.
 */

public class Utilities {
    public static void showToast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
