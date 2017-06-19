package queque.cabeoutlet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.io.IOException;

import queque.cabeoutlet.R;
import queque.cabeoutlet.dao.DBController;
import queque.cabeoutlet.dao.SessionManager;

/**
 * Created by willylaurents on 09/06/17.
 */
public class SplashScreen extends Activity {
    private DBController dbController;
    private SessionManager sessionManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        dbController = new DBController(SplashScreen.this);
                        sessionManager = new SessionManager(SplashScreen.this);
                        dbController.createDataBase();
                        if (sessionManager.isLogged()) {
                            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(SplashScreen.this, Login.class);
                            startActivity(intent);
                            finish();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 1000);
        }
}