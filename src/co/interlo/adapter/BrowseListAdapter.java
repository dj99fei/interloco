package co.interlo.adapter;

import com.google.inject.Inject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class BrowseListAdapter extends BaseAdapter<Integer> {

	@Inject
	public BrowseListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = new ImageView(context);
			convertView.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			convertView.setPadding(8, 8, 8, 8);
			((ImageView) convertView).setScaleType(ScaleType.CENTER_CROP);
		}
		((ImageView) convertView).setImageResource(getItemAt(position));
		return convertView;
	}

}
