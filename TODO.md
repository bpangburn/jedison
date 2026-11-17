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
- [x] Replace retired OSSRH/Nexus publishing with the official Central
      Publisher Portal Maven plugin (manual publishing; upload disabled by default).
- [ ] Confirm Central Portal namespace access, create a user token, configure
      local GPG signing, and validate signed source/Javadoc artifacts.
- [x] Set publishing to require manual Portal approval; do not auto-publish.
- [ ] Choose the next immutable release version and generate a local test bundle;
      validate a real Portal upload before manually publishing. No credentials in GitHub.
- [ ] Confirm license/source attribution continues to match the historical
      partial EDISON Java port.

- [x] Fix `SegTest` command-line display flag and eighth high-speed parameter (PR #9).
- [ ] Add subprocess-based CLI regression tests for these arguments.

## EBLA downstream integration
- [ ] After a verified jEDISON build, test the published
      `com.greatmindsworking.jedison:jedison:1.1.0` artifact as an EBLA
      dependency and confirm compatibility with EBLA's Java 8 baseline.
