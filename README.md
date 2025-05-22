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









