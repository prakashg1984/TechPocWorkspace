package com.example.demo.extract

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class Helper {
	
	//@Value('#{${ue.simtype.dummy.iccid: {ATT:\'89011508888888888888\',FIRSTNET:\'89011507777777777777\'}}')
	@Value
	('#{${ue.simtype.dummy.iccid: {ATT:\'89011508888888888888\',FIRSTNET:\'89011507777777777777\'}}}')
	
	Map<String,String> simTypeDummyICCIDMap;

	String fetchLosgTypes(order) {
		List<String> losgType = order?.losgs?.findAll {losg -> losg.losgType}?.collect({it.losgType})
		
		print 'losgType '+losgType.toString()
		
		return losgType.toString()
	}
}
