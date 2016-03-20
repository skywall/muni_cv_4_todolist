package cz.muni.cv4todoactiveandroid;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends BaseAdapter{

	List<TodoItem> todos;
	LayoutInflater inflater;

	public TodoAdapter(Context context, List<TodoItem> todos) {
		inflater = LayoutInflater.from(context);
		this.todos = todos;
	}

	@Override
	public int getCount() {
		return todos.size();
	}

	@Override
	public TodoItem getItem(int position) {
		return todos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item_todo, parent, false);
			convertView.setTag(new ViewHolder(convertView));
		}

		ViewHolder holder = ((ViewHolder) convertView.getTag());
		TodoItem item = getItem(position);

		holder.name.setText(item.name);
		holder.isDone.setChecked(item.isDone);

		if (item.isDone) {
			holder.name.setTextColor(Color.GRAY);
		} else {
			holder.name.setTextColor(Color.BLACK);
		}

		return convertView;
	}

	class ViewHolder {
		TextView name;
		CheckBox isDone;

		public ViewHolder(View view) {
			name = (TextView) view.findViewById(R.id.name);
			isDone = (CheckBox) view.findViewById(R.id.is_done);
		}
	}
}
