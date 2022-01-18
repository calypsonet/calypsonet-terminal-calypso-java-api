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
package org.calypsonet.terminal.calypso;

import java.util.List;

/**
 * Contains the input/output data of a "Search Record Multiple" card command.
 *
 * @since 1.1.0
 */
public interface SearchCommandData {

  /**
   * Sets the SFI of the EF in which the search is to be performed.
   *
   * @param sfi The SFI of the EF.
   * @return The current instance.
   * @since 1.1.0
   */
  SearchCommandData setSfi(byte sfi);

  /**
   * Sets the number of the record where the search should begin.
   *
   * <p>By default, the search will begin from the first record.
   *
   * @param recordNumber The number of the record where the search should begin.
   * @return The current instance.
   * @since 1.1.0
   */
  SearchCommandData startAtRecord(int recordNumber);

  /**
   * Sets the offset in number of bytes from which the analysis should be performed within a record.
   *
   * <p>By default, the analysis will start at the beginning of the record (offset 0).
   *
   * @param offset The offset.
   * @return The current instance.
   * @since 1.1.0
   */
  SearchCommandData setOffset(int offset);

  /**
   * Allows the command to analyze the data present at the given offset, and repeatedly at each
   * following offset, until the end of the record is reached. The last offset is less or equal than
   * ((record size) − (length of the search data)).
   *
   * @return The current instance.
   * @since 1.1.0
   */
  SearchCommandData enableRepeatedOffset();

  /**
   * Sets the data to search.
   *
   * @param data The data to search.
   * @return The current instance.
   * @since 1.1.0
   */
  SearchCommandData setSearchData(byte[] data);

  /**
   * Sets the mask of bits to take into account during the comparison (padded right with FFh if
   * absent or incomplete).
   *
   * <p>Requirement: The length of the mask must be <b>less or equal</b> than the length of the data
   * to search.
   *
   * @param mask The mask.
   * @return The current instance.
   * @since 1.1.0
   */
  SearchCommandData setMask(byte[] mask);

  /**
   * Requests to fetch the content of the first matching record into the {@link
   * org.calypsonet.terminal.calypso.card.CalypsoCard}.
   *
   * @return The current instance.
   * @since 1.1.0
   */
  SearchCommandData fetchFirstMatchingResult();

  /**
   * Returns a list containing the numbers of the records who has matched.
   *
   * @return An empty list if no record has matched or if the command has not yet been processed.
   * @since 1.1.0
   */
  List<Integer> getMatchingRecordNumbers();
}
