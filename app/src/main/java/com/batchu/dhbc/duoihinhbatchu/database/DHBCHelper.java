package com.batchu.dhbc.duoihinhbatchu.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by keban on 4/6/2018.
 */

public class DHBCHelper extends SQLiteAssetHelper {

    private static final int SCHEMA_VERSION = 1;

    private String dap_an = "dapan";
    private String goi_y = "goiy";
    private String ket_qua = "ketqua";
    private String file_name = "filename";

    private String table = "cauhoi";

    public DHBCHelper(Context context) {
        super(context, "database.db", null, SCHEMA_VERSION);
    }

    public Cursor getCursor() {
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {dap_an, goi_y, ket_qua, file_name};

        Cursor cursor = db.query(table, columns, null, null, null, null, null);
        return cursor;
    }

    public String getDapan(Cursor cursor) {
        String dapan = cursor.getString(cursor.getColumnIndex(dap_an));
        return dapan;
    }

    public String getGoiy(Cursor cursor) {
        String goiy = cursor.getString(cursor.getColumnIndex(goi_y));
        return goiy;
    }

    public String getKetqua(Cursor cursor) {
        String ketqua = cursor.getString(cursor.getColumnIndex(ket_qua));
        return ketqua;
    }

    public String getFilename(Cursor cursor) {
        String filename = cursor.getString(cursor.getColumnIndex(file_name));
        return filename;
    }
}
