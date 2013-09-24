/*
 * @author rockdfacesoft
 */
package uk.co.rockfacesoftware.awis.query.structure;

/**
 * 
 * Constructs the queryString from the QueryParameters
 */
public class QueryConstructor {

	public void construct(QueryParameters qb) {
		qb.createBasicQuery();
		qb.specialiseQuery();
		qb.finalizeQuery();
		qb.clearParameters();
	}
}
