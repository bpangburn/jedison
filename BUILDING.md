# Building and testing jEDISON

The root `pom.xml` is the authoritative Maven build. This is a single-module
Java library targeting **Java 8 bytecode**; it has no runtime Maven dependencies.

## Prerequisites

- JDK 8 or newer (CI builds with JDK 8, 11, 21 and 25)
- Maven 3.6.3 or newer

## Build and test

```sh
git clone https://github.com/bpangburn/jedison.git
cd jedison
mvn -B -ntp clean verify
```

The executable sample is in `target/jedison-1.1.0.jar`. Run:

```sh
java -jar target/jedison-1.1.0.jar sample 0 true 6.5 7 20 0
```

This writes `segment.png` in the **current working directory**; the sample
image is packaged in the JAR. Use an environment with AWT image support.
The library works headlessly for image reading/processing, but the demo may
produce console output and perform filesystem writes.

JUnit Jupiter tests are under `src/test/java`. The CI matrix executes
`mvn clean verify`, including tests. These smoke/unit tests do **not** yet
establish pixel-for-pixel segmentation equivalence with the original C++
EDISON algorithm; see [TODO.md](TODO.md) for desired image regression coverage.

To import into Eclipse, NetBeans or IntelliJ, see [IDE_SETUP.md](IDE_SETUP.md).
To publish rather than build, see the publishing notes and TODO: do not use
`-Prelease` or `mvn deploy` for routine verification.

## Security checks

Dependabot monitors Maven plugins and GitHub Actions weekly. The Maven
`owasp-check` profile runs OWASP Dependency-Check when explicitly selected:

```sh
mvn -B -ntp -Powasp-check verify
```

The vulnerability database may require network access/rate-limit handling;
avoid placing NVD API keys or Maven publishing credentials in source control.
CodeQL is managed by GitHub Code Security's **default setup**, not by a
checked-in custom CodeQL workflow. GitHub rejects uploads from advanced
configuration while default setup remains enabled. Review CodeQL findings
under **Security → Code scanning** in the repository.

See [Security Policy](.github/SECURITY.md) for private vulnerability reporting.
