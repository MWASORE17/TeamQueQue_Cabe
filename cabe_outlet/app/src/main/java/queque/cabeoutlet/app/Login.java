package queque.cabeoutlet.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import queque.cabeoutlet.R;
import queque.cabeoutlet.dao.DBController;
import queque.cabeoutlet.dao.SessionManager;

/**
 * Created by Willy Laurents on 3/14/2017.
 */

public class Login extends AppCompatActivity{
    private TextInputLayout til_email,til_password;
    private EditText et_email,et_password;
    private Button btn_login;
    private DBController dbController;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        dbController = new DBController(this);
        sessionManager = new SessionManager(this);
        try {
            dbController.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        til_email = (TextInputLayout)findViewById(R.id.til_email);
        til_password = (TextInputLayout)findViewById(R.id.til_password);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean _isvalid = true;
                til_email.setErrorEnabled(false);
                til_password.setErrorEnabled(false);

                if(TextUtils.isEmpty(et_email.getText())){
                    til_email.setErrorEnabled(true);
                    til_email.setError("Email required");
                    _isvalid = false;
                }else if(TextUtils.isEmpty(et_password.getText())){
                    til_password.setErrorEnabled(true);
                    til_password.setError("Password required");
                    _isvalid = false;
                }

                if(_isvalid) {
                    String query = "SELECT * FROM db_outlet where outlet_email = '" + et_email.getText().toString() + "' AND outlet_password = '" + et_password.getText().toString() + "'";
                    HashMap<String, String> map = dbController.getDataWhere(query);
                    if (map != null) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        sessionManager.createLoginSession(map.get("outlet_id"),map.get("outlet_name"));
                    } else {
                        Toast.makeText(Login.this, "Akun tidak terdaftar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
