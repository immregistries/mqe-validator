package org.immregistries.dqa.validator;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.immregistries.dqa.validator.issue.Detection;
import org.immregistries.dqa.validator.issue.IssueObject;
import org.immregistries.dqa.validator.issue.VxuField;

public class GenerateDetectionWiki {
  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
      System.err.println("usage: java org.immregistries.dqa.validator.GenerateWiki [file-name]");
    }
    File file = new File(args[0]);
    PrintWriter out = new PrintWriter(new FileWriter(file));
    for (IssueObject issueObject : IssueObject.values()) {
      boolean found = false;
      for (Detection detection : Detection.values()) {
        if (detection.getTargetObject() == issueObject) {
          found = true;
          break;
        }
      }
      if (found) {
        out.println("# " + issueObject.getDescription());
        out.println();
        out.println("Location: " + issueObject.getLocation());
        for (VxuField vxuField : VxuField.values()) {
          found = false;
          for (Detection detection : Detection.values()) {
            if (detection.getTargetObject() == issueObject
                && detection.getTargetField() == vxuField) {
              found = true;
              break;
            }
          }
          if (found) {
            out.println("## " + vxuField.getFieldDescription());
            out.println();
            if (vxuField.getHl7Field() != null) {
              out.println("HL7 Field: " + vxuField.getHl7Field());
            }
            if (vxuField.getCodesetType() != null) {
              out.println("Codest Type: " + vxuField.getCodesetType().getDescription());
            }
            out.println("");
            out.println("Detections: ");
            for (Detection detection : Detection.values()) {
              if (detection.getTargetObject() == issueObject
                  && detection.getTargetField() == vxuField) {
                out.println("* " + detection.getDqaErrorCode() + ": " + detection.getDisplayText());
              }
            }
          }
        }
      }
    }
    out.close();
  }
}
