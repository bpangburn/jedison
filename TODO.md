# Maintenance TODO — jEDISON

## Build and runtime
- [ ] Add a subprocess-based CLI integration test for both display modes, and
      high-speed mode with an eighth speed-factor parameter.
- [x] Validate the Java 8, 11, 21 and 25 Maven CI matrix and the new JUnit tests.
- [ ] Confirm CodeQL results are visible under GitHub's default CodeQL setup.
      Do not enable an advanced workflow while default setup is active.
- [ ] Add tests for deterministic segmentation output on a bundled test image,
      especially MeanShift and the speed-up mode selection.
- [ ] Verify headless and GUI invocation behavior of `SegTest` separately.
- [ ] Check for problems revealed by Dependabot before merging major-version
      upgrades; preserve the Java 8 release target unless intentionally changed.

## Release/publishing migration
- [ ] Replace the POM's legacy `oss.sonatype.org` distribution management and
      `nexus-staging-maven-plugin` release configuration with a supported
      **Sonatype Central Portal** process. OSSRH shut down in June 2025.
- [ ] Confirm namespace ownership, token-based credentials, signing, sources,
      Javadocs, validation, and manual/automatic publish decision.
- [ ] Validate publication against an appropriate staging/validation process
      before attempting a real release. Do not place credentials in GitHub.
- [ ] Confirm license/source attribution continues to match the historical
      partial EDISON Java port.

- [x] Fix `SegTest` command-line display flag and eighth high-speed parameter (PR #9).
- [ ] Add subprocess-based CLI regression tests for these arguments.

## EBLA downstream integration
- [ ] After a verified jEDISON build, test the published
      `com.greatmindsworking.jedison:jedison:1.1.0` artifact as an EBLA
      dependency and confirm compatibility with EBLA's Java 8 baseline.
