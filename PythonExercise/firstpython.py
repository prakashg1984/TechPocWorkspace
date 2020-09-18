x = int(input("Please enter an integer: "))
if x < 0:
  x = 0
  print('Value of X now is ',x)
  print('Negative changed to zero')
elif x == 0:
  print('Value of X now is ',x)
  print('Zero')
elif x == 1:
  print('Value of X now is ',x)
  print('Single')
else:
  print('Value of X now is ',x)
  print('Double digit')
print('End of Program')
