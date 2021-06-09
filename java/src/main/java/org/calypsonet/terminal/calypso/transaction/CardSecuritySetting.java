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
package org.calypsonet.terminal.calypso.transaction;

import org.calypsonet.terminal.calypso.WriteAccessLevel;
import org.calypsonet.terminal.calypso.sam.CalypsoSam;
import org.calypsonet.terminal.reader.CardReader;

/**
 * Data to manage the security operations of a Calypso transaction.
 *
 * <p>Fluent setters allow to define all the required parameters, among which the resource profile
 * of the SAM card is the only mandatory one.
 *
 * @since 1.0
 */
public interface CardSecuritySetting {

  /**
   * Enables multiple session mode to allow more changes to the card than the session buffer can
   * handle.
   *
   * @return The current instance.
   * @since 1.0
   */
  CardSecuritySetting enableMultipleSession();

  /**
   * Enables the ratification mechanism to handle the early removal of the card preventing the
   * terminal from receiving the acknowledgement of the session closing.
   *
   * @return The current instance.
   * @since 1.0
   */
  CardSecuritySetting enableRatificationMechanism();

  /**
   * Enables the PIN transmission in plain text.
   *
   * @return The current instance.
   * @since 1.0
   */
  CardSecuritySetting enablePinPlainTransmission();

  /**
   * Enables the collection of transaction data for a later security audit.
   *
   * @return The current instance.
   * @since 1.0
   */
  CardSecuritySetting enableTransactionAudit();

  /**
   * Enables the retrieval of both loading and debit log records.
   *
   * <p>The default value is false.
   *
   * @return The current instance.
   * @since 1.0
   */
  CardSecuritySetting enableSvLoadAndDebitLog();

  /**
   * Allows the SV balance to become negative.
   *
   * <p>The default value is false.
   *
   * @return The current instance.
   * @since 1.0
   */
  CardSecuritySetting authorizeSvNegativeBalance();

  /**
   * Defines for a given write access level the KIF value to use for cards that only provide KVC.
   *
   * @param writeAccessLevel The write access level.
   * @param kvc The card's KVC value.
   * @param kif The KIF value to use.
   * @return The current instance.
   * @throws IllegalArgumentException If the provided writeAccessLevel is null.
   * @since 1.0
   */
  CardSecuritySetting assignKif(WriteAccessLevel writeAccessLevel, byte kvc, byte kif);

  /**
   * Defines for a given write access level the default KIF value to use when it could not be
   * determined by any other means.
   *
   * @param writeAccessLevel The write access level.
   * @param kif The KIF value to use.
   * @return The current instance.
   * @throws IllegalArgumentException If the provided writeAccessLevel is null.
   * @since 1.0
   */
  CardSecuritySetting assignDefaultKif(WriteAccessLevel writeAccessLevel, byte kif);

  /**
   * Defines for a given write access level the KVC value to use for cards that do not provide KVC.
   *
   * @param writeAccessLevel The session level.
   * @param kvc The KVC to use.
   * @return The current instance.
   * @throws IllegalArgumentException If the provided writeAccessLevel is null.
   * @since 1.0
   */
  CardSecuritySetting assignDefaultKvc(WriteAccessLevel writeAccessLevel, byte kvc);

  /**
   * Adds an authorized session key defined by its KIF and KVC values.
   *
   * <p>By default, all keys are accepted. <br>
   * If at least one key is added using this method, then only authorized keys will be accepted.
   *
   * @param kif The KIF value.
   * @param kvc The KVC value.
   * @return The current instance.
   * @since 1.0
   */
  CardSecuritySetting addAuthorizedSessionKey(byte kif, byte kvc);

  /**
   * Adds an authorized Stored Value key defined by its KIF and KVC values.
   *
   * <p>By default, all keys are accepted. <br>
   * If at least one key is added using this method, then only authorized keys will be accepted.
   *
   * @param kif The KIF value.
   * @param kvc The KVC value.
   * @return The current instance.
   * @since 1.0
   */
  CardSecuritySetting addAuthorizedSvKey(byte kif, byte kvc);

  /**
   * Sets the KIF/KVC pair of the PIN ciphering key.
   *
   * <p>The default value for both KIF and KVC is 0.
   *
   * @param kif The KIF value.
   * @param kvc The KVC value.
   * @return The current instance.
   * @since 1.0
   */
  CardSecuritySetting setPinCipheringKey(byte kif, byte kvc);

  /**
   * Gets the associated SAM reader to use for secured operations.
   *
   * @return Null if no SAM reader is set.
   * @since 1.0
   */
  CardReader getSamReader();

  /**
   * Gets the SAM used for secured operations.
   *
   * @return Null if no SAM is set.
   * @since 1.0
   */
  CalypsoSam getCalypsoSam();

  /**
   * Indicates if the multiple session mode is enabled.
   *
   * @return True if the multiple session mode is enabled.
   * @since 1.0
   */
  boolean isMultipleSessionEnabled();

  /**
   * Indicates if the ratification mechanism is enabled.
   *
   * @return True if the ratification mechanism is enabled.
   * @since 1.0
   */
  boolean isRatificationMechanismEnabled();

  /**
   * Indicates if the transmission of the PIN in plain text is enabled.
   *
   * @return True if the transmission of the PIN in plain text is enabled.
   * @since 1.0
   */
  boolean isPinPlainTransmissionEnabled();

  /**
   * Indicates if the transaction audit is enabled.
   *
   * @return True if the transaction audit is enabled.
   * @since 1.0
   */
  boolean isTransactionAuditEnabled();

  /**
   * Indicates if the retrieval of both load and debit log is enabled.
   *
   * @return True if the retrieval of both load and debit log is enabled.
   * @since 1.0
   */
  boolean isSvLoadAndDebitLogEnabled();

  /**
   * Indicates if the SV balance is allowed to become negative.
   *
   * @return True if the retrieval of both load and debit log is enabled.
   * @since 1.0
   */
  boolean isSvNegativeBalanceAuthorized();

  /**
   * Gets the KIF value to use for the provided write access level and KVC value.
   *
   * @param writeAccessLevel The write access level.
   * @param kvc The KVC value.
   * @return Null if no KIF is available.
   * @throws IllegalArgumentException If the provided writeAccessLevel is null.
   * @since 1.0
   */
  Byte getKif(WriteAccessLevel writeAccessLevel, byte kvc);

  /**
   * Gets the default KIF value for the provided write access level.
   *
   * @param writeAccessLevel The write access level.
   * @return Null if no KIF is available.
   * @throws IllegalArgumentException If the provided argument is null.
   * @since 1.0
   */
  Byte getDefaultKif(WriteAccessLevel writeAccessLevel);

  /**
   * Gets the default KVC value for the provided write access level.
   *
   * @param writeAccessLevel The write access level.
   * @return Null if no KVC is available.
   * @throws IllegalArgumentException If the provided argument is null.
   * @since 1.0
   */
  Byte getDefaultKvc(WriteAccessLevel writeAccessLevel);

  /**
   * Indicates if the KIF/KVC pair is authorized for a session.
   *
   * @param kif The KIF value.
   * @param kvc The KVC value.
   * @return True if the KIF/KVC pair is authorized.
   * @since 1.0
   */
  boolean isSessionKeyAuthorized(byte kif, byte kvc);

  /**
   * Indicates if the KIF/KVC pair is authorized for a SV operation.
   *
   * @param kif The KIF value.
   * @param kvc The KVC value.
   * @return True if the KIF/KVC pair is authorized.
   * @since 1.0
   */
  boolean isSvKeyAuthorized(byte kif, byte kvc);

  /**
   * Gets the KIF value of the PIN ciphering key.
   *
   * @return Null if no KIF is available.
   * @since 1.0
   */
  byte getPinCipheringKif();

  /**
   * Gets the KVC value of the PIN ciphering key.
   *
   * @return Null if no KVC is available.
   * @since 1.0
   */
  byte getPinCipheringKvc();
}
