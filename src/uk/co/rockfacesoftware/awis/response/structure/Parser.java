/*
 * @author rockfacesoft
 */
package uk.co.rockfacesoftware.awis.response.structure;

import java.net.URL;

public interface Parser<R> {
	public void setUrl(URL url);

	public void buildDocument();

	public void parse();

	public R getResponse();

}
