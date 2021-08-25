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
 * Indicates a desynchronization of the APDU exchanges.
 *
 * <p>This means that the number of APDU requests is different from the number of APDU responses.
 *
 * @since 1.0.0
 */
public class DesynchronizedExchangesException extends CardTransactionException {

  /**
   * @param message The message to identify the exception context
   * @since 1.0.0
   */
  public DesynchronizedExchangesException(String message) {
    super(message);
  }
}
