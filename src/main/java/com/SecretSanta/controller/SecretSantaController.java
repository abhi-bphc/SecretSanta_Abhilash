package com.SecretSanta.controller;

import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.SecretSanta.entity.Participant;
import com.SecretSanta.service.*;

@Controller
@RequestMapping(value="/SecretSanta")
public class SecretSantaController {
	
	@Autowired
	SecretSantaService secretSantaService;
	
	@Autowired
	SecretSantaExcelReaderService secretSantaExcelReaderService;
	
	@Autowired
	SecretSantaEmailNotificationService secretSantaEmailNotificationService;
	
	@RequestMapping(value= "/getSantaPairs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Integer>> getSantaPairsController(){
		List<Integer> list= new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		Map<Integer, Integer> santaPairList = secretSantaService.getSecretSantaPairs(list);
		return new ResponseEntity<Map<Integer, Integer>>(santaPairList, HttpStatus.OK);
	}	
	
	@RequestMapping(value= "/getSecretSantaPairs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Participant[][]> getSecretSantaPairsController(){
		return new ResponseEntity<Participant[][]>(secretSantaExcelReaderService.getSecretSantaPairs(), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/sendmail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> sendMail(){
		secretSantaEmailNotificationService.sendEmailNotification();
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}

