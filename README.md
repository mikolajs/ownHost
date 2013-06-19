
Database - postgres

as postgres user:
createdb ownhost
psql ownhost

> Create user ownhost with password 'owncloud456';
> GRANT ALL ON DATABASE ownhost TO ownhost;

use - sbt   (0.12.3)

RUN:
container:start

STOP:
container:stop

view in browser: localhost:8080