package com.celeste.internal.model.protocol.state;

import lombok.Getter;

@Getter
public enum LoginState {

  START,
  ENCRYPTION_REQUEST,
  ENCRYPTION_RESPONSE,
  SUCCESS

}
