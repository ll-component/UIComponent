package com.ll.uicomponentexample.example.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ProjectName:    UIComponentExample
 * Package:        com.ll.uicomponentexample.example.bean
 * ClassName:      MyAidlBean
 * Author:         dev-gxy
 * CreateDate:     2021/5/13 12:36
 * Description:
 */
public class MyAidlBean implements Parcelable {

    private String name;
    private String age;

    public MyAidlBean() {
    }

    protected MyAidlBean(Parcel in) {
        name = in.readString();
        age = in.readString();
    }

    public static final Creator<MyAidlBean> CREATOR = new Creator<MyAidlBean>() {
        @Override
        public MyAidlBean createFromParcel(Parcel in) {
            return new MyAidlBean(in);
        }

        @Override
        public MyAidlBean[] newArray(int size) {
            return new MyAidlBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
    }
}
