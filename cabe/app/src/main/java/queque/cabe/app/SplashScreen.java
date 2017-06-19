package queque.cabe.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import queque.cabe.R;
import queque.cabe.dao.DBController;
import queque.cabe.utilities.SessionManager;

/**
 * Created by Willy Laurents on 3/10/2017.
 */

public class SplashScreen extends Activity{
    private DBController dbController;
    private SessionManager sessionManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        dbController = new DBController(this);
        sessionManager = new SessionManager(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    dbController.createDataBase();
                    if(sessionManager.isLogged()) {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(SplashScreen.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },1000);
    }
}
