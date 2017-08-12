/**
 * 
 */
package zn.entity;

/**
 * @author hq
 *
 */
public class MonSon {
	private int way;
	private String name;
	private String AA;
	private String BA;
	private String CA;
	private String ELA;
	private String state;
	private Object T;
	public int getWay() {
		return way;
	}
	public void setWay(int way) {
		this.way = way;
	}

	@Override
	public String toString() {
		return "MonSon [way=" + way + ", name=" + name + ", AA=" + AA + ", BA=" + BA + ", CA=" + CA + ", ELA=" + ELA
				+ ", state=" + state + ", T=" + T + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAA() {
		return AA;
	}
	public void setAA(String aA) {
		AA = aA;
	}
	public String getBA() {
		return BA;
	}
	public void setBA(String bA) {
		BA = bA;
	}
	public String getCA() {
		return CA;
	}
	public void setCA(String cA) {
		CA = cA;
	}
	public String getELA() {
		return ELA;
	}
	public void setELA(String eLA) {
		ELA = eLA;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Object getT() {
		return T;
	}
	public void setT(Object t) {
		T = t;
	}
	

}
