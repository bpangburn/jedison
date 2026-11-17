# IDE and Maven setup

The POM is the build source of truth. Import with **Eclipse → Existing Maven
Projects** (m2e), open as a Maven project in NetBeans, or use IntelliJ IDEA.

- The artifact remains Java 8-compatible: `version.java=8` is the Maven
  compiler `release` target. Build with `mvn -B -ntp clean verify`.
- `.settings/org.eclipse.jdt.core.prefs` is deliberately retained for its
  customized Java 8 compiler/diagnostic settings.
- Generic generated Eclipse project/classpath/encoding/m2e metadata is not
  tracked; your IDE regenerates it from `pom.xml`.
- `.editorconfig` specifies UTF-8, LF and two-space soft indentation; install
  an EditorConfig extension in an IDE without native support.
- `SegTest` is a GUI-capable sample, not an automated regression suite.
  Review and test the sample image/segmentation paths before releasing.
- JUnit Jupiter tests are under `src/test/java`; run `mvn -B -ntp clean verify`
  (see [BUILDING.md](BUILDING.md)) before requesting a merge.
