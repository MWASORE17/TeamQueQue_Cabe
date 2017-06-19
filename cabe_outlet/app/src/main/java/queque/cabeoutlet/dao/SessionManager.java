package queque.cabeoutlet.dao;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by willylaurents on 06/06/17.
 */

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;
    private Context context;
    private int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "preferences";

    public static final String key_isLogged = "login";

    public static final String key_skipLogin = "skip_login";

    public static final String key_user_id = "user_id";

    public static final String key_outlet_name = "outlet_name";
    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        sharedEditor = sharedPreferences.edit();
    }

    public void createLoginSession(String id,String outletName){
        sharedEditor.putBoolean(key_isLogged,true);
        sharedEditor.putString(key_user_id,id);
        sharedEditor.putString(key_outlet_name,outletName);
        sharedEditor.commit();
    }

    public String getUserId(){
        return sharedPreferences.getString(key_user_id,null);
    }

    public String getOutletName(){
        return sharedPreferences.getString(key_outlet_name,null);
    }

    public boolean isLogged(){
        return sharedPreferences.getBoolean(key_isLogged,false);
    }

    public void clearSession(){
        sharedEditor.clear();
        sharedEditor.commit();
    }
}
