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
 * Calypso Elementary File.
 *
 * @since 1.0
 */
public interface ElementaryFile {

  /**
   * Gets the associated SFI.
   *
   * @return The SFI.
   * @since 1.0
   */
  byte getSfi();

  /**
   * Gets the file header.
   *
   * @return Null if header is not yet set.
   * @since 1.0
   */
  FileHeader getHeader();

  /**
   * Gets the file data.
   *
   * @return A not null data reference.
   * @since 1.0
   */
  FileData getData();

  /**
   * The different types of EF.
   *
   * @since 1.0
   */
  enum Type {
    /**
     * A Linear EF is made of 1 to several records.
     *
     * @since 1.0
     */
    LINEAR,
    /**
     * A Binary EF contains a single continuous sequence of data bytes from byte #0 (first byte) to
     * byte #N−1 (last byte, for a binary file of N bytes).
     *
     * @since 1.0
     */
    BINARY,
    /**
     * A Cyclic EF is made of 1 to several records organized in a cycle, from the most recent
     * (record #1) to the oldest.
     *
     * @since 1.0
     */
    CYCLIC,
    /**
     * A Counters EF is made of a single record containing an ordered sequence of K counters of
     * three bytes each, from counter #1 (bytes at offsets 0, 1 and 2 of the record) to counter #K.
     *
     * @since 1.0
     */
    COUNTERS,
    /**
     * A Simulated Counter EF is a linear file with a single record.<br>
     * Simulated Counter EFs are present for compatibility with the Calypso Revision 2 access to
     * simulated individual counters.
     *
     * @since 1.0
     */
    SIMULATED_COUNTERS
  }
}
