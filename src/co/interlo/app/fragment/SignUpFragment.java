package co.interlo.app.fragment;

import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import co.interlo.app.R;
import co.interlo.domain.User;
import co.interlo.exception.VerifyException;
import co.interlo.util.Verifier;

import com.parse.ParseException;
import com.parse.SignUpCallback;

public class SignUpFragment extends BaseFragment {

	@InjectView(R.id.username)
	private EditText usernameEdit;
	@InjectView(R.id.email)
	private EditText emailEdit;
	@InjectView(R.id.password)
	private EditText passwordEdit;
	@InjectView(R.id.password_affirm)
	private EditText passwordAffirmEdit;
	@InjectView(R.id.signup)
	private Button signUpButton;

	private SignUpListener listener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		if (getActivity() instanceof SignUpListener) {
			listener = (SignUpListener) getActivity();
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		signUpButton.setOnClickListener(this);
		setShowProgress(false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_signup, container, false);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.signup:

			if (!verify()) {
				return;
			}
			User user = new User();
			setShowProgress(true);
			user.signup(usernameEdit.getText().toString(), passwordEdit
					.getText().toString(), emailEdit.getText().toString(),
					new SignUpCallback() {

						@Override
						public void done(ParseException e) {
							setShowProgress(false);
							if (e != null) {
								Toast.makeText(getActivity(), e.getMessage(),
										Toast.LENGTH_SHORT).show();
							} else {
								Toast.makeText(getActivity(),
										getString(R.string.signup_success),
										Toast.LENGTH_SHORT).show();
								if (listener != null) {
									listener.onSignUpSuccess();
								}
							}
						}
					});
			break;

		default:
			break;
		}
	}

	public static interface SignUpListener {
		public void onSignUpSuccess();

		public void onLogin();
	}

	private boolean verify() {
		Verifier verifier = new Verifier();
		String pwd = passwordEdit.getText().toString();
		String pwd2 = passwordAffirmEdit.getText().toString();
		String username = usernameEdit.getText().toString();
		String email = emailEdit.getText().toString();
		int usernameResId = R.string.username;
		try {
			verifier.notEmpty(username, usernameResId)
					.noWhiteSpace(username, usernameResId)
					.isLengthValid(username, Verifier.USERNAME_LENGTH_MIN,
							Verifier.USERNAME_LENGTH_MAX, usernameResId)
					.notEmpty(email, R.string.email).isEmailValid(email)
					.notEmpty(pwd, R.string.password)
					.notEmpty(pwd2, R.string.password_affirm)
					.isConsistent(pwd2, pwd, R.string.pwd_retype_pwd);
		} catch (VerifyException e) {
			verifier.handleVerifyException(getActivity()
					.getSupportFragmentManager(), e);
			return false;
		}
		return true;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_signup, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_login) {
			if (listener != null) {
				listener.onLogin();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
