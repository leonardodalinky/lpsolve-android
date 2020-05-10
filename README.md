# lpsolve-android
Migrate the `lp_solve` code from C, Java to Android.

Version of `lp_solve`: 5.5.2.5

## Background

Few days ago, I was developing some tools for Princess Connect ReDive and met the problem of linear programming.   

However, when I try the `org.apache.commons:commons-math3` library, it shows the **BAD** performance as the constraints and variable increase. That's the reason why I migrate the `lp_solve` library from C to Android.

## How to Use

Before we go on, we shall check some prerequisites:

1. **Android Studio**
2. Cmake
3. LLDB (might be included in **AS**)

After that, download the latest `lpsolve_android_release.zip` in [release page](https://github.com/leonardodalinky/lpsolve-android/releases) and unzip it. The directory structure is listed below

```
app
  - build.gradle
  - src
    - main
      - cpp
        - CMakeLists.txt
        - ...More
      - java
        - ...More
lpsolve_bin
  - lib
    - arm64-v8a
    - armeabi-v7a
    - x86
    - x86_64
```

The directories inside `lpsolve_bin/lib` contain the `.so` libraries for each platforms.   

First, build a ndk project in **AS**.  

The file structure in directory `app` correspond to the real structure in ndk project of Android Studio. Then:

1. Put the files in directory `app/src` in the corresponding location of the ndk project. **Make sure the `lpsolve` package is the subdirectory of `app/src/main/java` and the `cpp` dir is the subdirectory of `app/src/main`, or it may cause errors.**
2. Modify the `build.gradle` in `app` as below:

```
android{
	...
	defaultConfig{
		...
		# Here define the parameters which control the cmake
        externalNativeBuild {
            cmake {
                cFlags ""
                cppFlags ""
            }
        }
	}
	...
	# Here is the relative path of the CMakeList.txt
    externalNativeBuild {
        cmake {
            path "src/main/cpp/CMakeLists.txt"
        }
    }
}
```

When everything's done, you could import the `lpsolve` package the same as what we do with Java.   

And every time the apk is compiled and builded, the `lpsolve` would be packed in it.

## API Reference

[lp_solve reference guide](http://lpsolve.sourceforge.net/5.5/)

## License

The MIT License

## Credits

The project is rely on:

* lp_solve

