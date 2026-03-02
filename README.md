# Lab 4

## Overview
This repository contains the implementation and automated testing suite for Lab 4 of Software Testing. The primary focus of this lab is utilizing **Mockito** to isolate the System Under Test (`LibraryService`) from external dependencies like databases and email servers.

## Part 1: Test Design
When designing my tests, I used a decision table. There were a lot (all but 5) of the rules that were not possible to test due to how the LibraryService was implemented. You can see in my test design document that even though some "identified rules" have a complete FFTT rule list, the actual implementation doesn't require values for rules that fail out early. All of my rules are mapped to specific Test cases, whose names are reflected in the implementation of LibraryServiceTest.

* **[Test Design & TCIs](https://docs.google.com/spreadsheets/d/1x9wTaRSZgseRveC4Q_mWc6YUHHjoWI0VoN4qfouN0d8/edit?usp=sharing)**

## Part 2: Implementation
The package structure follows `com.samuel.lab4` and contains both the provided interfaces/exceptions and the implemented test class.

**Test Implementation:**
* [src/test/java/com/samuel/lab4/LibraryServiceTest.java](src/test/java/com/samuel/lab4/LibraryServiceTest.java)
> **Note:** My tests are all implemented manually, but all work great and pass JaCoCo with 100% coverage. 

Below is the generated JaCoCo Code Coverage report:
<img width="784" height="127" alt="JaCoCo_Report_Lab4" src="https://github.com/user-attachments/assets/573967f5-a71e-4880-ae46-3a4e93dd93a2" />
