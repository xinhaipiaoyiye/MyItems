package com.example.xUtils.entity;

public class GoodsEntity {
	private String cateurl;
	private String catetitle;
	private String catecontent;
	private String cateprice;


	public String getCateurl() {
		return cateurl;
	}
	public void setCateurl(String cateurl) {
		this.cateurl = cateurl;
	}
	public String getCatetitle() {
		return catetitle;
	}
	public void setCatetitle(String catetitle) {
		this.catetitle = catetitle;
	}
	public String getCatecontent() {
		return catecontent;
	}
	public void setCatecontent(String catecontent) {
		this.catecontent = catecontent;
	}
	public String getCateprice() {
		return cateprice;
	}
	public void setCateprice(String cateprice) {
		this.cateprice = cateprice;
		
	}
	@Override
	public String toString() {
		return "GoodsEntity [cateurl=" + cateurl
				+ ", catetitle=" + catetitle + ", catecontent=" + catecontent
				+ ", cateprice=" + cateprice + "]";
	}


}
