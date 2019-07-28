/**
 * Copyright (C) 2015  the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package mujava.op.basic;

import br.ufal.ic.easy.hunor.Rules;
import openjava.mop.*;
import openjava.ptree.*;
import java.io.*;

/**
 * <p>Generate ASRS (Assignment Operator Replacement (short-cut)) mutants --
 *    replace each occurrence of one of the assignment operators 
 *    (+=, -+, *=, /=, %=, &=, |=, ^=, <<=, >>=, >>>=) by each of the 
 *    other operators  
 * </p>
 * @author Yu-Seung Ma
 * @version 1.0
 */

/* Each occurrence of one of the assignment operators
 *    (+=, -+, *=, /=, %=, &=, |=, ^=, <<=, >>=, >>>=),
 *    is replaced by each of the other operators 
 * 
 */

public class ASRS extends MethodLevelMutator
{
   public ASRS(FileEnvironment file_env, ClassDeclaration cdecl, CompilationUnit comp_unit)
   {
      super( file_env, comp_unit );
   }

   /**
    * If the assignment operator is of arithmetic type (+=, -=, *=, /=, %=), 
    *    replace it with each of the other arithmetic assignment operators. 
    * If the assignment operator is of logical type (&=, |=, ^=), 
    *    replace it with each of the other logical assignment operators.
    * If the assignment operator is a shift operator (<<, >>, >>>)
    *    replace it with each of the other shift operators. 
    */
   public void visit( AssignmentExpression p ) throws ParseTreeException
   {
      int op = p.getOperator();
      if ( (op == AssignmentExpression.ADD) || (op == AssignmentExpression.SUB)  ||
           (op == AssignmentExpression.MULT) || (op == AssignmentExpression.DIVIDE) ||
           (op == AssignmentExpression.MOD) )
      {
         genArithmeticMutants(p,op);
      }
      else if ( (op == AssignmentExpression.AND) || (op == AssignmentExpression.OR) ||
                (op == AssignmentExpression.XOR))
      {
         genLogicalMutants(p,op);
      }
      else if ( (op == AssignmentExpression.SHIFT_L) || (op == AssignmentExpression.SHIFT_R) ||
                (op == AssignmentExpression.SHIFT_RR))
      {
         genShiftMutants(p,op);
      }
   }

   /*
    * Replace the arithmetic assignment operator (+=, -+, *=, /=, %=)
    * by each of the other operators  
    */   
   void genArithmeticMutants(AssignmentExpression p, int op)
   {
      AssignmentExpression mutant;
      if (!(op == AssignmentExpression.ADD)
              && Rules.canApply(p, Rules.Mutation.ASRS_ADD, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.ADD);
         outputToFile(p, mutant);
      }
      if (!(op == AssignmentExpression.DIVIDE)
              && Rules.canApply(p, Rules.Mutation.ASRS_DIV, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.DIVIDE);
         outputToFile(p, mutant);
      }
      if (!(op == AssignmentExpression.MULT)
              && Rules.canApply(p, Rules.Mutation.ASRS_MUL, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.MULT);
         outputToFile(p,mutant);
      }  
      if (!(op == AssignmentExpression.SUB)
              && Rules.canApply(p, Rules.Mutation.ASRS_SUB, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.SUB);
         outputToFile(p,mutant);
      }  
      if (!(op == AssignmentExpression.MOD)
              && Rules.canApply(p, Rules.Mutation.ASRS_MOD, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.MOD);
         outputToFile(p,mutant);
      }
   }

   /*
    * Replace the logical assignment operator (&=, |+, ^=)
    * by each of the other operators  
    */   
   void genLogicalMutants(AssignmentExpression p, int op)
   {
      AssignmentExpression mutant;
      if (!(op == AssignmentExpression.AND)
              && Rules.canApply(p, Rules.Mutation.ASRS_AND, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.AND);
         outputToFile(p, mutant);
      }
      if (!(op == AssignmentExpression.OR)
              && Rules.canApply(p, Rules.Mutation.ASRS_OR, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.OR);
         outputToFile(p, mutant);
      }
      if (!(op == AssignmentExpression.XOR)
              && Rules.canApply(p, Rules.Mutation.ASRS_XOR, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.XOR);
         outputToFile(p, mutant);
      }
   }

   /*
    * Replace the shift assignment operator (<<=, >>=, >>>=)
    * by each of the other operators  
    */   
   void genShiftMutants(AssignmentExpression p, int op)
   {
      AssignmentExpression mutant;
      if (!(op == AssignmentExpression.SHIFT_L)
              && Rules.canApply(p, Rules.Mutation.ASRS_LSH, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.SHIFT_L);
         outputToFile(p, mutant);
      }
      if (!(op == AssignmentExpression.SHIFT_R)
              && Rules.canApply(p, Rules.Mutation.ASRS_RSH, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.SHIFT_R);
         outputToFile(p, mutant);
      }
      if (!(op == AssignmentExpression.SHIFT_RR)
              && Rules.canApply(p, Rules.Mutation.ASRS_URS, this)) {
         mutant = (AssignmentExpression)(p.makeRecursiveCopy());
         mutant.setOperator(AssignmentExpression.SHIFT_R);
         outputToFile(p, mutant);
      }
   }

   /**
    * Output ASRS mutants to file
    * @param original
    * @param mutant
    */
   public void outputToFile(AssignmentExpression original,AssignmentExpression mutant)
   {
      if (comp_unit == null) 
         return;
      
      String f_name;
      num++;
      f_name = getSourceName("ASRS");
      String mutant_dir = getMuantID("ASRS");

      try 
      {
		 PrintWriter out = getPrintWriter(f_name);
		 ASRS_Writer writer = new ASRS_Writer(mutant_dir, out);
		 writer.setMutant(original, mutant);
         writer.setMethodSignature(currentMethodSignature);
		 comp_unit.accept( writer );
		 out.flush();  
		 out.close();
      } catch ( IOException e ) 
      {
		  System.err.println( "fails to create " + f_name );
      } catch ( ParseTreeException e ) 
      {
		 System.err.println( "errors during printing " + f_name );
		 e.printStackTrace();
      }
   }
}
