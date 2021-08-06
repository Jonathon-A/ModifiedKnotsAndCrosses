package ModifiedKnotsAndCrosses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    public Console() {
    }

    public char readChar() {
        return readChar("");
    }

    public char readChar(String prompt) {
        String str = readLine(prompt);
        char chr;
        try{
        chr = str.charAt(0);
    }catch(Exception e){
     return "false".charAt(0);
    }
            return chr;

        
    }
    

    public byte readByte(String prompt) {
        try {
            return Byte.parseByte(readLine(prompt).substring(0, 1));
        } catch (NumberFormatException nfe) {
            println(nfe.toString() + "Parsing a Byte");
        } catch (Exception e) {
            println("another exception" + e.toString());
        }
        return -1;
    }

    public byte readByte() {
        return readByte("");
    }

    public String readLine() {
        return readLine("");
    }

    public String readLine(String prompt) {
        String input = "";
        System.out.print(prompt);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            input = br.readLine();
        } catch (IOException ioe) {
            println("IO Error reading from command line.");
            System.exit(1);
        }
        return input;
    }

    public int readInteger(String prompt) {

        try {
            return (int) Integer.parseInt(readLine(prompt));
        } catch (Exception e) {
            return 0;
        }

    }

    public void print(Object o) {
        String output = String.valueOf(o);
        System.out.print(output);
    }

    public void println() {
        println("");
    }

    public void println(Object o) {
        String output = String.valueOf(o);
        System.out.println(output);
    }

    public void write(Object o) {
        print(o);
    }

    public void writeLine() {
        println();
    }

    public void writeLine(Object o) {
        println(o);
    }

    public void printf(String format, Object args) {
        System.out.printf(format, args);
    }
}
