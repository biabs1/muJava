package mujava.op.util;

import mujava.MutationSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FullCodeChangeLog {

    static final String logFile_name = "mutation_full_log";
    static PrintWriter log_writer;

    public static void openLogFile(){
        try{
            File f = new File(MutationSystem.MUTANT_PATH,logFile_name);
            FileWriter fout = new FileWriter(f);
            log_writer = new PrintWriter(fout);
        }catch(IOException e){
            System.err.println("[IOException] Can't make mutant log file." + e);
        }
    }

    public static void writeLog(String str){
        log_writer.println(str);
    }

    public static void closeLogFile(){
        log_writer.flush();
        log_writer.close();
    }
}

