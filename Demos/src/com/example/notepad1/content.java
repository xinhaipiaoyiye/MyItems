package com.example.notepad1;

import android.provider.ContactsContract.Data;

public class content {
    /* @parmes mid:编号
     * @parmes mpost_time：发表时间
     * @parmes mhood:心情
     * @parmes mtitle:标题
     * @parmes mcontent:内容
     * */
	 int mid;
     String mpost_time;
     String mhood;
     String mtitle;
     String mcontent;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMpost_time() {
		return mpost_time;
	}
	public void setMpost_time(String mpost_time) {
		this.mpost_time = mpost_time;
	}
	public String getMhood() {
		return mhood;
	}
	public void setMhood(String mhood) {
		this.mhood = mhood;
	}
	public String getMtitle() {
		return mtitle;
	}
	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}
	public String getMcontent() {
		return mcontent;
	}
	public void setMcontent(String mcontent) {
		this.mcontent = mcontent;
	}

}
