package com.example.signinapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class VisitorsDataSource {
	// Database fields
	private SQLiteDatabase database;
	private db_visitors dbHelper;
	private String[] allColumns = { db_visitors.COLUMN_ID,
			db_visitors.COLUMN_FNAME, db_visitors.COLUMN_LNAME };

	public VisitorsDataSource(Context context) {
		dbHelper = new db_visitors(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Visitor createComment(String firstName, String lastName) {
		ContentValues values = new ContentValues();
		values.put(db_visitors.COLUMN_FNAME, firstName);
		values.put(db_visitors.COLUMN_LNAME, lastName);
		long insertId = database.insert(db_visitors.TABLE_VISITORS, null,
				values);
		Cursor cursor = database.query(db_visitors.TABLE_VISITORS,
				allColumns, db_visitors.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Visitor newVisitor = cursorToVisitor(cursor);
		cursor.close();
		return newVisitor;
	}

	public void deleteComment(Visitor visitor) {
		long id = visitor.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(db_visitors.TABLE_VISITORS, db_visitors.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Visitor> getAllVisitors() {
		List<Visitor> visitors = new ArrayList<Visitor>();

		Cursor cursor = database.query(db_visitors.TABLE_VISITORS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Visitor visitor = cursorToVisitor(cursor);
			visitors.add(visitor);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return visitors;
	}

	private Visitor cursorToVisitor(Cursor cursor) {
		Visitor visitor = new Visitor();
		visitor.setId(cursor.getLong(0));
		visitor.setFirstName(cursor.getString(1));
		visitor.setLastName(cursor.getString(2));
		return visitor;
	}

}
