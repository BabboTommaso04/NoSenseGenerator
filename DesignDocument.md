
# Design Document

## 1. Domain Model
The domain model has been created starting from the user stories. We’ve extracted the most significant nouns from them and proceeded to create conceptual classes. We then started to find the connections between those classes, representing the relationships, responsibilities, and associations needed to fulfill the system’s functionality. This led to the identification of key aggregations, generalizations, and dependencies that define how the objects collaborate to support the behavior described in the user stories. The result is a domain model that reflects both the structural and semantic essence of the problem domain.
### Diagram
![DomainModel](https://github.com/user-attachments/assets/9bb921f4-b033-4b41-88a0-76a1d65c7a80)


## 2. External System Sequence Diagram
We have created the System Sequence Diagram from User Stories and Domain Model, it rapresents the connections between User, Systems and external components like API. The User interact with our System which call two sections of Google cloud natural language API, the Sentence Analysis and Sentence Moderation part.

### Diagram
![ExternalSequenceDiagram](https://github.com/user-attachments/assets/0f666d59-1bb2-45ea-a806-7203dfac1cfa)


## 3. Internal System Sequence Diagram
We have decided to do 3 internal Sequence diagram, one for each of the interactions between the user and the system rappresenting the effect that it has on the system.The three iteractions are:
- User select a Tamplate
- User decide to visualize Syntax Tree
- User write a sentence

### Diagram Select a Template Interaction
The first one to show the effect of the first interaction, comunicating to the Generator of the user wants a random tamplate or if he wants to choose one and which one to use;

![InternalSequenceDiagramTemplate](https://github.com/user-attachments/assets/d19a0674-87bd-435c-a960-635a7b181a9f)

### Diagram Show Syntax Tree Interaction
The second one to show the effect of the second interaction, comunicating to the SentenceAnalyzer to build the tree so the GUI can show it;

![InternalSequenceDiagramSyntaxTree](https://github.com/user-attachments/assets/7e45b549-e7b4-4b0f-9a1f-936f51f7b2af)


### Diagram Write a Sentence Interaction
The third and last one to show the effect of the third interaction, comunicating to program the sentence which the user wants analyzed and from which to take the words to generate a new one;

![InternalSequenceDiagramPhrase](https://github.com/user-attachments/assets/d4f88cb5-4aa2-4f03-a446-98283bfb2d2f)


## 4. Class Diagram


### Diagram
![ClassDiagram](https://github.com/user-attachments/assets/06a85391-d5eb-43b3-8582-adae6f5ebbd8)
