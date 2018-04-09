package org.immregistries.dqa.validator;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.vxu.VxuField;
import org.immregistries.dqa.vxu.VxuObject;

public class GenerateDetectionWiki {

  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
      System.err.println("usage: java org.immregistries.dqa.validator.GenerateWiki [file-name]");
    }
    File file = new File(args[0]);
    PrintWriter out = new PrintWriter(new FileWriter(file));
    for (VxuObject vxuObject : VxuObject.values()) {
      boolean found = false;
      for (Detection detection : Detection.values()) {
        if (detection.getTargetObject() == vxuObject) {
          found = true;
          break;
        }
      }
      if (found) {
        out.println("# " + vxuObject.getDescription());
        out.println();
        out.println("Location: " + vxuObject.getLocation());
        for (VxuField vxuField : VxuField.values()) {
          found = false;
          for (Detection detection : Detection.values()) {
            if (detection.getTargetObject() == vxuObject
                && detection.getTargetField() == vxuField) {
              found = true;
              break;
            }
          }
          if (found) {
            out.println("## " + vxuField.getFieldDescription());
            out.println();
            if (vxuField.getHl7Locator() != null) {
              out.println("HL7 Field: " + vxuField.getHl7Locator());
            }
            if (vxuField.getCodesetType() != null) {
              out.println("Codest Type: " + vxuField.getCodesetType().getDescription());
            }
            out.println("");
            out.println("Detections: ");
            for (Detection detection : Detection.values()) {
              if (detection.getTargetObject() == vxuObject
                  && detection.getTargetField() == vxuField) {
                out.println("* " + detection.getDqaMqeCode() + ": " + detection.getDisplayText());
              }
            }
          }
        }
      }
    }
    out.close();
  }
}
