# XO-Android

A two-player tic-tac-toe (X and O) game for Android. Both players share one phone and take turns tapping the 3x3 board. Written in Java as an Eclipse ADT project from 2013, so there is no Gradle build.

## Versions

| Tag | App | What it does |
| --- | --- | --- |
| `v2.0.0` | XO (`sy.XO_company.xo`) | Nine image buttons draw the X and O shapes, a filled cell can't be taken again, a toast shows "X Wins", "O Wins" or "Draw", and a **Rest** button starts a new round. |
| `v1.0.0` | XvsO (`my.example.xvso`) | First version: nine plain buttons that write X or O in turn. No win or draw check, and a cell can be overwritten. |

The X and O shapes were drawn in `design/XO shaps.pptx` and exported to the `xicon`, `oicon` and `null_icon` drawables.

## Download

`XO-Android-v2.0.0.apk` on [Releases](https://github.com/Mohammad-Diab/XO-Android/releases) is the original debug build of v2.0.0. v1.0.0 has no build.

It targets Android 4.2 (API 17), so Android 14 and later refuse to install it from the phone. Install it over USB instead:

```
adb install --bypass-low-target-sdk-block XO-Android-v2.0.0.apk
```

## Known issues (v2.0.0)

- **Rest** clears the game state but not the board: the X and O images stay on the cells until they are tapped again.
- After a draw, every further tap on the board shows "Draw" again.
- Cells are painted with `View.setBackground`, which needs Android 4.1 (API 16), while the app declares Android 2.2 (API 8) as its minimum; on older phones the first tap crashes.
- The X, O and empty sample buttons under the board are visible; the code copies their backgrounds onto the board cells.

## Building

```
git clone https://github.com/Mohammad-Diab/XO-Android.git
```

The project uses the Eclipse ADT layout (`AndroidManifest.xml`, `src`, `res`, `assets` and `libs` at the root) and targets `android-17`. Eclipse ADT is no longer available, so to build it today:

1. Add a `settings.gradle` and a `build.gradle` with the Android Gradle plugin, and point `android.sourceSets.main` at the existing folders: `manifest.srcFile 'AndroidManifest.xml'`, `java.srcDirs = ['src']`, `res.srcDirs = ['res']`, `assets.srcDirs = ['assets']`.
2. Set `namespace` and `applicationId` to `sy.XO_company.xo`, and remove the `package` attribute from `AndroidManifest.xml` (current plugin versions reject it).
3. Use a current `compileSdk` and a `minSdk` of 16 or more. The code compiles against API 34 without changes.
4. Ignore `project.properties`: it refers to a `../gridlayout_v7_2` library project that the code doesn't use. `libs/android-support-v4.jar` isn't used either.

Then open the folder in Android Studio, or run `gradle assembleDebug`.

## Author

Mohammad Diab
