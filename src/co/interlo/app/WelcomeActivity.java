package co.interlo.app;

import java.util.Timer;
import java.util.TimerTask;

import co.interlo.domain.User;

import com.parse.ParseUser;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {

	@InjectView(R.id.wel_des)
	private TextView welDesText;
	private static final int COUNT_MAX = 3;
	private int count = COUNT_MAX;

	private boolean paused = false;

	private Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (!paused) {
					if (--count <= 0) {
						// ....start activity
						toNext();
						timer.cancel();
					}
				}
			}

			
		}, 0, 1000);
	}

	@Override
	protected void onPause() {
		super.onPause();
		paused = true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		paused = false;
	}
	private void toNext() {
		User user = new User();
		if(user.hasGender() && user.hasStudy()){
			startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
		}else if(!user.hasStudy()){
			startActivity(new Intent(WelcomeActivity.this,SelLanguageActivity.class));
		}else if(!user.hasGender()){
			startActivity(new Intent(WelcomeActivity.this,SelGenderActivity.class));
		}
		finish();
	}
}
