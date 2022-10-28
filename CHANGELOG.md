# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Changed
- Added precision in the documentation of the methods of the `CardTransactionManager` API.
- UML diagrams moved to a dedicated repository (see `README.md` file).

## [1.4.0] - 2022-10-26
### Added
- `SelectFileException` to manage the status of the "Select File" card command.
### Upgraded
- "Calypsonet Terminal Reader API" to version `1.1.0`

## [1.3.0] - 2022-10-04
### Added
- `CalypsoSam.ProductType.HSM_C1` identifier for HSM products.

## [1.2.0] - 2022-05-30
### Added
- `CalypsoCard.getTransactionCounter` method (issue [#42]).
- `SamRevocationServiceSpi` SPI (issue [#29]).
- `CommonSignatureComputationData`.
- `BasicSignatureComputationData`.
- `TraceableSignatureComputationData` API (issue [#28]).
- `CommonSignatureVerificationData`.
- `BasicSignatureVerificationData`.
- `TraceableSignatureVerificationData` API (issue [#29]).
- `CommonSecuritySetting` API.
- `CommonSecuritySetting.setControlSamResource` method as a replacement for the `setSamResource` method.
- `CommonSecuritySetting.getTransactionAuditData` method (issue [#44]).
- `CommonTransactionManager` API.
- `CommonTransactionManager.getSecuritySetting` method as a replacement for the `getCardSecuritySetting` method.
- `CommonTransactionManager.prepareComputeSignature` method (issue [#28]).
- `CommonTransactionManager.prepareVerifySignature` method (issue [#29]).
- `CommonTransactionManager.processCommands` method as a replacement for the `processCardCommands` method.
- `SamSecuritySetting` API.
- `SamTransactionManager` API.
- `ReaderIOException` exception.
- `InvalidSignatureException` exception.
### Changed
- Renaming of `AtomicTransactionException` to `SessionBufferOverflowException`.
- Renaming of `AuthenticationNotVerifiedException` to `CardSignatureNotVerifiableException`.
- Merging of `CardAnomalyException`, `SamAnomalyException` and `CardCloseSecureSessionException` to `UnexpectedCommandStatusException`.
- Merging of `DesynchronizedExchangesException` and `InconsistencyDataException` to `InconsistentDataException`.
- Merging of `SessionAuthenticationException` and `SvAuthenticationException` to `InvalidCardSignatureException`.
### Deprecated
- `CardTransactionManager.getCardSecuritySetting` method.
- `CardTransactionManager.processCardCommands` method.
- `CardSecuritySetting.setSamResource` method.
### Removed
- `CardTransactionException` abstract common exception.

## [1.1.0] - 2022-02-01
### Added
- `prepareUpdateBinary` and `prepareWriteBinary` methods to `CardTransactionManager` API (issue [#19]).
- `prepareReadBinary` method to `CardTransactionManager` API (issue [#20]).
- `prepareReadRecordsPartially` method to `CardTransactionManager` APIs (issue [#21] and [#38]).
- `prepareSearchRecords` method to `CardTransactionManager` APIs (issue [#22]).
- `prepareIncreaseCounters` and `prepareDecreaseCounters` methods to `CardTransactionManager` API (issue [#23]).
- `processChangeKey` method to `CardTransactionManager` API (issue [#24]).
- `prepareReadRecord` and `prepareReadRecords` methods as a replacement for the `prepareReadRecordFile` methods.
- `prepareReadCounter` method as a replacement for the `prepareReadCounterFile` method.
- `EF_LIST` and `TRACEABILITY_INFORMATION` keys to `GetDataTag` API (issue [#18]).
- Management of EFs with no SFI (issue [#40]).
### Changed
- Documentation of card selection methods for older cards (issue [#17]).
- Documentation of `FileHeader` methods (issue [#18]).
- Documentation of `prepareSelectFile` methods.
### Deprecated
- `prepareSelectFile` methods using a byte array for the LID argument.
- `prepareReadRecordFile` methods.
- `prepareReadCounterFile` method.

## [1.0.5] - 2021-12-17
### Changed
- Documentation of `processClosing` method (issue [#16]).

## [1.0.4] - 2021-12-17
### Changed
- Documentation of `processOpening` and `processClosing` methods (issue [#15]).

## [1.0.3] - 2021-12-15
### Changed
- Documentation of `prepareReadRecord` methods (issue [#13]).
- Signature of methods throwing `NoSuchElementException` (issue [#13]).

## [1.0.2] - 2021-11-22
### Deprecated
- `addSuccessfulStatusWord` method (issue [#11]).

## [1.0.1] - 2021-11-22
### Added
- `CHANGELOG.md` file (issue [#9]).
- CI: Forbid the publication of a version already released (issue [#7]).
### Changed
- Description of the management of invalidated cards in `CalypsoCard` (issue [#6]).

## [1.0.0] - 2021-10-06
This is the initial release.

[unreleased]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.4.0...HEAD
[1.4.0]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.3.0...1.4.0
[1.3.0]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.2.0...1.3.0
[1.2.0]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.1.0...1.2.0
[1.1.0]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.5...1.1.0
[1.0.5]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.4...1.0.5
[1.0.4]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.3...1.0.4
[1.0.3]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.2...1.0.3
[1.0.2]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.1...1.0.2
[1.0.1]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.0...1.0.1
[1.0.0]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/releases/tag/1.0.0

[#44]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/44
[#42]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/42
[#40]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/40
[#38]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/38
[#29]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/29
[#28]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/28
[#27]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/27
[#26]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/26
[#25]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/25
[#24]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/24
[#23]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/23
[#22]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/22
[#21]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/21
[#20]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/20
[#19]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/19
[#18]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/18
[#17]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/17
[#16]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/16
[#15]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/15
[#13]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/13
[#11]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/11
[#9]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/9
[#7]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/7
[#6]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/6