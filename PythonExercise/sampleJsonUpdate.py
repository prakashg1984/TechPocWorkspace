import json
import io

listOfOrders = [line.rstrip('\n') for line in open('orders.txt')]

json_data = open("testfile.txt","r+").read()
data = json.loads(json_data)
orderData = data['Order']
groupData = orderData['Groups']['Group']

for order in listOfOrders:
	words = order.split(",")
	customerOrderNumber = words[0]
	oceOrderNumber = words[1]
	status = words[2]
	subStatus = words[3]
	losgId = words[4]
	losgType = words[5]
	productCategory = words[6]
	
	order_task = {
			"OrderTask": [{ 
				"OrderNumber": oceOrderNumber
			}]
			}
	losg_data = {
                	"Id": groupId,
                    "GroupCharacteristics": {
                        "LoSGCharacteristics": {
                            "LoSGReferenceId": losgId,
                            "LoSGStatus": {
                                "Status": status,
                                "SubStatus": subStatus
                            },
              		"LoSGType": losgType,
              		"ProductCategory": productCategory
			}
                    }
                }

	orderData.update({"CustomerOrderNumber" : customerOrderNumber})
	orderData.update({"OCEOrderNumber" : oceOrderNumber})

	for key in groupData:
		if 'characteristics' in key and 'losgCharacteristics' in key['characteristics']:
			key['characteristics']['losgCharacteristics']['losgReferenceId'] = losgId
			if 'losgStatus' in key['characteristics']['losgCharacteristics']:
				key['characteristics']['losgCharacteristics']['losgStatus']['status'] = status
				key['characteristics']['losgCharacteristics']['losgStatus']['subStatus'] = subStatus
			elif 'characteristics' in key and 'losgCharacteristics' in key['characteristics']:
				key['characteristics']['losgCharacteristics'].update({"losgStatus" : losg_data})


	print(data)

with open('new.txt', 'w') as txtfile:
	json.dump(data, txtfile)
