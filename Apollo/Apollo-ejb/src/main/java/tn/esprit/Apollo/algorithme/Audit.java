package tn.esprit.Apollo.algorithme;

import java.util.Date;

public class Audit {
	
	private Object obj ;
	private String OperationType ;
	private Date OperationTime ;
	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}
	/**
	 * @param obj the obj to set
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
	/**
	 * @return the operationType
	 */
	public String getOperationType() {
		return OperationType;
	}
	/**
	 * @param operationType the operationType to set
	 */
	public void setOperationType(String operationType) {
		OperationType = operationType;
	}
	/**
	 * @return the operationTime
	 */
	public Date getOperationTime() {
		return OperationTime;
	}
	/**
	 * @param operationTime the operationTime to set
	 */
	public void setOperationTime(Date operationTime) {
		OperationTime = operationTime;
	}
@Override
public String toString() {

	return OperationTime+" "+OperationType+" "+ obj.toString();
}
}
