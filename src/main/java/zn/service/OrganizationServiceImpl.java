/**
 * 
 */
package zn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zn.dao.OrganizationDao;
import zn.dao.UserDao;
import zn.entity.Organization;
import zn.until.NoteResult;

/**
 * @author hq
 *
 */
@Service("organizationService")
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	
	@Resource//注入
	private OrganizationDao organizationDao;
	
	
	
	
	/**
	 * 添加组织
	 */
	public NoteResult addOrg(String orgName) {
		NoteResult note=new NoteResult();
		if(null==orgName){
			note.setStatus(2);
			note.setMsg("组织名称不能为空");
			note.setData("");
			return note;
		}
		int a=  organizationDao.OrgIsExist(orgName);
		if(a==0){
			organizationDao.addOrg(orgName);                             
			note.setStatus(0);
			note.setMsg("添加组织成功");
			note.setData(""); 
		}else{
			note.setStatus(1);
			note.setMsg("组织已存在");
			note.setData(""); 	
		}
		            
		return note;
	}

		/**
		 * 改变组织名称
		 */
	public NoteResult changeOrgName(String oldOrgName, String nowOrgName) {
		NoteResult note=new NoteResult();
		if(null==oldOrgName||null==nowOrgName){
			note.setStatus(2);
			note.setMsg("组织名称不能为空");
			note.setData("");
			return note;
		}
		organizationDao.changeOrgName(oldOrgName, nowOrgName);
		
		note.setStatus(0);
		note.setMsg("更改组织名称成功");
		note.setData(""); 
		return note;
	}

	/**
	 * 删除组织
	 */
	public NoteResult deleteOrg(String orgName) {
		
		NoteResult note=new NoteResult();
		if(null==orgName){
			note.setStatus(2);
			note.setMsg("组织名称不能为空");
			note.setData("");
			return note;
		}
		organizationDao.deleteOrg(orgName);
		note.setStatus(0);
		note.setMsg("删除组织成功");
		note.setData(""); 
		return note;
	}


	/**
	 * 查询组织列表
	 */
	public NoteResult selectOrg() {
		NoteResult note=new NoteResult();
	    List<Organization> list=organizationDao.selectOrg();
	    note.setStatus(0);
		note.setMsg("查询成功");
		note.setData(list); 
		return note;
		
	}

}
