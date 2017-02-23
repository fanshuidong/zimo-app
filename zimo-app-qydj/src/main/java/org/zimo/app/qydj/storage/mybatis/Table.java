package org.zimo.app.qydj.storage.mybatis;

public enum Table {
	
	MEM_USER("mem_user");
	
	private String mark;
	
	private Table(String mark) {
		this.mark = mark;
	}
	
	public String mark() {
		return mark;
	}
}
