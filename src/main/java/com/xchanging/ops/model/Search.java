package com.xchanging.ops.model;

public class Search {
	private static final long serialVersionUID = 1L;
	private String searchText;
	private Integer offset;
	private Integer maxresult;
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getMaxresult() {
		return maxresult;
	}
	public void setMaxresult(Integer maxresult) {
		this.maxresult = maxresult;
	}
	@Override
	public String toString() {
		return "Search [searchText=" + searchText + ", offset=" + offset
				+ ", maxresult=" + maxresult + "]";
	}
	
	
	
	
}
