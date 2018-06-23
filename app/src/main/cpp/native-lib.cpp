#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_example_administrator_testndkandorid_utils_MyLib_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++00";
    return env->NewStringUTF(hello.c_str());
}
