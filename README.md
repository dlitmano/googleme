# googleme CLI
## Download

You can download the project and import as Maven-project from https://github.com/dlitmano/googleme

or you can download the googleme.jar from repository and use it

## Prerequisites - Installation

Download and install java JDK 1.8 from
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

## Usage

```sh
java -jar googleme.jar some_search_string
```

Options category 'startup':

  --results [-r] (an integer; default: "3")
  
    Definition of the number of results. Possible amount of results are 1-6.
    
  --search [-s] (a string; default: "google")
  
    Definition of the search engine. Available search engines are google and
    bing.
    
## Examples

### More results:

```sh
java -jar googleme.jar some_search_string -r 5
```

### Search with bing.com:

```sh
java -jar googleme.jar some_search_string -s bing
```

### Search with bing.com and get 4 results:

```sh
java -jar googleme.jar some_search_string -r 4 -s bing
```
