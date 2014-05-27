package co.interlo.app;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import co.interlo.app.fragment.BaseFragment;
import co.interlo.domain.User;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginFragment extends BaseFragment {

	@InjectView(R.id.login)
	private Button loginButton;
	@InjectView(R.id.signup)
	private Button signupButton;
	@InjectView(R.id.username)
	private EditText usernameEdit;
	@InjectView(R.id.password)
	private EditText passwordEdit;

	private LoginOperationListener listener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getActivity() instanceof LoginOperationListener) {
			listener = (LoginOperationListener) getActivity();
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		signupButton.setOnClickListener(this);
		loginButton.setOnClickListener(this);
		setShowProgress(false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_login, container, false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signup:
			if (listener != null) {
				listener.onSignUp();
			}
			break;
		case R.id.login:
			// verify...
			User user = new User();
			setShowProgress(true);
			user.login(usernameEdit.getText().toString(), passwordEdit
					.getText().toString(), new LogInCallback() {
				@Override
				public void done(ParseUser user, ParseException e) {
					setShowProgress(false);
					if (e == null) {
						if (listener != null) {
							listener.onLoginSuc(user);
						}
					} else {
						Toast.makeText(getActivity(), e.getMessage(),
								Toast.LENGTH_LONG).show();
					}
				}
			});
			break;
		}
	}

	public static interface LoginOperationListener {
		public void onSignUp();
		public void onLoginSuc(ParseUser user);
	}

}
