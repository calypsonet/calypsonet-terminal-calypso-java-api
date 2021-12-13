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

import java.util.SortedMap;

/**
 * Calypso EF content.
 *
 * @since 1.0.0
 */
public interface FileData {

  /**
   * Gets a reference to the known content of record #1.<br>
   * For a Binary file, it means all the bytes of the file.
   *
   * @return an empty array if the record #1 is not set.
   * @since 1.0.0
   */
  byte[] getContent();

  /**
   * Gets a reference to the known content of a specific record.
   *
   * @param numRecord The record number.
   * @return an empty array if the requested record is not set.
   * @since 1.0.0
   */
  byte[] getContent(int numRecord);

  /**
   * Gets a copy of a known content subset of a specific record from dataOffset to dataLength.
   *
   * @param numRecord The record number.
   * @param dataOffset The offset index (should be {@code >=} 0).
   * @param dataLength The data length (should be {@code >=} 1).
   * @return a not empty copy of the record subset content when the record is set, an empty array
   *     when the record is not set.
   * @throws IllegalArgumentException if dataOffset {@code <} 0 or dataLength {@code <} 1.
   * @throws IndexOutOfBoundsException if dataOffset {@code >=} content length or (dataOffset +
   *     dataLength) {@code >} content length.
   * @since 1.0.0
   */
  byte[] getContent(int numRecord, int dataOffset, int dataLength);

  /**
   * Gets a reference to all known records content.
   *
   * @return a not null map eventually empty if there's no content.
   * @since 1.0.0
   */
  SortedMap<Integer, byte[]> getAllRecordsContent();

  /**
   * Gets the known value of the counter #numCounter.<br>
   * The counter value is extracted from the 3 next bytes at the index [(numCounter - 1) * 3] of the
   * record #1.<br>
   * e.g. if numCounter == 2, then value is extracted from bytes indexes [3,4,5].
   *
   * @param numCounter The counter number (should be {@code >=} 1).
   * @return The counter value or null if record #1 or numCounter is not set.
   * @throws IllegalArgumentException if numCounter is {@code <} 1.
   * @throws IndexOutOfBoundsException if numCounter has a truncated value (when size of record #1
   *     modulo 3 != 0).
   * @since 1.0.0
   */
  Integer getContentAsCounterValue(int numCounter);

  /**
   * Gets all known counters value.<br>
   * The counters values are extracted from record #1.<br>
   * If last counter has a truncated value (when size of record #1 modulo 3 != 0), then last counter
   * value is not returned.
   *
   * @return an empty map if record #1 is not set.
   * @since 1.0.0
   */
  SortedMap<Integer, Integer> getAllCountersValue();
}
