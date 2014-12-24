package com.example.notepad1;
import java.util.ArrayList;
import java.util.List;

import com.example.addressbook.ActivityAddFriends;
import com.example.demos.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class NotepadActivity extends Activity {
	myDatabaseHelper database = null;// 这段代码放到Activity类中才用this
	SQLiteDatabase db = null;
	Button mButton;
	ListView mlListView;
	private TextView mGoBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notepad_main);
		database = new myDatabaseHelper(this);// 这段代码放到Activity类中才用this
		mButton = (Button) findViewById(R.id.write_note);
		mlListView = (ListView) findViewById(R.id.note_listView);
		mGoBtn=(TextView) findViewById(R.id.go);
		myBaseAdapter adapter = new myBaseAdapter(this, find());
		mlListView.setAdapter(adapter);
		mGoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(NotepadActivity.this,ActivityAddFriends.class);
				startActivity(it);
			}
		});
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(NotepadActivity.this, "更新完成", Toast.LENGTH_SHORT)
						.show();
				Intent intent = new Intent(NotepadActivity.this, EditnoteActivity.class);
				startActivity(intent);
                finish();
			}
		});
	}

	public List<content> find() {
		db = database.getWritableDatabase();
		Cursor c = db.rawQuery("select * from  notes order by  id", null);
		List<content> list = new ArrayList<content>();
		while (c.moveToNext()) {
			content cc = new content();
			cc.setMtitle(c.getString(1));
			cc.setMhood(c.getString(2));
			cc.setMcontent(c.getString(3));
			cc.setMpost_time(c.getString(4));
			list.add(cc);
		}
		 db.close();
		return list;
	}

}
