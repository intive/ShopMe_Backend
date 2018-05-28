#!/bin/sh

DATE=$(date "+%Y-%m-%d %H;%M;%S")

if grep -q Microsoft /proc/version; then
  OS=WSL
  FOLDER=../src/main/resources
else
  # TODO test in final server/docker environment
  OS=NATIVE
  FOLDER=~
fi

cp $FOLDER/premium_numbers.txt "$FOLDER/premium_numbers.txt-$DATE" 2>/dev/null

wget --quiet https://archiwum.uke.gov.pl/tablice/xml/PRM.xml.zip -O- | gunzip | \
    sed -n -e 's/ - /-/g' -e 's/.*<numer>\([0-9]\{9,10\}\([-0-9]\{9,10\}\)\{0,1\}\)<\/numer>/\1/p' -e 's/\*//g' | \
    sort > $FOLDER/premium_numbers.txt

echo Downloaded `wc -l < $FOLDER/premium_numbers.txt` premium rate numbers list
