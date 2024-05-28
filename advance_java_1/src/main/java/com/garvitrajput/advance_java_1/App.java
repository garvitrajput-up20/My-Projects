package com.garvitrajput.advance_java_1;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import display.*;
import inputsOutputs.*;
import attributes.TshirtData;;


public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
    	
    	InputData ip = new InputData();
    	SearchInput si = ip.getInput();
    	SearchOutput sop = new SearchOutput();
    	ArrayList<TshirtData>td = sop.getMatchingTshirts(si);
    	Output op = new Output();
    	op.displayOutput(td);

    }
}