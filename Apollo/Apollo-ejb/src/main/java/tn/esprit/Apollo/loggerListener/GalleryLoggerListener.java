package tn.esprit.Apollo.loggerListener;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.google.gson.Gson;

import tn.esprit.Apollo.algorithme.Audit;
import tn.esprit.Apollo.algorithme.FileGenerator;
import tn.esprit.Apollo.persistence.Gallery;

	public class GalleryLoggerListener {
		@PrePersist
		public void methodInvokedBeforePersist(Gallery gallery) {

			System.out.println("pre-persist Gallery with id = " + gallery.getName() );
		}

		@PostPersist
		public void methodInvokedAfterPersist(Gallery gallery) {

			System.out.println("post-persist Gallery with id = " + gallery.getName() );
		}

		@PostLoad
		public void methodInvokedBeforeLoad(Gallery gallery) {
			Audit audit = new Audit();
			audit.setObj(gallery);
			audit.setOperationType("update");
			audit.setOperationTime(new Date());
			FileGenerator f = new FileGenerator();
			try {
				f.writeOnJsonFile(audit);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


			System.out.println("Load Gallery with id = " + gallery.getName() );
		}		
		@PreUpdate
		public void methodInvokedBeforeUpdate(Gallery gallery) {


			System.out.println("pre-update Gallery with id = " + gallery.getName() );
		}

		@PostUpdate
		public void methodInvokedAfterUpdate(Gallery gallery) {

			System.out.println("post-update Gallery with id = " + gallery.getName() );
		}

		@PreRemove
		private void methodInvokedBeforeRemove(Gallery gallery) {
			
		}

		@PostRemove
		public void methodInvokedAfterRemove(Gallery gallery) {
		}

	}



