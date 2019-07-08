package org.notebook.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class SubProcessPool {
	
	private HashMap<String, SubProcess> hashMap = new HashMap();
	public void addSubProcess(String key,SubProcess sp ) {
		hashMap.put(key, sp);
	}
	public void deleteSubProcess(String key) {
		//call SubProcess destroy method and removing it from the pool
		hashMap.get(key).destroy();
		hashMap.remove(key);
	}
	public SubProcess getSubProcess(String key) {
		if (hashMap.containsKey(key)) {
			return hashMap.get(key);
		}
		else {
			return null;
		}
	}
	public HashMap<String, SubProcess> getHashMap() {
		return hashMap;
	}
	public void setHashMap(HashMap<String, SubProcess> hashMap) {
		this.hashMap = hashMap;
	}
	
	

}
