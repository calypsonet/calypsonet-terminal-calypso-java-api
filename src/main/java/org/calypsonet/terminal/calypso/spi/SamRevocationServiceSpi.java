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
package org.calypsonet.terminal.calypso.spi;

/**
 * Service to be implemented in order to check dynamically if a SAM is revoked.
 *
 * @since 1.2.0
 */
public interface SamRevocationServiceSpi {

  /**
   * Checks if the SAM with the provided serial number is revoked or not.
   *
   * <p>Note: the provided SAM serial number can be complete (4 bytes) or partial (3 LSBytes).
   *
   * @param serialNumber The complete or partial SAM serial number to check.
   * @return True if the SAM is revoked, otherwise false.
   * @since 1.2.0
   */
  boolean isSamRevoked(final byte[] serialNumber);

  /**
   * Checks if the SAM with the provided serial number and the associated counter value is revoked
   * or not.
   *
   * <p>Note: the provided SAM serial number can be complete (4 bytes) or partial (3 LSBytes).
   *
   * @param serialNumber The complete or partial SAM serial number to check.
   * @param counterValue The SAM counter value.
   * @return True if the SAM is revoked, otherwise false.
   * @since 1.2.0
   */
  boolean isSamRevoked(final byte[] serialNumber, int counterValue);
}
