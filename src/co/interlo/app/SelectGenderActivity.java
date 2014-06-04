package co.interlo.app;

import com.parse.ParseUser;

import co.interlo.domain.User;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

@ContentView(R.layout.activity_select_gender)
public class SelectGenderActivity extends BaseActivity {

	@InjectView(R.id.female)
	private Button femaleButton;
	@InjectView(R.id.male)
	private Button maleButton;

	@InjectView(R.id.as_secret)
	private Button asSecretButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		asSecretButton.setOnClickListener(this);
		maleButton.setOnClickListener(this);
		femaleButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int gender = 0;
		switch (v.getId()) {
		case R.id.female:
			gender = User.GENDER_FEMALE;
			break;
		case R.id.male:
			gender = User.GENDER_MALE;
			break;
		}
		if (gender != 0) {
			ParseUser user = ParseUser.getCurrentUser();
			user.put("gender", gender);
			user.saveInBackground();
		}
		toNext();
	}

	private void toNext() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

}
