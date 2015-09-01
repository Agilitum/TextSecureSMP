package org.thoughtcrime.SMP.protocol;

public class PrekeyBundleWirePrefix extends WirePrefix {
  @Override
  public String calculatePrefix(String message) {
    return super.calculatePreKeyBundlePrefix(message);
  }
}
