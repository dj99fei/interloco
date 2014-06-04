package co.interlo.app;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import co.interlo.adapter.DrawerListAdapter;
import co.interlo.app.fragment.BrowseFragment;
import co.interlo.app.fragment.ShareFragment;
import co.interlo.app.fragment.SignUpFragment;
import co.interlo.app.fragment.LoginFragment.LoginOperationListener;
import co.interlo.app.fragment.SignUpFragment.SignUpListener;
import co.interlo.util.Lists;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements SignUpListener,
		LoginOperationListener {

	private String[] mDrawerItems;

	@InjectView(R.id.drawer_layout)
	private DrawerLayout mDrawerLayout;
	@InjectView(R.id.left_drawer)
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setDrawer();

		setActionBar();

		if (savedInstanceState == null) {
			// select the first item
			selectItem(0);
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	private void setDrawer() {
		mDrawerItems = this.getResources().getStringArray(R.array.drawer_item);
		mDrawerList.setAdapter(new DrawerListAdapter(this, Lists
				.newArrayList(mDrawerItems)));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open_e,
				R.string.drawer_close_e) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				invalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void setActionBar() {
		this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		this.getSupportActionBar().setHomeButtonEnabled(true);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawers();
		setTitle(mDrawerItems[position]);
		switch (position) {
		case 0:
			show(new BrowseFragment());
			break;
		case 1:
			if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
				show(new SignUpFragment());
			} else {
				show(new ShareFragment());
			}
			break;
		}
	}

	private void show(Fragment fragment) {
		this.getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	public void onSignUpSuccess() {
		show(new ShareFragment());
	}

	@Override
	public void onSignUp() {
		show(new SignUpFragment());
	}

	@Override
	public void onLoginSuc(ParseUser user) {
		show(new ShareFragment());
	}

}
