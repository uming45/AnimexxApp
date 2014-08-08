package de.meisterfuu.animexx.activitys.rpg;

import de.meisterfuu.animexx.R;
import de.meisterfuu.animexx.data.APICallback;
import de.meisterfuu.animexx.data.rpg.RPGApi;
import de.meisterfuu.animexx.objects.RPGObject;
import de.meisterfuu.animexx.utils.APIException;
import de.meisterfuu.animexx.utils.views.TableDataView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class RPGDetailActivity extends Activity implements APICallback<RPGObject> {

	private long mRPGID;
	private RPGApi mAPI;
	private RPGObject mRPG;
	private TableDataView mTableView;

	public static void getInstance(Context pContext, long pID){
		Intent i = new Intent().setClass(pContext, RPGDetailActivity.class);
	     Bundle args = new Bundle();
	     args.putLong("id", pID);
	     i.putExtras(args);
	     pContext.startActivity(i);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rpgdetail);
		// Show the Up button in the action bar.
		setupActionBar();

		mTableView = (TableDataView) this.findViewById(R.id.activity_rpg_single_body_table);

		Bundle extras = this.getIntent().getExtras();
		mRPGID = extras.getLong("id");
		mAPI = new RPGApi(this);
		
		mAPI.getRPG(mRPGID, this);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rpgdetail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCallback(final APIException pError, final RPGObject pObject) {
		mRPG = pObject;
		mTableView.add(new TableDataView.TableDataEntity(mRPG.getName(), R.drawable.ens_flags_forwarded_blue));
		mTableView.add(new TableDataView.TableDataEntity(mRPG.getTopicName(), R.drawable.ens_flags_answered_blue));
		mTableView.add(new TableDataView.TableDataEntity(mRPG.getLastCharacter(), R.drawable.ens_flags_forwarded_blue));

	}
}