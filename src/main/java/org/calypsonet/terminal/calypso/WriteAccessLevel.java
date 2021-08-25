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
package org.calypsonet.terminal.calypso;

/**
 * Enumeration of the write access levels for the Calypso card Secure Session.
 *
 * <p>Each level induces the use of one of the 3 session key levels.
 *
 * @since 1.0.0
 */
public enum WriteAccessLevel {

  /**
   * For personalization, load and debit operations.
   *
   * <p>The <b>issuer key</b> will be used.
   *
   * @since 1.0.0
   */
  PERSONALIZATION,

  /**
   * For load and debit operations only.
   *
   * <p>The <b>load key</b> will be used.
   *
   * @since 1.0.0
   */
  LOAD,

  /**
   * For debit operations only.
   *
   * <p>The <b>debit key</b> will be used.
   *
   * @since 1.0.0
   */
  DEBIT
}
