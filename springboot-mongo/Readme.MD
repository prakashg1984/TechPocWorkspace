Prerequisite
 - Create 2 collection by the name accounts and promotions
 - For accounts collection create a unique index for field orderNumber
URL : localhost:8080/test/order
Request : 
			{
			    "orderNumber": "123456789",
			    "accounts": {
			        "account": [
			            {
			                "accountSubCategory": "ANONYMOUS",
			                "accountCategory": "MOBILITY_ACCOUNT",
			                "billingAccountNumber": "287280007349",
			                "paymentArrangement": "POSTPAID",
			                "id": "ACCOUNT_01",
			                "billingDetail": [
			                    {
			                        "nameReference": "NAME_01"
			                    }
			                ],
			                "langId": "ENGLISH",
			                "enterpriseType": "CON"
			            }
			        ]
			    },
			    "promotions": {
			        "promotion": [
			            {
			                "promotionCategory": "MOBILITY_ACCOUNT",
			                "promotionAccountNumber": "287280007349",
			                "paymentArrangement": "POSTPAID",
			                "promoid": "PROMO_01"
			            }
			        ]
			    }
			}
			
			
GET Order : localhost:8080/test/order/2134567890

Search Order : localhost:8080/test/order?searchString={"map.promotion.promotionCategory":"WIRELESS_ACCOUNT", "map.promotion.paymentArrangement" : "PREPAID_PAY"}