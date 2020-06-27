from __future__ import print_function

import cx_Oracle

ip = '192.168.0.1'
port = 1521
SID = 'YOURSIDHERE'
dsn_tns = cx_Oracle.makedsn(ip, port, SID)

# Connect as user "hr" with password "welcome" to the "oraclepdb" service running on this computer.
# connection = cx_Oracle.connect("hr", "welcome", dsn_tns)
connstr = 'scott/tiger@server:1521/orcl'
connection = cx_Oracle.connect(connstr)

cursor = connection.cursor()
cursor.execute("""
    SELECT first_name, last_name
    FROM employees
    WHERE department_id = :did AND employee_id > :eid""",
    did = 50,
    eid = 190)
for fname, lname in cursor:
    print("Values:", fname, lname)
