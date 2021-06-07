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

import java.util.NoSuchElementException;
import org.calypsonet.terminal.calypso.WriteAccessLevel;

/**
 * All metadata of a Calypso DF.
 *
 * @since 1.0
 */
public interface DirectoryHeader {

  /**
   * Gets the associated LID.
   *
   * @return The LID
   * @since 1.0
   */
  short getLid();

  /**
   * Gets the DF status.
   *
   * @return The DF status byte
   * @since 1.0
   */
  byte getDfStatus();

  /**
   * Gets a reference to access conditions.
   *
   * @return A not empty byte array
   * @since 1.0
   */
  byte[] getAccessConditions();

  /**
   * Gets a reference to keys indexes.
   *
   * @return A not empty byte array
   * @since 1.0
   */
  byte[] getKeyIndexes();

  /**
   * Returns true if the KIF for the provided write access level is available.
   *
   * @param writeAccessLevel The write access level (should be not null).
   * @return True if the KIF for the provided writeAccessLevel is available
   * @since 1.0
   */
  boolean isKifAvailable(WriteAccessLevel writeAccessLevel);

  /**
   * Gets the KIF associated to the provided write access level.
   *
   * @param writeAccessLevel The write access level (should be not null).
   * @return The KIF value.
   * @throws IllegalArgumentException if writeAccessLevel is null.
   * @throws NoSuchElementException if KIF is not found.
   * @since 1.0
   */
  byte getKif(WriteAccessLevel writeAccessLevel);

  /**
   * Returns true if the KVC for the provided write access level is available.
   *
   * @param writeAccessLevel The write access level (should be not null).
   * @return True if the KVC for the provided writeAccessLevel is available
   * @since 1.0
   */
  boolean isKvcAvailable(WriteAccessLevel writeAccessLevel);

  /**
   * Gets the KVC associated to the provided write access level.
   *
   * @param writeAccessLevel The write access level (should be not null).
   * @return The KVC value.
   * @throws IllegalArgumentException if writeAccessLevel is null.
   * @throws NoSuchElementException if KVC is not found.
   * @since 1.0
   */
  byte getKvc(WriteAccessLevel writeAccessLevel);
}
