// IMyAidlInterface.aidl
package com.ll.uicomponentexample;

// Declare any non-default types here with import statements
import com.ll.uicomponentexample.MyAidlBean;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    MyAidlBean getData();
}
