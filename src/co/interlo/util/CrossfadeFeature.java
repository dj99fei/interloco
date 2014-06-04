package co.interlo.util;

import android.view.View;

/**
 * 控制progress bar 和content view 的切换
 * 
 * @author fei.cheng
 */
public interface CrossfadeFeature {

	public void setShowProgress(final boolean show);

	public void setShowProgress(final View showView, final View hideView);

}
