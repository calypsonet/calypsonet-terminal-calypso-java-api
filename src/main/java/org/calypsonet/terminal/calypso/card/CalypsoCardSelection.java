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

import org.calypsonet.terminal.calypso.GetDataTag;
import org.calypsonet.terminal.calypso.SelectFileControl;
import org.calypsonet.terminal.reader.selection.spi.CardSelection;

/**
 * Card specific {@link CardSelection} providing means to filter cards, select applications and
 * define optional commands to be executed during the selection phase.
 *
 * <p>Note: by default, the selection process ignores PRIME revision 3 cards that have been
 * invalidated. The {@link CalypsoCardSelection#acceptInvalidatedCard()} method must be invoked to
 * stop ignoring them.<br>
 * This feature does not apply to earlier revisions for which it is necessary to run a Select File
 * (DF) command to determine the invalidation status. In this case, the rejection or acceptance of
 * invalidated cards will have to be handled by the application.
 *
 * @since 1.0.0
 */
public interface CalypsoCardSelection extends CardSelection {

  /**
   * Requests a protocol-based filtering by defining an expected card.
   *
   * <p>If the card protocol is set, only cards using that protocol will match the card selector.
   *
   * @param cardProtocol A not empty String.
   * @return The object instance.
   * @throws IllegalArgumentException If the argument is null or empty.
   * @since 1.0.0
   */
  CalypsoCardSelection filterByCardProtocol(String cardProtocol);

  /**
   * Requests a power-on data-based filtering by defining a regular expression that will be applied
   * to the card's power-on data.
   *
   * <p>If it is set, only the cards whose power-on data is recognized by the provided regular
   * expression will match the card selector.
   *
   * @param powerOnDataRegex A valid regular expression.
   * @return The object instance.
   * @throws IllegalArgumentException If the provided regular expression is null, empty or invalid.
   * @since 1.0.0
   */
  CalypsoCardSelection filterByPowerOnData(String powerOnDataRegex);

  /**
   * Requests a DF Name-based filtering by defining in a byte array the AID that will be included in
   * the standard SELECT APPLICATION command sent to the card during the selection process.
   *
   * <p>The provided AID can be a right truncated image of the target DF Name (see ISO 7816-4 4.2).
   *
   * @param aid A byte array containing 5 to 16 bytes.
   * @return The object instance.
   * @throws IllegalArgumentException If the provided array is null or out of range.
   * @since 1.0.0
   */
  CalypsoCardSelection filterByDfName(byte[] aid);

  /**
   * Requests a DF Name-based filtering by defining in a hexadecimal string the AID that will be
   * included in the standard SELECT APPLICATION command sent to the card during the selection
   * process.
   *
   * <p>The provided AID can be a right truncated image of the target DF Name (see ISO 7816-4 4.2).
   *
   * @param aid A hexadecimal string representation of 5 to 16 bytes.
   * @return The object instance.
   * @throws IllegalArgumentException If the provided AID is null, invalid or out of range.
   * @since 1.0.0
   */
  CalypsoCardSelection filterByDfName(String aid);

  /**
   * Sets the file occurrence mode (see ISO7816-4).
   *
   * <p>The default value is {@link CalypsoCardSelection.FileOccurrence#FIRST}.
   *
   * @param fileOccurrence The {@link CalypsoCardSelection.FileOccurrence}.
   * @return The object instance.
   * @throws IllegalArgumentException If fileOccurrence is null.
   * @since 1.0.0
   */
  CalypsoCardSelection setFileOccurrence(FileOccurrence fileOccurrence);

  /**
   * Sets the file control mode (see ISO7816-4).
   *
   * <p>The default value is {@link CalypsoCardSelection.FileControlInformation#FCI}.
   *
   * @param fileControlInformation The {@link CalypsoCardSelection.FileControlInformation}.
   * @return The object instance.
   * @throws IllegalArgumentException If fileControlInformation is null.
   * @since 1.0.0
   */
  CalypsoCardSelection setFileControlInformation(FileControlInformation fileControlInformation);

  /**
   * Adds a status word to the list of those that should be considered successful for the Select
   * Application APDU.
   *
   * <p>Note: initially, the list contains the standard successful status word {@code 9000h}.
   *
   * @param statusWord A positive int &le; {@code FFFFh}.
   * @return The object instance.
   * @since 1.0.0
   * @deprecated Use {@link #acceptInvalidatedCard()} method instead.
   */
  @Deprecated
  CalypsoCardSelection addSuccessfulStatusWord(int statusWord);

  /**
   * Request to accept invalidated cards during the selection stage.
   *
   * <p>Caution: this functionality is operational only from PRIME revision 3 cards. Invalidated
   * cards are rejected by default.
   *
   * @return The object instance.
   * @since 1.0.0
   */
  CalypsoCardSelection acceptInvalidatedCard();

  /**
   * Adds a command APDU to select file with an LID provided as a 2-byte byte array.
   *
   * @param lid LID of the EF to select as a byte array
   * @return The object instance.
   * @throws IllegalArgumentException If the argument is not an array of 2 bytes.
   * @since 1.0.0
   */
  CalypsoCardSelection prepareSelectFile(byte[] lid);

  /**
   * Adds a command APDU to select file with an LID provided as a short.
   *
   * @param lid A short.
   * @return The object instance.
   * @since 1.0.0
   */
  CalypsoCardSelection prepareSelectFile(short lid);

  /**
   * Adds a command APDU to select file according to the provided {@link SelectFileControl} enum
   * entry indicating the navigation case: FIRST, NEXT or CURRENT.
   *
   * @param selectControl A {@link SelectFileControl} enum entry.
   * @return The object instance.
   * @throws IllegalArgumentException If the argument is null.
   * @since 1.0.0
   */
  CalypsoCardSelection prepareSelectFile(SelectFileControl selectControl);

  /**
   * Adds a command APDU to read a single record from the indicated EF.
   *
   * @param sfi The SFI of the EF to read
   * @param recordNumber The record number to read.
   * @return The object instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CalypsoCardSelection prepareReadRecordFile(byte sfi, int recordNumber);

  /**
   * Adds a command APDU to retrieve the data indicated by the provided tag.
   *
   * <p>This method can be used to obtain FCI information when it is not provided directly by the
   * select application (e.g. OMAPI case).
   *
   * @param tag The tag to use.
   * @return The object instance.
   * @throws IllegalArgumentException If tag is null.
   * @throws UnsupportedOperationException If the Get Data command with the provided tag is not
   *     supported.
   * @since 1.0.0
   */
  CalypsoCardSelection prepareGetData(GetDataTag tag);

  /**
   * Navigation options through the different applications contained in the card according to the
   * ISO7816-4 standard.
   *
   * @since 1.0.0
   */
  enum FileOccurrence {
    /**
     * First occurrence.
     *
     * @since 1.0.0
     */
    FIRST,
    /**
     * Last occurrence.
     *
     * @since 1.0.0
     */
    LAST,
    /**
     * Next occurrence.
     *
     * @since 1.0.0
     */
    NEXT,
    /**
     * Previous occurrence.
     *
     * @since 1.0.0
     */
    PREVIOUS
  }

  /**
   * Types of templates available in return for the Select Application command, according to the
   * ISO7816-4 standard.
   *
   * @since 1.0.0
   */
  enum FileControlInformation {
    /**
     * File control information.
     *
     * @since 1.0.0
     */
    FCI,
    /**
     * No response expected.
     *
     * @since 1.0.0
     */
    NO_RESPONSE
  }
}
