package com.notfaqs.build_info;

import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Surface;

public class Converter
{
    public static String densityDpiToString(int densityDpi)
    {
        switch(densityDpi)
        {
        case DisplayMetrics.DENSITY_LOW:
            return "ldpi";
        case DisplayMetrics.DENSITY_MEDIUM:
            // Same as DENSITY_DEFAULT
            return "mdpi";
        case DisplayMetrics.DENSITY_TV:
            return "tvdpi";
        case DisplayMetrics.DENSITY_HIGH:
            return "hdpi";
        case DisplayMetrics.DENSITY_XHIGH:
            return "xhdpi";
        case DisplayMetrics.DENSITY_XXHIGH:
            return "xxhdpi";
        case DisplayMetrics.DENSITY_XXXHIGH:
            return "xxxhdpi";
        }
        return "unknown";
    }
    
    @SuppressWarnings("deprecation")
    public static String orientationToString(int orientation)
    {
        switch(orientation)
        {
        case Configuration.ORIENTATION_UNDEFINED:
            return "undefined";
        case Configuration.ORIENTATION_PORTRAIT:
            return "portrait";
        case Configuration.ORIENTATION_LANDSCAPE:
            return "landscape";
        case Configuration.ORIENTATION_SQUARE:
            return "square";
        }
        return "unknown";
    }
    
    public static String rotationToString(int rotation)
    {
        switch(rotation)
        {
        case Surface.ROTATION_0:
            return "0°";
        case Surface.ROTATION_90:
            return "90°";
        case Surface.ROTATION_180:
            return "180°";
        case Surface.ROTATION_270:
            return "270°";
        }
        return "unknown";
    }
    
    public static String screenSizeToString(int screenSize)
    {
        switch(screenSize)
        {
        case Configuration.SCREENLAYOUT_SIZE_UNDEFINED:
            return "undefined";
        case Configuration.SCREENLAYOUT_SIZE_SMALL:
            return "small";
        case Configuration.SCREENLAYOUT_SIZE_NORMAL:
            return "normal";
        case Configuration.SCREENLAYOUT_SIZE_LARGE:
            return "large";
        case Configuration.SCREENLAYOUT_SIZE_XLARGE:
            return "xlarge";
        }
        return "unknown";
    }
}
