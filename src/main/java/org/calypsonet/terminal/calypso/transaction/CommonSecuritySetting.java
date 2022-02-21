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

import org.calypsonet.terminal.calypso.spi.SamRevocationServiceSpi;

/**
 * Common data to manage the security operations of a Calypso transaction.
 *
 * @param <T> The type of the lowest level child object.
 * @since 1.2.0
 */
public interface CommonSecuritySetting<T extends CommonSecuritySetting<T>> {

  /**
   * Sets the service to be used to dynamically check if a SAM is revoked or not.
   *
   * @param service The user's service to be used.
   * @return The current instance.
   * @throws IllegalArgumentException If the provided service is null.
   * @since 1.2.0
   */
  T setSamRevocationService(SamRevocationServiceSpi service);
}
