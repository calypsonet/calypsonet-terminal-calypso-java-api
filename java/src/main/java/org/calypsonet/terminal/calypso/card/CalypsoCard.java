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

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.calypsonet.terminal.reader.selection.spi.SmartCard;

/**
 * A dynamic view of the card's content, regularly updated from the selection stage to the end of
 * the transaction.
 *
 * <p>An instance of CalypsoCard is obtained by casting the {@link SmartCard} object from the
 * selection process defined by the <b>Terminal Reader API</b> and updated by the {@link
 * org.calypsonet.terminal.calypso.transaction.CardTransactionManager}.
 *
 * <p>The various information contained in CalypsoCard includes:
 *
 * <ul>
 *   <li>The application identification fields (revision/version, class, DF name, serial number,
 *       ATR, issuer)
 *   <li>The indication of the presence of optional features (Stored Value, PIN, Rev3.2 mode,
 *       ratification management)
 *   <li>The management information of the modification buffer
 *   <li>The invalidation status
 *   <li>The files, counters, SV data read or modified during the execution of the processes defined
 *       by the card transaction manager.
 * </ul>
 *
 * @since 1.0
 */
public interface CalypsoCard extends SmartCard {

  /**
   * Gets the card product type.
   *
   * @return A not null reference.
   * @since 1.0
   */
  ProductType getProductType();

  /**
   * Gets the DF name as an array of bytes.
   *
   * <p>The DF name is the name of the application DF as defined in ISO/IEC 7816-4.
   *
   * <p>It also corresponds to the complete representation of the target covered by the AID value
   * provided in the selection command.
   *
   * <p>The AID selects the application by specifying all or part of the targeted DF Name (5 bytes
   * minimum).
   *
   * @return A not null byte array containing the DF Name bytes (5 to 16 bytes)
   * @since 1.0
   */
  byte[] getDfName();

  /**
   * Gets the Calypso application serial number as an array of bytes.
   *
   * @return A not null byte array containing the Application Serial Number (8 bytes).
   * @since 1.0
   */
  byte[] getApplicationSerialNumber();

  /**
   * Gets the raw Calypso startup information.
   *
   * @return A not null byte array.
   * @since 1.0
   */
  byte[] getStartupInfoRawData();

  /**
   * Gets the platform identification byte which is the reference of the chip.
   *
   * @return The platform identification byte.
   * @since 1.0
   */
  byte getPlatform();

  /**
   * Gets the Application Type byte which determines the Calypso product type and various options.
   *
   * @return The Application Type byte.
   * @since 1.0
   */
  byte getApplicationType();

  /**
   * Gets the Application Subtype that provides a reference to the file structure of the Calypso DF.
   *
   * @return The Application Subtype byte
   * @since 1.0
   */
  byte getApplicationSubtype();

  /**
   * Gets the Software Issuer byte that indicates the entity responsible for the software of the
   * selected application.
   *
   * @return The Software Issuer byte.
   * @since 1.0
   */
  byte getSoftwareIssuer();

  /**
   * Gets the Software Version field that may be set to any fixed value by the Software Issuer of
   * the Calypso application.
   *
   * @return The Software Version byte.
   * @since 1.0
   */
  byte getSoftwareVersion();

  /**
   * Gets the Software Revision field that may be set to any fixed value by the Software Issuer of
   * the Calypso application.
   *
   * @return The Software Revision byte.
   * @since 1.0
   */
  byte getSoftwareRevision();

  /**
   * Gets the session modification byte from the startup info structure.
   *
   * <p>Depending on the type of card, the session modification byte indicates the maximum number of
   * bytes that can be modified or the number of possible write commands in a session.
   *
   * @return The Session Modification byte.
   * @since 1.0
   */
  byte getSessionModification();

  /**
   * Gets the DF metadata.
   *
   * @return Null if is not set.
   * @since 1.0
   */
  DirectoryHeader getDirectoryHeader();

  /**
   * Gets a reference to the {@link ElementaryFile} that has the provided SFI value.
   *
   * <p>Note that if a secure session is actually running, then the object contains all session
   * modifications, which can be canceled if the secure session fails.
   *
   * @param sfi The SFI to search.
   * @return a not null reference.
   * @throws NoSuchElementException if requested EF is not found.
   * @since 1.0
   */
  ElementaryFile getFileBySfi(byte sfi);

  /**
   * Gets a reference to the {@link ElementaryFile} that has the provided LID value.
   *
   * <p>Note that if a secure session is actually running, then the object contains all session
   * modifications, which can be canceled if the secure session fails.
   *
   * @param lid The LID to search.
   * @return a not null reference.
   * @throws NoSuchElementException if requested EF is not found.
   * @since 1.0
   */
  ElementaryFile getFileByLid(short lid);

  /**
   * Gets a reference to a map of all known Elementary Files by their associated SFI.
   *
   * <p>Note that if a secure session is actually running, then the map contains all session
   * modifications, which can be canceled if the secure session fails.
   *
   * @return A not null reference (may be empty if no one EF is set).
   * @since 1.0
   */
  Map<Byte, ElementaryFile> getAllFiles();

  /**
   * Tells if the card has been invalidated or not.
   *
   * <p>An invalidated card has 6283h as status word in response to the Select Application command.
   *
   * @return True if the card has been invalidated.
   * @since 1.0
   */
  boolean isDfInvalidated();

  /**
   * Tells if the last session with this card has been ratified or not.
   *
   * @return True if the card has been ratified.
   * @throws IllegalStateException If these methods is invoked when no session has been opened.
   * @since 1.0
   */
  boolean isDfRatified();

  /**
   * Indicates whether the Public Key Authentication is supported or not (since rev 3.3).
   *
   * <p>This boolean is interpreted from the Application Type byte
   *
   * @return True if the Public Key Authentication is supported.
   * @since 1.0
   */
  boolean isPkiModeSupported();

  /**
   * Indicates whether the Extended Mode is supported or not (since rev 3.2).
   *
   * <p>This boolean is interpreted from the Application Type byte
   *
   * @return True if the Extended Mode is supported.
   * @since 1.0
   */
  boolean isExtendedModeSupported();

  /**
   * Indicates if the ratification is done on deselect (ratification command not necessary).
   *
   * <p>This boolean is interpreted from the Application Type byte
   *
   * @return True if the ratification on deselect is supported.
   * @since 1.0
   */
  boolean isRatificationOnDeselectSupported();

  /**
   * Indicates whether the card has the Calypso PIN feature.
   *
   * <p>This boolean is interpreted from the Application Type byte
   *
   * @return True if the card has the PIN feature.
   * @since 1.0
   */
  boolean isPinFeatureAvailable();

  /**
   * Indicates if the PIN is blocked. The maximum number of incorrect PIN submissions has been
   * reached.
   *
   * @return True if the PIN status is blocked
   * @throws IllegalStateException If the PIN has not been checked.
   * @since 1.0
   */
  boolean isPinBlocked();

  /**
   * Gives the number of erroneous PIN presentations remaining before blocking.
   *
   * @return The number of remaining attempts.
   * @throws IllegalStateException If the PIN has not been checked.
   * @since 1.0
   */
  int getPinAttemptRemaining();

  /**
   * Indicates whether the card has the Calypso Stored Value feature.
   *
   * <p>This boolean is interpreted from the Application Type byte
   *
   * @return True if the card has the Stored Value feature
   * @since 1.0
   */
  boolean isSvFeatureAvailable();

  /**
   * Gets the current SV balance value
   *
   * @return An int
   * @throws IllegalStateException If no SV Get command has been executed.
   * @since 1.0
   */
  int getSvBalance();

  /**
   * Gets the last SV transaction number
   *
   * @return An int
   * @throws IllegalStateException If no SV Get command has been executed.
   * @since 1.0
   */
  int getSvLastTNum();

  /**
   * Gets a reference to the last {@link SvLoadLogRecord}
   *
   * @return A last SV load log record object or null if not available.
   * @since 1.0
   */
  SvLoadLogRecord getSvLoadLogRecord();

  /**
   * Gets a reference to the last {@link SvDebitLogRecord}
   *
   * @return A last SV debit log record object or null if not available.
   * @since 1.0
   */
  SvDebitLogRecord getSvDebitLogLastRecord();

  /**
   * Gets list of references to the {@link SvDebitLogRecord} read from the card.
   *
   * @return A empty list if no log records are available.
   * @since 1.0
   */
  List<SvDebitLogRecord> getSvDebitLogAllRecords();

  /**
   * All Calypso Card products supported by this API.
   *
   * @since 1.0
   */
  public enum ProductType {
    PRIME_REV1_0,
    PRIME_REV2_4,
    PRIME_REV3,
    HCE,
    LIGHT,
    BASIC
  }
}
