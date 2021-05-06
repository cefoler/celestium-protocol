package com.celeste.internal.model.type;

import lombok.Getter;

@Getter
public enum LoginState {

  START,
  ENCRYPTION_REQUEST,
  ENCRYPTION_RESPONSE,
  SUCCESS

}
