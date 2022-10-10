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
package org.calypsonet.terminal.calypso.sam;

import java.util.SortedMap;
import org.calypsonet.terminal.reader.selection.spi.SmartCard;

/**
 * A static and partial view of the SAM's content.
 *
 * @since 1.0.0
 */
public interface CalypsoSam extends SmartCard {

  /**
   * Gets the SAM product type.
   *
   * @return The identified product type.
   * @since 1.0.0
   */
  ProductType getProductType();

  /**
   * Gets a text description of the SAM.
   *
   * @return A not null String.
   * @since 1.0.0
   */
  String getProductInfo();

  /**
   * Gets the SAM serial number as an array of bytes
   *
   * @return A not null array of bytes
   * @since 1.0.0
   */
  byte[] getSerialNumber();

  /**
   * Gets the platform identifier
   *
   * @return A byte
   * @since 1.0.0
   */
  byte getPlatform();

  /**
   * Gets the application type
   *
   * @return A byte
   * @since 1.0.0
   */
  byte getApplicationType();

  /**
   * Gets the application subtype
   *
   * @return A byte
   * @since 1.0.0
   */
  byte getApplicationSubType();

  /**
   * Gets the software issuer identifier
   *
   * @return A byte
   * @since 1.0.0
   */
  byte getSoftwareIssuer();

  /**
   * Gets the software version number
   *
   * @return A byte
   * @since 1.0.0
   */
  byte getSoftwareVersion();

  /**
   * Gets the software revision number
   *
   * @return A byte
   * @since 1.0.0
   */
  byte getSoftwareRevision();

  /**
   * All Calypso SAM products supported by this API.
   *
   * @since 1.0.0
   */
  enum ProductType {

    /**
     * SAM C1
     *
     * @since 1.0.0
     */
    SAM_C1,

    /**
     * SAM C1 HSM
     *
     * @since 1.3.0
     */
    HSM_C1,

    /**
     * SAM S1E1
     *
     * @since 1.0.0
     */
    SAM_S1E1,

    /**
     * SAM S1Dx
     *
     * @since 1.0.0
     */
    SAM_S1DX,

    /**
     * SAM CSAM_F
     *
     * @since 1.0.0
     */
    CSAM_F,

    /**
     * Unidentified smart card.
     *
     * @since 1.0.0
     */
    UNKNOWN
  }

  /**
   * Returns the value of an event counter.
   *
   * @param eventCounterNumber The number of the event counter (in range [0..26]).
   * @return null if the event counter value is not set.
   * @throws IllegalArgumentException If the provided argument is out of range.
   * @since 1.4.0
   */
  Integer getEventCounter(int eventCounterNumber);

  /**
   * Returns the value of known event counters in a sorted map where {@code key} is the counter
   * number and {@code value} is the counter value.
   *
   * @return a not null map.
   * @since 1.4.0
   */
  SortedMap<Integer, Integer> getEventCounters();

  /**
   * Returns the value of an event ceiling.
   *
   * @param eventCeilingNumber The number of the event ceiling (in range [0..26]).
   * @return null if the event ceiling value is not set.
   * @throws IllegalArgumentException If the provided argument is out of range.
   * @since 1.4.0
   */
  Integer getEventCeiling(int eventCeilingNumber);

  /**
   * Returns the value of known event ceilings in a sorted map where {@code key} is the ceiling
   * number and {@code value} is the ceiling value.
   *
   * @return a not null map.
   * @since 1.4.0
   */
  SortedMap<Integer, Integer> getEventCeilings();
}
