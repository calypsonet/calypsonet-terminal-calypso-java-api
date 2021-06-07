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

import org.calypsonet.terminal.reader.selection.spi.SmartCard;

/**
 * All known information about the current SAM.
 *
 * @since 1.0
 */
public interface CalypsoSam extends SmartCard {

  /**
   * All Calypso SAM products supported by this API.
   *
   * @since 1.0
   */
  public enum ProductType {
    SAM_C1,
    SAM_S1E1,
    SAM_S1Dx,
    CSAM_F
  }

  /**
   * Gets the SAM product type.
   *
   * @return The identified product type.
   * @since 1.0
   */
  ProductType getProductType();

  /**
   * Gets a text description of the SAM.
   *
   * @return A not null String.
   * @since 1.0
   */
  String getProductInfo();

  /**
   * Gets the SAM serial number as an array of bytes
   *
   * @return A not null array of bytes
   * @since 1.0
   */
  byte[] getSerialNumber();

  /**
   * Gets the platform identifier
   *
   * @return A byte
   * @since 1.0
   */
  byte getPlatform();

  /**
   * Gets the application type
   *
   * @return A byte
   * @since 1.0
   */
  byte getApplicationType();

  /**
   * Gets the application subtype
   *
   * @return A byte
   * @since 1.0
   */
  byte getApplicationSubType();

  /**
   * Gets the software issuer identifier
   *
   * @return A byte
   * @since 1.0
   */
  byte getSoftwareIssuer();

  /**
   * Gets the software version number
   *
   * @return A byte
   * @since 1.0
   */
  byte getSoftwareVersion();

  /**
   * Gets the software revision number
   *
   * @return A byte
   * @since 1.0
   */
  byte getSoftwareRevision();
}
