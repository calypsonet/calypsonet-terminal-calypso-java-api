/* **************************************************************************************
 * Copyright (c) 2021 Calypso Networks Association https://calypsonet.org/
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
 * Defines the type of Stored Value operation to be performed.
 *
 * @since 1.0.0
 */
public enum SvOperation {
  /**
   * Increase the balance of the stored value
   *
   * @since 1.0.0
   */
  RELOAD,
  /**
   * Decrease the balance of the stored value
   *
   * @since 1.0.0
   */
  DEBIT
}
