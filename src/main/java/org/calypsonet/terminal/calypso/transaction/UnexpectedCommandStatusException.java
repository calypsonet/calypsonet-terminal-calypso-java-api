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
 * Indicates that an unexpected command status was returned by the card or SAM.
 *
 * <p>This can occur if the card or SAM is not Calypso compliant or if the card has refused the
 * secure session closing.
 *
 * <p>For this last case, this is usually due to an incorrect SAM signature, or that the secure
 * session has been altered by other APDU commands that would have interfered with it.
 *
 * <p>If a secure session was open, the card discarded all data by cancelling all updates except for
 * PIN verification attempts.
 *
 * @since 1.2.0
 */
public class UnexpectedCommandStatusException extends RuntimeException {

  /**
   * @param message Message to identify the exception context.
   * @param cause The cause.
   * @since 1.2.0
   */
  public UnexpectedCommandStatusException(String message, Throwable cause) {
    super(message, cause);
  }
}
