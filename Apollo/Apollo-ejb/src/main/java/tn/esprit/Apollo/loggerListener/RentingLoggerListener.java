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
import tn.esprit.Apollo.persistence.Renting;

	public class RentingLoggerListener {

		@PostPersist
		public void methodInvokedAfterPersist(Renting Renting) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Audit audit = new Audit();
			audit.setObj(Renting);
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
		public void methodInvokedBeforeLoad(Renting Renting) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Audit audit = new Audit();
			audit.setObj(Renting);
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
		private void methodInvokedBeforeRemove(Renting Renting) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Audit audit = new Audit();
			audit.setObj(Renting);
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
