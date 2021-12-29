package com.SecretSanta.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class SecretSantaServiceImpl implements SecretSantaService{
	
	Map<Integer, Integer> adjacencyList= new HashMap<>();
	Random random = new Random();
	
	@Override
	public Map<Integer, Integer> getSecretSantaPairs(List<Integer> empIds) {		
		empIds.stream().forEach(id->{
			Integer santaPair = null;
			while(!isValidSantaPair(santaPair) || santaPair.equals(id)) {
				santaPair = empIds.get(random.nextInt(empIds.size()));
			}
			adjacencyList.put(id, santaPair);			
		});
		return adjacencyList;
	}

	private boolean isValidSantaPair(Integer santaPair) {	
		if(santaPair==null)
			return false;
		boolean[] isValid=new boolean[1];
		isValid[0]=true;
		for(Map.Entry<Integer, Integer> id: adjacencyList.entrySet()) {
			if(id.getValue().equals(santaPair))
				isValid[0]=false;
			};		
		return isValid[0];
	}

}
