# IDE and Maven setup

The POM is the build source of truth. Import with **Eclipse → Existing Maven
Projects** (m2e), open as a Maven project in NetBeans, or use IntelliJ IDEA.

- The artifact remains Java 8-compatible: `version.java=8` is the Maven
  compiler `release` target. Build with `mvn -B -ntp clean verify`.
- Eclipse `.project`, `.classpath`, and all `.settings/` directories are
  local generated metadata and should not be committed. Maven/m2e is the
  source of compiler configuration; verify the Java release matches the POM.
  Custom Eclipse diagnostic preferences are local to each developer.
- After pulling this cleanup, use **Maven → Update Project** if necessary.
  If Eclipse project registration is stale, remove it from the workspace
  without deleting project contents on disk, then re-import as a Maven project.
  Do not commit generated `bin/` or `target/` output.
- `.editorconfig` specifies UTF-8, LF and two-space soft indentation; install
  an EditorConfig extension in an IDE without native support.
- `SegTest` is a GUI-capable sample, not an automated regression suite.
  Review and test the sample image/segmentation paths before releasing.
- JUnit Jupiter tests are under `src/test/java`; run `mvn -B -ntp clean verify`
  (see [BUILDING.md](BUILDING.md)) before requesting a merge.
