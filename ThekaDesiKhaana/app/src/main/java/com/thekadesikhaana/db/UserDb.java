package com.thekadesikhaana.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import model.UserProfileModel;
import model.Wallet;

/**
 * Created by ParmeshMahore on 2/23/17.
 */

public class UserDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user";
    private static final int VERSION = 1;
    private static String TAG = UserDb.class.getSimpleName();

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL_ID = "email";
    private static final String COLUMN_USER_PHONE = "phone";
    private static final String COLUMN_USER_DOB = "dob";
    private static final String COLUMN_USER_GENDER = "gender";
    private static final String COLUMN_WALLET_BALANCE = "wallet_balance";
    private static final String COLUMN_PROMOTION_BALANCE = "promo_balance";
    private static final String COLUMN_REFERRAL_CODE = "refferal_code";
    private static final String COLUMN_REFREE_CODE = "referee_code";
    private static final String COLUMN_IMAGE_URL = "img_url";

    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME
            + " TEXT," + COLUMN_USER_EMAIL_ID + " TEXT," + COLUMN_USER_PHONE
            + " TEXT," + COLUMN_USER_DOB + " TEXT," + COLUMN_USER_GENDER
            + " TEXT," + COLUMN_WALLET_BALANCE + " TEXT," + COLUMN_PROMOTION_BALANCE
            + " TEXT," + COLUMN_REFERRAL_CODE + " TEXT," + COLUMN_REFREE_CODE
            + " TEXT," + COLUMN_IMAGE_URL +" TEXT"+")";

    public UserDb(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_USER);
        onCreate(db);
    }

    public long saveUserInDB(UserProfileModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL_ID, user.getEmail());
        values.put(COLUMN_USER_PHONE, user.getPhone());
        values.put(COLUMN_USER_DOB, user.getDob());
        values.put(COLUMN_USER_GENDER, user.getGender());
        values.put(COLUMN_IMAGE_URL, "");
        values.put(COLUMN_WALLET_BALANCE, user.getWallet().getWalletBalance());
        values.put(COLUMN_PROMOTION_BALANCE, user.getWallet().getPromotionalBalance());
        values.put(COLUMN_REFERRAL_CODE, user.getReferralCode());
        values.put(COLUMN_REFREE_CODE, user.getRefereeCode());

        return db.insert(TABLE_USER, null, values);

    }

    /*
 * get single todo
 */
    public UserProfileModel getUserFromDB() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        Log.e(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        UserProfileModel user = null;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            user = new UserProfileModel();
            user.setName(c.getString(c.getColumnIndex(COLUMN_USER_NAME)));
            user.setEmail((c.getString(c.getColumnIndex(COLUMN_USER_EMAIL_ID))));
            user.setPhone(c.getString(c.getColumnIndex(COLUMN_USER_PHONE)));
            user.setGender(c.getString(c.getColumnIndex(COLUMN_USER_GENDER)));
            user.setDob(c.getString(c.getColumnIndex(COLUMN_USER_DOB)));
            user.setRefereeCode(c.getString(c.getColumnIndex(COLUMN_REFREE_CODE)));
            user.setReferralCode(c.getString(c.getColumnIndex(COLUMN_REFERRAL_CODE)));

            Wallet wallet = new Wallet();
            wallet.setWalletBalance(c.getString(c.getColumnIndex(COLUMN_WALLET_BALANCE)));
            wallet.setPromotionalBalance(c.getString(c.getColumnIndex(COLUMN_PROMOTION_BALANCE)));

            user.setWallet(wallet);

            c.close();
        }

        return user;
    }
}
