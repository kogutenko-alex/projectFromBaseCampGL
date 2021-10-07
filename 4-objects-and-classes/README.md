# Dictionary

In this task you will process known words from the previous task and create your own English dictionary!

## Input
As an input you get a list of known words from the previous task. Please, note that completing the previous task is required to start working on this one.
You can assume that known words are located in the `words/known.txt` file (`words` is the folder)

## What Needs To Be Done
1. Read words from the file (see [Input](#input) section)
2. Make a request to the https://dictionaryapi.dev/ to get each word definition. It is a free API that doesn't require authentication.
   1. Request Description:
      1. Type: `GET`
      2. URL: `https://api.dictionaryapi.dev/api/v2/entries/en/<your_word>`, where `<your_word>` should be replaced with the word from your list (e.g. `https://api.dictionaryapi.dev/api/v2/entries/en/hello`)
3. If the word is not found, ignore it
4. If the word is found and definition can be retrieved, write the word to the CSV file

## CSV File Format
1. Headers:
   1. Word
   2. Phonetic
   3. Meanings
   4. Examples
2. Values (by headers):
   1. `Word` - processed word
   2. `Phonetic` - word's phonetic that is returned from the API call (e.g. `wəːd` for the word `word`)
   3. `Meanings` - all possible word meanings in the following format:
    ```
    <partOfSpeech> - <definition_1>;<definition_2>
    <partOfSpeech> - <definition_1>;<definition_2>
    ```
   4. `Examples` - all available examples in the following format:
    ```
    <example_1>
    <example_2>
    <example_3>
    ```
3. File Name: `dictionary.csv`
4. File Location: root folder of this task (e.g. `4-objects-and-classes`)

## Environment
- Java 11
- We allow to use third-party clients for HTTPS connection and JSON parsing
- Gradle or Maven