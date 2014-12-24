package com.example.fileBrower;
import java.io.File;

import com.example.demos.R;
import com.example.xUtils.activity.DbUtilsActity;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FileBrowerActivity extends Activity {

	public static String FILE_PATH_ACTION = "com.example.filebrowser.filepath";
	private GetPathBroadcastReceiver mGetpathReceiver = null;
	private Button mGoBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filebrower);
		Button btnBrowser = (Button) findViewById(R.id.button1);
		mGoBtn=(Button) findViewById(R.id.go);
		mGoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(FileBrowerActivity.this,DbUtilsActity.class);
				startActivity(it);
			}
		});
		btnBrowser.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FileBrowerActivity.this,
						FileManagerActivity.class);
				startActivity(intent);
			}
		});

		mGetpathReceiver = new GetPathBroadcastReceiver();
		IntentFilter filterPosition = new IntentFilter();
		filterPosition.addAction(FILE_PATH_ACTION);
		registerReceiver(mGetpathReceiver, filterPosition);
	}

	private class GetPathBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(FILE_PATH_ACTION)) {
				String strFilePath = intent.getStringExtra("filepath");
				EditText etPath = (EditText) findViewById(R.id.editText1);
				etPath.setText((CharSequence) strFilePath);
			}
		}

	}

}
