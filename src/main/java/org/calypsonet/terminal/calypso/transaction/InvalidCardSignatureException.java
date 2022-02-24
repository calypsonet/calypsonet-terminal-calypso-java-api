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
 * Indicates that the card has correctly closed the secure session, but the card session is not
 * authentic because the signature of the card is incorrect. This can happen in the following cases:
 *
 * <ul>
 *   <li>The "Digest Authenticate" SAM command status is 6985h;
 *   <li>The "SV Check" SAM command status is 6985h;
 * </ul>
 *
 * @since 1.2.0
 */
public class InvalidCardSignatureException extends RuntimeException {

  /**
   * @param message The message to identify the exception context.
   * @since 1.2.0
   */
  public InvalidCardSignatureException(String message) {
    super(message);
  }

  /**
   * Encapsulates a lower level exception.
   *
   * @param message Message to identify the exception context.
   * @param cause The cause.
   * @since 1.2.0
   */
  public InvalidCardSignatureException(String message, Throwable cause) {
    super(message, cause);
  }
}
