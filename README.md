# idle

Parse source code (Objective-C) to UML(Class Diagram) file format of plantuml.


# version

## 0.0.3-beta

### Example

#### web front objective-c
![AFNetworking](https://raw.githubusercontent.com/AwayQu/idle/master/imgs/idle0.0.3-demo.gif)


#### snapshot java support

![Java](https://raw.githubusercontent.com/AwayQu/idle/master/imgs/idle0.0.3-snapshot.png)


### Usage

* download the [idlerest0.0.3.jar](https://github.com/AwayQu/idle/releases/download/0.0.3-beta/idlerest-0.0.3.jar)

* run `java -jar idlerest0.0.3.jar`

* visit http://localhost:8080 (support local project path & git url)


## 0.0.1-alpha
### Example

### AFNetworking Class Diagram
![AFNetworking](https://raw.githubusercontent.com/AwayQuEM/idle/master/imgs/AFNetworking.png)


### Usage

```shell

# parse code to generate plantuml file
java -jar idlecommand-1.0-SNAPSHOT-jar-with-dependencies.jar analyze -p path/to/project -o output.puml

# parse plantuml file to png
java -DPLANTUML_LIMIT_SIZE=30000 -jar plantuml.jar output.puml

```

# Link

[plantuml](http://plantuml.com/download)
