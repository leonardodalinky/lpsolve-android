#/**
#* @ FileName: CMakeLists.txt
#* @ Function: The CMakeList to build up the dynamic library for lp_solve
#* @ Author: AyajiLin & YesunHuang
#* @ Mail: 493987054@qq.com
#* @ Github: https://github.com/leonardodalinky
#* @ Data: May 10, 2020
#*
#*/

# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

#add_subdirectory(c_src)

include_directories(java_src)
include_directories(c_src)
include_directories(c_src/colamd)
include_directories(c_src/shared)
include_directories(c_src/bfp)
include_directories(c_src/bfp/bfp_LUSOL)
include_directories(c_src/bfp/bfp_LUSOL/LUSOL)

aux_source_directory(java_src DIR_JAVA_SRCS)

SET(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -O3 -lc -lm -ldl -DYY_NEVER_INTERACTIVE -DPARSER_LP -DINVERSE_ACTIVE=INVERSE_LUSOL -DRoleIsExternalInvEngine -DNOLONGLONG -DCLOCKTIME -dy")
SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -frtti -fexceptions -O3 -lc -lm -ldl -DYY_NEVER_INTERACTIVE -DPARSER_LP -DINVERSE_ACTIVE=INVERSE_LUSOL -DRoleIsExternalInvEngine -DNOLONGLONG -DCLOCKTIME -dy")
SET(C_SRCS c_src/lp_MDO.c c_src/shared/commonlib.c c_src/shared/mmio.c c_src/shared/myblas.c c_src/ini.c c_src/fortify.c c_src/colamd/colamd.c c_src/lp_rlp.c c_src/lp_crash.c c_src/bfp/bfp_LUSOL/lp_LUSOL.c c_src/bfp/bfp_LUSOL/LUSOL/lusol.c c_src/lp_Hash.c c_src/lp_lib.c c_src/lp_wlp.c c_src/lp_matrix.c c_src/lp_mipbb.c c_src/lp_MPS.c c_src/lp_params.c c_src/lp_presolve.c c_src/lp_price.c c_src/lp_pricePSE.c c_src/lp_report.c c_src/lp_scale.c c_src/lp_simplex.c c_src/lp_SOS.c c_src/lp_utils.c c_src/yacc_read.c)

add_library( # Sets the name of the library.
            lpsolve55j

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
            ${C_SRCS} ${DIR_JAVA_SRCS}
        )
# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                        lpsolve55j

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib}
                       )