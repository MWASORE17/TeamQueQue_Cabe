package queque.cabeoutlet.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by willy on 3/14/2017.
 */

public class DBController extends SQLiteOpenHelper {
    public static String db_category = "db_category";
    public static String db_menu = "db_menu";
    public static String db_outlet = "db_outlet";
    public static String db_transaction = "db_transaction";
    public static String db_user = "db_user";
    public static String db_wishlist = "db_wishlist";
    public static String DB_NAME = "cabeOutlet.db";
    public static final int DATABASE_VERSION = 1;
    private static String DB_PATH ;
    private Context context;
    private SQLiteDatabase myDataBase;

    public DBController(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        PackageManager pM = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            PackageInfo p = pM.getPackageInfo(packageName, 0);
            packageName = p.applicationInfo.dataDir;
            DB_PATH = packageName +"/databases/";
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("SQLiteOpenHelper", "Error Package name not found ", e);
        }
        this.context = context;
    }

    public void createDataBase() throws IOException {
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
                Log.e("DataBaseHelper", "database created");
            }
            catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

//    public ArrayList<HashMap<String,String>> getUserData(){
//        ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String,String>>();
//        myDataBase = this.getReadableDatabase();
//        String query = "SELECT * from db_user";
//        Cursor cursor = myDataBase.rawQuery(query,null);
//        if(cursor.getCount()>0){
//            if(cursor.moveToFirst()){
//                do{
//                    HashMap<String,String> map = new HashMap<>();
//                    for(int i = 0;i<cursor.getColumnCount();i++){
//                        map.put(cursor.getColumnName(i),cursor.getString(i));
//                    }
//                    arrayList.add(map);
//                }while(cursor.moveToNext());
//            }
//        }else{
//            arrayList = null;
//        }
//        cursor.close();
//        return arrayList;
//    }

    public ArrayList<HashMap<String,String>> getAllDataFromTable(String table){
        ArrayList<HashMap<String,String>> arrayList = new ArrayList<HashMap<String,String>>();
        myDataBase = this.getReadableDatabase();
        String query = "SELECT * from "+table+"";
        Cursor cursor = myDataBase.rawQuery(query,null);
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                do{
                    HashMap<String,String> map = new HashMap<>();
                    for(int i = 0;i<cursor.getColumnCount();i++){
                        map.put(cursor.getColumnName(i),cursor.getString(i));
                    }
                    arrayList.add(map);
                }while(cursor.moveToNext());
            }
        }else{
            arrayList = null;
        }
        cursor.close();
        return arrayList;
    }

    public HashMap<String,String> getDataWhere(String table,String where,String value) {
        HashMap<String,String> hashMap = new HashMap<>();
        myDataBase = this.getReadableDatabase();
        String query = "SELECT * FROM " + table + " where " + where + " = '" + value + "'";
        Cursor cursor = myDataBase.rawQuery(query,null);
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                do{
                    for(int i = 0;i<cursor.getColumnCount();i++){
                        hashMap.put(cursor.getColumnName(i),cursor.getString(i));
                    }
                }while(cursor.moveToNext());
            }
        }else{
            hashMap = null;
        }
        cursor.close();
        return hashMap;
    }

    public HashMap<String,String> getDataWhere(String table,String where, String value, String where1 ,String value1) {
        HashMap<String,String> hashMap = new HashMap<>();
        myDataBase = this.getReadableDatabase();
        String query = "SELECT * FROM " + table + " where " + where + " = '" + value + "' AND " + where1 + " = '" +value1 +"'";
        Cursor cursor = myDataBase.rawQuery(query,null);
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                do{
                    for(int i = 0;i<cursor.getColumnCount();i++){
                        hashMap.put(cursor.getColumnName(i),cursor.getString(i));
                    }
                }while(cursor.moveToNext());
            }
        }else{
            hashMap = null;
        }
        cursor.close();
        return hashMap;
    }

    public HashMap<String,String> getDataWhere(String query){
        HashMap<String,String> hashMap = new HashMap<>();
        myDataBase = this.getReadableDatabase();
        Cursor cursor = myDataBase.rawQuery(query,null);
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                do{
                    for(int i = 0;i<cursor.getColumnCount();i++){
                        hashMap.put(cursor.getColumnName(i),cursor.getString(i));
                    }
                }while(cursor.moveToNext());
            }
        }else{
            hashMap = null;
        }
        cursor.close();
        return hashMap;
    }

    public void insertDataTo(String table,ArrayList<String> values){
        myDataBase = this.getReadableDatabase();
        ArrayList<String> colomnName = getColumnName(table);
        ContentValues contentValues = new ContentValues();
        for(int i = 1;i<colomnName.size();i++){
            contentValues.put(colomnName.get(i),values.get(i));
        }
        myDataBase.insert(table,null,contentValues);
        myDataBase.close();
    }

    private ArrayList<String> getColumnName(String table){
        ArrayList<String> arrayList = new ArrayList<>();
        String query = "SELECT * from "+table+"";
        Cursor cursor = myDataBase.rawQuery(query,null);
        for(int i = 0;i<cursor.getColumnCount();i++){
            arrayList.add(cursor.getColumnName(i));
        }
        return arrayList;
    }

    public ArrayList<HashMap<String,String>> getNearMeData(String table, String myLat,String myLng){
        ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
        myDataBase = this.getReadableDatabase();
        String query = "SELECT *, ( 3959 * acos( cos( radians("+myLat+") ) * cos( radians( lat ) ) * cos( radians( lng ) - radians("+myLng+") ) + sin( radians("+myLat+") ) * sin( radians( lat ) ) ) ) AS distance FROM "+table+" HAVING distance < 25 ORDER BY distance LIMIT 0 , 20;";
        Cursor cursor = myDataBase.rawQuery(query,null);
        if(cursor.getCount()>0){
            if(cursor.moveToFirst()){
                do{
                    HashMap<String,String> map = new HashMap<>();
                    for(int i = 0;i<cursor.getColumnCount();i++){
                        map.put(cursor.getColumnName(i),cursor.getString(i));
                    }
                    arrayList.add(map);
                }while(cursor.moveToNext());
            }
        }else{
            arrayList = null;
        }
        cursor.close();
        return arrayList;
//        SELECT * FROM Coords WHERE (latitude BETWEEN 25.81 AND 25.91) AND (longitude BETWEEN 56.98 AND 57.08);
    }
}
