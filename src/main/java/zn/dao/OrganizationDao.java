/**
 * 
 */
package zn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import zn.entity.Organization;

/**
 * @author hq
 *
 */
public interface OrganizationDao {
	public List<Organization> selectOrg();
	public int addOrg(String orgName);
	public int OrgIsExist(String orgName);
	public int deleteOrg(String orgName);
	public int changeOrgName(@Param("oldOrgName")String oldOrgName,@Param("nowOrgName")String nowOrgName);
	
}
