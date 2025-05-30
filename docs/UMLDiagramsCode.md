## Domain Model
```
@startuml
object User
object SyntacticTree
object TemplateList
object Template
object Word{
NounAdjectiveVerb
}
object Sentence
object ToxicAnalyzer
object SentenceAnalyzer
object Dictionary
object SentenceBucket
object GeneratedSentence

User "1" -- "1" Template : select >
User "1" -- "1" SyntacticTree : visualize >
User "1" -- "1" Sentence : input >
ToxicAnalyzer "1" -- "1" GeneratedSentence : analize >
GeneratedSentence "1" -- "1" Template : follow > 
TemplateList "1" o-- "*" Template : contained in
Dictionary "1" o-- "*" Word 
SyntacticTree "1" -- "1" SentenceAnalyzer : returns <
Sentence "1" -- "1" SentenceAnalyzer: analyze <
GeneratedSentence "1" -- "*" Word : made with >
GeneratedSentence "1" -- "1" SentenceBucket : saved >
SentenceAnalyzer "1" -- "*" Word : returns >
@enduml
```


## External System Sequence Diagram
```
@startuml
title System Sequence Diagram
actor User as U
participant System as S
participant "Syntax API" as SA
participant "Moderation API" as MA


  U -> S : show the templates list
  U <-- S : template list
  U -> S : select a Template
  U -> S : set syntaxTree flag
  U -> S : write in input a sentence
  S -> SA : analyze the sentence 
  S <-- SA : sentence analyzed
  S -> S : add words to dictionary
  opt syntaxTree = true
    S -> S :generate syntaxTree
  end 
  S -> S : generate a random sentence
  S -> S : save the generated sentece to sentenceBucket
  S -> MA : analyze the toxicity
  S <-- MA : toxicity
  
  U <-- S : generated sentence, toxicity
  opt syntaxTree = true
  U <-- S : syntaxTree
  end
  
@enduml
```


## 3 Internale System Sequence Diagram

### 1. Template Interaction
```
@startuml

title Internal Sequence Diagram
actor User

User -> Controller : Template(int NTemplate)
Activate Controller
Controller -> Generator :  selectTemplate(int NTemplate)
Activate Generator
Generator --> Controller 
Deactivate Generator
Controller --> User
Deactivate Controller
@enduml
```

### 2. Syntax Tree Interaction
```
@startuml

title Internal Sequence Diagram
actor User

User -> Controller : syntaxTree(boolean syntaxTree)
Activate Controller
Controller -> SententceAnalyzer :  updateBoolean(boolean syntaxTree)
Activate SententceAnalyzer
SententceAnalyzer --> Controller 
Deactivate SententceAnalyzer
Controller --> User
Deactivate Controller
@enduml
```

### 3. Phrase Interaction
```
@startuml
title Internal Sequence Diagram
actor User

User -> Controller : getSentence(String inputString)
Activate Controller
Controller -> SentenceAnalyzer : getString(String inputString)
Activate SentenceAnalyzer
SentenceAnalyzer -> SentenceAnalyzer : APIHandler(String inputString)
Activate Word
Word --> SentenceAnalyzer

SentenceAnalyzer -> Dictionary : add(String tag, String lemma, String number)
Activate Dictionary
Dictionary -> Word : create(String tag, String lemma, String number)
Word --> Dictionary

opt GenerateTree = True
  SentenceAnalyzer -> SentenceAnalyzer : generates syntax tree
end

SentenceAnalyzer --> Controller : return syntax tree
Deactivate SentenceAnalyzer

Controller -> Generator : generate()
Activate Generator
Generator -> Dictionary : takeWords()
Dictionary --> Generator
Deactivate Dictionary
Generator -> Controller : return generatedSentence
Deactivate Generator
Deactivate Word

Controller -> ToxicAnalyzer : takeSentenceGenerated
Activate ToxicAnalyzer
ToxicAnalyzer -> ToxicAnalyzer : APIHandler(String inputString)
ToxicAnalyzer -> Controller : return toxicity
Deactivate ToxicAnalyzer
Controller --> User : return generatedSentence and toxicity
opt GenerateTree = True
Controller --> User : return syntax tree
end
Deactivate Controller
@enduml
```


## Class Diagram
```
@startuml
top to bottom direction
skinparam linetype ortho

enum ClassificationCategoryName << enumeration >> {
  TOXIC
  INSULT
  PROFANITY
  DEROGATORY
  SEXUAL
  DEATH_HARM_AND_TRAGEDY
  VIOLENT
  FIREARMS_AND_WEAPONS
  PUBLIC_SAFETY
  HEALTH
  RELIGION_AND_BELIEF
  ILLICIT_DRUGS
  WAR_AND_CONFLICT
  POLITICS
  FINANCE
  LEGAL
  - ClassificationCategoryName(): 
  + values(): ClassificationCategoryName[]
  + valueOf(String): ClassificationCategoryName
}

class Controller {
  - dictionary : Dictionary
  - generator : Generator
  - sentenceAnalyzer : SentenceAnalyzer
  - toxicAnalyzer : ToxicAnalyzer
  + Controller(): 
  + processInput(String, int, boolean): String
}

class Dictionary {
  - singularNouns : Set<Word>
  - pluralNouns : Set<Word>
  - singularVerbs : Set<Word>
  - pluralVerbs : Set<Word>
  - adjectives : Set<Word>
  - temporaryWords : Set<Word>
  - random : Random
  + Dictionary(): 
  + add(String, String, String): void
  + takeFromTemporary(String, String): Word
  + clearTemporaryWords(): void
  + loadFromFile(String): void
  + takeWord(String, String): Word
  + saveToFile(String): void
}

class GUI {
  - inputField : JTextField
  - syntaxTreeCheckbox : JCheckBox
  - templateDropdown : JComboBox<String>
  - generateButton : JButton
  - controller : Controller
  + GUI(): 
  - createSectionPanel(String, Component): JPanel
  - createButtonPanel(): JPanel
  - createInputPanel(): JPanel
  - createOptionsPanel(): JPanel
  - showResultWindow(String, boolean): void
  - createMainPanel(): JPanel
  - showError(String, String): void
}

class GenerateButtonListener {
  - GenerateButtonListener(): 
  + actionPerformed(ActionEvent): void
}

class Generator {
  - outputPath : String
  - templates : List<String[]>
  + Generator(String): 
  + Generator(): 
  + generate(int, Dictionary): String
}

class MTextApiAdapter {
  - currentSentence: String
  - conf: float[] 
  + MTextApiAdapter(): 
  + getCategoryConfidence(String, ClassificationCategoryName): float
}

class Main {
  + Main(): 
  + main(String[]): void
}

class SentenceAnalyzer {
  - language : LanguageServiceClient
  + SentenceAnalyzer(): 
  - APIHandler(String): List<Token>
  + syntaxAnalyzer(String, boolean, Dictionary): String
  - buildTreeString(List<Token>, Map<Integer, List<Integer>>, int, String, boolean): String
}

interface ToxicAnalyzer << interface >> {
  + getCategoryConfidence(String, ClassificationCategoryName): float
}

class Word {
  - number: String
  - content: String
  - tag: String
  + Word(String, String, String): 
  + hashCode(): int
  + toString(): String
  + equals(Object): boolean
}

Controller "1" *--> "1" Dictionary : creates
Word "1" <--* "*" Dictionary : made_of

Controller "1" *--> "1" Generator : creates/uses
Generator  "1" ..>  "1" Dictionary : consults

SentenceAnalyzer "1" <--* "1" Controller : creates/uses
SentenceAnalyzer "1" ..> "1"  Dictionary : adds_to

Controller "1" *--> "1" ToxicAnalyzer : creates/uses
'quella sotto si può togliere se vogliamo rimanere astratti utile se vogliamo rendere palese il coupling tra controller e classe di implementazione
MTextApiAdapter "1" <--* "1"  Controller: instantiates 
MTextApiAdapter "1" ..|> ToxicAnalyzer : implements
ClassificationCategoryName "1" <-- "1" MTextApiAdapter : categories_from

GUI "1" *--> "1" Controller : sends_input
Main "1" ..> "1" GUI : starts
GenerateButtonListener "1" -[#820000]-+ "1" GUI : nested                      
@enduml
``` 
