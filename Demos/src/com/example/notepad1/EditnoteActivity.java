package com.example.notepad1;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.demos.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
public class EditnoteActivity extends Activity {
	EditText mtitle,mcontent;
	CheckBox mch1,mch2,mch3;
	//TextView mshow;
	Button mButton;
	String mmood="心情：",mtitles,mcontents;
	myDatabaseHelper my;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notepad_editnote);
		my=new myDatabaseHelper(this);
		mtitle=(EditText)findViewById(R.id.edtitle);
		mcontent=(EditText)findViewById(R.id.editnote);
		mch1=(CheckBox)findViewById(R.id.chgood);
		mch2=(CheckBox)findViewById(R.id.chbad);
		mch3=(CheckBox)findViewById(R.id.nohappy);
		mButton=(Button)findViewById(R.id.save);
		mButton.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				mtitles=mtitle.getText().toString()+"\n";
				mcontents=mcontent.getText().toString()+"\n";
				insertDate(my.getWritableDatabase(),mtitles,mmood.substring(0, mmood.length()-1),mcontents,getDate());
				Toast.makeText(EditnoteActivity.this, "保存成功", 1).show();
				Intent intent =new Intent(EditnoteActivity.this,NotepadActivity.class);
				startActivity(intent);			
			}
		});	
	}
	@SuppressLint("SimpleDateFormat")
	public String getDate(){
		Date date=new Date();
		SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd HH:MM");
		String datetime=simp.format(date);				
		return datetime;		
	}
	public void ischecked(View view){
		CheckBox checkBox=(CheckBox)view;
		boolean flag=checkBox.isChecked();
		switch (view.getId()) {
		case R.id.chgood:
			if(flag){
				mmood+=mch1.getText().toString()+"、";
			}
			else{
				mmood=mmood.replace(mch1.getText().toString()+"、", "");
			}
			break;
		case R.id.chbad:
			if(flag){
				mmood+=mch2.getText().toString()+"、";
			}
			else{
				mmood=mmood.replace(mch2.getText().toString()+"、", "");
			}
			break;	case R.id.nohappy:
				if(flag){
					mmood+=mch3.getText().toString()+"、";
				}
				else{
					mmood=mmood.replace(mch3.getText().toString()+"、", "");
				}
				break;
		}				
	}
	public void insertDate(SQLiteDatabase db,String title,String mood,String content,String date) {
		Cursor c = db.rawQuery("select * from  notes order by  id", null);
		if(c==null)
		{
			db.execSQL("insert into notes values(1,?,?,?,?)",new String []{title,mood,content,date});
			db.close();	 
		} else {
			db.execSQL("insert into notes values(null,?,?,?,?)",new String []{title,mood,content,date});
			db.close();	
		}
	}}






















