package com.bgmiddleeast.Utilities;


import android.content.Context;
import android.content.SharedPreferences;

public class AppManager {

	

	private final static String PREF_TAG = "sfsettings";
	private static final String PREF_TAG_VERSION = "version";
	private static final String PREF_TAG_LANGUAGE= "language";
	private static final String PREF_TAG_MAX_VAL= "value";
	private static final String PREF_TAG_ISLOGIN= "login";
	private static final String PREF_TAG_USER_ID= "user_id";
	private static final String PREF_TAG_TOKEN_CODE= "token";
    private static final String PREF_TAG_PUSH_TOKEN_CODE= "push_token";




	private SharedPreferences mSharedPreference;
	private static AppManager mAppManager;
	private Context mContext;
	
	
	
	private AppManager(Context context) {
		
		
		this.mContext = context;
		mSharedPreference = mContext.getSharedPreferences(PREF_TAG, Context.MODE_PRIVATE);

		
		
	} 
	
	
	
	public static synchronized AppManager getInstance(Context context) {
		
		if (mAppManager == null)
			mAppManager = new AppManager(context);
		return mAppManager;
		
	}
	
	/*
	 * This is a singleton class. so we must avoid cloning the objects. DO NOT
	 * ALTER THE CODE.
	 */
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		throw new CloneNotSupportedException();
	}


	public void saveVersionCode(String version) {

		SharedPreferences.Editor e = mSharedPreference.edit();
		e.putString(PREF_TAG_VERSION, version);
		e.commit();
	}

	public String getVersionCode() {

		return mSharedPreference.getString(PREF_TAG_VERSION,"0");
	}


	public void saveSystemLanguage(String language) {

		SharedPreferences.Editor e = mSharedPreference.edit();
		e.putString(PREF_TAG_LANGUAGE, language);
		e.commit();
	}

	public String getSystemLanguage() {

		return mSharedPreference.getString(PREF_TAG_LANGUAGE, "");
	}


	public void saveMaxValue(int val) {

		SharedPreferences.Editor e = mSharedPreference.edit();
		e.putInt(PREF_TAG_MAX_VAL, val);
		e.commit();
	}

	public int getMaxValue() {

		return mSharedPreference.getInt(PREF_TAG_MAX_VAL, 0);
	}

	public void saveIsLogin(boolean val) {

		SharedPreferences.Editor e = mSharedPreference.edit();
		e.putBoolean(PREF_TAG_ISLOGIN, val);
		e.commit();
	}

	public boolean getIsLogin() {

		return mSharedPreference.getBoolean(PREF_TAG_ISLOGIN, false);
	}
	public void saveUserId(String id) {

		SharedPreferences.Editor e = mSharedPreference.edit();
		e.putString(PREF_TAG_USER_ID, id);
		e.commit();
	}

	public String getUserId() {

		return mSharedPreference.getString(PREF_TAG_USER_ID, "");
	}

	public void saveTokenCode(String id) {

		SharedPreferences.Editor e = mSharedPreference.edit();
		e.putString(PREF_TAG_TOKEN_CODE, id);
		e.commit();
	}

	public String getTokeCode() {

		return mSharedPreference.getString(PREF_TAG_TOKEN_CODE, "");
	}

    public void savePushToken(String id) {

        SharedPreferences.Editor e = mSharedPreference.edit();
        e.putString(PREF_TAG_PUSH_TOKEN_CODE, id);
        e.commit();
    }

    public String getPushToken() {

        return mSharedPreference.getString(PREF_TAG_PUSH_TOKEN_CODE, "");
    }


}





