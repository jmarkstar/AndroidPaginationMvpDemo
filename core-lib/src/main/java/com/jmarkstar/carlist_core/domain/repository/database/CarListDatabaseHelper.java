package com.jmarkstar.carlist_core.domain.repository.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jmarkstar.carlist_core.domain.repository.database.entity.ItemData;
import javax.inject.Inject;

/** Database helper
 * Created by jmarkstar on 27/08/2017.
 */
public class CarListDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "carlist.db";
    private static final Integer DATABASE_VERSION = 1;

    @Inject public CarListDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(stCreateItemTable.toString());
    }

    @Override public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    private StringBuilder stCreateItemTable = new StringBuilder()
        .append("CREATE TABLE IF NOT EXISTS "+ ItemData.TABLE_NAME+" ( ")
        .append(ItemData.ID_FIELD+" INTEGER PRIMARY KEY AUTOINCREMENT, ")
        .append(ItemData.NUMBER_FIELD+" TEXT, ")
        .append(ItemData.NAME_FIELD+" TEXT, ")
        .append(ItemData.MANUFACTURER_FIELD+" TEXT, ")
        .append(ItemData.TYPE_FIELD+" INTEGER,")
        .append("UNIQUE ("+ItemData.ID_FIELD+", "+ItemData.NUMBER_FIELD+", "+ItemData.TYPE_FIELD+"))");
}
