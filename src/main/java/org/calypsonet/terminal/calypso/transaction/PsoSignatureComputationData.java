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

/**
 * Contains the input/output data of the {@link
 * CommonTransactionManager#prepareComputeSignature(SignatureComputationData)} method for advanced
 * signature computation using the "PSO" command.
 *
 * @since 1.2.0
 */
public interface PsoSignatureComputationData extends SignatureComputationData {

  /**
   * Enables the "SAM traceability" mode to securely record in the data to sign the SAM serial
   * number and the value of the counter associated with the signing key.
   *
   * <p>The SAM replaces the bits after the indicated offset by its serial number (3 or 4 bytes)
   * followed by the new value (3 bytes) of the counter.
   *
   * <p>To reduce the size of the data modified, the SAM may use only the 3 LSBytes of its serial
   * number. With the full serial number, 56 bits of data are replaced. With the 3 LSBytes of the
   * serial number, 48 bits of data are replaced.
   *
   * <p>By default, the "SAM traceability" mode is disabled.
   *
   * @param offset The offset in bits.
   * @param usePartialSamSerialNumber True if only the 3 LSBytes of the SAM serial number should be
   *     used.
   * @return The current instance.
   * @since 1.2.0
   */
  PsoSignatureComputationData withSamTraceabilityMode(
      int offset, boolean usePartialSamSerialNumber);

  /**
   * Disables the "Busy" mode. When enabled, if the "PSO Verify Signature" command used to check the
   * signature fails because of an incorrect signature, other "PSO Verify Signature" command with
   * "Busy" mode is rejected for a few seconds by responding with the "busy" status word. For
   * security reasons, it is recommended to use the "Busy" mode in all new usages of this command.
   *
   * <p>By default, the "Busy" mode is enabled.
   *
   * @return The current instance.
   * @since 1.2.0
   */
  PsoSignatureComputationData withoutBusyMode();

  /**
   * Returns the data that was used to generate the signature. If the "SAM traceability" mode was
   * enabled, then the signed data are the original data modified with the SAM traceability
   * information.
   *
   * @return A byte array of the same size as the original data to be signed.
   * @throws IllegalStateException If the command has not yet been processed.
   * @since 1.2.0
   */
  byte[] getSignedData();
}
