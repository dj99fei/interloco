package co.interlo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import co.interlo.app.MyApplication;
import co.interlo.app.R;
import co.interlo.app.fragment.MessageDialogFragment;
import co.interlo.exception.VerifyException;

public class Verifier {

	private MyApplication myapp;
	
	public static final int USERNAME_LENGTH_MAX = 20;
	public static final int USERNAME_LENGTH_MIN = 3;

	public Verifier() {
		this.myapp = MyApplication.get();
	}

	public Verifier notEmpty(String data, int resId) throws VerifyException {
		if ("".equals(data)) {
			throw new VerifyException(myapp.getString(
					R.string.verify_not_empty, myapp.getString(resId)));
		}
		return this;
	}

	public Verifier noWhiteSpace(String data, int resId) throws VerifyException {
		if (data != null && data.contains(" ")) {
			throw new VerifyException(myapp.getString(
					R.string.verify_no_whitespace, myapp.getString(resId)));
		}
		return this;
	}

	public Verifier isLengthValid(String data, int min, int max, int resId)
			throws VerifyException {
		if (data != null && (data.length() < min || data.length() > max)) {
			throw new VerifyException(myapp.getString(R.string.verify_length,
					myapp.getString(resId), min, max));
		}
		return this;
	}

	public Verifier isConsistent(String data, String data2, int resId)
			throws VerifyException {
		if (!data.equals(data2)) {
			throw new VerifyException(myapp.getString(
					R.string.verify_not_consistent, myapp.getString(resId)));
		}
		return this;
	}
	
	public Verifier isEmailValid(String email) throws VerifyException{
		Pattern p = Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new VerifyException(myapp.getString(
					R.string.verify_email_invalid));
		}
		return this;
	}
	
	public void handleVerifyException(FragmentManager fm,VerifyException e){
		MessageDialogFragment dialog = new MessageDialogFragment();
		Bundle args = new Bundle();
		args.putString(Constants.MSG, e.getMessage());
		args.putString(Constants.TITLE, "Error");
		dialog.setArguments(args);
		dialog.show(fm, null);
	}
}
