# Course project at git@bitbucket.org:drmark/randomjavaprogramgenerator.git
### Description: create a random Java program generator
### Grade: 25%

## Overview
In your course project, you will gain experience with creating a generator that creates a syntactically correct but semantically meaningless Java application. The input to your program is a configuration file that defines various parameters that constrain the generation of a Java application (e.g., the number of lines of code and the number of classes).  The git repo for HW4 can be cloned using the command `git clone git@bitbucket.org:drmark/randomjavaprogramgenerator.git`.

Consider the following simple grammar that contains seven production rules:
```
<expression> ::= <expression> + <expression>	//production rule 1
<expression> ::= <expression> - <expression>	//production rule 2
<expression> ::= <expression> × <expression>	//production rule 3
<expression> ::= ( <expression> )				//production rule 4
<expression> ::= <number> | <var>				//production rule 5
<number> 	 ::= 0 | [1-9][0-9]*				//production rule 6
<var> ::= [_a-zA-Z][_a-zA-Z0-9]{1,255}			//production rule 7
```

Suppose that you use a random number generator to choose a top-level production rule that ranges from 1 to 5. The random number generator produces the number 3, which corresponding to the rule `<expression> ::= <expression> × <expression>`. The chosen production rule creates a main template for your generated program, `<expression> × <expression>`. At this point, you run the random number generator twice to choose production rules for each nonterminal <expression>. Suppose that the generated random numbers are 2 and 5 respectively. Thus, the expression is transformed into the following `<expression> - <expression>` for the left of `x` and `<number> | <var>` for the right side of `x`. For the latter we generate a random number between 6 and 7, say it is 7 and we continue with the left hand side eventually replacing all nonterminals <expression>. Suppose that the result is `(<number> - <var>) x <var>`. At this point, we use a random string generator to match regular expressions (for example, [github regex generator](https://github.com/fent/randexp.js) or some other regex generator) and obtain the resulting program `(231 - xy09) x jk3049`. This is your resulting randomly generated program.

Consider that every program is an instance of the grammar of the language in which this program is written. Typically, grammars are used in compiler construction to write parsers that check the syntactic validity of a program and transform its source code into a parse tree. An opposite use of the grammar is to generate branches of a parse tree for different production rules, where each rule is assigned the probability with which it is instantiated in a program. These grammars and parse trees are called stochastic, and they are widely used in natural language processing, speech recognition, information retrieval, and also in generating SQL statements for testing database engines. It is equivalent to assigning some number between zero and one to each production rule in a grammar and generating a program from it randomly. Consider an example of the parse tree for the GCD program in the Scott's textbook on page 31. The parse tree shows the instantiation of the grammar rules for the GCD program. Now, consider that this tree is obtained by randomly selecting production rules. There is a small chance that the GCD program can be generated, however, it is likelier that you will generate a syntactically correct but semantically meaningless program.
 
In this course project, you will use a stochastic grammar model to generate large random object-oriented programs. Random programs are constructed based on the stochastic grammar model, and the construction process can be described as follows. Starting with the top production rules of the grammar, each nonterminal is recursively replaced with its corresponding production rule. When more than one production rule is available to replace a nonterminal, a rule is randomly chosen based on the rules’ probabilities. Terminals are replaced with randomly generated identifiers and values that preserve syntax rules of the given language. Termination conditions for this process of generating programs include the limit on the size of the program or selected complexity metrics. 
 
In addition to the rules that are found in a typical context-free grammar of a programming language, you will take into account additional rules and constraints that are imposed by the programming language specification. For example, a variable has to be defined before it can be used and a non-abstract class in an object-oriented program has to implement all abstract methods it inherits from its super-types. With such an enhanced stochastic grammar model it is ensured that the generated program is syntactically correct and compiles. The construction process can be fine-tuned by varying the ranges of different configuration parameter values and limiting the grammar to a subset of the production rules that are important for evaluating specific software tools (e.g., recursion, use of arrays, or use of different data types can be turned off). 

To satisfy different requirements for generated programs, your program generator should be highly configurable. Some of the important parameters include the max and the min number of classes, the max and the min number of methods per class, the max and the min number of interfaces, the max and the min number of methods per interface, the maximum depth of the inheritance hierarchy, the max and the min number of class fields, the max and the min number of parameters per method, and the recursion depth (if recursion is enabled). As you see, most of the parameters have a lower and an upper limit. Moreover, many parameters are inter-dependent (e.g., there should be enough classes to populate an inheritance tree of a desired depth). Once these limits are defined, your program will randomly choose values from each range. 

For each of these configuration parameters, you define a default range that seems reasonable. For example, to determine the number of classes, you can follow an observation that LOC is roughly 114 times the number of classes in a program and set classes to be equal to LOC/114. To define the number of interfaces, you can follow an observation that each package in a program has roughly 12 classes and there is one interface per package. Hence you can set default interfaces to be equal to LOC/(114 * 12) = LOC/1368. It was found that the average value for the maximum number of methods per interface’ is 3.4 and 96% of the Java programs in Sourceforge have less than 20 class fields, and 99% of the programs have less than 60 methods per class. You can conform to these observations and used these values as the default upper bound for respective parameters in your configuration. You can similar heuristics for other parameters, such as number of parameters per method and maximum inheritance depth. The following XML-based configuration file has a more detailed list of the configuration parameters and their default values.

<ProgGen>
<noOfInterfaces>10</noOfInterfaces>
<maxNoOfMethodsPerInterface>5</maxNoOfMethodsPerInterface>
<noOfClasses>40</noOfClasses>
<maxAllowedMethodCalls>5</maxAllowedMethodCalls>
<maxValueForLoop>10</maxValueForLoop>
<allowIndirectRecursion>no</allowIndirectRecursion>
<maximumArraySize>100</maximumArraySize>
<noOfInheritanceChains>7</noOfInheritanceChains>
<maxNestedIfs>4</maxNestedIfs>
<minNoOfClassFields>2</minNoOfClassFields>
<allowArray>yes</allowArray>
<maxInheritanceDepth>5</maxInheritanceDepth>
<maxNoOfParametersPerMethod>10</maxNoOfParametersPerMethod>
<minInheritanceDepth>2</minInheritanceDepth>
<totalLOC>10000</totalLOC>
<maxInterfacesToImplement>2</maxInterfacesToImplement>
<maxRecursionDepth>1</maxRecursionDepth>
<classNamePrefix>TenKLOC</classNamePrefix>
<maxNoOfClassFields>5</maxNoOfClassFields>
<maxNoOfMethodsPerClass>20</maxNoOfMethodsPerClass>
<recursionProbability>0.5</recursionProbability>
<minNoOfParametersPerMethod>3</minNoOfParametersPerMethod>
<intMaxValue>100</intMaxValue>
<allowedTypes>
	<type>char</type>
	<type>byte</type>
	<type>short</type>
	<type>int</type>
	<type>long</type>
	<type>float</type>
	<type>double</type>
	<type>String</type>
	<type>Object</type>
	</allowedTypes>
</ProgGen>

Many language features cannot be generated correctly by blind random program generation, because they have associated well-formedness rules that any legal program must satisfy. For example, a method can only be called if it and its defining type have a visibility that permits the call from the specific call-site, a final field defined by class C must be initialized directly or by each constructor of C, and generated non-abstract classes have to provide implementations for all inherited abstract methods. There are limitations on using the keyword super in Java. Special care has to be given to avoid generation of loops that may not terminate or nonterminating recursive calls, if desired. To enforce these restrictions, you should utilize internal tables and sets. That is, you will implements a symbol table to ensure that only variables from correct scopes are used, and your generator will maintain type compatibility. It will allow primitive and reference types in method parameters and method bodies. To avoid runtime exceptions such as divide-by-zero, your generator can enforce that only non-zero valued expressions occur in the denominator of a division operation. To avoid infinite loops, you generator will only use literals in the loop condition. Special configuration parameters will be used to enable and control recursion and indirect recursion. Your generator must also ensure that all abstract methods of all (transitive) supertypes are implemented and no non-static field is referenced in a static method.

## Submission Quality 
The quality of your submission will be measured by determining if your generated programs have a wide a wide variety of language constructs, which are important for evaluating software tools, e.g., a Java compiler. Sample constructs include recursion, dynamic dispatch, and array manipulations using expressions that compute array indices that test the boundaries of the JVM algorithms. 

## Logistics
You can work in groups on this project, however, a student cannot be involved in more than one group. If you want to work alone, it is perfectly fine, however, you can decide to work in a group with up to three more of your classmates. Logistically, one of team members will create a private fork and will invite one or two of her classmates with the write access to your fork. You should be careful who you partner with - once you form a group and write and submit code, you cannot start dividing your work and claim you did most of the work. Your forkmates may turn out to be freeloaders and you will be screwed, but it is a part of your life experience. Be very careful and make sure that you trust your classmates before forming your group. Neither your TA not I can and will resolve your internal group conflicts, unless you can present convincing evidence that you did all the work alone. Your submission will include the names of all of your forkmates and you will receive the same grade for this project. Working in a group will be an excellent opportunity for you to explore branching in git, merging, rebasing, and resolving semantic conflicts when merging your code changes. Don't pass on this opportunity!

If you submitted your previous homework, it means that you were already added as a member of CS_474_2017 team in Bitbucket. Separate repositories will be created for each of your homeworks and for the course project. You will find a corresponding entry for this project. You will fork this repository and your fork will be private, no one else besides you, your forkmates, the TA and your course instructor will have access to your fork. Please remember to grant a read access to your repository to your TA and your instructor and write access to your forkmates. You can commit and push your code as many times as you want. Your code will not be visible and it should not be visible to other students except for your forkmate, of course. When you push your project, your instructor and the TA will see you code in your separate private fork. Making your fork public or inviting other students except for your forkmates to join your fork will result in losing your grade. For grading, only the latest push timed before the deadline will be considered. If you push after the deadline, your grade for the homework will be zero. For more information about using git and bitbucket specifically, please use this link as the starting point https://confluence.atlassian.com/bitbucket/bitbucket-cloud-documentation-home-221448814.html. For those of you who still struggle with Git, I keep recommending a book by Ryan Hodson on Ry's Git Tutorial. The other book called Pro Git is written by Scott Chacon and Ben Straub and published by Apress and it is freely available https://git-scm.com/book/en/v2/. There are multiple videos on youtube that go into details of Git organization and use.

As your TA specified, please follow this naming convention while submitting your work : "Firstname_Lastname_hw3", so that we can easily recognize your submission. Those who work in groups can use longer names: "Firstname1_Lastname1_Firstname2_Lastname2_Firstname3_Lastname3_hw3". I repeat, please make sure that you will give both your TA and me read access to your private forked repository.

## Piazza Collaboration
As usual, I allow you to post questions and replies, statements, comments, discussion, etc. on Piazza either using your names or anonymously. Remember that you cannot share your code and your solutions beyond your forkmate group, but you can ask and advise others using Piazza on where resources and sample programs can be found on the internet, how to resolve dependencies and configuration issues, and how to design the logic of the algorithm, as usual. Yet, your implementation should be your own and you cannot share it beyond your forkmate group. Alternatively, you cannot copy and paste someone else's implementation and put your name on it. Your submissions will be checked for plagiarism. When posting question and answers on Piazza, please select the appropriate folder, i.e., hw3 to ensure that all discussion threads can be easily located.

## Submission
Submission deadline: Wednesday, December 13 at 10PM CST. Your submission will include your source code, the SBT and Gradle build configurations, the IntelliJ project, the README.md file in the root directory that contains the description of your implementation, how to compile and run it, and what are the limitations of your implementation. You should describe what JSL rules you implemented and how their work.

THE INSTRUCTOR (and the TA) WILL NOT ANSWER ANY REQUESTS FROM STUDENTS STARTING 7PM THE NIGHT BEFORE THE SUBMISSION DEADLINE.

## Evaluation criteria:
- the maximum grade for this prokect is 25 Points are subtracted from this maximum grade: for example, saying that 2% is lost if some requirement is not completed means that the resulting grade will be 25%-2% => 23%;
- no comments or highly insufficient comments: up to 15% lost;
- no tests that include various configuration profiles: up to 15% lost;
- the generator's code does not compile or it crashes without completing the core functionality: up to 25% lost;
- the generated code does not compile: up to 20% lost;
- the generator simply outputs a set of hardcoded strings as a generated program: 25% lost;
- the documentation is missing or insufficient to understand how to compile and run your program: up to 20% lost;
- the minimum grade for this course project cannot be less than zero.