package com.kose.milkytea.viewmodel.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginStateViewModel extends ViewModel {
    public MutableLiveData<String> state = new MutableLiveData<>();
}
