  
  /*
   * Preppa, Inc.
   * 
   * Copyright 2009. All rights
  reserved.
   * 
   * $Id$
   */

package com.preppa.web.pages.user;

import com.preppa.web.entities.User;
import java.io.File;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.upload.components.Upload;
import org.apache.tapestry5.upload.services.UploadedFile;
import org.springframework.security.annotation.Secured;

/**
 *
 * @author Jan Jan
 */
    @Secured("ROLE_USER")
    public class UploadUser
    {
        @ApplicationState
        private User user;
        @Property
        private UploadedFile file;
        @Component
        private Upload upload;
        @Persist(PersistenceConstants.FLASH)
         @Property
         private String message;

        public void onSuccess()
        {
            File dir1 = new File (".");
            try {
            System.out.println ("Current dir : " + dir1.getCanonicalPath());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
            String classLocation = UploadUser.class.getName().replace('.', '/') + ".class";
            ClassLoader loader = UploadUser.class.getClassLoader();
            String copyLocation = loader.getResource(classLocation).toString();
            copyLocation = copyLocation.substring(8, 73); // Remove "file" from the string
            copyLocation = this.formatSpace(copyLocation);
            System.out.println(copyLocation + "images/" + user.getId() + ".jpg");
            File copied = new File(copyLocation + "images/" + user.getId() + ".jpg");
            file.write(copied);
        }

        private String formatSpace(String s) {
            String returnVal = new String();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '%') {
                    returnVal = s.substring(0, i) + " " + s.substring(i+3, s.length());
                }
            }
            
            return returnVal;
        }

        Object onUploadException(FileUploadException ex)
        {
            message = "Upload exception: " + ex.getMessage();
            return this;
        }
    }