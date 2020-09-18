import json
import requests
import cx_Oracle
import metaData

#Read Inputs - Read Order Nr, Sample Json Request and Metadata
listOfOrders = [line.rstrip('\n') for line in open('orders.txt')]
#json_data = open("orderRequest.txt","r+").read()
#data = json.loads(json_data)

data = {"Order": {
		"CustomerOrderNumber": "${customerOrderNumber}",
		"OCEOrderNumber": "${orderNumber}",
		"Groups": {
		}
	    }
	}

#Reads metada
httpUrl = metaData.resturl
dbConnstr = metaData.connstr
dbUser = metaData.userName
dbPwd = metaData.password

#Read required Json data
orderData = data['Order']
groupData = orderData['Groups']

#Create DB Connection
connection = cx_Oracle.connect(user=dbUser, password=dbPwd, dsn=dbConnstr)

#Function to execute the DB Query for an order
def dbQuery(customerOrderNumber):
	cursor = connection.cursor()
	cursor.execute("""
	    select distinct odo.order_id,og.ID,ogc.LOSG_REFERENCEID,ogc.status,ogc.sub_status,ogc.loSGType,ogc.product_category
		from OCE_PRODCORE.OCE_ORDER_GROUPLIST oog,OCE_PRODCORE.OCE_DCSPP_ORDER odo,OCE_PRODCORE.dcspp_order do, OCE_PRODCORE.OCE_GROUP og,  
		OCE_PRODCORE.OCE_GROUP_CHARACTERISTICS ogc where odo.ORDER_ID=oog.ORDER_ID  and oog.GROUPLIST_ID=og.GROUP_ID 
		and og.GROUP_CHARACTERISTICS=ogc.ORDER_GROUP_CHARACTERISTICS and odo.ORDER_ID=do.ORDER_ID and do.CREATED_BY_ORDER is null and ogc.LOSG_REFERENCEID is not null  and  odo.EXTERNAL_ORDER_NUM=  :customerOrderNumber""",
		customerOrderNumber = customerOrderNumber)
	return cursor


#Function to Create the Order Update payload
def updateOrder(order):
	words = order.split(",")
	customerOrderNumber = words[0]
	targetStatus = words[1]
	targetSubStatus = words[2]
	losgRefId = ""
	if len(words) > 3:
		losgRefId = words[3]
	cursor = dbQuery(customerOrderNumber)
	group = []
	
	orderData.update({"CustomerOrderNumber" : customerOrderNumber})
	for order_id,ID,LOSG_REFERENCEID,status,sub_status,loSGType,product_category in cursor:
		
		if losgRefId == "":
			losgData = {
				    "GroupCharacteristics": {
					"LoSGCharacteristics": {
					    "LoSGReferenceId": LOSG_REFERENCEID,
					    "LoSGStatus": {
						"Status": targetStatus,
						"SubStatus": targetSubStatus
					    },
					"LoSGType": loSGType,
					"ProductCategory": product_category
					}
				    }
				}
			group.append(losgData)
			
		elif losgRefId == LOSG_REFERENCEID:
			losgData = {"GroupCharacteristics": {
					"LoSGCharacteristics": {
					    "LoSGReferenceId": LOSG_REFERENCEID,
					    "LoSGStatus": {
						"Status": targetStatus,
						"SubStatus": targetSubStatus
					    },
					"LoSGType": loSGType,
					"ProductCategory": product_category
					}
				    }
				}
			group.append(losgData)
		
		orderData.update({"OCEOrderNumber" : order_id})
		groupData.update({"Group" : group})
		
		order_task = {"OrderTask": [{ 
						"OrderNumber": order_id
					}]
		     		 }
		
	orderData.update({"Notes" : "Order Update Manually"})
	
	if targetStatus == "IN_QUEUE":
		data.update({"OrderTasks" : order_task})
	
	invokeRestCall(data)


#Function to Make the Rest POST Call
def invokeRestCall(data):
	print(json.dumps(data))
	response = requests.post(httpUrl, data=json.dumps(data),headers={"Content-Type": "application/json"})
	print(response.text)
	
	
#Starting Method
for order in listOfOrders:
	updateOrder(order)