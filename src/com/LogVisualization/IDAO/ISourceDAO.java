package com.LogVisualization.IDAO;

import java.util.List;
import com.LogVisualization.Bean.Source;

public interface ISourceDAO {
	public int createSource(String name, String url, String state, String username);
	public int removeSource(int id);
	public int modifySource(int id, String name, String url, String state);
	public List<Source> querySource(String username);
}
