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
package org.calypsonet.terminal.calypso.transaction;

import org.calypsonet.terminal.calypso.sam.CalypsoSam;
import org.calypsonet.terminal.calypso.spi.SamRevocationServiceSpi;
import org.calypsonet.terminal.reader.CardReader;

/**
 * Common data to manage the security operations of a Calypso transaction.
 *
 * @param <S> The type of the lowest level child object.
 * @since 1.2.0
 */
public interface CommonSecuritySetting<S extends CommonSecuritySetting<S>> {

  /**
   * Defines the control SAM and the reader through which it is accessible to be used to handle the
   * relevant cryptographic operations.
   *
   * @param samReader The control SAM reader.
   * @param calypsoSam The control Calypso SAM.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the arguments is null or if the product type of
   *     {@link CalypsoSam} is equal to {@link CalypsoSam.ProductType#UNKNOWN}.
   * @since 1.2.0
   */
  S setControlSamResource(CardReader samReader, CalypsoSam calypsoSam);

  /**
   * Sets the service to be used to dynamically check if a SAM is revoked or not.
   *
   * @param service The user's service to be used.
   * @return The current instance.
   * @throws IllegalArgumentException If the provided service is null.
   * @since 1.2.0
   */
  S setSamRevocationService(SamRevocationServiceSpi service);
}
