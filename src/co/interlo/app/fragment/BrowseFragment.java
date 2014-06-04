package co.interlo.app.fragment;

import javax.annotation.Nullable;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import co.interlo.adapter.BrowseListAdapter;
import co.interlo.app.R;
import co.interlo.util.Lists;

import com.google.inject.Inject;

public class BrowseFragment extends RoboFragment {

	@InjectView(R.id.list)
	private ListView listView;
	@Inject
	private BrowseListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		Integer[] imgs = new Integer[] { R.drawable.p1, R.drawable.p2,
				R.drawable.p3, R.drawable.p4 };
		listView.setAdapter(adapter);
		adapter.setData(Lists.newArrayList(imgs));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_browse, container, false);
	}

}
