package com.LogVisualization;

import java.util.List;

import com.LogVisualization.DAO.SourceDAO;
import com.LogVisualization.Bean.Source;

public class Test {
	public static void main(String[] args) throws Exception {
		SourceDAO sourceDAO = new SourceDAO();
		sourceDAO.createSource("helloSource",
				"http://test", "initialized", SV.DEFUALT_USERNAME);
		sourceDAO.createSource("helloSource2",
				"http://test", "initialized", SV.DEFUALT_USERNAME);
		sourceDAO.removeSource(3);
		sourceDAO.modifySource(2, null, null, "done");
		sourceDAO.modifySource(2, null, null, null);
		List<Source> list = sourceDAO.querySource("admin");
		return;
	}
}
