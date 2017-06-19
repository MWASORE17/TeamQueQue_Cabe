package queque.cabe.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by WillyLaurents on 3/13/2017.
 */

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;
    private Context context;
    private int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "preferences";

    private static final String isLogged = "login";

    private static final String skipLogin = "skip_login";

    private static final String user_id = "user_id";
    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        sharedEditor = sharedPreferences.edit();
    }

    public void createLoginSession(String id){
        sharedEditor.putBoolean(isLogged,true);
        sharedEditor.putString(user_id,id);
        sharedEditor.commit();
    }

    public boolean isLogged(){
        return sharedPreferences.getBoolean(isLogged,false);
    }
}
