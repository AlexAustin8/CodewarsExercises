/**
 * PROMPT:
 * Esolang Interpreters #2 - Custom Smallf Interpreter
 *
 *About this Kata Series
 *
 *"Esolang Interpreters" is a Kata Series that originally began as three separate, independent esolang interpreter Kata authored by @donaldsebleung 
 *which all shared a similar format and were all somewhat inter-related. Under the influence of a fellow Codewarrior, these three high-level inter-related Kata gradually evolved into what 
 *is known today as the "Esolang Interpreters" series.
 *
 *This series is a high-level Kata Series designed to challenge the minds of bright and daring programmers by implementing interpreters for various esoteric programming languages/Esolangs,
 *mainly Brainf derivatives but not limited to them, given a certain specification for a certain Esolang. Perhaps the only exception to this rule is the very first Kata in this Series 
 *which is intended as an introduction/taster to the world of esoteric programming languages and writing interpreters for them.
 *
 *The Language
 *
 *smallf is an esoteric programming language/Esolang invented in 2002 which is a sized-down variant of the famous Brainf Esolang. Key differences include:
 *
 *smallf operates only on bits as opposed to bytes
 *It has a limited data storage which varies from implementation to implementation depending on the size of the tape
 *It does not define input or output - the "input" is encoded in the initial state of the data storage (tape) and the "output" should be decoded in the final state of the data storage (tape)
 *Here are a list of commands in smallf:
 *
 *> - Move pointer to the right (by 1 cell)
 *< - Move pointer to the left (by 1 cell)
 * * - Flip the bit at the current cell
 *[ - Jump past matching ] if value at current cell is 0
 *] - Jump back to matching [ (if value at current cell is nonzero)
 *As opposed to Brainf where a program terminates only when all of the commands in the program have been considered (left to right), smallf terminates when any of the two conditions 
 *mentioned below become true:
 *
 * All commands have been considered from left to right
 *The pointer goes out-of-bounds (i.e. if it moves to the left of the first cell or to the right of the last cell of the tape)
 *smallf is considered to be Turing-complete if and only if it had a tape of infinite length; however, since the length of the tape is always defined as finite (as the interpreter cannot 
 *return a tape of infinite length), its computational class is of bounded-storage machines with bounded input.
 *
 *More information on this Esolang can be found here.
 *
 *The Task
 *
 *Implement a custom smallf interpreter interpreter() (interpreter in Haskell, Interpreter in C#) which accepts the following arguments:
 *
 *code - Required. The smallf program to be executed, passed in as a string. May contain non-command characters. Your interpreter should simply ignore any non-command characters.
 *tape - Required. The initial state of the data storage (tape), passed in as a string. For example, if the string "00101100" is passed in then it should translate to something of 
 *this form within your interpreter: [0, 0, 1, 0, 1, 1, 0, 0]. You may assume that all input strings for tape will be non-empty and will only contain "0"s and "1"s.
 *  Your interpreter should return the final state of the data storage (tape) as a string in the same format that it was passed in.
 *   For example, if the tape in your interpreter ends up being [1, 1, 1, 1, 1] then return the string "11111".
 * @author Alex
 *
 */
public class Smallf {
	public static String interpreter(String code, String tape) {
        int pointer = 0;
        int count = 0;
        char[] tapeArr = tape.toCharArray();
        String finishedCode ="";


        for(int i = 0 ; i< code.length(); i++){
            if(code.charAt(i) == '>'){
               pointer++;
               if(pointer >= tapeArr.length){
                  for(int p = 0; p < tapeArr.length; p++){
                     finishedCode += tapeArr[p];
                  }
                  return finishedCode;
                  }
            }else if(code.charAt(i) == '<'){
               pointer--;
               if(pointer < 0){
                  for(int p = 0; p < tapeArr.length; p++){
                     finishedCode += tapeArr[p];
                  }
                  return finishedCode;
                 }
            }else if(code.charAt(i) == '*'){
               if(tapeArr[pointer] == '1'){
                   tapeArr[pointer] = '0';
                }else if(tapeArr[pointer] == '0'){
                   tapeArr[pointer] = '1';
                }
            }else if(code.charAt(i) == '[' && tapeArr[pointer] == '0'){
                count++;
                

                while(i < code.length() && count > 0){
                i++;
                if(code.charAt(i) == '['){
                   count++;
                  }
                if(code.charAt(i) == ']'){
                   count--;
                   }

                }
             }else if(code.charAt(i) == ']' && tapeArr[pointer] != '0'){
                count++;
                
                while(i >= 0 && count > 0){
                i--;
                if(code.charAt(i) == ']'){
                   count++;
                  }
                if(code.charAt(i) == '['){
                   count--;
                   }

                }
              }
            }
          for(int p = 0; p < tapeArr.length; p++){
             finishedCode += tapeArr[p];
             }
          return finishedCode;
             
    }

}
