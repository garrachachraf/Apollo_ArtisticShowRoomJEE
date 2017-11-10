package tn.esprit.Apollo.loggerListener;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PreRemove;

import tn.esprit.Apollo.algorithme.Audit;
import tn.esprit.Apollo.algorithme.FileGenerator;
import tn.esprit.Apollo.persistence.Artist;

	public class ArtistLoggerListener {

		@PostPersist
		public void methodInvokedAfterPersist(Artist Artist) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Audit audit = new Audit();
			audit.setObj(Artist);
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
		public void methodInvokedBeforeLoad(Artist Artist) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Audit audit = new Audit();
			audit.setObj(Artist);
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
		private void methodInvokedBeforeRemove(Artist Artist) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Audit audit = new Audit();
			audit.setObj(Artist);
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
