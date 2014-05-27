package co.interlo.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import co.interlo.app.BaseActivityFeature;
import co.interlo.app.MyApplication;

/**
 * 控制progress bar 和content view 的切换
 * 
 * @author fei.cheng
 */
public class Crossfader implements CrossfadeFeature {

	private BaseActivityFeature activityFeature;
	private int animTime;

	public Crossfader(BaseActivityFeature activityFeature) {
		super();
		this.activityFeature = activityFeature;
		animTime = MyApplication.get().getResources()
				.getInteger(android.R.integer.config_shortAnimTime);
	}

	@Override
	public void setShowProgress(boolean show) {
		if (canUseProgress()) {
			final View showView = !show ? activityFeature.getContentView()
					: activityFeature.getProgressView();
			final View hideView = !show ? activityFeature.getProgressView()
					: activityFeature.getContentView();
			setShowProgress(showView, hideView);
		}
	}

	@Override
	public void setShowProgress(final View showView, final View hideView) {
		if (canUseProgress()) {

			if (hideView.getVisibility() != View.GONE) {
				hideView.setVisibility(View.GONE);
			}
			if (showView.getVisibility() != View.VISIBLE) {
				showView.setVisibility(View.VISIBLE);
			}

			final Animator showAnim = ObjectAnimator.ofFloat(showView, "alpha",
					0f, 1f);
			showAnim.setDuration(animTime);
			showAnim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					showView.setVisibility(View.VISIBLE);
				}
			});
			showAnim.start();
			final Animator hideAnim = ObjectAnimator.ofFloat(hideView, "alpha",
					1f, 0f);
			hideAnim.setDuration(animTime);
			hideAnim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					hideView.setVisibility(View.GONE);
				}
			});
			hideAnim.start();
		}
	}

	private boolean canUseProgress() {
		return activityFeature.getContentView() != null
				&& activityFeature.getProgressView() != null;
	}

}
