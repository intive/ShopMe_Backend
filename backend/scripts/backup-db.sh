#!/bin/sh
set -x
DATE=$(date --iso-8601=seconds)
cp shopme.mv.db shopme.db-$DATE
