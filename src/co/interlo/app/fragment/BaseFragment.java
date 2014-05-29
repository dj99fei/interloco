package co.interlo.app.fragment;

import javax.annotation.Nullable;

import com.google.inject.Inject;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import co.interlo.app.R;
import co.interlo.util.Crossfader;

public class BaseFragment extends RoboFragment implements OnClickListener,
		BaseFragmentFeature {

	@Nullable
	@InjectView(R.id.loading_spinner)
	protected ProgressBar loadingSpinner;
	@Nullable
	@InjectView(R.id.content_id)
	protected View content;
	private Crossfader crossfader;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		crossfader = new Crossfader(this);
	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public View getContentView() {
		return content;
	}

	@Override
	public View getProgressView() {
		return loadingSpinner;
	}

	@Override
	public void setShowProgress(boolean show) {
		crossfader.setShowProgress(show);
	}

	@Override
	public void setShowProgress(View showView, View hideView) {
		crossfader.setShowProgress(showView, hideView);
	}

}
