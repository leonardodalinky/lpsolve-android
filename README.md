# lpsolve-android
Migrate the `lp_solve` code from C, Java to Android.

Version of `lp_solve`: 5.5.2.5

## Background

Few days ago, I was developing some tools for Princess Connect Re:Dive and met the problem of linear programming.   

However, when I try the `org.apache.commons:commons-math3` library, it shows the **BAD** performance as the constraints and variable increase. That's the reason why I migrate the `lp_solve` library from C to Android. 

It's believed that `lp_solve` libraries give the correct answer at least **10 TIMES FASTER THAN ** `org.apache.commons:commons-math3`. And the accuracy has increased dramatically, compared to the former.  

## How to Use

Before we go on, we shall check some prerequisites:

1. **Android Studio**
2. CMake

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
        - lpsolve
        - ...More
lpsolve_bin
  - lib
    - arm64-v8a
    - armeabi-v7a
    - x86
    - x86_64
```

The directories inside `lpsolve_bin/lib` contain the `.so` libraries for each platforms.   

There are **2 different ways** to lay out the `lp_solve` libraries.  

### Way 1: Build A New NDK Project

First, **build a NDK project** in **AS**.  

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
        	cmake  {
			path "src/main/cpp/CMakeLists.txt"
       		}
    	}
}
```

### Way 2: Add the `lp_solve` libraries to an existing project

1. Start an existing android project with AS.  

2. Put the files in directory `app/src` in the corresponding location of the NDK project. **Make sure the `lpsolve` package is the subdirectory of `app/src/main/java` and the `cpp` directory is the subdirectory of `app/src/main`, or it may cause errors.**  

3. Open the **Project** pane from the left side of the IDE and select the **Android** view.
4. Right-click on the module you would like to link to your native library, such as the **app** module, and select **Link C++ Project with Gradle** from the menu.
5. From the drop-down menu, select **CMake**. Then, use the field next to **Project Path** to specify the `src/main/cpp/CMakeLists.txt` script file for your external CMake project.
6. Click **OK**.

Above steps could be referred to [Link Gradle to your native library](https://developer.android.com/studio/projects/gradle-external-native-builds) on the website of Android Developers.  

  

When everything is done, you could import the `lpsolve` package the same as what we do with Java.   

And every time the `.apk` file is compiled and built, the `lpsolve` would be packed in it.

## API Reference

[lp_solve reference guide](http://lpsolve.sourceforge.net/5.5/)

## License

[The MIT License](https://github.com/leonardodalinky/lpsolve-android/blob/master/LICENSE)

## Credits

The project relies on:

* [lp_solve](https://sourceforge.net/projects/lpsolve/)

