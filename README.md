# JBoss EAP 5.x playground

Playground for my JBoss EAP 5.x (or AS 5.x) related tasks.

## Compile and run EAP testsuite

https://docs.jboss.org/process-guide/en/html/testsuite.html

```bash
cd ${HOME}/test/510/
unzip jboss-eap-noauth-5.1.0.zip
unzip jboss-eap-src-5.1.0.zip
JBOSS_HOME=${HOME}/test/510/jboss-eap-5.1/jboss-as

cd jboss-eap-5.1-src/jboss-as/build
./build.sh -Dbuild.unsecure=true clean main -Dthirdparty.maven.opts="-Dskip-download-sources" -Dunsign.supressBackup=true

export ANT_OPTS=-Xmx2048m
export ANT_OPTS="$ANT_OPTS -Djbossas.startup.timeout=600"

cd ../testsuite
./build.sh clean tests -Djbosstest.test.soa=true -Djbosstest.useJBM=true "-Djbosstest.dist=$JBOSS_HOME" "-Djboss.dist=$JBOSS_HOME" -Djunit.timeout=600000
```

### Run single test

Start the EAP first. If you want to debug it, then uncomment corresponding line in `bin/run.conf`.

```bash
cd testsuite
JBOSS_HOME=${HOME}/test/510/jboss-eap-5.1/jboss-as
./build.sh clean jar test -Dtest=org.jboss.test.jca.test.InflowUnitTestCase
```
