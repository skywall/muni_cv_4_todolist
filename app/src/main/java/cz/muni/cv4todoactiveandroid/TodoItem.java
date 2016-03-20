package cz.muni.cv4todoactiveandroid;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Todos")
public class TodoItem extends Model {

	public TodoItem() {}

	public TodoItem(String name) {
		this.name = name;
	}

	@Column(name = "Name")
	public String name;

	@Column(name = "IsDone")
	public boolean isDone;
}
