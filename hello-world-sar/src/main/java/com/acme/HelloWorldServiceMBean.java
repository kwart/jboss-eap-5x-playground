package com.acme;

import org.jboss.system.ServiceMBean;

public interface HelloWorldServiceMBean extends ServiceMBean
{
   // The print message operation
   void printMessage();
}
