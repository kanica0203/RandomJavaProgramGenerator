# RandomJavaProgramGenerator

The project creates a generator that creates a syntactically correct but semantically meaningless Java application for a given 
configuration file. Configuration file(input.xml) will be having the number of classes, methods, expression and other parameters 
that constrain generation of java program.

A subset of Java BNF grammars had been used while generating the Java code from the already defined Java grammar. When the “Main” is run, the program auto generates syntactically correct but semantically meaningless Java code. 
There exists class where production rule of grammar has been specified. “ParseGrammar” is the class which decodes the production rule. In addition to the rules that are found in a typical context-free grammar of a programming language, additional rules and constraints that are imposed by the programming language specification are considered by the “Structure_Final” class which generates the program.
