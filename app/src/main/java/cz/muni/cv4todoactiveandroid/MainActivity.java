package cz.muni.cv4todoactiveandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.List;

public class MainActivity extends AppCompatActivity {

	ListView list;
	EditText name;
	Button addTodo;

	List<TodoItem> todos;
	TodoAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();

		todos = new Select().from(TodoItem.class).orderBy("Id DESC").execute();
		adapter = new TodoAdapter(this, todos);

		addTodo.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				String todoName = name.getText().toString();
				if (!TextUtils.isEmpty(todoName)) {
					TodoItem todoItem = new TodoItem(todoName);
					todoItem.save();
					todos.add(0, todoItem);

					adapter.notifyDataSetChanged();
					name.setText("");
				}
			}
		});

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TodoItem item = (TodoItem) parent.getItemAtPosition(position);
				item.isDone = !item.isDone;
				item.save();

				adapter.notifyDataSetChanged();
			}
		});
		list.setAdapter(adapter);
	}

	private void initUI() {
		name = (EditText) findViewById(R.id.name);
		addTodo = (Button) findViewById(R.id.add_todo);
		list = (ListView) findViewById(R.id.list);
	}
}
