#!/bin/sh
set -x
REMOTE="it-29663@10.5.170.2"
(cd ../target \
     && scp be.jar $REMOTE:backend \
     && ssh $REMOTE <<EOF
sudo service shopme-backend stop
~/backup-db.sh
cd backend/prod
mv be.jar be.jar.old
mv ../be.jar .
sudo service shopme-backend start
EOF
)
