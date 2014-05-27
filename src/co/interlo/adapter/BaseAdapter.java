package co.interlo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import co.interlo.util.Lists;

/**
 * If you use a <code>Collection</code> as the data source,you should consider
 * to use this class as your own adapter's super class.
 * 
 * @author fei.cheng
 * @param <T>
 */
public class BaseAdapter<T> extends android.widget.BaseAdapter {

	public List<T> mData = Lists.newArrayList();

	protected LayoutInflater mInflater;
	protected Context context;

	public BaseAdapter(Context context) {
		this.context = context;
		mInflater = LayoutInflater.from(context);
	}
	
	public BaseAdapter(Context context, List<T> data) {
		this(context);
		mData.addAll(data);
	}

	@Override
	public int getCount() {
		return mData == null ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData == null || mData.size() == 0 ? null : mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

	public T getItemAt(int position) {
		return mData == null || mData.size() == 0 ? null : mData.get(position);
	}

	public List<T> getItems(List<Integer> positions) {
		List<T> result = Lists.newArrayList();
		for (Integer position : positions) {
			result.add(getItemAt(position));
		}
		return result;
	}

	public BaseAdapter<T> setData(List<T> data) {
		this.mData.clear();
		if (data != null) {
			this.mData.addAll(data);
		}
		notifyDataSetChanged();
		return this;
	}

	public void addData(T data) {
		if (this.mData == null)
			this.mData = Lists.newArrayList();
		this.mData.add(data);
		notifyDataSetChanged();
	}

	public void addHeaderData(List<T> list) {
		if (this.mData == null)
			this.mData = Lists.newArrayList();
		this.mData.addAll(0, list);
		notifyDataSetChanged();
	}

	public void reset() {
		this.mData.clear();
		notifyDataSetChanged();
	}

}
