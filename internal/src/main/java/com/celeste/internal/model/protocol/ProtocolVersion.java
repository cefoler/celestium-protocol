package com.celeste.internal.model.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProtocolVersion {

  SEVEN(5, "1.7.10"),
  EIGHT(47, "1.8.9"),
  NINE(110, "1.9.4"),
  TEN(210, "1.10.2"),
  ELEVEN(316, "1.12.2"),
  TWELVE(340, "1.12.2"),
  THIRTEEN(404, "1.13.2"),
  FOURTEEN(498, "1.14.4"),
  FIVETEEN(578, "1.15.2"),
  SIXTEEN(754, "1.16.5"),
  SEVENTEEN(755, "1.17");

  private final int version;
  private final String name;

}
