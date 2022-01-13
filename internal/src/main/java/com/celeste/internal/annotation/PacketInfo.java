package com.celeste.internal.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PacketInfo {

  int inboundId() default 999999;

  int outboundId() default 999999;

}
