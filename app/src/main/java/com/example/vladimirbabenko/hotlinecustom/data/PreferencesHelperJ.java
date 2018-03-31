package com.example.vladimirbabenko.hotlinecustom.data;

import android.content.Context;
import com.example.vladimirbabenko.hotlinecustom.utils.AppConstants;

public class PreferencesHelperJ extends BasePreferencesHelperJ {

    public PreferencesHelperJ(Context context) {
        super(context);
    }

    public void clearUserPreferences() {
        setUserLoggedIn(false);
        setUserEmail("");
        //setUserPassword(-1);
        //setUserName("");
        //setUserSecondName("");
        //setDialogIsShown(false);
    }

    public void setUserLoggedIn(Boolean param) {
        //setBoolean(AppConstants.Prefs.IS_USER_LOGGED_IN_KEY, param);
        setBoolean(AppConstants.USER_EMAIL.getKey(), param);
    }
    public boolean isUserChecked(){
        return getBoolean(AppConstants.IS_USER_LOGGED.getKey());
    }

    public void setUserEmail(String email) {
        setString(AppConstants.USER_EMAIL.getKey(), email);
    }

    public String getUserEmain(){
        return getString(AppConstants.USER_EMAIL.getKey());
    }

    //public void setUserPassword(int password) {
    //    setInt(BooksConstants.PrefsConstants.USER_PASSWORD_KEY, password);
    //}
    //
    //public int getUserPassword(){
    //    return getInt(BooksConstants.PrefsConstants.USER_PASSWORD_KEY);
    //}
    //
    //public void setUserName(String name) {
    //    setString(BooksConstants.PrefsConstants.USER_NAME, name);
    //}
    //
    //public String getUserName(){
    //    return getString(BooksConstants.PrefsConstants.USER_NAME);
    //}
    //
    //public void setUserSecondName(String secondName) {
    //    setString(BooksConstants.PrefsConstants.USER_SECOND_NAME, secondName);
    //}
    //
    //public String getUserSecondName(){
    //    return getString(BooksConstants.PrefsConstants.USER_SECOND_NAME);
    //}
    //
    //public boolean isDialogShown() {
    //    return getBoolean(BooksConstants.PrefsConstants.IS_CONGRATULATION_SHOWN_KEY);
    //}
    //
    //public void setDialogIsShown(boolean isShown){
    //    setBoolean(BooksConstants.PrefsConstants.IS_CONGRATULATION_SHOWN_KEY, isShown);
    //}
}
