package com.ll.uicomponentexample.example.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.ll.uicomponentexample.BR;

/**
 * ProjectName:    UIComponentExample
 * Package:        com.ll.uicomponentexample.example.bean
 * ClassName:      FormExampleBean
 * Author:         dev-gxy
 * CreateDate:     2021/4/25 14:45
 * Description:
 */
public class FormExampleBean extends BaseObservable {

    private String name;
    private String hint;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
