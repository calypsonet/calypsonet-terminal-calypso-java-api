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
package org.calypsonet.terminal.calypso.card;

/**
 * Record of a Stored Value load log.
 *
 * @since 1.0.0
 */
public interface SvLoadLogRecord {

  /**
   * Gets the raw data of the SV load log record.
   *
   * @return A byte array.
   * @since 1.0.0
   */
  byte[] getRawData();

  /**
   * Gets the load date as an array of bytes
   *
   * @return A 2-byte byte array
   * @since 1.0.0
   */
  byte[] getLoadDate();

  /**
   * Gets the load time as an array of bytes
   *
   * @return A 2-byte byte array
   * @since 1.0.0
   */
  byte[] getLoadTime();

  /**
   * Gets the load amount value
   *
   * @return An int
   * @since 1.0.0
   */
  int getAmount();

  /**
   * Gets the SV balance value
   *
   * @return An int
   * @since 1.0.0
   */
  int getBalance();

  /**
   * Gets the free bytes as an array of bytes
   *
   * @return A 2-byte byte array
   * @since 1.0.0
   */
  byte[] getFreeData();

  /**
   * Gets the KVC of the load key (as given in the last SV Reload)
   *
   * @return A byte
   * @since 1.0.0
   */
  byte getKvc();

  /**
   * Gets the SAM ID as an array of bytes
   *
   * @return A 4-byte byte array
   * @since 1.0.0
   */
  byte[] getSamId();

  /**
   * Gets the SAM transaction number value as an int
   *
   * @return An int
   * @since 1.0.0
   */
  int getSamTNum();

  /**
   * Gets the SV transaction number value as an int
   *
   * @return An int
   * @since 1.0.0
   */
  int getSvTNum();
}
