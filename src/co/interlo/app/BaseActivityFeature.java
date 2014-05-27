package co.interlo.app;

import co.interlo.util.CrossfadeFeature;
import android.view.View;

public interface BaseActivityFeature extends CrossfadeFeature {
	View getContentView();

	View getProgressView();

}
