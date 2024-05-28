package com.dao;

import java.io.IOException;
import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entitites.tshirtBrand;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;

@Repository
public class TshirtDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	/*
	 * @Transactional public String saveTshirt(tshirtBrand tshirt) { String str =
	 * (String) this.hibernateTemplate.save(tshirt); return str; }
	 */
	
	public static void insertingInDB(List<String> FileName) throws NumberFormatException, IOException {
		SessionFactory factory = new Configuration().configure("hibernateC.cfg.xml").addAnnotatedClass(tshirtBrand.class)
				.buildSessionFactory();

		Session session = factory.openSession();
		session.beginTransaction();

		for (String filename : FileName) {
			FileReader filereader = new FileReader(filename);
			CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).withCSVParser(parser).build();
			List<String[]> allData = csvReader.readAll();

			for (String[] record : allData) {
				tshirtBrand tshirt = new tshirtBrand();
				tshirt.setID(record[0].toUpperCase());
				tshirt.setNAME(record[1].toUpperCase());
				tshirt.setCOLOUR(record[2].toUpperCase());
				tshirt.setGENDER_RECOMMENDATION(record[3].toUpperCase());
				tshirt.setSIZE(record[4].toUpperCase());
				tshirt.setPRICE(Float.parseFloat(record[5]));
				tshirt.setRATING(Float.parseFloat(record[6]));
				tshirt.setAVAILABILITY(record[7].toUpperCase());

				session.save(tshirt);
			}

		}
		session.getTransaction().commit();
	}

	
	@SuppressWarnings("deprecation")
	@Transactional
	public List<tshirtBrand> search(String color,String gender,String size,String output){
		String query = "";
		Session s = hibernateTemplate.getSessionFactory().openSession();
		@SuppressWarnings("unused")
		Transaction tx = s.beginTransaction();
		if(output.equalsIgnoreCase("price")) {
			query = "from tshirtBrand where COLOUR = :x AND "
			   		+ "GENDER_RECOMMENDATION = :y AND SIZE = :z AND "
			   		+ "AVAILABILITY = 'Y' ORDER BY PRICE";
		}else if(output.equalsIgnoreCase("rating")) {
			query = "from tshirtBrand where COLOUR = :x AND "
			   		+ "GENDER_RECOMMENDATION = :y AND SIZE = :z AND "
			   		+ "AVAILABILITY = 'Y' ORDER BY RATING DESC";
		}else {
			query = "from tshirtBrand where COLOUR = :x AND "
			   		+ "GENDER_RECOMMENDATION = :y AND SIZE = :z AND "
			   		+ "AVAILABILITY = 'Y' ORDER BY RATING DESC,PRICE ASC";
		}
		
		@SuppressWarnings("rawtypes")
		Query q = s.createQuery(query);
		 q.setParameter("x",color);
		   q.setParameter("y",gender);
		   q.setParameter("z",size);
		   @SuppressWarnings("unchecked")
		ArrayList<tshirtBrand> list = (ArrayList<tshirtBrand>) q.list();
		   
		   return list;
		
	}
	
}
