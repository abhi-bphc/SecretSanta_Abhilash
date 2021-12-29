package com.SecretSanta.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface SecretSantaService {
	public Map<Integer, Integer> getSecretSantaPairs(List<Integer> empIds);
}
