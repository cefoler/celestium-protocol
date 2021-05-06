package com.celeste.internal.util;

import com.google.common.flogger.FluentLogger;
import lombok.Getter;

public final class Logging {

  @Getter
  public static final FluentLogger LOGGER = FluentLogger.forEnclosingClass();

}
