
# Design Document

## 1. Domain Model
Description

### Diagram
![DomainModel](https://github.com/user-attachments/assets/9bb921f4-b033-4b41-88a0-76a1d65c7a80)


## 2. External System Sequence Diagram
Description

### Diagram
![ExternalSequenceDiagram](https://github.com/user-attachments/assets/0f666d59-1bb2-45ea-a806-7203dfac1cfa)


## 3. Internal System Sequence Diagram
We have decided to do 3 internal Sequence diagram, one for each of the interactions between the user and the system rappresenting the effect that it has on the system.

### Diagram Show Syntax Tree Interaction
The first one to show the effect of the first interaction, comunicating to the SentenceAnalyzer to build the tree so the UI can show it;

![InternalSequenceDiagramSyntaxTree](https://github.com/user-attachments/assets/7e45b549-e7b4-4b0f-9a1f-936f51f7b2af)

### Diagram Select a Template Interaction
The second one to show the effect of the second interaction, comunicating to the Generator of the user wants a random tamplate or if he wants to choose one and which one to use;

![InternalSequenceDiagramTemplate](https://github.com/user-attachments/assets/d19a0674-87bd-435c-a960-635a7b181a9f)

### Diagram Write a Sentence Interaction
The third and last one to show the effect of the third interaction, comunicating to program the sentence which the user wants analyzed and from which to take the words to generate a new one;

![InternalSequenceDiagramPhrase](https://github.com/user-attachments/assets/ef6eddeb-3d7e-4450-89ae-6d81176f7e11)



## 4. Class Diagram
Description

### Diagram
![Class Diagram](img/class-sequence.png)
