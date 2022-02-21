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
 * @param <E> The type of the lowest level child object of the associated {@link
 *     CommonSecuritySetting}.
 * @since 1.2.0
 */
public interface CommonTransactionManager<
    T extends CommonTransactionManager<T, E>, E extends CommonSecuritySetting<E>> {

  /**
   * Returns the settings defining the security parameters of the transaction.
   *
   * @return Null if the transaction does not use security settings.
   * @since 1.2.0
   */
  E getSecuritySetting();

  /**
   * Returns the audit data of the transaction containing all APDU exchanges with the card and the
   * SAM.
   *
   * @return An empty list if there is no audit data.
   * @since 1.2.0
   */
  List<byte[]> getTransactionAuditData();

  /**
   * Schedules the execution of a "PSO Compute Signature" SAM command.
   *
   * <p>Once the command is processed, the result will be available in the provided input/output
   * {@link SignatureComputationData} object.
   *
   * @param data The input/output data containing the parameters of the command.
   * @return The current instance.
   * @throws IllegalArgumentException If the input data is inconsistent.
   * @since 1.2.0
   */
  T prepareComputeSignature(SignatureComputationData data);

  /**
   * Schedules the execution of a "PSO Verify Signature" SAM command.
   *
   * <p>Once the command is processed, the result will be available in the provided input/output
   * {@link SignatureVerificationData} object.
   *
   * @param data The input/output data containing the parameters of the command.
   * @return The current instance.
   * @throws IllegalArgumentException If the input data is inconsistent.
   * @since 1.2.0
   */
  T prepareVerifySignature(SignatureComputationData data);

  /**
   * Process all previously prepared commands.
   *
   * @return The current instance.
   * @throws CardTransactionException If a functional error occurs (including card and SAM IO
   *     errors)
   * @since 1.2.0
   */
  T processCommands();
}
