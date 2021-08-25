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
 * @since 1.0.0
 */
public interface CardSecuritySetting {

  /**
   * Defines the SAM and the reader through which it is accessible to be used to handle the relevant
   * cryptographic operations.
   *
   * @param samReader The SAM reader.
   * @param calypsoSam The Calypso SAM.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the arguments is null or if the product type of
   *     {@link CalypsoSam} is equal to {@link CalypsoSam.ProductType#UNKNOWN}.
   * @since 1.0.0
   */
  CardSecuritySetting setSamResource(CardReader samReader, CalypsoSam calypsoSam);

  /**
   * Enables multiple session mode to allow more changes to the card than the session buffer can
   * handle.
   *
   * @return The current instance.
   * @since 1.0.0
   */
  CardSecuritySetting enableMultipleSession();

  /**
   * Enables the ratification mechanism to handle the early removal of the card preventing the
   * terminal from receiving the acknowledgement of the session closing.
   *
   * @return The current instance.
   * @since 1.0.0
   */
  CardSecuritySetting enableRatificationMechanism();

  /**
   * Enables the PIN transmission in plain text.
   *
   * @return The current instance.
   * @since 1.0.0
   */
  CardSecuritySetting enablePinPlainTransmission();

  /**
   * Enables the collection of transaction data for a later security audit.
   *
   * @return The current instance.
   * @since 1.0.0
   */
  CardSecuritySetting enableTransactionAudit();

  /**
   * Enables the retrieval of both loading and debit log records.
   *
   * <p>The default value is false.
   *
   * @return The current instance.
   * @since 1.0.0
   */
  CardSecuritySetting enableSvLoadAndDebitLog();

  /**
   * Allows the SV balance to become negative.
   *
   * <p>The default value is false.
   *
   * @return The current instance.
   * @since 1.0.0
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
   * @since 1.0.0
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
   * @since 1.0.0
   */
  CardSecuritySetting assignDefaultKif(WriteAccessLevel writeAccessLevel, byte kif);

  /**
   * Defines for a given write access level the KVC value to use for cards that do not provide KVC.
   *
   * @param writeAccessLevel The session level.
   * @param kvc The KVC to use.
   * @return The current instance.
   * @throws IllegalArgumentException If the provided writeAccessLevel is null.
   * @since 1.0.0
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
   * @since 1.0.0
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
   * @since 1.0.0
   */
  CardSecuritySetting addAuthorizedSvKey(byte kif, byte kvc);

  /**
   * Sets the KIF/KVC pair of the PIN verification ciphering key.
   *
   * <p>The default value for both KIF and KVC is 0.
   *
   * @param kif The KIF value.
   * @param kvc The KVC value.
   * @return The current instance.
   * @since 1.0.0
   */
  CardSecuritySetting setPinVerificationCipheringKey(byte kif, byte kvc);

  /**
   * Sets the KIF/KVC pair of the PIN modification ciphering key.
   *
   * <p>The default value for both KIF and KVC is 0.
   *
   * @param kif The KIF value.
   * @param kvc The KVC value.
   * @return The current instance.
   * @since 1.0.0
   */
  CardSecuritySetting setPinModificationCipheringKey(byte kif, byte kvc);
}
