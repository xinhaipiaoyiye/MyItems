package com.example.notepad;

import java.util.ArrayList;
import java.util.List;


import com.example.demos.R;
import com.example.notepad.entity.NotepadEntity;
import com.lidroid.xutils.DbUtils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
public class NotepadMainActivity extends Activity{
//	DbUtils mDbUtils;
//	private List<NotepadEntity> mList=new ArrayList<NotepadEntity>();
//	@ViewInject(R.id.write_note)
	
	private Button mWriteNoteBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notepad_main);
	}

}
