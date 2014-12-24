package com.example.duome_locus.locus;

import com.example.demos.R;
import com.example.notepad1.NotepadActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DuomeActivity extends Activity{

private Button mGoBtn;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.duome_locus_main);
		mGoBtn=(Button) findViewById(R.id.go);
		mGoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(DuomeActivity.this,NotepadActivity.class);
				startActivity(it);
			}
		});
		View v = (View) this.findViewById(R.id.tvReset);
		v.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(DuomeActivity.this, SetPasswordActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart()
	{
		super.onStart();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
	}

}
