package com.SecretSanta.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.SecretSanta.entity.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretSantaExcelReaderServiceImpl implements SecretSantaExcelReaderService{
	
	@Autowired
	SecretSantaService secretSantaService;
	
	List<Integer> empIds= new ArrayList<>();
	Map<Integer, Participant> empMap= new HashMap<>();
	
	@Override
	public Participant[][] getSecretSantaPairs() {
		prepareParticipantsData();
		prepareEmpIds();	
		Map<Integer, Integer> secretSantaPairIds= secretSantaService.getSecretSantaPairs(empIds);
		Participant[][] response = new Participant[empIds.size()][2];
		int index=0;
		for(Map.Entry<Integer, Integer> entry: secretSantaPairIds.entrySet()) {
			response[index][0]=empMap.get(entry.getKey());
			response[index][1]=empMap.get(entry.getValue());
			index++;
		}
		return response;
	}
	
	private void prepareEmpIds() {
		empIds.addAll(empMap.keySet());
	}

	private void prepareParticipantsData() {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File("C:\\Book1.xlsx"));
			XSSFWorkbook workbook= new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			iterator.next();
			while(iterator.hasNext()) {
				Row row= iterator.next();
				empMap.put(convertToInt(row.getCell(0)), new Participant(convertToInt(row.getCell(0)),
						row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private Integer convertToInt(Cell num) {
		return (int)num.getNumericCellValue();
	}
}
