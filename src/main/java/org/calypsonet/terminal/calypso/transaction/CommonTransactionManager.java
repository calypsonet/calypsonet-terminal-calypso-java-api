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

import java.util.List;

/**
 * Common service providing the high-level API to manage transactions with a Calypso card or SAM.
 *
 * @param <T> The type of the lowest level child object.
 * @param <S> The type of the lowest level child object of the associated {@link
 *     CommonSecuritySetting}.
 * @since 1.2.0
 */
public interface CommonTransactionManager<
    T extends CommonTransactionManager<T, S>, S extends CommonSecuritySetting<S>> {

  /**
   * Returns the settings defining the security parameters of the transaction.
   *
   * @return Null if the transaction does not use security settings.
   * @since 1.2.0
   */
  S getSecuritySetting();

  /**
   * Returns the audit data of the transaction containing all APDU exchanges with the card and the
   * SAM.
   *
   * @return An empty list if there is no audit data.
   * @since 1.2.0
   */
  List<byte[]> getTransactionAuditData();

  /**
   * Schedules the execution of a "Data Cipher" or "PSO Compute Signature" SAM command.
   *
   * <p>Once the command is processed, the result will be available in the provided input/output
   * {@link BasicSignatureComputationData} or {@link PsoSignatureComputationData} objects.
   *
   * <p>The signature may be used for many purposes, for example:
   *
   * <ul>
   *   <li>To add a signature to data recorded in a contactless card or ticket.<br>
   *       <u>Remark</u>: to speed up processing, it is recommended to use a constant signing key
   *       (which is not diversified before ciphering). Instead, the serial number of the card or
   *       ticket should be inserted at the beginning of the data to sign.
   *   <li>To sign some data reported from a terminal to a central system.<br>
   *       <u>Remark</u>: in this case, the terminal SAM contains a signing work key diversified
   *       with its own serial number, guarantying that the data has indeed been signed by this SAM.
   *       The central system SAM uses the master signing key, diversified before signing with the
   *       diversifier set previously by "Select Diversifier" command.
   * </ul>
   *
   * @param data The input/output data containing the parameters of the command.
   * @return The current instance.
   * @throws IllegalArgumentException If the input data is inconsistent.
   * @see CommonSignatureComputationData
   * @see BasicSignatureComputationData
   * @see PsoSignatureComputationData
   * @since 1.2.0
   */
  T prepareComputeSignature(CommonSignatureComputationData<?> data);

  /**
   * Schedules the execution of a "Data Cipher" or "PSO Verify Signature" SAM command.
   *
   * <p>Once the command is processed, the result will be available in the provided input/output
   * {@link BasicSignatureVerificationData} or {@link PsoSignatureVerificationData} objects.
   *
   * @param data The input/output data containing the parameters of the command.
   * @return The current instance.
   * @throws IllegalArgumentException If the input data is inconsistent.
   * @throws SamRevokedException If the signature has been computed in "SAM traceability" mode and
   *     the SAM revocation status check has been requested and the SAM is revoked (for PSO mode
   *     only).
   * @see CommonSignatureVerificationData
   * @see BasicSignatureVerificationData
   * @see PsoSignatureVerificationData
   * @since 1.2.0
   */
  T prepareVerifySignature(CommonSignatureVerificationData<?> data);

  /**
   * Process all previously prepared commands.
   *
   * @return The current instance.
   * @throws ReaderIOException If a communication error with the card reader or SAM reader occurs.
   * @throws CardIOException If a communication error with the card occurs.
   * @throws SamIOException If a communication error with the SAM occurs.
   * @throws InvalidSignatureException If a signature associated to a prepared signature
   *     verification SAM command is invalid.
   * @throws UnexpectedCommandStatusException If a command returns an unexpected status.
   * @throws InconsistentDataException If inconsistent data have been detected.
   * @throws SessionBufferOverflowException If a secure session is open and multiple session mode is
   *     disabled and the session buffer capacity is not sufficient.
   * @throws CardSignatureNotVerifiableException If a secure session is open and multiple session
   *     mode is enabled and an intermediate session is correctly closed but the SAM is no longer
   *     available to verify the card signature.
   * @throws InvalidCardSignatureException If a secure session is open and multiple session mode is
   *     enabled and an intermediate session is correctly closed but the card signature is
   *     incorrect.
   * @since 1.2.0
   */
  T processCommands();
}
