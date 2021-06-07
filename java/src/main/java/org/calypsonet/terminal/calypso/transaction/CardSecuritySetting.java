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

import java.util.List;
import org.calypsonet.terminal.calypso.WriteAccessLevel;

/**
 * This POJO contains all the needed data to manage the security operations of a Calypso
 * transaction.
 *
 * <p>Fluent setters allow to define all the required parameters, among which the resource profile
 * of the SAM card is the only mandatory one.
 *
 * @since 1.0
 */
public interface CardSecuritySetting {

  /**
   * Enable multiple session mode to allow more changes to the card than the session buffer can
   * handle.
   *
   * @return The object instance.
   * @since 1.0
   */
  CardSecuritySetting enableMultipleSession();

  /**
   * Enable the ratification mechanism to handle the early removal of the card preventing the
   * terminal from receiving the acknowledgement of the session closing.
   *
   * <p>This feature is particularly useful for validators.
   *
   * @return The object instance.
   * @since 1.0
   */
  CardSecuritySetting enableRatificationMechanism();

  /**
   * Disable the PIN transmission encryption.
   *
   * @return The object instance.
   * @since 1.0
   */
  CardSecuritySetting disablePinEncryption();

  /**
   * Set the default KIF for the provide session level.
   *
   * @param sessionAccessLevel the session level.
   * @param kif the desired default KIF.
   * @return The object instance.
   * @throws IllegalArgumentException If sessionAccessLevel is null.
   * @since 1.0
   */
  CardSecuritySetting assignKif(WriteAccessLevel sessionAccessLevel, byte kif);

  /**
   * Set the default KVC for the provide session level. P
   *
   * @param sessionAccessLevel the session level.
   * @param kvc the desired default KVC.
   * @return The object instance.
   * @throws IllegalArgumentException If sessionAccessLevel is null.
   * @since 1.0
   */
  CardSecuritySetting assignKvc(WriteAccessLevel sessionAccessLevel, byte kvc);

  /**
   * Set the default key record number
   *
   * @param sessionAccessLevel the session level.
   * @param keyRecordNumber the desired default key record number.
   * @return The object instance.
   * @throws IllegalArgumentException If sessionAccessLevel is null.
   * @since 1.0
   */
  CardSecuritySetting assignKeyRecordNumber(
      WriteAccessLevel sessionAccessLevel, byte keyRecordNumber);

  /**
   * Provides a list of authorized KVC
   *
   * @param sessionAuthorizedKvcList The list of authorized KVCs.
   * @return The object instance.
   * @throws IllegalArgumentException If sessionAuthorizedKvcList is null or empty.
   * @since 1.0
   */
  CardSecuritySetting sessionAuthorizedKvcList(List<Byte> sessionAuthorizedKvcList);

  /**
   * Provides the KIF/KVC pair of the PIN ciphering key
   *
   * <p>The default value for both KIF and KVC is 0.
   *
   * @param kif the KIF of the PIN ciphering key.
   * @param kvc the KVC of the PIN ciphering key.
   * @return The object instance.
   * @since 1.0
   */
  CardSecuritySetting pinCipheringKey(byte kif, byte kvc);

  /**
   * Sets the SV Get log read mode.
   *
   * <p>The default value is false.
   *
   * @param isLoadAndDebitSvLogRequired true if both Load and Debit logs are required.
   * @return The object instance.
   * @since 1.0
   */
  CardSecuritySetting isLoadAndDebitSvLogRequired(boolean isLoadAndDebitSvLogRequired);

  /**
   * Sets the SV negative balance.
   *
   * <p>The default value is false.
   *
   * @param isSvNegativeBalanceAllowed true if negative balance is allowed, false if not.
   * @return The object instance.
   * @since 1.0
   */
  CardSecuritySetting isSvNegativeBalanceAllowed(boolean isSvNegativeBalanceAllowed);
}
