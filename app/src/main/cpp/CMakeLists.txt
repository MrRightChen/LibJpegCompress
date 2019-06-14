
cmake_minimum_required(VERSION 3.4.1)

#set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})
add_library( # Sets the name of the library.
        compress-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             native-lib.cpp )

#STATIC
add_library(libjpeg STATIC IMPORTED)
set_target_properties(libjpeg PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/armeabi-v7a/libturbojpeg.a)

#引入头文件    import
include_directories(${CMAKE_SOURCE_DIR}/include)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS}  -D__STDC_CONSTANT_MACROS -L${CMAKE_SOURCE_DIR}/libs/armeabi-v7a")



target_link_libraries( # Specifies the target library.
        compress-lib

        libjpeg
        #jnigraphics是安卓NDK目录中直接有的
        jnigraphics

                       # Links the target library to the log library
                       # included in the NDK.
                       log )