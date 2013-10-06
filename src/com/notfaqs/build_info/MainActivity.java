// Reference
// http://developer.android.com/reference/android/os/Build.html
// https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/os/Build.java

package com.notfaqs.build_info;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.TextView;

public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.dialog_title);
        
        final StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.display_header) + "\n");
        sb.append(getDisplayMetrics() + "\n");
        sb.append(getString(R.string.build_header) + "\n");
        sb.append(getFields(new android.os.Build()) + "\n");
        sb.append(getString(R.string.build_version_header) + "\n");
        sb.append(getFields(new android.os.Build.VERSION()));
        
        final TextView t = (TextView) findViewById(R.id.label);
        t.setMovementMethod(new ScrollingMovementMethod());
        t.setText(sb.toString());
    }
    
    private String getDensity()
    {
        final Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return String.format(getString(R.string.density_format),
                             dm.density,
                             Converter.densityDpiToString(dm.densityDpi),
                             dm.densityDpi);
    }
    
    private String getOrientation()
    {
        final Configuration config = getResources().getConfiguration();
        return String.format(getString(R.string.orientation_format),
                             Converter.orientationToString(config.orientation));
    }
    
    private String getResolution()
    {
        final Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return String.format(getString(R.string.resolution_format),
                             Math.max(dm.widthPixels, dm.heightPixels),
                             Math.min(dm.widthPixels, dm.heightPixels));
    }
    
    private String getRotation()
    {
        final Display display = getWindowManager().getDefaultDisplay();
        return String.format(getString(R.string.rotation_format),
                             display.getRotation(),
                             Converter.rotationToString(display.getRotation()));
    }
    
    private String getScreenSize()
    {
        final int screenLayout = getResources().getConfiguration().screenLayout;
        final int screenSize = screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        return String.format(getString(R.string.screen_size_format),
                             Converter.screenSizeToString(screenSize));
    }
    
    private String getDisplayMetrics()
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(getDensity() + "\n");
        sb.append(getOrientation() + "\n");
        sb.append(getResolution() + "\n");
        sb.append(getRotation() + "\n");
        sb.append(getScreenSize() + "\n");
        
        final String result = sb.toString();
        Log.i("Display metrics", result);
        return result;
    }
    
    private String getFields(final Object obj)
    {
        final Class<?> c = obj.getClass();
        
        final Field[] fields = c.getFields();
        Log.d(c.getName(), "Number of fields = " + fields.length);
        
        final StringBuilder sb = new StringBuilder();
        for(Field field : fields)
        {
            try
            {
                sb.append(String.format("%1$s = %2$s\n", field.getName(), field.get(obj)));
            }
            catch(IllegalAccessException e)
            {
                Log.e(c.getName(), e.toString());
            }
        }
        final String result = sb.toString();
        Log.i(c.getName(), result);
        return result;
    }
}
