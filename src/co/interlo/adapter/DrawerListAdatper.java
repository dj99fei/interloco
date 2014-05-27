package co.interlo.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import co.interlo.app.R;

public class DrawerListAdatper extends BaseAdapter<String> {
	private static final int[] ICONS = { R.drawable.ic_drawer_consume, R.drawable.ic_drawer_consume };
	
	private Drawable[] mDrawables = new Drawable[ICONS.length];
	private int mPaddingLeft;
	private int mCompoundDrawablePadding;

	public DrawerListAdatper(Context mContext, List<String> data) {
		super(mContext, data);

		mPaddingLeft = mContext.getResources().getDimensionPixelOffset(
				R.dimen.drawer_item_padding_left);
		mCompoundDrawablePadding = mContext.getResources().getDimensionPixelOffset(
				R.dimen.drawer_item_compound_drawable_padding);
		
		for(int i = 0; i < mDrawables.length; i++){
			mDrawables[i] = mContext.getResources().getDrawable(ICONS[i]);
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_drawer_list, null);
			holder = new ViewHodler();
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.title.setCompoundDrawablePadding(mCompoundDrawablePadding);
			holder.title.setPadding(mPaddingLeft, 0, 0, 0);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHodler) convertView.getTag();
		}

		String item = getItemAt(position);
		holder.title.setCompoundDrawablesWithIntrinsicBounds(mDrawables[position], null, null, null);
		holder.title.setText(item);
		
		return convertView;
	}

	static class ViewHodler {
		public TextView title;
	}

}
