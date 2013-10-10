package com;


	import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.compiler.PackageBuilder;
import org.drools.compiler.PackageBuilderErrors;
import org.mvel2.util.Make.String;
		

		public class Mainmethod {
			private static RuleBase rbase = RuleBaseFactory.newRuleBase();;
			private static PackageBuilder pbuilder = new PackageBuilder();
			private static StatefulSession sessionObject;
			

			public static void main(String[] args) {
			    initialiseDrools();
			    initiliseMessageObject();
			    
			    }

				
			       private static void initialiseDrools()
			       {
			       
			       try 
			       { 
			       System.out.println("from here it is starting connection");
			       Reader reader = new InputStreamReader(Mainmethod.class.getResourceAsStream("/com/hello.drl"));
			       pbuilder.addPackageFromDrl(reader);
			       } 
			       catch (Exception e)
			       {
			    	   e.printStackTrace();
			       }
			       /*catch (DroolsParserException ex)
			       {
			       Logger.getLogger(Mainmethod.class.getName()).log(Level.SEVERE, null, ex);
			       } 
			       catch (IOException ex) 
			       {
			       Logger.getLogger(Mainmethod.class.getName()).log(Level.SEVERE, null, ex);
			       }*/

			       
			
			       
			      PackageBuilderErrors errors = pbuilder.getErrors();

			       System.out.println("some error ");
			       
			       if (errors.getErrors().length > 0) {
			      
			       System.out.println("errors exists in packageBuilder");
			       
			       for (int i = 0; i < errors.getErrors().length; i++) {
			    	   
			       System.out.println(errors.getErrors()[i]);
			      }
			        throw new IllegalArgumentException(" Could not parse knowledge ");
			        
			        }

			
			             try
			             {
			            rbase.addPackage(pbuilder.getPackage()); 
			       
			             } 
			             catch (Exception e) {
			  System.out.println("Error: "+ e);
			  }
			       }
			       
			       
			private static void initiliseMessageObject()
			
			{
				System.out.println("connecting with drl with base class by using stateful session");
				A tx = new A(); 
				System.out.println("my first name is");
			    tx.setName("gcs");
			    sessionObject = rbase.newStatefulSession();
			    sessionObject.insert(tx);
			      
			    sessionObject.fireAllRules();
			     
			    System.out.println("my second name is");
			       tx.setMessage("reddy");
			       sessionObject=rbase.newStatefulSession();
			       sessionObject.insert(tx);
			      
			      
			   
			      StatefulSession sessionObject1=rbase.newStatefulSession();
			       sessionObject1.insert(tx);
			      
			      }

				         @SuppressWarnings("unused")
						private static void runRules() {
			        	 System.out.println("all will rule ");
			         sessionObject.fireAllRules();
			         }
			         }




