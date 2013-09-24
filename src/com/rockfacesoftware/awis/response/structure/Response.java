/*
 * @author rockfacesoft
 */
package com.rockfacesoftware.awis.response.structure;

import java.util.List;

public interface Response<R> {
	public void addData(R data);

	public void addData(List<R> data);

	public List<R> getData();

	public boolean hasData();

	public void clear();
}
