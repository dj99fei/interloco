package co.interlo.app;

import com.parse.Parse;
import com.parse.ParseUser;

import android.app.Application;

public class MyApplication extends Application {

	private static MyApplication myapp;

	@Override
	public void onCreate() {
		myapp = this;
		super.onCreate();
		Parse.initialize(this, "pH6CyBranIRvltvzs2fqkfyB5xlXIDpDFzqO7KUq",
				"PU7iVnjwCbqALzri6P6bZ4CtFaPLO1oyOXixj12s");
		ParseUser.enableAutomaticUser();
		ParseUser.getCurrentUser().increment("RunCount");
		ParseUser.getCurrentUser().saveInBackground();
	}

	public static MyApplication get() {
		return myapp;
	}

}
