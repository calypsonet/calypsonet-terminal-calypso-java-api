/* **************************************************************************************
 * Copyright (c) 2020 Calypso Networks Association https://calypsonet.org/
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
 * Parent abstract class of all Calypso card transaction functional exceptions.
 *
 * @since 1.0.0
 */
public abstract class CardTransactionException extends RuntimeException {

  /**
   * @param message The message to identify the exception context
   * @since 1.0.0
   */
  protected CardTransactionException(String message) {
    super(message);
  }

  /**
   * Encapsulates a lower level card transaction exception
   *
   * @param message Message to identify the exception context.
   * @param cause The cause.
   * @since 1.0.0
   */
  protected CardTransactionException(String message, Throwable cause) {
    super(message, cause);
  }
}
