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

import org.calypsonet.terminal.calypso.GetDataTag;
import org.calypsonet.terminal.calypso.SelectFileControl;
import org.calypsonet.terminal.calypso.WriteAccessLevel;
import org.calypsonet.terminal.calypso.card.CalypsoCard;
import org.calypsonet.terminal.calypso.card.ElementaryFile;
import org.calypsonet.terminal.reader.CardReader;

/**
 * Service providing the high-level API to manage transactions with a Calypso card.
 *
 * <p>Depending on the type of operations required, the presence of a SAM may be necessary.
 *
 * <p>The {@link CalypsoCard} object provided to the build is kept and updated at each step of using
 * the service. It is the main container of the data handled during the transaction and acts as a
 * card image.
 *
 * <p>There are two main steps in using the methods of this service:
 *
 * <ul>
 *   <li>A command preparation step during which the application invokes prefixed "prepare" methods
 *       that will add to an internal list of commands to be executed by the card. The incoming data
 *       to the card are placed in {@link CalypsoCard}.
 *   <li>A processing step corresponding to the prefixed "process" methods, which will carry out the
 *       communications with the card and if necessary the SAM. The outgoing data from the card are
 *       placed in {@link CalypsoCard}.
 * </ul>
 *
 * <p>Technical or data errors, security conditions, etc. are reported as exceptions.
 *
 * @since 1.0.0
 */
public interface CardTransactionManager {

  /**
   * Gets the reader used to communicate with the card on which the transaction is performed.
   *
   * @return A not null reference.
   * @since 1.0.0
   */
  CardReader getCardReader();

  /**
   * Gets the card on which the transaction is performed.
   *
   * @return A not null {@link CalypsoCard} having a {@link CalypsoCard.ProductType} different from
   *     {@link CalypsoCard.ProductType#UNKNOWN}.
   * @since 1.0.0
   */
  CalypsoCard getCalypsoCard();

  /**
   * Gets the settings defining the security parameters of the transaction.
   *
   * @return Null if the transaction does not use security settings.
   * @since 1.0.0
   */
  CardSecuritySetting getCardSecuritySetting();

  /**
   * Gets the audit data of the transaction.
   *
   * @return Null if there is no audit data.
   * @since 1.0.0
   */
  String getTransactionAuditData();

  /**
   * Schedules the execution of a <b>Select File</b> command based on the file's LID.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoCard} through the
   * {@link CalypsoCard#getFileBySfi(byte)} and {@link ElementaryFile#getHeader()} methods.
   *
   * @param lid The LID of the EF to select.
   * @return The current instance.
   * @throws IllegalArgumentException If the provided lid is not 2 bytes long.
   * @since 1.0.0
   */
  CardTransactionManager prepareSelectFile(byte[] lid);

  /**
   * Schedules the execution of a <b>Select File</b> command using a navigation selectFileControl
   * defined by the ISO standard.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoCard} through the
   * {@link CalypsoCard#getFileBySfi(byte)} and {@link ElementaryFile#getHeader()} methods.
   *
   * @param selectFileControl A {@link SelectFileControl} enum entry.
   * @return The current instance.
   * @throws IllegalArgumentException If selectFileControl is null.
   * @since 1.0.0
   */
  CardTransactionManager prepareSelectFile(SelectFileControl selectFileControl);

  /**
   * Adds a command APDU to retrieve the data indicated by the provided tag.
   *
   * <p>The data returned by the command is used to update the current {@link CalypsoCard} object.
   *
   * @param tag The tag to use.
   * @return The object instance.
   * @throws IllegalArgumentException If tag is null.
   * @throws UnsupportedOperationException If the Get Data command with the provided tag is not
   *     supported.
   * @since 1.0.0
   */
  CardTransactionManager prepareGetData(GetDataTag tag);

  /**
   * Schedules the execution of a <b>Read Records</b> command to read a single record from the
   * indicated EF.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoCard}.
   *
   * <p>Depending on whether we are inside a secure session, there are two types of behavior
   * following this command:
   *
   * <ul>
   *   <li>Outside a secure session (best effort mode): the following "process" command will not
   *       fail whatever the existence of the targeted file or record (the {@link CalypsoCard}
   *       object may not be filled).
   *   <li>Inside a secure session in contactless mode (strict mode): the following "process"
   *       command will fail if the targeted file or record does not exist (the {@link CalypsoCard}
   *       object is always filled or an exception is raised when the reading failed).
   * </ul>
   *
   * <p><b>This method should not be used inside a secure session in contact mode</b> because
   * additional exchanges with the card will be operated and will corrupt the security of the
   * session. Instead, use the method {@link #prepareReadRecordFile(byte, int, int, int)} for this
   * case and provide valid parameters.
   *
   * @param sfi The SFI of the EF to read.
   * @param recordNumber The record number to read.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided arguments is out of range.
   * @throws IllegalStateException If this method is invoked inside a secure session in contact
   *     mode.
   * @since 1.0.0
   */
  CardTransactionManager prepareReadRecordFile(byte sfi, int recordNumber);

  /**
   * Schedules the execution of a <b>Read Records</b> command to read one or more records from the
   * indicated EF.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoCard}.
   *
   * <p>Depending on whether we are inside a secure session, there are two types of behavior
   * following this command:
   *
   * <ul>
   *   <li>Outside a secure session (best effort mode): the following "process" command will not
   *       fail whatever the existence of the targeted file or record (the {@link CalypsoCard}
   *       object may not be filled).
   *   <li>Inside a secure session (strict mode): the following "process" command will fail if the
   *       targeted file or record does not exist (the {@link CalypsoCard} object is always filled
   *       or an exception is raised when the reading failed).<br>
   *       Invalid parameters could lead to additional exchanges with the card and thus corrupt the
   *       security of the session.
   * </ul>
   *
   * @param sfi The SFI of the EF.
   * @param firstRecordNumber The record number to read (or first record to read in case of several
   *     records)
   * @param numberOfRecords The number of records expected.
   * @param recordSize The record length.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CardTransactionManager prepareReadRecordFile(
      byte sfi, int firstRecordNumber, int numberOfRecords, int recordSize);

  /**
   * Schedules the execution of a <b>Read Records</b> command to reads a record of the indicated EF,
   * which should be a counter file.
   *
   * <p>The record will be read up to the counter location indicated in parameter.<br>
   * Thus, all previous counters will also be read.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoCard}.
   *
   * <p>Depending on whether we are inside a secure session, there are two types of behavior
   * following this command:
   *
   * <ul>
   *   <li>Outside a secure session (best effort mode): the following "process" command will not
   *       fail whatever the existence of the targeted file or counter (the {@link CalypsoCard}
   *       object may not be filled).
   *   <li>Inside a secure session (strict mode): the following "process" command will fail if the
   *       targeted file or counter does not exist (the {@link CalypsoCard} object is always filled
   *       or an exception is raised when the reading failed).<br>
   *       Invalid parameters could lead to additional exchanges with the card and thus corrupt the
   *       security of the session.
   * </ul>
   *
   * @param sfi The SFI of the EF.
   * @param countersNumber The number of the last counter to be read.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CardTransactionManager prepareReadCounterFile(byte sfi, int countersNumber);

  /**
   * Schedules the execution of a <b>Verify Pin</b> command without PIN presentation in order to get
   * the attempt counter.
   *
   * <p>The PIN status will be made available in CalypsoCard after the execution of process command.
   * <br>
   * Adds it to the list of commands to be sent with the next process command.
   *
   * <p>See {@link CalypsoCard#isPinBlocked} and {@link CalypsoCard#getPinAttemptRemaining} methods.
   *
   * @return The current instance.
   * @throws UnsupportedOperationException If the PIN feature is not available for this card.
   * @since 1.0.0
   */
  CardTransactionManager prepareCheckPinStatus();

  /**
   * Schedules the execution of a <b>Append Record</b> command to adds the data provided in the
   * indicated cyclic file.
   *
   * <p>A new record is added, the oldest record is deleted.
   *
   * <p>Note: {@link CalypsoCard} is filled with the provided input data.
   *
   * @param sfi The sfi to select.
   * @param recordData The new record data to write.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CardTransactionManager prepareAppendRecord(byte sfi, byte[] recordData);

  /**
   * Schedules the execution of a <b>Update Record</b> command to overwrites the target file's
   * record contents with the provided data.
   *
   * <p>If the input data is shorter than the record size, only the first bytes will be overwritten.
   *
   * <p>Note: {@link CalypsoCard} is filled with the provided input data.
   *
   * @param sfi The sfi to select.
   * @param recordNumber The record number to update.
   * @param recordData The new record data. If length {@code <} RecSize, bytes beyond length are.
   *     left unchanged.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CardTransactionManager prepareUpdateRecord(byte sfi, int recordNumber, byte[] recordData);

  /**
   * Schedules the execution of a <b>Write Record</b> command to updates the target file's record
   * contents with the result of a binary OR between the existing data and the provided data.
   *
   * <p>If the input data is shorter than the record size, only the first bytes will be overwritten.
   *
   * <p>Note: {@link CalypsoCard} is filled with the provided input data.
   *
   * @param sfi The sfi to select.
   * @param recordNumber The record number to write.
   * @param recordData The data to overwrite in the record. If length {@code <} RecSize, bytes.
   *     beyond length are left unchanged.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CardTransactionManager prepareWriteRecord(byte sfi, int recordNumber, byte[] recordData);

  /**
   * Schedules the execution of a <b>Update Binary</b> command to replace the indicated data of a
   * Binary file with the new data given from the indicated offset.
   *
   * <p>The data of the file before the offset and after the data given are left unchanged.
   *
   * <p>Note: {@link CalypsoCard} is filled with the provided input data.
   *
   * @param sfi The SFI of the file to select or 0 for the current file.
   * @param offset The offset.
   * @param data The new data.
   * @return The current instance.
   * @throws UnsupportedOperationException If this command is not supported by this card.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.1.0
   */
  CardTransactionManager prepareUpdateBinary(byte sfi, int offset, byte[] data);

  /**
   * Schedules the execution of a <b>Write Binary</b> command to write over the indicated data of a
   * Binary file. The new data will be the result of a binary OR operation between the existing data
   * and the data given in the command from the indicated offset.
   *
   * <p>The data of the file before the offset and after the data given are left unchanged.
   *
   * <p>Note: {@link CalypsoCard} is computed with the provided input data.
   *
   * @param sfi The SFI of the file to select or 0 for the current file.
   * @param offset The offset.
   * @param data The data to write over the existing data.
   * @return The current instance.
   * @throws UnsupportedOperationException If this command is not supported by this card.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.1.0
   */
  CardTransactionManager prepareWriteBinary(byte sfi, int offset, byte[] data);

  /**
   * Schedules the execution of a <b>Increase command</b> command to increase the target counter.
   *
   * <p>Note: {@link CalypsoCard} is filled with the provided input data.
   *
   * @param counterNumber {@code >=} 01h: Counters file, number of the counter. 00h: Simulated.
   *     Counter file.
   * @param sfi SFI of the file to select or 00h for current EF.
   * @param incValue Value to add to the counter (defined as a positive int {@code <=} 16777215
   *     [FFFFFFh])
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CardTransactionManager prepareIncreaseCounter(byte sfi, int counterNumber, int incValue);

  /**
   * Schedules the execution of a <b>Decrease command</b> command to decrease the target counter.
   *
   * <p>Note: {@link CalypsoCard} is filled with the provided input data.
   *
   * @param counterNumber {@code >=} 01h: Counters file, number of the counter. 00h: Simulated.
   *     Counter file.
   * @param sfi SFI of the file to select or 00h for current EF.
   * @param decValue Value to subtract to the counter (defined as a positive int {@code <=} 16777215
   *     [FFFFFFh])
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CardTransactionManager prepareDecreaseCounter(byte sfi, int counterNumber, int decValue);

  /**
   * Schedules the execution of a command to set the value of the target counter.
   *
   * <p>It builds an Increase or Decrease command and add it to the list of commands to be sent with
   * the next <b>process</b> command in order to set the target counter to the specified value.<br>
   * The operation (Increase or Decrease) is selected according to whether the difference between
   * the current value and the desired value is negative (Increase) or positive (Decrease).
   *
   * <p>Note: it is assumed here that:<br>
   *
   * <ul>
   *   <li>the counter value has been read before,
   *   <li>the type of session (and associated access rights) is consistent with the requested
   *       operation: reload session if the counter is to be incremented, debit if it is to be
   *       decremented.<br>
   *       No control is performed on this point by this method; the closing of the session will
   *       determine the success of the operation..
   * </ul>
   *
   * @param counterNumber {@code >=} 01h: Counters file, number of the counter. 00h: Simulated.
   *     Counter file.
   * @param sfi SFI of the file to select or 00h for current EF.
   * @param newValue The desired value for the counter (defined as a positive int {@code <=}
   *     16777215 [FFFFFFh])
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @throws IllegalStateException If the current counter value is unknown.
   * @since 1.0.0
   */
  CardTransactionManager prepareSetCounter(byte sfi, int counterNumber, int newValue);

  /**
   * Schedules the execution of a <b>SV Get</b> command to prepare an SV operation or simply
   * retrieves the current SV status.
   *
   * <p>Once this command is processed, the result is available in {@link CalypsoCard}.
   *
   * <p>See the methods {@link CalypsoCard#getSvBalance()}, {@link
   * CalypsoCard#getSvLoadLogRecord()}, {@link CalypsoCard#getSvDebitLogLastRecord()}, {@link
   * CalypsoCard#getSvDebitLogAllRecords()}.
   *
   * @param svOperation Informs about the nature of the intended operation: debit or reload.
   * @param svAction The type of action: DO a debit or a positive reload, UNDO an undebit or a.
   *     negative reload
   * @return The current instance.
   * @throws IllegalArgumentException If one of the arguments is null.
   * @throws UnsupportedOperationException If the SV feature is not available for this card.
   * @since 1.0.0
   */
  CardTransactionManager prepareSvGet(SvOperation svOperation, SvAction svAction);

  /**
   * Schedules the execution of a <b>SV Reload</b> command to increase the current SV balance and
   * using the provided additional data.
   *
   * <p>Note #1: a communication with the SAM is done here.
   *
   * <p>Note #2: the key used is the reload key.
   *
   * @param amount The value to be reloaded, positive or negative integer in the range.
   *     -8388608..8388607
   * @param date 2-byte free value.
   * @param time 2-byte free value.
   * @param free 2-byte free value.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @throws UnsupportedOperationException If the SV feature is not available for this card.
   * @throws CardTransactionException If a functional error occurs (including card and SAM IO
   *     errors)
   * @since 1.0.0
   */
  CardTransactionManager prepareSvReload(int amount, byte[] date, byte[] time, byte[] free);

  /**
   * Schedules the execution of a <b>SV Reload</b> command to increase the current SV balance.
   *
   * <p>Note #1: the optional SV additional data are set to zero.
   *
   * <p>Note #2: a communication with the SAM is done here.
   *
   * <p>Note #3: the key used is the reload key.
   *
   * @param amount The value to be reloaded, positive integer in the range 0..8388607 for a DO.
   *     action, in the range 0..8388608 for an UNDO action.
   * @return The current instance.
   * @throws IllegalArgumentException If the provided argument is out of range.
   * @throws UnsupportedOperationException If the SV feature is not available for this card.
   * @throws CardTransactionException If a functional error occurs (including card and SAM IO
   *     errors)
   * @since 1.0.0
   */
  CardTransactionManager prepareSvReload(int amount);

  /**
   * Schedules the execution of a <b>SV Debit</b> or <b>SV Undebit</b> command to increase the
   * current SV balance or to partially or totally cancels the last SV debit command and using the
   * provided additional data.
   *
   * <p>It consists in decreasing the current balance of the SV by a certain amount or canceling a
   * previous debit according to the type operation chosen in when invoking the previous SV Get
   * command.
   *
   * <p>Note #1: a communication with the SAM is done here.
   *
   * <p>Note #2: the key used is the reload key.
   *
   * @param amount The amount to be subtracted or added, positive integer in the range 0..32767 when
   *     subtracted and 0..32768 when added.
   * @param date 2-byte free value.
   * @param time 2-byte free value.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CardTransactionManager prepareSvDebit(int amount, byte[] date, byte[] time);

  /**
   * Schedules the execution of a <b>SV Debit</b> or <b>SV Undebit</b> command to increase the
   * current SV balance or to partially or totally cancels the last SV debit command.
   *
   * <p>It consists in decreasing the current balance of the SV by a certain amount or canceling a
   * previous debit.
   *
   * <p>Note #1: the optional SV additional data are set to zero.
   *
   * <p>Note #2: a communication with the SAM is done here.
   *
   * <p>Note #3: the key used is the reload key.The information fields such as date and time are set
   * to 0. The extraInfo field propagated in Logs are automatically generated with the type of
   * transaction and amount.
   *
   * <p>Note #4: operations that would result in a negative balance are forbidden (SV Exception
   * raised).
   *
   * <p>Note #5: the key used is the debit key
   *
   * @param amount The amount to be subtracted or added, positive integer in the range 0..32767 when
   *     subtracted and 0..32768 when added.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the provided argument is out of range.
   * @since 1.0.0
   */
  CardTransactionManager prepareSvDebit(int amount);

  /**
   * Schedules the execution of <b>Read Records</b> commands to read all SV logs.
   *
   * <p>Note: this method requires that the selected application is of type Store Value (file
   * structure 20h).
   *
   * <p>The SV transaction logs are contained in two files with fixed identifiers:
   *
   * <ul>
   *   <li>The file whose SFI is 14h contains 1 record containing the unique reload log.
   *   <li>The file whose SFI is 15h contains 3 records containing the last three debit logs.
   * </ul>
   *
   * <p>At the end of this reading operation, the data will be accessible in {@link CalypsoCard} in
   * raw format via the standard commands for accessing read files or in the form of dedicated
   * objects (see {@link CalypsoCard#getSvLoadLogRecord()} and {@link
   * CalypsoCard#getSvDebitLogAllRecords()}).
   *
   * <p>See the methods {@link CalypsoCard#getSvBalance()}, {@link
   * CalypsoCard#getSvLoadLogRecord()}, {@link CalypsoCard#getSvDebitLogLastRecord()}, {@link
   * CalypsoCard#getSvDebitLogAllRecords()}.
   *
   * @return The current instance.
   * @throws UnsupportedOperationException If the application is not of type Stored Value.
   * @since 1.0.0
   */
  CardTransactionManager prepareSvReadAllLogs();

  /**
   * Schedules the execution of a <b>Invalidate</b> command.
   *
   * <p>This command is usually executed within a secure session with the DEBIT key (depends on the
   * access rights given to this command in the file structure of the card).
   *
   * @throws IllegalStateException If the card is already invalidated.
   * @return The current instance.
   * @since 1.0.0
   */
  CardTransactionManager prepareInvalidate();

  /**
   * Schedules the execution of a <b>Rehabilitate</b> command.
   *
   * <p>This command is usually executed within a secure session with the PERSONALIZATION key
   * (depends on the access rights given to this command in the file structure of the card).
   *
   * @return The current instance.
   * @throws IllegalStateException If the card is not invalidated.
   * @since 1.0.0
   */
  CardTransactionManager prepareRehabilitate();

  /**
   * Requests the closing of the card channel.
   *
   * <p>If this command is called before a "process" command (except for processOpening) then the
   * last transmission to the card will be associated with the indication CLOSE_AFTER in order to
   * close the card channel.
   *
   * <p>Note: this command must imperatively be called at the end of any transaction, whether it
   * ended normally or not.
   *
   * <p>In case the transaction was interrupted (exception), an additional invocation of
   * processCardCommands must be made to effectively close the channel.
   *
   * @return The current instance.
   * @since 1.0.0
   */
  CardTransactionManager prepareReleaseCardChannel();

  /**
   * Process all previously prepared card commands outside or inside a Secure Session.
   *
   * <ul>
   *   <li>All APDUs resulting from prepared commands are grouped and sent to the card.
   *   <li>The {@link CalypsoCard} object is updated with the result of the executed commands.
   *   <li>If a secure session is opened, except in the case where reloading or debit SV operations
   *       have been prepared, the invocation of this method does not generate communication with
   *       the SAM. The data necessary for the calculation of the terminal signature are kept to be
   *       sent to the SAM at the time of the invocation of {@link #processClosing()}.<br>
   *       The card channel is kept open.
   *   <li>If no secure session is opened, the card channel is closed depending on whether or not
   *       prepareReleaseCardChannel has been called.
   *   <li>The card session buffer overflows are managed in the same way as in {@link
   *       #processOpening(org.calypsonet.terminal.calypso.WriteAccessLevel)}. For example, when the
   *       multiple session mode is enabled, the commands are separated in as many sessions as
   *       necessary to respect the capacity of the card buffer.
   * </ul>
   *
   * @return The current instance.
   * @throws CardTransactionException If a functional error occurs (including card and SAM IO
   *     errors)
   * @since 1.0.0
   */
  CardTransactionManager processCardCommands();

  /**
   * Performs a PIN verification, in order to authenticate the cardholder and/or unlock access to
   * certain card files.
   *
   * <p>This command can be performed both in and out of a secure session. The PIN code can be
   * transmitted in plain text or encrypted according to the parameter set in CardSecuritySetting
   * (by default the transmission is encrypted).
   *
   * <p>If the execution is done out of session but an encrypted transmission is requested, then
   * CardTransactionManager must be constructed with {@link CardSecuritySetting}
   *
   * <p>If CardTransactionManager is constructed without {@link CardSecuritySetting} the
   * transmission in done in plain.
   *
   * <p>The card channel is closed if prepareReleaseCardChannel is called before this command.
   *
   * @param pin The PIN code value (4-byte long byte array).
   * @return The current instance.
   * @throws UnsupportedOperationException If the PIN feature is not available for this card
   * @throws IllegalArgumentException If the provided argument is out of range.
   * @throws IllegalStateException If commands have been prepared before invoking this process
   *     method.
   * @throws CardTransactionException If a functional error occurs (including card and SAM IO
   *     errors)
   * @since 1.0.0
   */
  CardTransactionManager processVerifyPin(byte[] pin);

  /**
   * Replaces the current PIN with the new value provided.
   *
   * <p>This command can be performed only out of a secure session. The new PIN code can be
   * transmitted in plain text or encrypted according to the parameter set in CardSecuritySetting
   * (by default the transmission is encrypted).
   *
   * <p>When the PIN is transmitted plain, this command must be preceded by a successful Verify PIN
   * command (see {@link #processVerifyPin(byte[])}).
   *
   * @param newPin The new PIN code value (4-byte long byte array).
   * @return The current instance.
   * @throws UnsupportedOperationException If the PIN feature is not available for this card
   * @throws IllegalArgumentException If the provided argument is out of range.
   * @throws IllegalStateException If the command is executed while a secure session is open.
   * @throws CardTransactionException If a functional error occurs (including card and SAM IO
   *     errors)
   * @since 1.0.0
   */
  CardTransactionManager processChangePin(byte[] newPin);

  /**
   * Opens a Calypso Secure Session and then executes all previously prepared commands.
   *
   * <p>It is the starting point of the sequence:
   *
   * <ul>
   *   <li>{@code processOpening(WriteAccessLevel)}
   *   <li>[{@link #processCardCommands()}]
   *   <li>[...]
   *   <li>[{@link #processCardCommands()}]
   *   <li>{@link #processClosing()}
   * </ul>
   *
   * <p>Each of the steps in this sequence may or may not be preceded by the preparation of one or
   * more commands and ends with an update of the {@link CalypsoCard} object provided when
   * CardTransactionManager was created.
   *
   * <p>As a prerequisite for invoking this method, since the Calypso Secure Session involves the
   * use of a SAM, the CardTransactionManager must have been built in secure mode, i.e. the
   * constructor used must be the one expecting a reference to a valid {@link CardSecuritySetting}
   * object, otherwise a {@link IllegalStateException} is raised.
   *
   * <p>The secure session is opened with the {@link WriteAccessLevel} passed as an argument
   * depending on whether it is a personalization, reload or debit transaction profile.
   *
   * <p>The possible overflow of the internal session buffer of the card is managed in two ways
   * depending on the setting chosen in {@link CardSecuritySetting}.
   *
   * <ul>
   *   <li>If the session was opened with the default atomic mode and the previously prepared
   *       commands will cause the buffer to be exceeded, then an {@link AtomicTransactionException}
   *       is raised and no transmission to the card is made. <br>
   *   <li>If the session was opened with the multiple session mode and the buffer is to be exceeded
   *       then a split into several secure sessions is performed automatically. However, regardless
   *       of the number of intermediate sessions performed, a secure session is opened at the end
   *       of the execution of this method.
   * </ul>
   *
   * <p>Be aware that in the "MULTIPLE" case we lose the benefit of the atomicity of the secure
   * session.
   *
   * <p><b>Card and SAM exchanges in detail</b>
   *
   * <p>When executing this method, communications with the card and the SAM are (in that order) :
   *
   * <ul>
   *   <li>Sending the card diversifier (Calypso card serial number) to the SAM and receiving the
   *       terminal challenge
   *   <li>Grouped sending to the card of
   *       <ul>
   *         <li>the open secure session command including the challenge terminal.
   *         <li>all previously prepared commands
   *       </ul>
   *   <li>Receiving grouped responses and updating {@link CalypsoCard} with the collected data.
   * </ul>
   *
   * For optimization purposes, if the first command prepared is the reading of a single record of a
   * card file then this one is replaced by a setting of the session opening command allowing the
   * retrieval of this data in response to this command.
   *
   * <p>Please note that the CAAD mechanism may require a file to be read before being modified. For
   * this mechanism to work properly, this reading must not be placed in the first position of the
   * prepared commands in order to be correctly taken into account by the SAM.
   *
   * <p><b>Other operations carried out</b>
   *
   * <ul>
   *   <li>The card KIF, KVC and card challenge received in response to the open secure session
   *       command are kept for a later initialization of the session's digest (see {@link
   *       #processClosing}).
   *   <li>All data received in response to the open secure session command and the responses to the
   *       prepared commands are also stored for later calculation of the digest.
   *   <li>If a list of authorized KVCs has been defined in {@link CardSecuritySetting} and the KVC
   *       of the card does not belong to this list then a {@link UnauthorizedKeyException} is
   *       thrown.
   * </ul>
   *
   * <p>All unexpected results (communication errors, data or security errors, etc. are notified to
   * the calling application through dedicated exceptions.
   *
   * <p><i>Note: to understand in detail how the secure session works please refer to the card
   * specification documents.</i>
   *
   * @param writeAccessLevel An {@link WriteAccessLevel} enum entry.
   * @return The current instance.
   * @throws IllegalArgumentException If the provided argument is null.
   * @throws IllegalStateException If no {@link CardSecuritySetting} is available
   * @throws CardTransactionException If a functional error occurs (including card and SAM IO
   *     errors)
   * @since 1.0.0
   */
  CardTransactionManager processOpening(WriteAccessLevel writeAccessLevel);

  /**
   * Terminates the Secure Session sequence started with {@link #processOpening(WriteAccessLevel)}.
   *
   * <p><b>Nominal case</b>
   *
   * <p>The previously prepared commands are integrated into the calculation of the session digest
   * by the SAM before execution by the card by anticipating their responses.
   *
   * <p>Thus, the session closing command containing the terminal signature is integrated into the
   * same APDU group sent to the card via a final card request.
   *
   * <p>Upon reception of the response from the card, the signature of the card is verified with the
   * SAM.
   *
   * <p>If the method terminates normally, it means that the secure session closing and all related
   * security checks have been successful; conversely, if one of these operations fails, an
   * exception is raised.
   *
   * <p><b>Stored Value</b>
   *
   * <p>If the SV counter was debited or reloaded during the session, an additional verification
   * specific to the SV is performed by the SAM.
   *
   * <p><b>Ratification</b>
   *
   * <p>A ratification command is added after the close secure session command when the
   * communication is done in a contactless mode.
   *
   * <p>The logical channel is closed or left open depending on whether the {@link
   * #prepareReleaseCardChannel()} method has been called before or not.
   *
   * <p><b>Card and SAM exchanges in detail</b>
   *
   * <ul>
   *   <li>All the data exchanged with the card so far, to which are added the last prepared orders
   *       and their anticipated answers, are sent to the SAM for the calculation of the session
   *       digest. The terminal signature calculation request is also integrated in the same group
   *       of SAM Apdu.
   *   <li>All previously prepared commands are sent to the card along with the session closing
   *       command and possibly the ratification command within a single card request.
   *   <li>The responses received from the card are integrated into CalypsoCard. <br>
   *       Note: the reception of the answers of this final card request from the card is tolerant
   *       to the non-reception of the answer to the ratification order.
   *   <li>The data received from the card in response to the logout (card session signature and
   *       possibly SV signature) are sent to the SAM for verification.
   * </ul>
   *
   * @return The current instance.
   * @throws IllegalStateException If no session is open.
   * @throws CardTransactionException If a functional error occurs (including card and SAM IO
   *     errors).
   * @since 1.0.0
   */
  CardTransactionManager processClosing();

  /**
   * Aborts a Secure Session.
   *
   * <p>Send the appropriate command to the card
   *
   * <p>Clean up internal data and status.
   *
   * @return The current instance.
   * @throws IllegalStateException If no session is open.
   * @throws CardTransactionException If a functional error occurs (including card and SAM IO
   *     errors)
   * @since 1.0.0
   */
  CardTransactionManager processCancel();
}
