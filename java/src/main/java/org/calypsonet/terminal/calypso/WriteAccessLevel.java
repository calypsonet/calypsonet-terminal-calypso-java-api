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
 * Definition of the write access levels of the Calypso card Secure Session inducing the use of the
 * issuer, reload or debit key.
 *
 * @since 1.0
 */
public enum WriteAccessLevel {

  /**
   * For card personalization operations.
   *
   * @since 1.0
   */
  PERSONALIZATION,
  /**
   * For reloading/selling operations.
   *
   * @since 1.0
   */
  LOAD,
  /**
   * For debit/validation operations.
   *
   * @since 1.0
   */
  DEBIT;
}
