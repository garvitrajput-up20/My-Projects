package com.garvitrajput.assignment2AdvanceJava;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import csv.TshirtCsv;


public class App
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        System.out.println( "Program Started ..." );
        
        Configuration conf = new Configuration();
    	conf.configure();
    	SessionFactory factory = conf.buildSessionFactory();
    	Session session = factory.openSession();
    	Transaction tx = session.beginTransaction();
    	
    	ArrayList<Tshirt> allTshirtData = TshirtCsv.getAllTshirts();
    	for (Tshirt ts: allTshirtData) {
    		session.save(ts);
   		}
    	
    	tx.commit();
    	session.close();
    	
    	Input input = new Input();
		TshirtSearchIp tshirtSearchIp = input.getInput();

        TshirtSearchOp tshirtSearchOp = new TshirtSearchOp();
        ArrayList<Tshirt>matchedTShirts = tshirtSearchOp.getMatchingTshirts(tshirtSearchIp);
        
        Output output = new Output();
        output.displayOutput(matchedTShirts);
    }
}
