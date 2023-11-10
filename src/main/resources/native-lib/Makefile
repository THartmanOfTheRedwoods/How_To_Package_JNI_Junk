# Compiler and flags
CXX := g++
CXXFLAGS := -dynamiclib -fPIC
INCLUDES := -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/darwin

# Source and output files
SRC := HelloWorld.cpp
OUTPUT := libHelloWorld.dylib

# Build rule
all: $(OUTPUT)

$(OUTPUT): $(SRC)
	$(CXX) $(CXXFLAGS) $(INCLUDES) -o $(OUTPUT) $<

clean:
	rm -f $(OUTPUT)

.PHONY: all clean
