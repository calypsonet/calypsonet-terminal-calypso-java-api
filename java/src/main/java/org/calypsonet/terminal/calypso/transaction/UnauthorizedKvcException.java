/* **************************************************************************************
 * Copyright (c) 2018 Calypso Networks Association https://calypsonet.org/
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
 * Indicates that the current card has an unauthorized KVC.
 *
 * @since 1.0
 */
public class UnauthorizedKvcException extends CardTransactionException {

  /**
   * @param message The message to identify the exception context
   * @since 1.0
   */
  public UnauthorizedKvcException(String message) {
    super(message);
  }
}
