#include "HelloWorldJNI.h"
#include <iostream>

// Define a sayHello method
JNIEXPORT void JNICALL Java_HelloWorldApp_sayHello(JNIEnv *, jobject) {
    std::cout << "Hello, Trevor!" << std::endl;
}

int main() {
    // Call the sayHello method
    Java_HelloWorldApp_sayHello(NULL, NULL);
    return 0;
}

