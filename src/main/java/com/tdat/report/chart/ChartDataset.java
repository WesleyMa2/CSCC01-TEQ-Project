package com.tdat.report.chart;

import java.util.List;

/**
 * A class to hold the y values of a given data set, with the title of the data set
 */
public class ChartDataset {
	private String header;
	private List<Integer> data;

	public ChartDataset(String header, List<Integer> data) {
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
