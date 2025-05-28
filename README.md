# Non Sense Generator Manual

**Author**: BBC Team

## Introduction

The Non Sense Generator processes input text to create grammatically structured yet absurd sentences. Key features include:

- Syntax tree visualization using Google Cloud NLP  
- Template-based sentence generation  
- Toxicity analysis via machine learning models  
- Dynamic dictionary expansion from user input

## Technologies Used

| Name                    | Version | Description                        |
|-------------------------|---------|------------------------------------|
| Java                    | 17      | Core programming language          |
| Google Cloud NLP API    | v1      | Syntax analysis and toxicity scoring |
| Swing                   | N/A     | GUI framework                      |
| Maven                   | 4.0.0   | Dependency management and building tool         |
| JUnit                   | 5.0     | Testing                            |

## Project Description and Implementation

During the initial phases of the project, the requirements were analyzed with the intention of approaching the design and implementation stages in a structured and sequential manner.  
Once the analysis and design phases were completed (the related documents are provided in the following sections), the development of the application began.  
The main idea underlying the implementation of the software's features was to use an easy interface to visualize a generated sentence, toxicity, and syntax tree.

### Graphic Interface

The main idea underlying the implementation of the software's features was to use a easy interface for visualize a generated sentence, toxicity and syntax tree
The interface is composed of some buttons and text boxes where a sentence is inserted and where the generated sentence and the syntax tree are displayed.

## API Integration

### Google Cloud Natural Language API

| Function            | Implementation |
|---------------------|----------------|
| **Syntax Analysis** | Used in `SentenceAnalyzer` for:  |
|| - Input text tokenization | 
|| - Part-of-Speech (POS) tagging  |
|| - Dependency tree construction  |
|| - Grammatical relationship extraction |
| **Content Moderation** | Implemented in `ToxicAnalyzer` for:  |
|| - Toxicity score calculation (0-1) |
|| - Inappropriate language detection |

## Library

### Gson

| Usage              | Description |
|--------------------|-------------|
|**Dictionary Persistence**| Serializes/deserializes: | 
||  - `Map<String, Set<Word>>` structure  |
||  - `Word` objects inside sets |  
|| - JSON files for saving/loading state |
| **Generic Type Handling** | Enabled via:  
||  - `TypeToken<Map<String, Set<Word>>>`  |
||  - Runtime retention of type info  |
||  - Safe deserialization of complex generics |
| **Integration Simplicity** | Minimal setup: |
|| - `new Gson()` instance  |
||  - Standard `toJson()` / `fromJson()` methods  |
||  - No external config files |


