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
import org.calypsonet.terminal.calypso.sam.CalypsoSam;
import org.calypsonet.terminal.reader.CardReader;

/**
 * Service providing the high-level API to manage transactions with a Calypso SAM.
 *
 * <p>The target {@link CalypsoSam} object provided to the build is kept and updated at each step of
 * using the service. It is the main container of the data handled during the transaction and acts
 * as a SAM image.
 *
 * <p>There are two main steps in using the methods of this service:
 *
 * <ul>
 *   <li>A command preparation step during which the application invokes prefixed "prepare" methods
 *       that will add to an internal list of commands to be executed by the SAM. The incoming data
 *       to the target SAM are placed in {@link CalypsoSam}.
 *   <li>A processing step corresponding to the prefixed "process" methods, which will carry out the
 *       communications with the target SAM. The outgoing data from the card are placed in {@link
 *       CalypsoSam}.
 * </ul>
 *
 * <p>Technical or data errors, security conditions, etc. are reported as exceptions.
 *
 * @since 1.2.0
 */
public interface SamTransactionManager
    extends CommonTransactionManager<SamTransactionManager, SamSecuritySetting> {

  /**
   * Gets the reader used to communicate with the target SAM on which the transaction is performed.
   *
   * @return A not null reference.
   * @since 1.2.0
   */
  CardReader getSamReader();

  /**
   * Gets the target SAM on which the transaction is performed.
   *
   * @return A not null {@link CalypsoSam} having a {@link CalypsoSam.ProductType} different from
   *     {@link CalypsoSam.ProductType#UNKNOWN}.
   * @since 1.2.0
   */
  CalypsoSam getCalypsoSam();

  /**
   * Schedules the execution of a <b>Read Event Counter</b> command to read a single event counter.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoSam}.
   *
   * @param eventCounterNumber The number of the event counter to read (in range [0..26]).
   * @return The current instance.
   * @throws IllegalArgumentException If the provided argument is out of range.
   * @since 1.4.0
   */
  SamTransactionManager prepareReadEventCounter(int eventCounterNumber);

  /**
   * Schedules the execution of a <b>Read Event Counter</b> command to read one or more consecutive
   * event counters.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoSam}.
   *
   * @param fromEventCounterNumber The number of the first event counter to read (in range [0..26]).
   * @param toEventCounterNumber The number of the last event counter to read (in range [0..26]).
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.4.0
   */
  SamTransactionManager prepareReadEventCounters(
      int fromEventCounterNumber, int toEventCounterNumber);

  /**
   * Schedules the execution of a <b>Read Ceilings</b> command to read a single event ceiling.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoSam}.
   *
   * @param eventCeilingNumber The number of the event ceiling to read (in range [0..26]).
   * @return The current instance.
   * @throws IllegalArgumentException If the provided argument is out of range.
   * @since 1.4.0
   */
  SamTransactionManager prepareReadEventCeiling(int eventCeilingNumber);

  /**
   * Schedules the execution of a <b>Read Ceilings</b> command to read one or more consecutive event
   * ceilings.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoSam}.
   *
   * @param fromEventCeilingNumber The number of the first event ceiling to read (in range [0..26]).
   * @param toEventCeilingNumber The number of the last event ceiling to read (in range [0..26]).
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.4.0
   */
  SamTransactionManager prepareReadEventCeilings(
      int fromEventCeilingNumber, int toEventCeilingNumber);

  /**
   * Schedules the execution of a <b>Write Ceilings</b> command to write a single event ceiling.
   *
   * <p>Once this command is processed, the ceiling value available in {@link CalypsoSam} is
   * updated.
   *
   * @param eventCeilingNumber The number of the event ceiling to write.
   * @param newValue The desired value for the event ceiling (defined as a positive int {@code <=}
   *     16777215 [FFFFFFh]).
   * @return The current instance.
   * @throws IllegalArgumentException If the provided argument is out of range.
   * @since 1.4.0
   */
  SamTransactionManager prepareWriteEventCeiling(int eventCeilingNumber, int newValue);

  /**
   * Schedules the execution of a <b>Write Ceilings</b> command to write multiple event ceilings.
   *
   * <p>Once this command is processed, the ceiling values available in {@link CalypsoSam} are
   * updated.
   *
   * @param fromEventCeilingNumber The number of the first event ceiling to write.
   * @param newValues A list of event ceilings values to be written from the indicated position
   *     (each event ceiling value is defined as a positive int {@code <=} 16777215 [FFFFFFh]).
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.4.0
   */
  SamTransactionManager prepareWriteEventCeilings(
      int fromEventCeilingNumber, List<Integer> newValues);
}
