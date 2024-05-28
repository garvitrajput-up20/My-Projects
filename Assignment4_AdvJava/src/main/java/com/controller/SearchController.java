package com.controller;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dao.TshirtDao;
import com.service.TshirtService;

@Controller
public class SearchController extends Thread {
	
	@Autowired
	TshirtService tshirtService;
	
	// list containing all the files with .csv extention
		static List<String> FileName;
		
		static File folder = new File("src\\main\\resources");

		public void run() {
			FileName = new LinkedList<String>();

			for (final File fileEntry : folder.listFiles()) {

				if (fileEntry.getName().contains(".csv"))
					FileName.add(
							"src\\main\\resources\\"
									+ fileEntry.getName());

			}

			try {
				TshirtDao.insertingInDB(FileName);

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

}
