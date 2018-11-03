package com.tdat.data;

import java.util.List;

/**
 * A class to represent a each type of
 */
public class ChartData {
	private String header;
	private List<Integer> data;

	public ChartData(String header, List<Integer> data) { 
		this.header = header;
		this.data = data;
	}

	public String getHeader() {
		return this.header;
	}

	public List<Integer> getData() {
		return this.data;
	}
}
