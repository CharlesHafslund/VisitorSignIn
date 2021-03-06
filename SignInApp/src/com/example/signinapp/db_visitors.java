package com.example.signinapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class db_visitors extends SQLiteOpenHelper{
	
	public static final String TABLE_VISITORS = "visitors";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_FNAME = "firstname";
	  public static final String COLUMN_LNAME = "lastname";

	  private static final String DATABASE_NAME = "visitors.db";
	  private static final int DATABASE_VERSION = 1;
	
	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_VISITORS 	+ "(" 
		  + COLUMN_ID 		+ " integer primary key autoincrement, " 
	      + COLUMN_FNAME 	+ " text not null, " 
	      + COLUMN_LNAME 	+ " text not null);";
	
	public db_visitors(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(db_visitors.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_VISITORS);
		    onCreate(db);
		
	}

}
