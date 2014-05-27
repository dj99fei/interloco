package co.interlo.app;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import co.interlo.domain.User;

import com.parse.ParseObject;
import com.parse.ParseUser;

@ContentView(R.layout.activity_sel_language)
public class SelLanguageActivity extends BaseActivity{

	@InjectView(R.id.english)
	private Button selEnglish;

	@InjectView(R.id.chinese)
	private Button selChinese;

	@InjectView(R.id.skip)
	private TextView skipText;

	ParseObject userInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		selChinese.setOnClickListener(this);
		selEnglish.setOnClickListener(this);
		skipText.setOnClickListener(this);

		addUnderline(skipText);
	}

	@Override
	public void onClick(View v) {
		
		int study = 0;
		switch (v.getId()) {
		case R.id.chinese:
			study = User.STUDY_CHINESE;
			break;
		case R.id.english:
			study = User.STUDY_ENGLISH;
			break;
		}
		if(study != 0){
			ParseUser.getCurrentUser().put("study", study);
			ParseUser.getCurrentUser().saveInBackground();
		}
		startActivity(new Intent(this,SelGenderActivity.class));
		finish();
	}

	private void addUnderline(TextView tv) {
		tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
	}

}
