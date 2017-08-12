/**
 * 
 */
package zn.service;

import zn.until.NoteResult;

/**
 * @author hq
 *
 */
public interface OrganizationService {
	public NoteResult addOrg(String orgName);

	public NoteResult changeOrgName(String oldOrgName, String nowOrgName);

	public NoteResult deleteOrg(String orgName);

	public NoteResult selectOrg();

}
