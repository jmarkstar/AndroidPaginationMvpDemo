package com.jmarkstar.carlist_core.domain.repository.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jmarkstar.carlist_core.R;
import com.jmarkstar.carlist_core.domain.repository.database.CarListDatabaseHelper;
import com.jmarkstar.carlist_core.domain.repository.database.entity.ItemData;
import com.jmarkstar.carlist_core.domain.repository.database.entity.ItemType;
import com.jmarkstar.carlist_core.exception.LocalDatabaseException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by jmarkstar on 27/08/2017.
 */
public class ItemDao {

    private String [] ALL_COLUMNS = {ItemData.NUMBER_FIELD, ItemData.NAME_FIELD, ItemData.TYPE_FIELD};

    private SQLiteDatabase mSQLiteDatabase;
    private CarListDatabaseHelper helper;

    @Inject public ItemDao(CarListDatabaseHelper helper){
        this.mSQLiteDatabase = helper.getWritableDatabase();
        this.helper = helper;
    }

    public void insertList(List<ItemData> dataList) throws LocalDatabaseException {
        try{
            if(!mSQLiteDatabase.isOpen())
                open();
            mSQLiteDatabase.beginTransaction();
            for (ItemData data : dataList){
                ContentValues contentValues = new ContentValues();
                contentValues.put(ItemData.NUMBER_FIELD, data.getNumber());
                contentValues.put(ItemData.NAME_FIELD, data.getName());
                contentValues.put(ItemData.MANUFACTURER_FIELD, data.getManufacturer());
                contentValues.put(ItemData.TYPE_FIELD, data.getType());
                mSQLiteDatabase.insert(ItemData.TABLE_NAME, null, contentValues);
            }
            mSQLiteDatabase.setTransactionSuccessful();
        }catch(Exception ex){
            ex.printStackTrace();
            throw new LocalDatabaseException(R.string.exception_db_insert_items);
        }finally {
            mSQLiteDatabase.endTransaction();
        }
    }

    public List<ItemData> getItemsByType(ItemType itemType, String manufacturer) throws LocalDatabaseException{
        try{
            if(!mSQLiteDatabase.isOpen())
                open();
            List<ItemData> items = new ArrayList<>();

            String selection;
            String [] selectionArgs;
            if(itemType == ItemType.MAIN_TYPE){
                selection = ItemData.TYPE_FIELD+"=? and "+ItemData.MANUFACTURER_FIELD+"=?";
                selectionArgs = new String[]{ String.valueOf(itemType.getValue()), manufacturer };
            }else{
                selection = ItemData.TYPE_FIELD+"=?";
                selectionArgs = new String[]{ String.valueOf(itemType.getValue()) };
            }

            Cursor cursor = mSQLiteDatabase.query(true, ItemData.TABLE_NAME, ALL_COLUMNS, selection, selectionArgs , null,null, null, null);
            while(cursor.moveToNext()){
                items.add(convertCursorToItemData(cursor));
            }
            cursor.close();
            return items;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new LocalDatabaseException(R.string.exception_db_get_items);
        }
    }

    public void deleteAll() throws LocalDatabaseException {
        try {
            if(!mSQLiteDatabase.isOpen())
                open();
            mSQLiteDatabase.delete(ItemData.TABLE_NAME, null, null);
        }catch(Exception ex){
            ex.printStackTrace();
            throw new LocalDatabaseException(R.string.exception_db_delete_items);
        }
    }

    private void open(){
        this.mSQLiteDatabase = helper.getWritableDatabase();
    }

    public void close(){
        this.mSQLiteDatabase.close();
    }

    private ItemData convertCursorToItemData(Cursor cursor){
        ItemData itemData = new ItemData();
        itemData.setNumber(cursor.getString(cursor.getColumnIndex(ItemData.NUMBER_FIELD)));
        itemData.setName(cursor.getString(cursor.getColumnIndex(ItemData.NAME_FIELD)));
        itemData.setType(cursor.getInt(cursor.getColumnIndex(ItemData.TYPE_FIELD)));
        return itemData;
    }
}
