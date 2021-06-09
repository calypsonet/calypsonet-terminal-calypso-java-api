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
 * Indicates an anomaly in the SAM.
 *
 * <p>This can occur if the SAM is not Calypso compliant.
 *
 * @since 1.0
 */
public class SamAnomalyException extends CardTransactionException {

  /**
   * @param message Message to identify the exception context.
   * @param cause The cause.
   * @since 1.0
   */
  public SamAnomalyException(String message, Throwable cause) {
    super(message, cause);
  }
}
