/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.testing;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author john
 */
public class TestRunner {
    
    public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestSample1.class, TestEnhancement.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      if(result.wasSuccessful()){
          System.out.println("All Testcases were passed!");
      } else {
          System.out.println("Test failed");
      }
   }
}
