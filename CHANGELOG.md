# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- `prepareUpdateBinary` and `prepareWriteBinary` methods to `CardTransactionManager` API (issue [#19]).
### Changed
- Documentation of card selection methods for older cards (issue [#17]).

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

[unreleased]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.5...HEAD
[1.0.5]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.4...1.0.5
[1.0.4]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.3...1.0.4
[1.0.3]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.2...1.0.3
[1.0.2]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.1...1.0.2
[1.0.1]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/compare/1.0.0...1.0.1
[1.0.0]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/releases/tag/1.0.0

[#19]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/19
[#17]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/17
[#16]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/16
[#15]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/15
[#13]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/13
[#11]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/11
[#9]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/9
[#7]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/7
[#6]: https://github.com/calypsonet/calypsonet-terminal-calypso-java-api/issues/6