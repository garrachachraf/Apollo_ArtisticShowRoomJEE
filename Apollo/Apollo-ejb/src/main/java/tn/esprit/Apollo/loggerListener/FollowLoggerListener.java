package tn.esprit.Apollo.loggerListener;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import tn.esprit.Apollo.algorithme.Audit;
import tn.esprit.Apollo.algorithme.FileGenerator;
import tn.esprit.Apollo.persistence.Follow;

	public class FollowLoggerListener {

		@PostPersist
		public void methodInvokedAfterPersist(Follow Follow) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Audit audit = new Audit();
			audit.setObj(Follow);
			audit.setOperationType("CREATE");
			audit.setOperationTime(timestamp);
			FileGenerator f = new FileGenerator();
			try {
				f.writeOnJsonFile(audit);
			} catch (IOException e) {
				e.printStackTrace();
			}		
			}

		@PostLoad
		public void methodInvokedBeforeLoad(Follow Follow) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Audit audit = new Audit();
			audit.setObj(Follow);
			audit.setOperationType("UPDATE");
			audit.setOperationTime(timestamp);
			FileGenerator f = new FileGenerator();
			try {
				f.writeOnTextFile(audit);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}		


		@PreRemove
		private void methodInvokedBeforeRemove(Follow Follow) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Audit audit = new Audit();
			audit.setObj(Follow);
			audit.setOperationType("REMOVE");
			audit.setOperationTime(timestamp);
			FileGenerator f = new FileGenerator();
			try {
				f.writeOnTextFile(audit);
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}



}