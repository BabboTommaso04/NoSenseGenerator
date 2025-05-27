# System test document

## Acceptance criteria 1

| Acceptance criteria name  |  Basic functionality                |
|--------------------|--------------------------|
| Actors             | User                     |
| Description        | Given I'm a user, when I input a phrase, then the system generates another one|
| Preconditions      |  - |
| Main Scenario      | 1. User enters a phrase<br>2. User clicks "generate"<br>3. System computes the input |
| Alternative Scenario| 1. Empty imput<br>2. System asks to write a phrase in the box |
| Post-Conditions    | New phrase is showed to the user  |
| Notes              | The phrase will be displayd in a new window |
| Test Passed        | Yes |


## Acceptance criteria 2

| Acceptance criteria name  |  Show syntactic tree                |
|--------------------|--------------------------|
| Actors             | User                     |
| Description        | Given I'm a user and I've already input my phrase, when I choose to see the syntactic tree, then the program provides the sentence structure.|
| Preconditions      |  User wrote something in the input box and chose to see the syntax tree|
| Main Scenario      | 1. User enters a phrase<br>2. User clicks "Show syntax tree"<br>4. User clicks "generate"<br>3. System computes the input|
| Alternative Scenario| 1. Empty imput<br>2. System asks to write a phrase in the box |
| Post-Conditions    |1. New phrase is showed to the user<br>2. Below the generated phrase the user can see the syntax tree  |
| Notes              | - |
| Test Passed        | Yes |



## Acceptance criteria 3

| Acceptance criteria name  |  Input word found in the output|
|--------------------|--------------------------|
| Actors             | User                     |
| Description        | Given I'm a user and I've already input my phrase, when the program generates the random sentence, then it must contain terms from the input phrase|
| Preconditions      |  User wrote something in the input box|
| Main Scenario      | 1. User enters a phrase and clicks "generate"<br>2. System elaborates the input |
| Alternative Scenario| 1. Empty imput<br>2. System asks to write a phrase in the box |
| Post-Conditions    |1. New phrase is showed to the user<br>2. The generated sentence contains at least one word from the input  |
| Notes              | - |
| Test Passed        | Yes |




## Acceptance criteria 4

| Acceptance criteria name  |  Output contains words from a dictionary |
|--------------------|--------------------------|
| Actors             | User                     |
| Description        | Given I'm a user and I already input my phrase, when the program generates the random sentence, then it must contain not only terms from the input phrase, but also terms from a built-in dictionary.|
| Preconditions      |  User wrote something in the input box |
| Main Scenario      | 1. User enters a phrase and clicks "generate"<br>2. System elaborates the input |
| Alternative Scenario| 1. Empty imput<br>2. System asks to write a phrase in the box |
| Post-Conditions    |1. New phrase is showed to the user<br>2. The generated sentence contains at least one word from the input and also words from an internal dictionary  |
| Notes              | The words taken from the dictionary are randomly chosen |
| Test Passed        | Yes |

## Acceptance criteria 5

| Acceptance criteria name  |  Show toxicity |
|--------------------|--------------------------|
| Actors             | User                     |
| Description        | Given I'm a user and I've already input my phrase, when the program generates the random sentence, then it must also provide the probability that the random phrase is toxic|
| Preconditions      |  User wrote something in the input box |
| Main Scenario      | 1. User enters a phrase and clicks "generate"<br>2. System elaborates the input |
| Alternative Scenario| 1. Empty imput<br>2. System asks to write a phrase in the box |
| Post-Conditions    | 1. System return the generated sentence<br>2. System returns the toxicity of the generated sentence |
| Notes              | The displayed number indicates the probability that the generated sentence is toxic |
| Test Passed        | Yes |

## Acceptance criteria 6

| Acceptance criteria name  |  Sentence bucket |
|--------------------|--------------------------|
| Actors             | User                     |
| Description        | Given I'm a user and I've already received the generated phrase from the program, then that phrase must be saved in a file|
| Preconditions      |  User wrote something in the input box |
| Main Scenario      | 1. User enters a phrase and clicks "generate"<br>2. System elaborates the input |
| Alternative Scenario| 1. Empty imput<br>2. System asks to write a phrase in the box |
| Post-Conditions    | 1. System return the generated sentence<br>2. System returns the toxicity of the generated sentence<br>3. The generated sentence is saved in a .txt file |
| Notes              | The .txt file can be found in the directory: "src/main/resources/generated_sentences.txt" |
| Test Passed        | Yes |

## Acceptance criteria 7

| Acceptance criteria name  |  Template selection |
|--------------------|--------------------------|
| Actors             | User                     |
| Description        | Given I'm a user and I've started the program, when I select a template from the list of templates, then the output phrase must follow that template|
| Preconditions      |  User wrote something in the input box |
| Main Scenario      | 1. User enters a phrase<br>2. User clicks on the drop down menu containing the templates<br>3. User chooses a template <br>4. User clicks "generate" |
| Alternative Scenario| 1. Empty imput<br>2. System asks to write a phrase in the box |
| Post-Conditions    | 1. System return the generated sentence<br>2. System returns the toxicity of the generated sentence<br>3. The generated sentence follows the specified template |
| Notes              | - |
| Test Passed        | Yes |

## Acceptance criteria 8

| Acceptance criteria name  |  Saving words to dictionary |
|--------------------|--------------------------|
| Actors             | User                     |
| Description        | Given I'm a user and I've already input a phrase, when the program generates the random sentence, then it must also save the words from the input in the dictionary|
| Preconditions      |  User wrote something in the input box |
| Main Scenario      | 1. User enters a phrase<br>2. User chooses a template <br>4. User clicks "generate" |
| Alternative Scenario| 1. Empty imput<br>2. System asks to write a phrase in the box |
| Post-Conditions    | 1. System return the generated sentence<br>2. The words contained in the input are saved in the dictionary for future phrase generation |
| Notes              | The words from the input are added to a json file that can be found in the directory: "src/main/resources/dictionary.json". The dictionary doesn't contain duplicates. The words saved in the .json will be used for future phrase generation |
| Test Passed        | Yes |


![Screenshot 2025-05-27 alle 22 52 53](https://github.com/user-attachments/assets/36aaba05-07b8-4ab7-8885-ce5db10beb91)


