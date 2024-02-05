# Expression Parser

A command line java application which parses a cron string and expands each field to show the times at which it will
run.

## Decision of Java language
Decision between Java and GoLang can be seen in the below image. Even though GoLang is hands down winner, on a high level Java also has it own advantages and given the limited time, I have choosen Java.

![Language Decision](https://github.com/venky333/expression-engine/blob/main/language_decision.png)

## High Level: Input - Output flow
See the image to find on high level how input is converted to output.

![Request Response flow](https://github.com/venky333/expression-engine/blob/main/request_response_flow.png)

## Requirements

Requirements needed to run this application in local

* Java version 1.8
* Maven version 3.9.5

## How to run this application

Run the following command in terminal after navigating to the root of the repository

1. Evaluating expression `*/15 0 1,15 * 1-5 /usr/bin/find`
```
make run "*/15 0 1,15 * 1-5 /usr/bin/find"
```

## Output

```
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

2. Evaluating expression `*/15 0 1,15 * 1-5 /usr/bin/find`
```
make run "1-16/15 23 1,15 * 1-7 /usr/bin/find"
```

## Output

```
minute        1 16
hour          23
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5 6 7
command       /usr/bin/find
```


## Test cases

Over-all code coverage is 90% and there is still scope of improvement.
Test can be found in /test folder
Just building the application will by default run all the tests.

**Command:**
```
make build
```