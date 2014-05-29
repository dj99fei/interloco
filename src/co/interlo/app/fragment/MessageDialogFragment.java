package co.interlo.app.fragment;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import co.interlo.util.Constants;

public class MessageDialogFragment extends DialogFragment {

	private String msg;

	private String title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		title = getArguments().getString(Constants.TITLE);
		msg = getArguments().getString(Constants.MSG);
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Builder builder = new Builder(getActivity());
		builder.setMessage(msg);
		builder.setTitle(title);

		return builder.create();
	}

}
