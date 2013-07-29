#!/bin/bash

echo Generate Static Site
rm -Rf  tapestry/

echo Generate Tapestry JavaDoc
cd ../kawwa2-tapestry
mvn clean site:site
cd ../staticsite

mkdir tapestry
cp  -r ../kawwa2-tapestry/target/site/apidocs/* tapestry/

echo Generate AngularJS Documentation [NOT READY]

echo Generate Release Notes Components [NOT READY]

echo Generate Release Notes Tapestry [NOT READY]

echo Generate Release Notes AngularJS [NOT READY]

echo Generate Release Notes SublimeText [NOT READY]