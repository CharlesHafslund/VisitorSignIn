package com.example.signinapp;

import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	private VisitorsDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		datasource = new VisitorsDataSource(this);
	    datasource.open();

	    List<Visitor> values = datasource.getAllVisitors();

	    // Use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Visitor> adapter = new ArrayAdapter<Visitor>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// Will be called via the onClick attribute
	  // of the buttons in main.xml
	  public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    ArrayAdapter<Visitor> adapter = (ArrayAdapter<Visitor>) getListAdapter();
	    Visitor comment = null;
	    switch (view.getId()) {
	    case R.id.add:
	      String[][] names = new String[][] { {"Zero","Cool"}, {"Very", "nice"}, {"Hate", "it"} };
	      int nextInt = new Random().nextInt(3);
	      // Save the new comment to the database
	      comment = datasource.createComment(names[nextInt][0], names[nextInt][1]);
	      adapter.add(comment);
	      break;
	    case R.id.delete:
	      if (getListAdapter().getCount() > 0) {
	        comment = (Visitor) getListAdapter().getItem(0);
	        datasource.deleteComment(comment);
	        adapter.remove(comment);
	      }
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }

	  @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }


}
