/* **************************************************************************************
 * Copyright (c) 2022 Calypso Networks Association https://calypsonet.org/
 *
 * See the NOTICE file(s) distributed with this work for additional information
 * regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 ************************************************************************************** */
package org.calypsonet.terminal.calypso.transaction;

/**
 * Indicates that the card has correctly closed the secure session, but that it is impossible to
 * check the authenticity of the card session because the SAM is no more available (timeout, network
 * problem, etc...).
 *
 * @since 1.2.0
 */
public class CardSignatureNotVerifiableException extends RuntimeException {

  /**
   * @param message The message to identify the exception context.
   * @since 1.2.0
   */
  public CardSignatureNotVerifiableException(String message) {
    super(message);
  }

  /**
   * Encapsulates a lower level exception.
   *
   * @param message Message to identify the exception context.
   * @param cause The cause.
   * @since 1.2.0
   */
  public CardSignatureNotVerifiableException(String message, Throwable cause) {
    super(message, cause);
  }
}
