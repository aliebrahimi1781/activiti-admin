#!/bin/bash
for i in {1..31}
do
curl -v -H "Content-Type: application/xml" -X POST --data "@SP_C_FIXE_SP_C_ADSL.xml" http://localhost:8080/capactiviti-webapp/rest/process/start 
done
