package org.immregistries.dqa.validator;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.immregistries.dqa.validator.detection.Detection;
import org.immregistries.dqa.validator.detection.MessageObject;
import org.immregistries.dqa.vxu.VxuField;

public class GenerateDetectionWiki {
  public static void main(String[] args) throws Exception {
    if (args.length == 0) {
      System.err.println("usage: java org.immregistries.dqa.validator.GenerateWiki [file-name]");
    }
    File file = new File(args[0]);
    PrintWriter out = new PrintWriter(new FileWriter(file));
    for (MessageObject messageObject : MessageObject.values()) {
      boolean found = false;
      for (Detection detection : Detection.values()) {
        if (detection.getTargetObject() == messageObject) {
          found = true;
          break;
        }
      }
      if (found) {
        out.println("# " + messageObject.getDescription());
        out.println();
        out.println("Location: " + messageObject.getLocation());
        for (VxuField vxuField : VxuField.values()) {
          found = false;
          for (Detection detection : Detection.values()) {
            if (detection.getTargetObject() == messageObject
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
              if (detection.getTargetObject() == messageObject
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
