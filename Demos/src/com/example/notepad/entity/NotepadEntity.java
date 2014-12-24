package com.example.notepad.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NotepadEntity implements Serializable{
	private int id;
	private String note_time;
	private String note_mood;
	private String note_title;
	private String note_content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNote_time() {
		return note_time;
	}
	public void setNote_time(String note_time) {
		this.note_time = note_time;
	}
	public String getNote_mood() {
		return note_mood;
	}
	public void setNote_mood(String note_mood) {
		this.note_mood = note_mood;
	}
	public String getNote_title() {
		return note_title;
	}
	public void setNote_title(String note_title) {
		this.note_title = note_title;
	}
	public String getNote_content() {
		return note_content;
	}
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}
	@Override
	public String toString() {
		return "NotepadEntity [id=" + id + ", note_time=" + note_time
				+ ", note_mood=" + note_mood + ", note_title=" + note_title
				+ ", note_content=" + note_content + "]";
	}
	

}
