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
 * CommonTransactionManager#prepareComputeSignature(SignatureComputationData)} method for common
 * signature computation modes.
 *
 * @param <T> The type of the lowest level child object.
 * @since 1.2.0
 */
public interface SignatureComputationData<T extends SignatureComputationData<T>> {

  /**
   * Sets the data to be signed and the KIF/KVC of the key to be used for the signature computation.
   *
   * @param data The data to be signed.
   * @param kif The KIF of the key to be used for the signature computation.
   * @param kvc The KVC of the key to be used for the signature computation.
   * @return The current instance.
   * @since 1.2.0
   */
  T setData(byte[] data, byte kif, byte kvc);

  /**
   * Sets the expected size of the signature in bytes, which can be between 1 and 8 bytes
   * (optional).
   *
   * <p>By default, the signature will be generated on 8 bytes.
   *
   * <p>Note: the longer the signature, the more secure it is.
   *
   * @param size The expected size [1..8]
   * @return The current instance.
   * @since 1.2.0
   */
  T setSignatureSize(int size);

  /**
   * Sets a specific key diversifier to use before signing (optional).
   *
   * <p>By default, the key diversification is performed with the full serial number of the target
   * card or SAM depending on the transaction context (Card or SAM transaction).
   *
   * @param diversifier The diversifier to be used (from 1 to 8 bytes long).
   * @return The current instance.
   * @since 1.2.0
   */
  T setKeyDiversifier(byte[] diversifier);

  /**
   * Returns the computed signature.
   *
   * @return A byte array of 1 to 8 bytes.
   * @throws IllegalStateException If the command has not yet been processed.
   * @since 1.2.0
   */
  byte[] getSignature();
}
