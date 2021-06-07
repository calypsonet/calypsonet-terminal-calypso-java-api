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

/** Indicates an improper use of the {@link CardTransactionService} API. */
public class CalypsoCardTransactionIllegalStateException extends CalypsoCardTransactionException {

  /** @param message the message to identify the exception context */
  public CalypsoCardTransactionIllegalStateException(String message) {
    super(message);
  }
}
