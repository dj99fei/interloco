package co.interlo.app;

import com.parse.ParseException;
import com.parse.SignUpCallback;

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
			// verify ...
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
										getString(R.string.signup_suc),
										Toast.LENGTH_SHORT).show();
								if (listener != null) {
									listener.onSignUpSuc();
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
		public void onSignUpSuc();
	}

}
