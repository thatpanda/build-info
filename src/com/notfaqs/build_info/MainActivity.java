// Reference
// http://developer.android.com/reference/android/os/Build.html
// https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/java/android/os/Build.java

package com.notfaqs.build_info;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
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
        sb.append(getString(R.string.build_header) + "\n");
        sb.append(getFields(new android.os.Build()) + "\n");
        sb.append(getString(R.string.build_version_header) + "\n");
        sb.append(getFields(new android.os.Build.VERSION()));
        
        final TextView t = (TextView) findViewById(R.id.label);
        t.setMovementMethod(new ScrollingMovementMethod());
        t.setText(sb.toString());
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
                sb.append(String.format("%s = %s\n", field.getName(), field.get(obj)));
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
