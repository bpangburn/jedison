# Publishing jEDISON to Maven Central

The repository uses Sonatype's **Central Publisher Portal**, not the retired
OSSRH/Nexus Staging service. This procedure covers release preparation,
local bundle inspection, Portal validation, and **manual** publication.

The ordinary `mvn clean verify` build is unchanged. Publishing is available
only with the explicit `-Prelease` Maven profile. The release profile is
configured to **skip uploads by default**, even when `deploy` is requested.
The Portal plugin also has `autoPublish=false`.

## Before publishing

1. Sign in to [Central Publisher Portal](https://central.sonatype.com/).
   Verify your publisher account can publish the existing
   `com.greatmindsworking.jedison` namespace. OSSRH namespaces were migrated
   to the Portal, but access and credentials must be confirmed in your account.
2. Verify you have a working GPG secret key and its public key is available
   from a public key server. Inspect locally with
   `gpg --list-secret-keys --keyid-format LONG`. This repository does
   not store a GPG key or passphrase.
3. Generate a **Portal user token** (not your account password). In your
   private `~/.m2/settings.xml`, configure the matching server ID:

   ```xml
   <settings>
     <servers>
       <server>
         <id>central</id>
         <username>YOUR_TOKEN_USERNAME</username>
         <password>YOUR_TOKEN_PASSWORD</password>
       </server>
     </servers>
   </settings>
   ```

   Protect this file appropriately, for example `chmod 600 ~/.m2/settings.xml`.
   Never commit tokens, keys, or `settings.xml` to this repository.
4. Choose a **new, previously unpublished release version** in `pom.xml`.
   Version `1.1.0` is already on Maven Central and must not be uploaded again.
   Change the version in a dedicated release commit when ready, not as part of
   the publishing-infrastructure migration.

## Build and inspect a local bundle (no upload)

Use a suitable installed JDK (21 is fine; bytecode still targets Java 8) and
Maven 3.6.3+:

```sh
mvn -B -ntp clean verify
mvn -B -ntp -Prelease -Dcentral.skipUpload=true clean deploy
unzip -l target/central-publishing/central-bundle.zip
```

`-Prelease` builds source and Javadoc JARs and signs the artifacts with
`maven-gpg-plugin`. Select a particular signing key with
`-Dgpg.keyname=YOUR_KEY_ID` if necessary; GPG may prompt through your local
agent for the passphrase. **Do not** use `-Dgpg.skip=true` for publishing.
The plugin creates checksums when bundling.

Expect a release JAR, POM, sources JAR, Javadoc JAR, and `.asc` signatures
for the artifacts. Verify the release metadata, contents, signatures, and
version before attempting an upload. A dry-run bundle is not proof that
Central Portal validation will pass.

## Upload for validation — deliberate, manual step

Only after reviewing the local bundle, enable upload explicitly:

```sh
mvn -B -ntp -Prelease -Dcentral.skipUpload=false clean deploy
```

This uploads the signed bundle to the Portal. By design, it **does not
automatically publish**. Inspect the deployment at
[Central Portal Deployments](https://central.sonatype.com/publishing/deployments);
verify it reaches **VALIDATED**, check the coordinates and uploaded files,
then use the Portal's **Publish** action when ready.

After publication, verify the new version is available under
[com.greatmindsworking.jedison:jedison](https://central.sonatype.com/artifact/com.greatmindsworking.jedison/jedison)
and confirm the downstream [EBLA](https://github.com/bpangburn/ebla) build
still resolves the artifact.

## Notes

- `-SNAPSHOT` publishing is a distinct workflow and should be separately
  verified before use; this procedure targets immutable releases.
- The GPG signing configuration now uses the local GPG key/agent or the
  `gpg.keyname` Maven property, rather than the old private
  `gmw.gpg.keyname` property.
- CI does **not** publish artifacts. Never configure release credentials
  as an automatic pull-request or push workflow.
- The current Sonatype plugin is version **0.12.0**.
- [Sonatype: Maven publishing plugin](https://central.sonatype.org/publish/publish-portal-maven/)
- [Sonatype: publishing requirements](https://central.sonatype.org/publish/requirements/)
- [Sonatype: GPG signing](https://central.sonatype.org/publish/requirements/gpg/)
