package com.pg.helper

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class TestGroovyHelper {
	
	//@Value('#{${ue.simtype.dummy.iccid: {ATT:\'89011508888888888888\',FIRSTNET:\'89011507777777777777\'}}')
	@Value('#{${ue.simtype.dummy.iccid: {ATT:\'89011508888888888888\',FIRSTNET:\'89011507777777777777\'}}}')
	Map<String,String> simTypeDummyICCIDMap;

	def processOrder(order) {
		print 'simTypeDummyICCIDMap : '+simTypeDummyICCIDMap
		print 'Order '+order.order
		def losgs = order.order.losgs
		
		List<String> fulfillmentMethods = order?.order?.losgs?.findAll {losg -> losg.fulfillmentMethod}?.collect({it.fulfillmentMethod})
		
		print 'fulfillmentMethod '+fulfillmentMethods.toString()
		
		return losgs
	}
}
