package org.immregistries.mqe.validator.engine.rules;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Set;
import org.immregistries.mqe.validator.detection.ImplementationDetail;
import org.immregistries.mqe.validator.engine.ValidationRule;

public class ValidationEntityRuleEntityListsPrint {
  public static void main(String[] args) throws Exception {
    System.out.println("Starting");
    File file = new File("c:/dev/mqe/text.html");
    PrintWriter out = new PrintWriter(new FileWriter(file));
    out.println("<html>");
    out.println("  <body>");
    for (ValidationRuleEntityLists vrel : ValidationRuleEntityLists.values())
    {
      out.println("    <h1>" + vrel.name() + "</h1>");
      for (ValidationRule vr : vrel.getRules())
      {
        out.println("    <h2>" + vr.getClass().getSimpleName() + "</h2>");
        Set<ImplementationDetail> idSet = vr.getImplementationDocumentation();
        for (ImplementationDetail id : idSet)
        {
          out.println("    <h3>" + id.getDetection().getDisplayText() + "</h3>");
          out.println("    <h4>How To Fix</h4>");
          out.println("    <p>" + id.getHowToFix() + "</p>");
          out.println("    <h4>Why To Fix</h4>");
          out.println("    <p>" + id.getWhyToFix() + "</p>");
        }
      }
    }
    out.println("  </body>");
    out.println("</html>");
    out.close();
    System.out.println("Finished");
  }
}
