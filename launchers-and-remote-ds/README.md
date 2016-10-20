# DataSource access

Applications and custom JBoss launchers used for testing access to DataSource from another classloader and from another JVM.

If DS used from another JVM, then configure it in the corresponding `*-ds.xml`
as described in [JBoss documentation](https://docs.jboss.org/jbossas/docs/Server_Configuration_Guide/beta500/html/ch13s15.html)

```xml
<jndi-name>GenericDS</jndi-name>
<use-java-context>false</use-java-context>
<connection-url>...</connection-url>
```

## Securing DataSource password

### Generate obfuscated password

```bash
java -cp client/jboss-logging-spi.jar:lib/jbosssx.jar org.jboss.resource.security.SecureIdentityLoginModule PASSWORD
```

### login-config.xml

```xml
<application-policy name="secdomain-pw-encrypt">
  <authentication>
      <login-module code="org.jboss.resource.security.SecureIdentityLoginModule" flag="required">
          <module-option name="username">dballo02</module-option>
          <module-option name="password">-2a07519054e4ed40207a6df87216de44</module-option>
          <module-option name="managedConnectionFactoryName">jboss.jca:name=DS2,service=XATxCM</module-option>
      </login-module>
  </authentication>
</application-policy>
```
