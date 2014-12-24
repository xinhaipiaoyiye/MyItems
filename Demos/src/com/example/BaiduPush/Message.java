package com.example.BaiduPush;

import com.baidu.android.pushservice.PushConstants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Message extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
			String message = intent.getExtras().getString(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		}else if(intent.getAction().equals(PushConstants.ACTION_RECEIVE)){
			String message = intent.getStringExtra(PushConstants.EXTRA_METHOD);
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		}else if(intent.getAction().equals(PushConstants.ACTION_RECEIVER_NOTIFICATION_CLICK)){
			String message = intent.getStringExtra(PushConstants.EXTRA_EXTRA);
			Toast.makeText(context, message, Toast.LENGTH_LONG).show();
		}
		
		
		
		
		
		
		
		
		
		
	}
	
}
