file = open("testfile.txt","r+") 

print(file.read())

file.write("\n") 
file.write("Appending new data to file\n") 

for line in file: 
	print (line)
 
file.close() 