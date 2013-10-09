package net.hughmarshall.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnItemSelectedListener {
	
	private int spinnerInitialCount= 0;
	private static final int NO_OF_EVENTS = 1;

	// String constant is the key intent.putExtra() in sendMessage()
	public final static String EXTRA_MESSAGE = "net.hughmarshall.myFirstApp.MESSAGE";

	@Override
	/**
	 * Called when the activity is created
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Create the spinner
		Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);

		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array,
				android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
		//Set this as the listener of the spinner
		spinner.setOnItemSelectedListener(this);

	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

		//trying to avoid undesired spinner selection changed event, a known problem
        if (spinnerInitialCount < NO_OF_EVENTS) {
            spinnerInitialCount++;
        } else {  
		// create a new intent, passing it this context and the class
		// of the app component to deliver the intent to
		Intent intent = new Intent(this, DisplayMessageActivity.class);

		// Message is URL for a google search + the contents of the spinner
		String message = "https://www.google.com/search?q=" + parent.getItemAtPosition(pos).toString();
		intent.putExtra(EXTRA_MESSAGE, message);

		// start an instance of DisplayMessageActivity as specified by the
		// intent
		startActivity(intent);
        }
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// another interface callback
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
