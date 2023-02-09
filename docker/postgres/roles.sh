#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "exchangerates" <<-EOSQL
	  INSERT INTO roles(name) VALUES('ROLE_USER');
    INSERT INTO roles(name) VALUES('ROLE_ADMIN');
EOSQL