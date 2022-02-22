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

import org.calypsonet.terminal.calypso.spi.SamRevocationServiceSpi;

/**
 * Contains the input/output data of the {@link
 * CommonTransactionManager#prepareVerifySignature(SignatureVerificationData)} method.
 *
 * @since 1.2.0
 */
public interface SignatureVerificationData {

  /**
   * Sets the signed data, the associated signature and the KIF/KVC of the key to be used for the
   * signature verification.
   *
   * @param data The signed data.
   * @param signature The associated signature.
   * @param kif The KIF of the key to be used for the signature verification.
   * @param kvc The KVC of the key to be used for the signature verification.
   * @return The current instance.
   * @since 1.2.0
   */
  SignatureVerificationData setData(byte[] data, byte[] signature, byte kif, byte kvc);

  /**
   * Indicates that the signature has been computed in "SAM traceability" mode and therefore whether
   * the revocation status of the signing SAM should be checked or not.
   *
   * <p>By default, the signature is not supposed to have been computed in "SAM traceability" mode.
   *
   * @param offset The offset in bits of the SAM traceability data.
   * @param isPartialSamSerialNumber True if only the 3 LSBytes of the SAM serial number have been
   *     used.
   * @param checkSamRevocationStatus True if it is requested to check if the SAM is revoked or not.
   *     If true, then the {@link org.calypsonet.terminal.calypso.spi.SamRevocationServiceSpi}
   *     service must be registered in the security settings using the {@link
   *     CommonSecuritySetting#setSamRevocationService(SamRevocationServiceSpi)} method.
   * @return The current instance.
   * @see SignatureComputationData#enableSamTraceabilityMode(int, boolean)
   * @see SamRevocationServiceSpi
   * @see CommonSecuritySetting#setSamRevocationService(SamRevocationServiceSpi)
   * @since 1.2.0
   */
  SignatureVerificationData withSignatureComputedInSamTraceabilityMode(
      int offset, boolean isPartialSamSerialNumber, boolean checkSamRevocationStatus);

  /**
   * Indicates that the signature has been computed in non "Busy" mode.
   *
   * <p>By default, the signature is supposed to have been computed in "Busy" mode.
   *
   * <p>The signature may have been generated with "Busy mode" enabled. In this mode, after a "PSO
   * Verify Signature" failing because of an incorrect signature, during a few seconds the SAM
   * rejects any "PSO Verify Signature" commands with "Busy" mode by responding with the "busy"
   * status word.
   *
   * <p>When a "PSO Verify Signature" fails with the busy status, the terminal should repeat the
   * command until the SAM is not busy anymore.
   *
   * <p>The busy mode duration is typically of a few seconds, and it is never of greater than ten
   * seconds.
   *
   * <p>Note that after a reset of the SAM, "PSO Verify Signature" commands being in "Busy" mode
   * fail with the busy status until the end of the busy mode duration.
   *
   * @return The current instance.
   * @see SignatureComputationData#disableBusyMode()
   * @since 1.2.0
   */
  SignatureVerificationData withSignatureComputedInNonBusyMode();

  /**
   * Requires to perform a key diversification before verifying the signature using the provided
   * diversifier.
   *
   * <p>Note: if the SAM traceability data is present, it is possible that the key used to compute
   * the signature was diversified according to the serial number of the signing SAM.
   *
   * <p>By default, there will be no new diversification of the key.
   *
   * @param keyDiversifier The diversifier to be used (8 bytes long).
   * @return The current instance.
   * @since 1.2.0
   */
  SignatureVerificationData useKeyDiversifier(byte[] keyDiversifier);

  /**
   * Returns the result of the signature verification process by indicating if the signature is
   * valid or not.
   *
   * @return True if the signature is valid.
   * @throws IllegalStateException If the command has not yet been processed.
   * @since 1.2.0
   */
  boolean isSignatureValid();

  /**
   * Returns the full (4 bytes) or partial (3 LSBytes) SAM serial number written in the traceability
   * information if the check of the "SAM traceability" data has been enabled.
   *
   * @return A byte array of 3 or 4 bytes.
   * @throws IllegalStateException If the command has not yet been processed or if the check of the
   *     "SAM traceability" data is disabled.
   * @see #withSignatureComputedInSamTraceabilityMode(int, boolean, boolean)
   * @since 1.2.0
   */
  byte[] getSamTraceabilitySerialNumber();

  /**
   * Returns the SAM counter value (3 bytes) written in the traceability information if the check of
   * the "SAM traceability" data has been enabled.
   *
   * @return A byte array of 3 bytes.
   * @throws IllegalStateException If the command has not yet been processed or if the check of the
   *     "SAM traceability" data is disabled.
   * @see #withSignatureComputedInSamTraceabilityMode(int, boolean, boolean)
   * @since 1.2.0
   */
  int getSamTraceabilityKeyCounter();
}
