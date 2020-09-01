
package com.mwl.util;

import java.util.Scanner;

public class ExitGame {
    public static void exit(String exit){
        if(exit.strip().toLowerCase().equals("exit")){
            System.exit(0);
        }
    }
}